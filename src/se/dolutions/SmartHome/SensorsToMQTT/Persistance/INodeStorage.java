package se.dolutions.SmartHome.SensorsToMQTT.Persistance;

import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Node;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValueType;

/**
 * Storage interface to be implemented by all storage methos.
 * 
 * @author scurb
 *
 */
public interface INodeStorage {

	/**
	 * Saves a new node to storage
	 * @param node
	 */
	public void saveNode(Node node);
	
	/**
	 * Deletes a node from storage
	 * @param node
	 */
	public void deleteNode(Node node);
	
	/**
	 * Updates information about a new node. Today this is done by saveNode and not implemented.
	 * @param node
	 */
	public void updateNode(Node node);

	
	/**
	 * Saves a new sensor on a node.
	 * @param sensor
	 */
	public void saveSensor(Sensor sensor);
	/**
	 * Deletes a sensor on a node
	 * @param sensor
	 */
	public void deleteSensor(Sensor sensor);
	/**
	 * Updates information about a node.
	 * @param sensor
	 */
	public void updateSensor(Sensor sensor);
	
	/**
	 * This returns the entire node list from persistance storage
	 * @return
	 */
	public ArrayList<Node> getNodeList();	
	
	/**
	 * Returns the next available ID to be given to any new sensor/node requests.
	 * @return
	 */
	public Integer assignNextAvailableRadioID();
	
	/**
	 * Saves the last sensor value to data storage. This is done so a sensor can request "last reported value".
	 * @param sensorValue
	 */
	public void saveSensorValue(Sensor sensor, SensorValue sensorValue);
	
	/**
	 * Retrieves the last stored value for a sensor and given value type.
	 * @return
	 */
	public SensorValue getLastSensorValue(Sensor sensor, SensorValueType sensorValueType);
}
