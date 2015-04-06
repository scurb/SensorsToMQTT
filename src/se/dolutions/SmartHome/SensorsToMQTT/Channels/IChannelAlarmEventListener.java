package se.dolutions.SmartHome.SensorsToMQTT.Channels;

import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxGeneralEventEvent;
import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxGeneralEventKeepAlive;
import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxSimulatedEventArmDisarmEvent;
import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxSimulatedEventSystemInformation;
import se.dolutions.SmartHome.SensorsToMQTT.ILogger;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationChannel;

public interface IChannelAlarmEventListener {
	public void channelConfigure(ConfigurationChannel channelConfigurator);
	public void setLogger(ILogger logger);

	
	//TODO Replace the PowerMax specific event classes with more general ones.
	public void publishZoneEvent(PowerMaxGeneralEventEvent newEvent);
	public void publishKeepAliveStatus(PowerMaxGeneralEventKeepAlive newKeepAlive);
	public void publishSystemInformation(PowerMaxSimulatedEventSystemInformation newInformation);
	public void publishSystemArmDisarmState(PowerMaxSimulatedEventArmDisarmEvent armDisarmState);
	
}
