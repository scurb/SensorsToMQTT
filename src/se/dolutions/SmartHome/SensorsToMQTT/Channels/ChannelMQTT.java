package se.dolutions.SmartHome.SensorsToMQTT.Channels;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import se.dolutions.SmartHome.SensorsToMQTT.ILogger;
import se.dolutions.SmartHome.SensorsToMQTT.LogLevel;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationChannel;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.ISensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Node;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;

public class ChannelMQTT implements IChannelPublishEventListener, MqttCallback {

	MqttClient client = null;
	String rootTopic;
	ILogger logger;
	ConfigurationChannel channelConfigurator;
	
	private void log(String logText, LogLevel logLevel)
	{
		logger.log("ChannelMQTT: " + logText, logLevel);
	}
	
	private void log(String logText){
		log(logText, LogLevel.Info);
	}
	
	@Override
	public void setLogger(ILogger logger){
		this.logger = logger;
	}
	
	@Override
	public void channelConfigure(ConfigurationChannel channelConfigurator){
		this.channelConfigurator = channelConfigurator;
		rootTopic  = channelConfigurator.getRootTopic();
		
		try{
			//connect(channelConfigurator.getChannelUrl(), channelConfigurator.getClientID());
			connect();
			sendHelloString();
		} catch(MqttException e){
			//TODO Convert to standardized channel exception
			log(e.toString(), LogLevel.Error );
		}
	}
	
	@Override
	public void publishNodePresentation(ArrayList<Node> nodeList) {
		log("Got the request to publish the node list!", LogLevel.Debug);
		String nodeTopic;
		String sensorTopic;
		
		for(Node node: nodeList){
			nodeTopic = rootTopic + "nodes/" + node.getRadioID();
			sendStringToTopic(nodeTopic, node.getJSONPresentation(), 1);
			
			for(ISensor sensor : node.getSensorList()){
				sensorTopic = nodeTopic + "/sensors/" + sensor.getChildID();
				sendStringToTopic(sensorTopic, sensor.getJSONPresentation(), 1 );
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

	@Override
	public void publishNewNodeToChannel(Node node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void publishNewSensorToChannel(Node ownerNode, Sensor newSensor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void publishNewSensorValue(Sensor sensor, SensorValue newSensorValue) {
		log("Got the request to publish a new SensorValue", LogLevel.Debug);
		this.sendStringToTopic(rootTopic + "nodes/" + sensor.getRadioID() + "/sensors/" + sensor.getChildID() + "/" + newSensorValue.getSensorValueType().name() + "/value", newSensorValue.getValue(), 1);
	}

	@Override
	public void publishNewNodeInformation(Node node) {
		log("Got the request to publish new Node Information", LogLevel.Debug);
		String nodeTopic;
		nodeTopic = rootTopic + "nodes/" + node.getRadioID();
		sendStringToTopic(nodeTopic, node.getJSONPresentation(), 1);		
	}
	
	@Override
	public void publishNewSensorInformation(Sensor sensor) {
		log("Got the request to publish new Sensor Information", LogLevel.Debug);
		String sensorTopic;
		sensorTopic = rootTopic + "nodes/" + sensor.getRadioID() + "/sensors/" + sensor.getChildID();
		sendStringToTopic(sensorTopic, sensor.getJSONPresentation(), 1 );
	}
	
	

    /**
     * This function connects to the broker via the parameters given in the procedure call.
     * 
     * @param brokerAddress
     * @param clientIdentifier
     * @throws MqttException
     */
    
    //public void connect(String brokerAddress, String clientIdentifier) throws MqttException {
	public void connect() throws MqttException {

        try {
            MemoryPersistence persistence = new MemoryPersistence();
            client = new MqttClient(channelConfigurator.getChannelUrl(), channelConfigurator.getClientID(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            
            if(channelConfigurator.getUserName()!=""){
            	connOpts.setUserName(channelConfigurator.getUserName());
            	connOpts.setPassword(channelConfigurator.getPassword().toCharArray());
            }
            
            connOpts.setCleanSession(true);
            log("Connecting to broker: " + channelConfigurator.getChannelUrl()); 
            client.setCallback(this);
            client.connect();
        } catch(MqttException me) {
            log("Exception reason "+me.getReasonCode(), LogLevel.Error);
            log("Exception msg "+me.getMessage(), LogLevel.Error);
            log("Exception loc "+me.getLocalizedMessage(), LogLevel.Error);
            log("Exception cause "+me.getCause(), LogLevel.Error);
            log("Exception excep "+me, LogLevel.Error);
            me.printStackTrace();
        }
    }
    
    /**
     * Tells the MQTT client which topic to subscribe to.
     * 
     * @param topicToSubsribeTo
     * @throws MqttException
     */
    public void subscribeToString(String topicToSubsribeTo) throws MqttException {
        client.subscribe(topicToSubsribeTo);	
    }
    
    /**
     * Function that sends data to the MQTT bus. 
     * 
     * @param topicString
     * @param payloadString
     * @param qosValue
     * @throws MqttException
     */
    public void sendStringToTopic(String topicString, String payloadString, Integer qosValue) {
		if ((client != null) && client.isConnected()) {

	    	MqttTopic topic = client.getTopic(topicString);
	        // Create message and set quality of service to deliver the message once
	        MqttMessage message = new MqttMessage();
	        message.setPayload(payloadString.getBytes());
	        message.setRetained(true);
	        message.setQos(qosValue);
	        try {
	                // Give the message to the client for publishing. For QoS 2, this
	                // will involve multiple network calls, which will happen
	                // asynchronously after this method has returned.
	                topic.publish(message);
	                
	                log("Sending to, Topic: " + topicString + ", payload: " + payloadString + ", with QoS: " + qosValue );
	        } catch (MqttException ex) {
	                // Client has not accepted the message due to a failure
	                // Depending on the exception's reason code, we could always retry
	                log("Exception: Failed to send message:" + ex.toString(), LogLevel.Error);
	                log("Exception: Message, Topic: " + topicString + ", payload: " + payloadString + ", with QoS: " + qosValue, LogLevel.Error );
	        }
		}
    }
    
	public void sendHelloString() throws MqttException {
			sendStringToTopic("home/sensors/init", "Starting SensorToMQTT application.", 1);
	}
	
	@Override
	public void connectionLost(Throwable arg0) {
		log("Connection lost", LogLevel.Error);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		 //Log("Delivery complete");
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		log("SensorMQTTClient: Topic: " + arg0 + ". Value: " + arg1.toString());
/*		Log("message Arrived");
		Log("arg0: " + arg0);
		Log("message: " + arg1.toString());
		Log("=======");*/
		
	}

	

}
