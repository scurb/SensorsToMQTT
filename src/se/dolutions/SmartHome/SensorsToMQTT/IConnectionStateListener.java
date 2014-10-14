package se.dolutions.SmartHome.SensorsToMQTT;

import java.net.ConnectException;

/**
 * Interface for ConnectionStateListener implementations. 
 * @author scurb
 *
 */
public interface IConnectionStateListener {
	public void handleConnectionExceptionEvent( Exception exc );
	public void handleParsingExceptionEvent( Exception exc );
	public void handleConnectionConnectExceptionEvent( ConnectException exc );
	public void handleClosedConnectionEvent();
	
}
