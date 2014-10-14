package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

public interface ISensor {

	public String getSensorID();
	public Integer getRadioID();
	public void setRadioID(Integer radioID);
	public Integer getChildID();
	public void setChildID(Integer childID);
	public void touchLastChanged();
	public void setSensorType(SensorType sensorType);
	public SensorType getSensorType();
	public void setSensorSketchName(String sketchName);
	public void setSensorSketchVersion(String sketchVersion);
	public String getSensorSketchName();
	public String getSensorSketchVersion();
	public void sensorValueUpdate(SensorValue sValue);
	public String getSensorValue(SensorValueType sValueType);
	public String getJSONPresentation();
	
	
}
