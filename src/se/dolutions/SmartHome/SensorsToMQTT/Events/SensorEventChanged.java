package se.dolutions.SmartHome.SensorsToMQTT.Events;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.*;

public class SensorEventChanged extends java.util.EventObject {

	private Sensor _sensor;
	public Sensor sensor(){
		return _sensor;
	}
	
	private SensorMessageType _mType;
	public SensorMessageType messageType(){
		return _mType;
	}
	
	public SensorEventChanged(Object source, Sensor sensor, SensorMessageType sensorMessageType) {
		super(source);
		_sensor = sensor;
		_mType = sensorMessageType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7654771601865850540L;

}
