package se.dolutions.SmartHome.SensorsToMQTT.Messages;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorMessageInternalType;

public class SensorMessageInternal extends SensorMessage {

	private SensorMessageInternalType messageInternalType;
		public SensorMessageInternalType getMessageInternalType() {
		return messageInternalType;
	}
	public void setMessageInternalType(SensorMessageInternalType messageInternalType) {
		this.messageInternalType = messageInternalType;
	}


	private String data;
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
