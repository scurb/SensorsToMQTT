package se.dolutions.SmartHome.SensorsToMQTT.Events;

public interface ISensorEventListener {
	public void handleSensorSetVariableEvent(SensorEventSetVariable event);
	public void handleSensorPresentationEvent(SensorEventPresentation event);
	public void handleSensorInternalEvent(SensorEventInternal event);
	public void handleSensorRequestVariableEvent(SensorEventRequestVariable event);
}
