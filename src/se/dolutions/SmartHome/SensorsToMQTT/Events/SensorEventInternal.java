package se.dolutions.SmartHome.SensorsToMQTT.Events;

import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessageInternal;

public class SensorEventInternal extends java.util.EventObject {

//	private Sensor _sensor;
	private SensorMessageInternal _mInternal;
	public SensorMessageInternal sensorMessageInternal(){
		return _mInternal;		
	}
	
	public SensorEventInternal(Object source, SensorMessageInternal sensorMessageInternal) {
		super(source);
		_mInternal = sensorMessageInternal;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458575784419827165L;

}
