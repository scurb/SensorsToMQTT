package se.dolutions.SmartHome.SensorsToMQTT;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.dolutions.SmartHome.SensorsToMQTT.Events.*;
import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageInternal;
import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessagePresentation;
import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageRequestVariable;
import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageSetVariable;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.*;

public class GatewayIPClient implements Runnable {

	private List<ISensorEventListener> _listeners = new ArrayList<ISensorEventListener>();
	private List<IConnectionStateListener> _connectionListeners = new ArrayList<IConnectionStateListener>();
	
	Socket clientSocket;
	
	String hostname;
	Integer port;
	
	ILogger logger;
	
	public void setLogger(ILogger logger){
		this.logger = logger;
	}
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	private void log(String logText, LogLevel logLevel){
		logger.log("IP: " + logText, logLevel);		
	}
	private void log(String logText){
		log(logText, LogLevel.Info);
		
	}
	
	@Override
	public void run() {
		
		try{
			clientSocket = new Socket(hostname, port);
			
			log("Connected");
			
			BufferedReader inStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
			
			String bufferString;

			while((bufferString = inStream.readLine())!=null){

				
				log("IP Socket IN-data: " + bufferString);				
				
				// TODO Fix some more validation of incoming protocol. Handle garbage that can come out from ser2net connection...
				if(bufferString.length()>0){
					String[] parts = bufferString.split(";");
					if(parts.length > 2) {					
		
	
						switch(SensorMessageType.getSensorMessageType(Integer.parseInt(parts[2]))){
	 					case MT_Presentation: 
	 						SensorMessagePresentation sensorPresentation = new SensorMessagePresentation();
	 						sensorPresentation.setRadioID(Integer.parseInt(parts[0]));
	 						sensorPresentation.setChildID(Integer.parseInt(parts[1]));
	 						sensorPresentation.setSensorType(SensorType.getSensorType(Integer.parseInt(parts[4])));
	 						if(Integer.parseInt(parts[3])==1) {
	 							sensorPresentation.setAckRequested(true);
	 						} else {
	 							sensorPresentation.setAckRequested(false);
	 						}
	 						if(parts.length>5)
	 							sensorPresentation.setSensorLibraryVersion(parts[5]);
	 						
	 						fireSensorPresentationEvent(sensorPresentation);
	 						break; 
	 					case MT_SetVariable: 
	 						SensorMessageSetVariable _sensorSetVariableMessage;

	 						_sensorSetVariableMessage = new SensorMessageSetVariable();
	 						_sensorSetVariableMessage.setRadioID(Integer.parseInt(parts[0]));
	 						_sensorSetVariableMessage.setChildID(Integer.parseInt(parts[1]));

	 						if(Integer.parseInt(parts[3])==1) {
	 							_sensorSetVariableMessage.setAckRequested(true);
	 						} else {
	 							_sensorSetVariableMessage.setAckRequested(false);
	 						}
	 							 						
	 						SensorValue sValue = new SensorValue();
							sValue.setSensorValueType(SensorValueType.getSensorValueType(Integer.parseInt(parts[4])));
							if(parts.length>5)
								sValue.setValue(parts[5]);
							
							_sensorSetVariableMessage.setSensorValue(sValue);
							
							if(_sensorSetVariableMessage != null)
								fireSensorSetVariableEvent(_sensorSetVariableMessage);				

							
							break;
						case MT_RequestVariable: 
							SensorMessageRequestVariable _sensorRequestVariableMessage;
							_sensorRequestVariableMessage = new SensorMessageRequestVariable();
							_sensorRequestVariableMessage.setRadioID(Integer.parseInt(parts[0]));
							_sensorRequestVariableMessage.setChildID(Integer.parseInt(parts[1]));

	 						if(Integer.parseInt(parts[3])==1) {
	 							_sensorRequestVariableMessage.setAckRequested(true);
	 						} else {
	 							_sensorRequestVariableMessage.setAckRequested(false);
	 						}							
							
							_sensorRequestVariableMessage.setRequestedValueType(SensorValueType.getSensorValueType(Integer.parseInt(parts[4])));
							
							if(_sensorRequestVariableMessage!=null)
								fireSensorRequestVariableEvent(_sensorRequestVariableMessage);
							
							break;
						case MT_VariableAcknowledge: 
							
							break;
						case MT_Internal:
							SensorMessageInternal _internalMessage;
							_internalMessage = new SensorMessageInternal();
							
							_internalMessage.setRadioID(Integer.parseInt(parts[0]));
							_internalMessage.setChildID(Integer.parseInt(parts[1]));
							
	 						if(Integer.parseInt(parts[3])==1) {
	 							_internalMessage.setAckRequested(true);
	 						} else {
	 							_internalMessage.setAckRequested(false);
	 						}							
							
							_internalMessage.setMessageInternalType(SensorMessageInternalType.getSensorMessageInternalType(Integer.parseInt(parts[4])));
							if(parts.length>=6)
								_internalMessage.setData(parts[5]);
							
							fireSensorInternalEvent(_internalMessage);
									
							break;
						default:
							
							
							break;
						}
												
					}	
				}
			}
			
			inStream.close();
			clientSocket.close();
			
			//TODO Handle reconnection timer... this is not good.
			log("Closed connection");
			fireConnectionClosedEvent();
		} catch (ConnectException connExc){
			log("Could not connect to the IP address/port given. IP: " + this.getHostname() + ", Port: " + this.getPort().toString() + ".",LogLevel.Error);
			fireConnectionConnectExceptionEvent(connExc);
		} catch (NumberFormatException nExc){
			log("Exception in connection. IP address/port given. IP: " + this.getHostname() + ", Port: " + this.getPort().toString() + ".", LogLevel.Error);
			log("Exception info: " + nExc.toString(), LogLevel.Error);
			this.fireParsingExceptionEvent(nExc);		
		} catch (IOException exc){
			log("Exception in connection. IP address/port given. IP: " + this.getHostname() + ", Port: " + this.getPort().toString() + ".", LogLevel.Error);
			log("Exception info: " + exc.toString(), LogLevel.Error);
			this.fireConnectionExceptionEvent(exc);
		}
		
	}

	public void sendDataToGW(String dataToSend){
		log("Will send the following data: " + dataToSend);
		
	    try{  
	    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	    	bufferedWriter.write(dataToSend);
	    	bufferedWriter.flush();		
	    } catch(IOException ioxc) {
	    	log("Error while sending reponse for a ID Request. Error" + ioxc.getMessage(), LogLevel.Error);
	    }
		
	}
	
	public void sendRequestIDResponse(Integer radioID, Integer childID, Integer assignedRadioID){
		//1.4 format
		sendDataToGW(radioID.toString() + ";" + childID.toString() + ";3;0;4;" + assignedRadioID.toString() + ";" + "\n");
	}
	
	public void sendRequestMetricResponse(Integer radioID, Integer childID, String metricCode){
		sendDataToGW(radioID+";"+childID+";4;13;"+metricCode + "\n");
	}
	
	public void sendRequestRequestVariable(Integer radioID, Integer childID, Integer valueTypeID, String value){
		sendDataToGW(radioID+";"+childID+";3;"+valueTypeID+";" + value + "\n");
	}
	
	public void sendPresentationRequest(Integer radioID, Integer childID){
		//TODO send the presentation request over the wire!
	}
	
	
    public synchronized void addSensorEventListener( ISensorEventListener l ) {
        _listeners.add( l );
    }
    
    public synchronized void removeSensorEventListener( ISensorEventListener l ) {
        _listeners.remove( l );
    }	
    
    public synchronized void addConnectionListener( IConnectionStateListener l ){
    	_connectionListeners.add(l);
    }
    public synchronized void removeConnectionListener( IConnectionStateListener l ){
    	_connectionListeners.remove(l);
    }
    
    private synchronized void fireConnectionConnectExceptionEvent(ConnectException connExc){
    	Iterator<IConnectionStateListener> listeners = _connectionListeners.iterator();
    	while(listeners.hasNext() ){
    		((IConnectionStateListener) listeners.next() ).handleConnectionConnectExceptionEvent(connExc);
    	}
    }
    
    private synchronized void fireParsingExceptionEvent(Exception connExc){
    	Iterator<IConnectionStateListener> listeners = _connectionListeners.iterator();
    	while(listeners.hasNext() ){
    		((IConnectionStateListener) listeners.next() ).handleParsingExceptionEvent(connExc);
    	}
    }

    private synchronized void fireConnectionExceptionEvent(Exception connExc){
    	Iterator<IConnectionStateListener> listeners = _connectionListeners.iterator();
    	while(listeners.hasNext() ){
    		((IConnectionStateListener) listeners.next() ).handleConnectionExceptionEvent(connExc);
    	}
    }
    
    private synchronized void fireConnectionClosedEvent(){
    	Iterator<IConnectionStateListener> listeners = _connectionListeners.iterator();
    	while(listeners.hasNext() ){
    		((IConnectionStateListener) listeners.next() ).handleClosedConnectionEvent();
    	}
    }
    
    private synchronized void fireSensorRequestVariableEvent(SensorMessageRequestVariable sMessage){
        SensorEventRequestVariable sEvent = new SensorEventRequestVariable( this, sMessage);
        Iterator<ISensorEventListener> listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ISensorEventListener) listeners.next() ).handleSensorRequestVariableEvent(sEvent);
        }    	
    }
    
	private synchronized void fireSensorSetVariableEvent(SensorMessageSetVariable sMessage) {
        SensorEventSetVariable sEvent = new SensorEventSetVariable( this, sMessage);
        Iterator<ISensorEventListener> listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ISensorEventListener) listeners.next() ).handleSensorSetVariableEvent(sEvent);
        }
    }	
	private synchronized void fireSensorInternalEvent(SensorMessageInternal sMessage) {
        SensorEventInternal sEvent = new SensorEventInternal( this, sMessage);
        Iterator<ISensorEventListener> listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ISensorEventListener) listeners.next() ).handleSensorInternalEvent(sEvent);
        }
    }	
	
	private synchronized void fireSensorPresentationEvent(SensorMessagePresentation sMessage) {
        SensorEventPresentation sEvent = new SensorEventPresentation( this, sMessage);
        Iterator<ISensorEventListener> listeners = _listeners.iterator();
        while( listeners.hasNext() ) {
            ( (ISensorEventListener) listeners.next() ).handleSensorPresentationEvent(sEvent);
        }
    }	
	
}
