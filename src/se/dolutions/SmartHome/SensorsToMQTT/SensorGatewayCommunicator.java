package se.dolutions.SmartHome.SensorsToMQTT;

import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.dolutions.SmartHome.SensorsToMQTT.Channels.IChannelPublishEventListener;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationSensorNetwork;
import se.dolutions.SmartHome.SensorsToMQTT.Events.ISensorEventListener;
import se.dolutions.SmartHome.SensorsToMQTT.Events.SensorEventInternal;
import se.dolutions.SmartHome.SensorsToMQTT.Events.SensorEventPresentation;
import se.dolutions.SmartHome.SensorsToMQTT.Events.SensorEventRequestVariable;
import se.dolutions.SmartHome.SensorsToMQTT.Events.SensorEventSetVariable;
import se.dolutions.SmartHome.SensorsToMQTT.Persistance.INodeStorage;
import se.dolutions.SmartHome.SensorsToMQTT.Persistance.MariaDBStorage;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Node;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorMessageInternalType;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;

public class SensorGatewayCommunicator extends Thread implements ISensorEventListener, IConnectionStateListener, ILogger {

	private List<IChannelPublishEventListener> _pubListeners = new ArrayList<IChannelPublishEventListener>();


	GatewayIPClient ipClient;
	
	SensorPool sensorPool;
	INodeStorage storage;
	
	private Integer sensorGwID;
	private String sensorGwComment;
	
	private Boolean inclusionEnabled;
	DateFormat df;

	private static Logger logger;

	
	public void log(String logText, LogLevel logLevel)
	{
		switch(logLevel){
		case Info: logger.info(logText); break;
		case Error: logger.error(logText); break;
		case Warn: logger.warn(logText); break;
		case Trace: logger.trace(logText); break;
		case Debug: logger.debug(logText); break;
		case Fatal: logger.fatal(logText); break;
		default: logger.info(logText); break;
		}
	}
	
	private void log(String logText){
		log(logText, LogLevel.Info);
	}
	
	public SensorGatewayCommunicator(ConfigurationSensorNetwork cfg, SensorPool _sensorPool){//String arduinoHost, Integer arduinoPort, SensorPool _sensorPool){
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 
		sensorGwID = cfg.getId();
		sensorGwComment = cfg.getComment();
				
		logger = LogManager.getFormatterLogger("SensorGatewayCommunicator" + "(" + sensorGwID + ")");
		
		MariaDBStorage mDB;

		if((cfg.getConfigDB().getDbType().compareToIgnoreCase("mariadb") == 0) || (cfg.getConfigDB().getDbType().compareToIgnoreCase("mysql") == 0)){
			mDB = new MariaDBStorage(cfg.getConfigDB().getDbHost(), cfg.getConfigDB().getDbDatabase(), cfg.getConfigDB().getDbLogin(), cfg.getConfigDB().getDbPassword(), cfg.getConfigDB().getDbPort());
			storage = mDB;
		} else {
			System.err.println("No other DBMS are supported as of today!");
		}

		ipClient = new GatewayIPClient();		
		ipClient.hostname = cfg.getConfigSensorGwIp().getIp_address();
		ipClient.port = cfg.getConfigSensorGwIp().getIp_port();
		ipClient.addSensorEventListener(this);	
		ipClient.addConnectionListener(this);
		ipClient.logger = this;
		this.sensorPool = _sensorPool;
		
		this.sensorPool.nodeList = storage.getNodeList();		
		inclusionEnabled = false;
		
		log("Starting up sensor gateway with ID " + sensorGwID.toString() + ". Description: " + sensorGwComment);	
	}
	
	@Override
	public void run() {
		
		// First send out what we have...
		fireSensorNetworkPresentationEvent();

		try {
			ipClient.run();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}	

	public void startCommunicate(){

		try {
			ipClient.run();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void startInclusionMode(){
		inclusionEnabled = true;
		log("Starting Inclusion mode!");
	}
	public void stopInclusionMode(){
		inclusionEnabled = false;
		log("Stopping Inclusion mode!");
	}
	
	public INodeStorage getStorageClass(){
		return storage;
	}


    public synchronized void addChannelPublisher( IChannelPublishEventListener l ) {
        _pubListeners.add( l );
    }
    
    public synchronized void removeChannelPublisher( IChannelPublishEventListener l ) {
        _pubListeners.remove( l );
    }	
    
	private synchronized void fireSensorChangedEvent(Sensor sensor, SensorValue sensorValue) {
//        SensorEventChanged sChangedEvent = new SensorEventChanged( this, sensor, SensorMessageType.MT_SetVariable);
        Iterator<IChannelPublishEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelPublishEventListener) listeners.next() ).publishNewSensorValue(sensor, sensorValue);
        }        
    }	

	private synchronized void fireSensorNetworkPresentationEvent(){
        Iterator<IChannelPublishEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelPublishEventListener) listeners.next() ).publishNodePresentation(sensorPool.nodeList);
        }        		
	}

	private synchronized void fireSensorNodePresentationEvent(Node node){
        Iterator<IChannelPublishEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelPublishEventListener) listeners.next() ).publishNewNodeInformation(node);
        }        		
	}

	private synchronized void fireSensorSensorPresentationEvent(Sensor sensor){
        Iterator<IChannelPublishEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelPublishEventListener) listeners.next() ).publishNewSensorInformation(sensor);
        }        		
	}
/*
	public void publishNewNodeToChannel(Node node);
	public void publishNewSensorToChannel(Node ownerNode, Sensor newSensor);

	*/

	@Override
	public void handleConnectionConnectExceptionEvent(ConnectException exc) {
		log("SensorGateway: Got ConnectionException. Trying to restart IP connection!");
		this.startCommunicate();
	}

	@Override
	public void handleConnectionExceptionEvent(Exception exc) {
		System.err.println("SensorGateway: Got IOException from IP. Will not try to connect again!");
		System.err.println(exc.toString());
	}

	@Override
	public void handleClosedConnectionEvent() {
		log("SensorGateway: Got Connection Closed. Trying to restart IP connection!");
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.startCommunicate();
	}

	@Override
	public void handleParsingExceptionEvent(Exception exc) {
		log("SensorGateway: Got Parse exception. Trying to restart IP connection!");
		this.startCommunicate();		
	}

	@Override
	public void handleSensorRequestVariableEvent(SensorEventRequestVariable event){
		Sensor storedSensor = sensorPool.getSensorFromPool(event.sensorRequestVariableMessage().getRadioID(), event.sensorRequestVariableMessage().getChildID());

		if(storedSensor==null){
			log("RequestVariableEvent failed, this sensor was not found.", LogLevel.Error);
			return;
		} else {
			

			//OK - got something - lets reply!
			ipClient.sendRequestRequestVariable(storedSensor.getRadioID(), storedSensor.getChildID(), event.sensorRequestVariableMessage().getRequestedValueType().ordinal(), storedSensor.getSensorValue(event.sensorRequestVariableMessage().getRequestedValueType()));
		
/* OLD way to do this... Delete this code after verification!			
			//Dig up the right value here!			
			SensorValue lastValueStored = storage.getLastSensorValue(storedSensor, event.sensorRequestVariableMessage().getRequestedValueType());

			String dataToReplyWith;
			
			//If reply from storage is null, then we probably haven't had a sensor update. Reply with empty. This maybe should be "0" instead.
			if(lastValueStored.getValue()!=null)
				dataToReplyWith = lastValueStored.getValue();
			else
				dataToReplyWith = "1";
			//OK - got something - lets reply!
			ipClient.sendRequestRequestVariable(storedSensor.getRadioID(), storedSensor.getChildID(), event.sensorRequestVariableMessage().getRequestedValueType().ordinal(), dataToReplyWith);
*/			
		}
		
	}
	
	@Override
	public void handleSensorSetVariableEvent(SensorEventSetVariable event) {
		Sensor storedSensor = sensorPool.getSensorFromPool(event.sensorSetVariableMessage().getRadioID(), event.sensorSetVariableMessage().getChildID());
		
		log("SensorGateway: For sensor (" + 
				event.sensorSetVariableMessage().getRadioID().toString() + 
				"-" + 
				event.sensorSetVariableMessage().getChildID().toString() + 
				") We got type: "+
				event.sensorSetVariableMessage().getSensorValue().getSensorValueType().toString() + 
				" with value " + 
				event.sensorSetVariableMessage().getSensorValue().getValue().toString());		
		
		if(storedSensor==null){
			System.err.println("SensorGateway: SetVariableEvent failed, this sensor was not found.");
		} else {				
			storedSensor.sensorValueUpdate(event.sensorSetVariableMessage().getSensorValue());
													
			//Also fire events to our channels!
			fireSensorChangedEvent(storedSensor, event.sensorSetVariableMessage().getSensorValue());
		}
	}
	
	
	@Override
	public void handleSensorInternalEvent(SensorEventInternal event) {
		
		Boolean globalNodeData = false;
		Sensor storedSensor = null;
		
		Node storedNode = sensorPool.getNodeFromPool(event.sensorMessageInternal().getRadioID());
		
		if((storedNode==null) && (event.sensorMessageInternal().getMessageInternalType()!=SensorMessageInternalType.REQUEST_ID)){
			System.err.println("This node was not previously added to the network. Will not accept presentation from node with radioID " + event.sensorMessageInternal().getRadioID() + " and childID " + event.sensorMessageInternal().getChildID() + ".");
			return;
		}
	
		
		if(event.sensorMessageInternal().getChildID()==255)
			globalNodeData=true;
		else
			storedSensor = sensorPool.getSensorFromPool(event.sensorMessageInternal().getRadioID(), event.sensorMessageInternal().getChildID());
		
		switch(event.sensorMessageInternal().getMessageInternalType()){
		case BATTERY_DATE: 
			
			break;
		case LAST_TRIP: 
			break;
		case TIME: 
			break;
		case VERSION: 
			break;
		case REQUEST_ID: 
			log("Global (255;255) request for a new radio ID");
			if(!inclusionEnabled){
				log("Got a request for a new ID but Inclusion mode was not enabled!", LogLevel.Warn);
				break;
			}
			Integer nodeID= storage.assignNextAvailableRadioID();
			
			//Request made, DB updated - lets create node!
			Node newNode = new Node(storage);
			newNode.setRadioID(nodeID);
			sensorPool.addNodeToPool(newNode);

			//Reply back to the network!
			ipClient.sendRequestIDResponse(event.sensorMessageInternal().getRadioID(), event.sensorMessageInternal().getChildID(), nodeID);
			break;
		case INCLUSION_MODE: 
			if(event.sensorMessageInternal().getData().equalsIgnoreCase("1"))
				startInclusionMode();
			else
				stopInclusionMode();
			break;
		case RELAY_NODE: 
				if(event.sensorMessageInternal().getData().equalsIgnoreCase("1"))
					storedNode.setRelayNode(true);
				else
					storedNode.setRelayNode(false);
				fireSensorNodePresentationEvent(storedNode);				
			break;
		case LAST_UPDATE:
			break;
		case PING: 
			break;
		case PING_ACK: 
			break;
		case LOG_MESSAGE:
			if((globalNodeData) && (storedNode!=null))
				log("Node " + storedNode.getRadioID() + " log: " + event.sensorMessageInternal().getData());
			else if(storedSensor!=null)
				log("Sensor " + storedSensor.getSensorID() + " log: " + event.sensorMessageInternal().getData());
			break;
		case CHILDREN: 
			break;
		case UNIT:
			ipClient.sendRequestMetricResponse(event.sensorMessageInternal().getRadioID(), event.sensorMessageInternal().getChildID(), "M");
			break;
		case SKETCH_NAME: 
			storedNode.setSketchName(event.sensorMessageInternal().getData());
			storedNode.saveNode();
			fireSensorNodePresentationEvent(storedNode);
			break;
		case SKETCH_VERSION: 
			storedNode.setSketchVersion(event.sensorMessageInternal().getData());
			storedNode.saveNode();
			fireSensorNodePresentationEvent(storedNode);
			break;
		default:
			System.err.println("No message type was found!");
			break;
		}
	}

	@Override
	public void handleSensorPresentationEvent(SensorEventPresentation event) {
		Node storedNode = sensorPool.getNodeFromPool(event.sensorMessagePresentation().getRadioID());

		if(storedNode==null){
			System.err.println("This node was not previously added to the network. Will not accept presentation from  node with radioID " + event.sensorMessagePresentation().getRadioID() + " and childID " + event.sensorMessagePresentation().getChildID() + ". SensorType " + event.sensorMessagePresentation().getSensorType().toString());
			return;
		}
		
		Sensor storedSensor = storedNode.getSensor(event.sensorMessagePresentation().getChildID());
		
		if(storedSensor==null){
			storedSensor = new Sensor(storage);
		}
		storedSensor.setRadioID(event.sensorMessagePresentation().getRadioID());
		storedSensor.setChildID(event.sensorMessagePresentation().getChildID());
		storedSensor.setSensorType(event.sensorMessagePresentation().getSensorType());
		storedSensor.setSensorSketchVersion(event.sensorMessagePresentation().getSensorLibraryVersion());
		storedNode.addSensorToNode(storedSensor);
		
		this.fireSensorSensorPresentationEvent(storedSensor);
		
		log("Got presentation for sensor (" + 
				storedSensor.getRadioID().toString() + "-" + 
				storedSensor.getChildID().toString() + "). Type is: " + 
				storedSensor.getSensorType().toString() + " with library version " + 
				storedSensor.getSensorSketchVersion().toString()				
				);	
	}
}
