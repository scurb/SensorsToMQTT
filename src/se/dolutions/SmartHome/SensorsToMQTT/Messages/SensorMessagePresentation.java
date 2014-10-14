package se.dolutions.SmartHome.SensorsToMQTT.Messages;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorType;

public class SensorMessagePresentation extends SensorMessage{

	private SensorType sType;
	public SensorType getSensorType(){
		return sType;
	}
	public void setSensorType(SensorType sensorType){
		this.sType = sensorType;
	}
	
	private String _sketchVersion;
	public String sketchVersion(){
		return _sketchVersion;
	}
	
}
