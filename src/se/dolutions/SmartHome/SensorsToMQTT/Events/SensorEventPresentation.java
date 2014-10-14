package se.dolutions.SmartHome.SensorsToMQTT.Events;

import se.dolutions.SmartHome.SensorsToMQTT.Messages.SensorMessagePresentation;

public class SensorEventPresentation extends java.util.EventObject {

	private SensorMessagePresentation _sensorMessagePresentation;
	public SensorMessagePresentation sensorMessagePresentation(){
		return _sensorMessagePresentation;
	}
	
	public SensorEventPresentation(Object source, SensorMessagePresentation sensorMessagePresentation) {
		super(source);
		this._sensorMessagePresentation = sensorMessagePresentation;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5495769497498719678L;

}
