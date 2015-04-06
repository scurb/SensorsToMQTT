package se.dolutions.SmartHome.AlarmToMQTT;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import se.dolutions.SmartHome.SensorsToMQTT.IConnectionStateListener;
import se.dolutions.SmartHome.SensorsToMQTT.ILogger;
import se.dolutions.SmartHome.SensorsToMQTT.LogLevel;
import se.dolutions.SmartHome.SensorsToMQTT.Channels.IChannelAlarmEventListener;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationAlarm;
import se.dolutions.SmartHome.AlarmToMQTT.Visonic.*;

public class AlarmMasterClass extends Thread implements ILogger,IConnectionStateListener {

	private List<IChannelAlarmEventListener> _pubListeners = new ArrayList<IChannelAlarmEventListener>();

	private static Logger logger;

	private PowerMaxCommunicator ipClient;
	
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
	
	public AlarmMasterClass(ConfigurationAlarm alarmConfig){
		logger = LogManager.getFormatterLogger("AlarmMasterClass" + "(0)");

		ipClient = new PowerMaxCommunicator();		
		ipClient.setHostname("192.168.0.40");
		ipClient.setPort(2001);
//		ipClient.addSensorEventListener(this);	
//		ipClient.addConnectionListener(this);
		ipClient.setLogger(this);

	}

	@Override
	public void run() {
		
		// First send out what we have...
//		fireSensorNetworkPresentationEvent();

		try {
			ipClient.run();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void handleConnectionExceptionEvent(Exception exc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleParsingExceptionEvent(Exception exc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleConnectionConnectExceptionEvent(ConnectException exc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleClosedConnectionEvent() {
		// TODO Auto-generated method stub
		
	}	

	
    public synchronized void addChannelPublisher( IChannelAlarmEventListener l ) {
        _pubListeners.add( l );
        ipClient.addPublishListener(l);
    }
    
    public synchronized void removeChannelPublisher( IChannelAlarmEventListener l ) {
        _pubListeners.remove( l );
        ipClient.addPublishListener(l);
    }	

}
