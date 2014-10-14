package se.dolutions.SmartHome.SensorsToMQTT.Messages;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;

public class SensorMessageSetVariable extends SensorMessage{
	
	SensorValue sensorValue;
		
	public SensorValue getSensorValue() {
		return sensorValue;
	}

	public void setSensorValue(SensorValue sensorValue) {
		this.sensorValue = sensorValue;
	}
	
}
