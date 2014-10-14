package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

import java.util.ArrayList;
import java.util.Date;

import se.dolutions.SmartHome.SensorsToMQTT.Persistance.INodeStorage;

public class Sensor implements ISensor {

	ArrayList<SensorValue> _sensorValues;
	
	/* Variable declarations */
	Integer radioID;
	Integer childID;
	Date lastUpdated;
	SensorType sensorType;
	SensorValueType sensorValueType;
	String sensorSketchVersion;
	String sensorSketchName;
	INodeStorage storage;
	
	public Sensor(INodeStorage storage) {
		_sensorValues = new ArrayList<SensorValue>();
		sensorType = SensorType.S_UNDEFINED;
		this.storage = storage;
		lastUpdated = new Date();
		sensorSketchVersion = "";
		sensorSketchName = "";
		
	}
	
	public void saveSensor(){
		storage.saveSensor(this);
	}
	public void deleteSensor(){
		storage.deleteSensor(this);
	}
	
	
	
	public ArrayList<SensorValue> sensorValues(){
		return _sensorValues;
	}
	
	@Override
	public Integer getRadioID() {
		return radioID;
	}
	@Override
	public Integer getChildID() {		
		return childID;
	}
	@Override
	public String getSensorID() {
		return radioID.toString() + "-" + childID.toString();
	}
	@Override
	public void touchLastChanged() {
		lastUpdated = new Date();
	}

	@Override
	public void sensorValueUpdate(SensorValue sValue){
		SensorValue storedSensorValue;
		storedSensorValue = null;
		
		for(SensorValue _storedValue : _sensorValues){
			if (_storedValue.sensorValueType == sValue.sensorValueType)
				storedSensorValue = _storedValue;
		}
		
		if(storedSensorValue!=null)
			storedSensorValue.setValue(sValue.getValue());
		else{
			_sensorValues.add(sValue);
		}
		this.lastUpdated = new Date();	
		
		//Persist this value as the sensors last known value. 
		storage.saveSensorValue(this,  sValue);
	}

	@Override
	public String getSensorValue(SensorValueType sValueType){
		
		for(SensorValue _storedValue : _sensorValues){
			if(_storedValue.getSensorValueType().equals(sValueType)){
				return _storedValue.getValue();
			}
		}
		
		return "";
	}
	
	@Override
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	@Override
	public SensorType getSensorType() {
		return this.sensorType;
	}

	@Override
	public void setRadioID(Integer radioID) {
		this.radioID = radioID;
	}

	@Override
	public void setChildID(Integer childID) {
		this.childID = childID;
	}

	@Override
	public void setSensorSketchName(String sketchName) {
		this.sensorSketchName = sketchName;
	}

	@Override
	public void setSensorSketchVersion(String sketchVersion) {
		this.sensorSketchVersion = sketchVersion;
	}

	@Override
	public String getSensorSketchName() {
		return sensorSketchName;
	}

	@Override
	public String getSensorSketchVersion() {
		return sensorSketchVersion;
	}

	
	
	  // call this method whenever you want to notify
	  //the event listeners of the particular event
/*	  private synchronized void fireEvent() {
	    SensorEvent event = new SensorEvent(this);
	    Iterator<ISensorEventListener> i = _listeners.iterator();
	    while(i.hasNext())  {
	      ((ISensorEventListener) i.next()).handleSensorEvent(event);
	    }
	  } */
	@Override
	public String getJSONPresentation(){
		return "{ \"childID\": \"" + this.getChildID() + "\", " + 
				"\"sketchName\": \"" + this.getSensorSketchName() + "\", "+
				"\"sketchVersion\": \"" + this.getSensorSketchVersion() + "\", " + 
				"\"sensorType\": \"" + this.getSensorType().name() + "\"}";
		
	}
}
