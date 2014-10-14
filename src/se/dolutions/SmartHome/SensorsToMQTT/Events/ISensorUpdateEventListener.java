package se.dolutions.SmartHome.SensorsToMQTT.Events;

public interface ISensorUpdateEventListener {
	public void handleSensorUpdateEvent(SensorEventSetVariable event);
}
