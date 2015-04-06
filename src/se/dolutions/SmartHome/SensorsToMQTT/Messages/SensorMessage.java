package se.dolutions.SmartHome.SensorsToMQTT.Messages;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;

public class SensorMessage {
	Integer radioID;
	Integer childID;
	String sensorLibraryVersion;
	Boolean ackRequested;

	public Boolean getAckRequested() {
		return ackRequested;
	}
	public void setAckRequested(Boolean ackRequested) {
		this.ackRequested = ackRequested;
	}
	Sensor sensor;
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	
	public String getSensorLibraryVersion() {
		return sensorLibraryVersion;
	}
	public void setSensorLibraryVersion(String sensorLibraryVersion) {
		this.sensorLibraryVersion = sensorLibraryVersion;
	}

	public Integer getRadioID() {
		return radioID;
	}
	public Integer getChildID() {
		return childID;
	}

	public void setChildID(Integer childID) {
		this.childID = childID;
	}
	public void setRadioID(Integer radioID) {
		this.radioID = radioID;
	}
	
	
}
