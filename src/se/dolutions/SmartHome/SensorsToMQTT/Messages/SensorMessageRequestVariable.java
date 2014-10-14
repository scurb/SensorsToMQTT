package se.dolutions.SmartHome.SensorsToMQTT.Messages;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValueType;

public class SensorMessageRequestVariable extends SensorMessage {

	private SensorValueType requestedValueType;

	public SensorValueType getRequestedValueType() {
		return requestedValueType;
	}

	public void setRequestedValueType(SensorValueType requestedValueType) {
		this.requestedValueType = requestedValueType;
	}
	
	
	
}
