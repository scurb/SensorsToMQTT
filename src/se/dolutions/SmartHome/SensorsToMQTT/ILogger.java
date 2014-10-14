package se.dolutions.SmartHome.SensorsToMQTT;


/**
 * Logger interface.
 * @author scurb
 *
 */
public interface ILogger {

	public void log(String logText, LogLevel logLevel);
	
}
