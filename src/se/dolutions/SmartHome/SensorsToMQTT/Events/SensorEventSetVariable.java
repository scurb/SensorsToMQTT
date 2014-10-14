package se.dolutions.SmartHome.SensorsToMQTT.Events;

import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageSetVariable;

public class SensorEventSetVariable extends java.util.EventObject{

	private SensorMessageSetVariable _sensorSetVariableMessage;
	public SensorMessageSetVariable sensorSetVariableMessage(){
		return _sensorSetVariableMessage;
	}
	
	public SensorEventSetVariable(Object source, SensorMessageSetVariable sensorSetVariableMessage) {
		super(source);
		_sensorSetVariableMessage = sensorSetVariableMessage;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6779501051689864707L;

}
