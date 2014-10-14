package se.dolutions.SmartHome.SensorsToMQTT;

import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.*;

/**
 * Sensorpool is a memory pool of active sensors. 
 * @author scurb
 *
 */
public class SensorPool {

	ArrayList<ISensor> sensorList;
	public ArrayList<Node> nodeList;

	public SensorPool(){
		sensorList = new ArrayList<ISensor>();
		nodeList = new ArrayList<Node>();
	}
	
	public void addNodeToPool(Node node){
		nodeList.add(node);	
		node.saveNode();
	}
	public void removeNodeFromPool(Node node){
		nodeList.remove(node);
		node.deleteNode();	
	}

	public Node getNodeFromPool(Integer radioID){
		
		for(Node node : nodeList){
			if((node.getRadioID() == radioID)){
				return node;
			}
		}

		System.err.println("SensorPool (getNodeFromPool): Found no Node!");
		return null;
	}
	
	public void addSensorToPool(Sensor sensor){
		Boolean foundNode = false;
		for(Node node : nodeList){
			if (node.getRadioID() == sensor.getRadioID()){
				node.addSensorToNode(sensor);
				sensor.saveSensor();
				foundNode = true;
			}
		}
		if(!foundNode){
			System.err.println("SensorPool (addSensorToPool): Found no node for this sensor. Will not add it!");
		}
	}
	
	public void removeSensorFromPool(Sensor sensor){
		for(Node node : nodeList){
			if (node.getRadioID() == sensor.getRadioID()){
				node.removeSensorFromNode(sensor);
				sensor.deleteSensor();
			}
		}
	}

	public Sensor getSensorFromPool(Integer radioID, Integer childID){
		for(Node node : nodeList){
			if(node.getRadioID() == radioID){
				return node.getSensor(childID);
			}
		}
		System.err.println("SensorPool (getSensorFromPool): Did not find this sensor on the node.");
		return null;
	}
}
