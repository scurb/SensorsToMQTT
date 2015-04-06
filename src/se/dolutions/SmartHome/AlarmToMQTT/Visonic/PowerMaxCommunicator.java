package se.dolutions.SmartHome.AlarmToMQTT.Visonic;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.dolutions.SmartHome.SensorsToMQTT.*;
import se.dolutions.SmartHome.SensorsToMQTT.IConnectionStateListener;
import se.dolutions.SmartHome.SensorsToMQTT.Channels.IChannelAlarmEventListener;

public class PowerMaxCommunicator implements Runnable {

	protected static final int ssDisarm = (0x00);
	protected static final int ssExitDelay = (0x01);
	protected static final int ssExitDelay2 = (0x02);
	protected static final int ssEntryDelay = (0x03);
	protected static final int ssArmedHome = (0x04);
	protected static final int ssArmedAway = (0x05);
	protected static final int ssUserText = (0x06);
	protected static final int ssDownload = (0x07);
	protected static final int ssProgramming = (0x08);
	protected static final int ssInstaller = (0x09);
	protected static final int ssHomeBypass = (0x0A);
	protected static final int ssAwayBypass = (0x0B);
	protected static final int ssReady = (0x0C);
	protected static final int ssNotReady = (0x0D);
    
	protected static final int pmMsgTypeGeneralEvent = 0xA5;
	protected static final int pmMsgTypeRequestStatusUpdate = 0xA2;
	protected static final int pmMsgTypeAcknowledge = 0x02;
	protected static final int pmMsgTypeAccessDenied = 0x03;
	protected static final int pmMsgTypeSetDateTime = 0x46;
	protected static final int pmMsgTypeGetEventLog = 0xA0;
	protected static final int pmMsgTypeArmDisarm = 0xA1;

	protected static final int geLogEvent = 0x01;
	protected static final int geKeepAlive = 0x02;
	protected static final int geTamperEvent = 0x03;
	protected static final int geEvent = 0x04;

	protected static final int sseReady = 0;
	protected static final int sseAlertMemory = 1;
	protected static final int sseTrouble = 2;
	protected static final int sseBypassOn = 3;
	protected static final int sseLast10SecDelay = 4;
	protected static final int sseZoneEvent = 5;
	protected static final int sseArmDisarmEvent = 6;
	protected static final int sseAlarmEvent = 7;

	protected static final int bitZoneOffset1 = 0;
	protected static final int bitZoneOffset2 = 1;
	protected static final int bitZoneOffset3 = 2;
	protected static final int bitZoneOffset4 = 3;
	protected static final int bitZoneOffset5 = 4;
	protected static final int bitZoneOffset6 = 5;
	protected static final int bitZoneOffset7 = 6;
	protected static final int bitZoneOffset8 = 7;

	protected static final int keepAliveStatusTypeState = 0;
	protected static final int keepAliveStatusTypeBattery = 1;
	
	
	protected static final int PMSystem = (0x00);
	protected static final int PMZone1 = (0x01);
	protected static final int PMZone2 = (0x02);
	protected static final int PMZone3 = (0x03);
	protected static final int PMZone4 = (0x04);
	protected static final int PMZone5 = (0x05);
	protected static final int PMZone6 = (0x06);
	protected static final int PMZone7 = (0x07);
	protected static final int PMZone8 = (0x08);
	protected static final int PMZone9 = (0x09);
	protected static final int PMZone10 = (0x0A);
	protected static final int PMZone11 = (0x0B);
	protected static final int PMZone12 = (0x0C);
	protected static final int PMZone13 = (0x0D);
	protected static final int PMZone14 = (0x0E);
	protected static final int PMZone15 = (0x0F);
	protected static final int PMZone16 = (0x10);
	protected static final int PMZone17 = (0x11);
	protected static final int PMZone18 = (0x12);
	protected static final int PMZone19 = (0x13);
	protected static final int PMZone20 = (0x14);
	protected static final int PMZone21 = (0x15);
	protected static final int PMZone22 = (0x16);
	protected static final int PMZone23 = (0x17);
	protected static final int PMZone24 = (0x18);
	protected static final int PMZone25 = (0x19);
	protected static final int PMZone26 = (0x1A);
	protected static final int PMZone27 = (0x1B);
	protected static final int PMZone28 = (0x1C);
	protected static final int PMZone29 = (0x1D);
	protected static final int PMZone30 = (0x1E);
	protected static final int PMPGM = (0x4C);
	protected static final int PMGSM = (0x4D);
	protected static final int PMPowerLink = (0x4E);
	
	protected static final int zeNone = (0x00);
	protected static final int zeTamperAlarm = (0x01);
	protected static final int zeTamperRestore = (0x02);
	protected static final int zeOpen = (0x03);
	protected static final int zeClosed = (0x04);
	protected static final int zeViolatedMotion = (0x05);
	protected static final int zePanicAlarm = (0x06);
	protected static final int zeRFJamming = (0x07);
	protected static final int zeTamperOpen = (0x08);
	protected static final int zeCommFailure = (0x09);
	protected static final int zeLineFailure = (0x0A);
	protected static final int zeFuse = (0x0B);
	protected static final int zeNotActive = (0x0C);
	protected static final int zeLowBattery = (0x0D);
	protected static final int zeACFailure = (0x0E);
	protected static final int zeFireAlarm = (0x0F);
	protected static final int zeEmergency = (0x10);
	protected static final int zeSirenTamper = (0x11);
	protected static final int zeSirenTamperRestore = (0x12);
	protected static final int zeSirenLowBatt = (0x13);
	protected static final int zeSireACFail = (0x14);
	
	private List<IChannelAlarmEventListener> _pubListeners = new ArrayList<IChannelAlarmEventListener>();
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
		logger.log("VisonicIP: " + logText, logLevel);		
	}
	private void log(String logText){
		log(logText, LogLevel.Info);
		
	}
	
	@Override
	public void run() {
		
		try{
			clientSocket = new Socket(hostname, port);
			
			log("Connected");
			
			BufferedReader inStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream(),"ISO-8859-1"));
			
			String bufferString;

			while((bufferString = inStream.readLine())!=null){

				
				
				// TODO CRC routine!
				if(bufferString.length()>0){
					log("IP Socket IN-data: " + bufferString, LogLevel.Debug);				

/*					for(int x = 0; x<bufferString.length()-1;x++){
						int a = bufferString.charAt(x);
						log("char at" + x + ": " + (bufferString.charAt(x)) + " int value: " + a + ".", LogLevel.Debug);
					}
	*/				
                    if ((int)(bufferString.charAt(0)) == pmMsgTypeGeneralEvent){
                    	log("General Event. Will try to parse :" + bufferString.substring(1, bufferString.length()), LogLevel.Debug);
                    	parsePowerMaxGeneralEvent(bufferString.substring(1, bufferString.length()));
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

	public void sendDataToVisonic(String dataToSend){
		log("Will send the following data: " + dataToSend);
		
	    try{  
	    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	    	bufferedWriter.write(dataToSend);
	    	bufferedWriter.flush();		
	    } catch(IOException ioxc) {
	    	log("Error while sending reponse for a ID Request. Error" + ioxc.getMessage(), LogLevel.Error);
	    }
	}
	
	
	
	public void parsePowerMaxGeneralEvent(String pmMessage){
		if(pmMessage.length()<=1)
				return;
		
		
		int generalEventType = pmMessage.charAt(1);
		
		switch(generalEventType){
		case geEvent:
			log("Got an incoming General Event of Type EVENT");
			
			PowerMaxGeneralEventEvent pmEvent = new PowerMaxGeneralEventEvent();
			pmEvent.setSystemStateValue((int)pmMessage.charAt(3));
			pmEvent.setSystemStatus((int)pmMessage.charAt(2));
			
			log("System state: " + pmEvent.getSystemStateText());
			log("System status: " + pmEvent.getSystemStatusText());
			
			PowerMaxSimulatedEventSystemInformation pmSystemInfo = new PowerMaxSimulatedEventSystemInformation();
			pmSystemInfo.setSystemState(pmEvent.getSystemStateText());
			pmSystemInfo.setSystemStatus(pmEvent.getSystemStatusText());
			
			//Fire System info event!
			fireSystemInformationEvent(pmSystemInfo);
			
			if((pmEvent.getSystemStateValue() & (1<<sseZoneEvent)) != 0){ 
				pmEvent.setZoneEventType((int)pmMessage.charAt(5));
				pmEvent.setZone((int)pmMessage.charAt(4));
				
                log("Zone that got event: " + pmEvent.getZoneEventZoneText());
                log("Zone event type: " + pmEvent.getZoneEventTypeText());
                
                
                //Fire Zone Event  
                fireZoneEventEvent(pmEvent);
                
                
			}
			
			/*                if (pmEvent.SystemStatus=ssArmedAway) and (pmEvent.ZoneEventType in [zeOpen, zeViolatedMotion]) then
                    SendToMQTTBus('home/alarm/burgular', 'ON');

                if (pmEvent.ZoneEventType in [zeFireAlarm]) then
                    SendToMQTTBus('home/alarm/fire', 'ON');

*/
			
			
			if((pmEvent.getSystemStateValue() & (1<<sseArmDisarmEvent)) != 0){ 
				String statusText = pmEvent.getSystemStatusText();
				
				PowerMaxSimulatedEventArmDisarmEvent armDisarmEvent = new PowerMaxSimulatedEventArmDisarmEvent();
				
				if(statusText.equalsIgnoreCase("armedhome")){
					log("Will send ARMED HOME activation");
					armDisarmEvent.setArmDisarmState(EnumSystemArmDisarmState.ARMEDHOME);
					fireArmDisarmEvent(armDisarmEvent);
				} else if(statusText.equalsIgnoreCase("armedaway")){
					log("Will send ARMED AWAY activation");
					armDisarmEvent.setArmDisarmState(EnumSystemArmDisarmState.ARMED);
					fireArmDisarmEvent(armDisarmEvent);
				} else if(statusText.equalsIgnoreCase("disarm")){
					log("Will send alarm DISARM");
					armDisarmEvent.setArmDisarmState(EnumSystemArmDisarmState.DISARMED);
					fireArmDisarmEvent(armDisarmEvent);
				}
				
				//Fire arm disarm event!
			}
			
			
			break;
		case geKeepAlive:
			log("Got an incoming General Event of Type KEEP ALIVE");
			
			PowerMaxGeneralEventKeepAlive pmKeepAlive = new PowerMaxGeneralEventKeepAlive();
			
			pmKeepAlive.setZoneState1((int)pmMessage.charAt(2));
			pmKeepAlive.setZoneState2((int)pmMessage.charAt(3));
			pmKeepAlive.setZoneState3((int)pmMessage.charAt(4));
			pmKeepAlive.setZoneState4((int)pmMessage.charAt(5));

			
			
			
			
			/*
			 * 
			 *         for counter := 0 to 8 - 1 do
            if (AZoneState and (1 shl counter) <> 0) then begin
                Log('Zone ' + inttostr(counter + AOffset) + ' ('+subtopicstring+') is ' + onValue);
                SendToMQTTBus('home/alarm/keepalive/'+subtopicstring+'/zone'+IntToStr(counter + AOffset), onValue);
            end else begin
                Log('Zone ' + inttostr(counter + AOffset) + ' ('+subtopicstring+') is ' + offValue);
                SendToMQTTBus('home/alarm/keepalive/'+subtopicstring+'/zone'+IntToStr(counter + AOffset), offValue);
            end;

			 */
			
			
			pmKeepAlive.setZoneBatteryLevel1((int)pmMessage.charAt(6));
			pmKeepAlive.setZoneBatteryLevel2((int)pmMessage.charAt(7));
			pmKeepAlive.setZoneBatteryLevel3((int)pmMessage.charAt(8));
			pmKeepAlive.setZoneBatteryLevel4((int)pmMessage.charAt(9));
			
			
			fireKeepAliveEvent(pmKeepAlive);
/*			for(int x = 0; x<8;x++){
				if((pmKeepAlive.getZoneBatteryLevel1() & (1<<x))!=0){
					log("Zone Battery Level " + (x+1) + " LOW");
				} else {
					log("Zone Battery Level " + (x+1) + " OK");					
				}
			}
			for(int x = 0; x<8;x++){
				if((pmKeepAlive.getZoneBatteryLevel2() & (1<<x))!=0){
					log("Zone Battery Level " + (x+9) + " LOW");
				} else { 
					log("Zone Battery Level " + (x+9) + " OK");					
				}
			}
			for(int x = 0; x<8;x++){
				if((pmKeepAlive.getZoneBatteryLevel3() & (1<<x))!=0){
					log("Zone Battery Level " + (x+18) + " LOW");
				} else {
					log("Zone Battery Level " + (x+18) + " OK");					
				}
			}
			for(int x = 0; x<8;x++){
				if((pmKeepAlive.getZoneBatteryLevel4() & (1<<x))!=0){
					log("Zone Battery Level " + (x+26) + " LOW");
				} else {
					log("Zone Battery Level " + (x+26) + " OK");					
				}
			}
			
*/			
/*
 *             pmKeepAlive := TPMGeneralEventKeepAlive.Create;

            pmKeepAlive.FZoneState_1 := Ord(AData[4]);
            pmKeepAlive.FZoneState_2 := Ord(AData[5]);
            pmKeepALive.FZoneState_3 := Ord(AData[6]);
            pmKeepAlive.FZoneState_4 := Ord(AData[7]);

            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneState_1, keepAliveStatusTypeState, 1);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneState_2, keepAliveStatusTypeState, 9);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneState_3, keepAliveStatusTypeState, 18);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneState_4, keepAliveStatusTypeState, 26);

            pmKeepAlive.FZoneBatteryLevel_1 := Ord(AData[8]);
            pmKeepAlive.FZoneBatteryLevel_2 := Ord(AData[9]);
            pmKeepAlive.FZoneBatteryLevel_3 := Ord(AData[10]);
            pmKeepAlive.FZoneBatteryLevel_4 := Ord(AData[11]);

            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneBatteryLevel_1, keepAliveStatusTypeBattery, 1);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneBatteryLevel_2, keepAliveStatusTypeBattery, 9);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneBatteryLevel_3, keepAliveStatusTypeBattery, 18);
            PublishKeepAliveZoneStatusString(pmKeepALive.FZoneBatteryLevel_4, keepAliveStatusTypeBattery, 26);

            pmKeepAlive.Free;
			
 */
			
			break;
		case geLogEvent:
			log("Got an incoming General Event of Type LOG EVENT");
			break;
		case geTamperEvent:
			log("Got an incoming General Event of Type TAMPER EVENT");			
			break;
		default:
			log("Got an incoming General Event of unkown type. Value: " + generalEventType);			
			break;
		}
	}
	
    public synchronized void addConnectionListener( IConnectionStateListener l ){
    	_connectionListeners.add(l);
    }
    public synchronized void removeConnectionListener( IConnectionStateListener l ){
    	_connectionListeners.remove(l);
    }
	
    
    public synchronized void addPublishListener( IChannelAlarmEventListener l ){
    	_pubListeners.add(l);
    }
    public synchronized void removeConnectionListener( IChannelAlarmEventListener l ){
    	_pubListeners.remove(l);
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
    
    
    private synchronized void fireKeepAliveEvent(PowerMaxGeneralEventKeepAlive newKeepAliveEvent){
        Iterator<IChannelAlarmEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelAlarmEventListener) listeners.next() ).publishKeepAliveStatus(newKeepAliveEvent);
        }    	
    }
    
    private synchronized void fireArmDisarmEvent(PowerMaxSimulatedEventArmDisarmEvent newArmDisarmEvent){
        Iterator<IChannelAlarmEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelAlarmEventListener) listeners.next() ).publishSystemArmDisarmState(newArmDisarmEvent);
        }         	    	
    }
    
    private synchronized void fireSystemInformationEvent(PowerMaxSimulatedEventSystemInformation newSystemInformation){
        Iterator<IChannelAlarmEventListener> listeners = _pubListeners.iterator();
        while( listeners.hasNext() ) {
            ( (IChannelAlarmEventListener) listeners.next() ).publishSystemInformation(newSystemInformation);
        }         	
    }
    
	private synchronized void fireZoneEventEvent(PowerMaxGeneralEventEvent newEvent) {
//      SensorEventChanged sChangedEvent = new SensorEventChanged( this, sensor, SensorMessageType.MT_SetVariable);
      Iterator<IChannelAlarmEventListener> listeners = _pubListeners.iterator();
      while( listeners.hasNext() ) {
          ( (IChannelAlarmEventListener) listeners.next() ).publishZoneEvent(newEvent);
      }     
  }	

	

    
	
}
