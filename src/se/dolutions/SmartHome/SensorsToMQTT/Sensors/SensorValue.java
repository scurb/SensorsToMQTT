package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

public class SensorValue {
	public SensorValueType getSensorValueType() {
		return sensorValueType;
	}
	public void setSensorValueType(SensorValueType sensorValueType) {
		this.sensorValueType = sensorValueType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	SensorValueType sensorValueType;
	String value;

}
