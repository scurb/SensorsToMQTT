package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.Persistance.INodeStorage;

public class Node {

	ArrayList<ISensor> _sensors;

	private Integer _radioID;
	private String _sketchName;
	private String _sketchVersion;
	private Boolean _relayNode;
	private INodeStorage storage;
	
	
	public Node(INodeStorage storage){
		this.storage = storage;
		_sensors = new ArrayList<ISensor>();
		_relayNode = false;
		_sketchName = "";
		_sketchVersion = "";
	}

	public ArrayList<ISensor> getSensorList(){
		return _sensors;
	}
	public Integer getRadioID() {
		return _radioID;
	}

	public void setRadioID(Integer _radioID) {
		this._radioID = _radioID;
	}

	public String getSketchName() {
		return _sketchName;
	}

	public void setSketchName(String _sketchName) {
		this._sketchName = _sketchName;
	}

	public String getSketchVersion() {
		return _sketchVersion;
	}

	public void setSketchVersion(String _sketchVersion) {
		this._sketchVersion = _sketchVersion;
	}

	public Boolean getRelayNode() {
		return _relayNode;
	}

	public void setRelayNode(Boolean _relayNode) {
		this._relayNode = _relayNode;
	}

	
	public void addSensorToNode(Sensor sensor){
		_sensors.add(sensor);
		sensor.saveSensor();
	}
	public void removeSensorFromNode(Sensor sensor){
		_sensors.remove(sensor);
		sensor.deleteSensor();
	}
	public Sensor getSensor(Integer childID){
		for(ISensor sensor: _sensors){
			if (sensor.getChildID() == childID){
				return (Sensor)sensor;
			}
		}
		return null;
	}
	
	public void saveNode(){
		storage.saveNode(this);
	}
	public void deleteNode(){
		storage.deleteNode(this);
	}
	
	public String getJSONPresentation(){
		
		return "{ \"radioID\": \"" + this.getRadioID() + "\", " + 
				"\"sketchName\": \"" + this.getSketchName() + "\", "+
				"\"sketchVersion\": \"" + this.getSketchVersion() + "\", " + 
				"\"relayNode\": \"" + this.getRelayNode().toString() + "\"}";
				
	}
	
}
