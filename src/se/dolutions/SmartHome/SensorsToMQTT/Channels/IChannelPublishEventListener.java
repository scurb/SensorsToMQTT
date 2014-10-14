package se.dolutions.SmartHome.SensorsToMQTT.Channels;

import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.ILogger;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationChannel;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Node;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;

public interface IChannelPublishEventListener {

	public void channelConfigure(ConfigurationChannel channelConfigurator);
	public void setLogger(ILogger logger);
	
	public void publishNodePresentation(ArrayList<Node> nodeList);
	public void publishNewNodeToChannel(Node node);
	public void publishNewSensorToChannel(Node ownerNode, Sensor newSensor);
	public void publishNewSensorValue(Sensor sensor, SensorValue newSensorValue);

	//Like for example battery state
	public void publishNewNodeInformation(Node node);
	public void publishNewSensorInformation(Sensor sensor);
	
	//TODO Handle events FROM channel back to "any listeners"
}
