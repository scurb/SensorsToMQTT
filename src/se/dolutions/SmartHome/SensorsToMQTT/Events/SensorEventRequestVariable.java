package se.dolutions.SmartHome.SensorsToMQTT.Events;

import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageRequestVariable;

public class SensorEventRequestVariable extends java.util.EventObject {

	private SensorMessageRequestVariable _sensorMessageRequestVariable;

	public SensorMessageRequestVariable sensorRequestVariableMessage() {
		return _sensorMessageRequestVariable;
	}

	public SensorEventRequestVariable(Object source, SensorMessageRequestVariable sensorRequestVariableMessage) {
		super(source);
		_sensorMessageRequestVariable = sensorRequestVariableMessage;
	}


	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4197174556370131298L;

}
