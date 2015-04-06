package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

import java.util.ArrayList;

public class ConfigurationSensorNetwork {
	private ArrayList<ConfigurationChannel> channelList;
	
	private String comment;
	private Integer id;

	private ConfigurationDatabase configDB;
	private ConfigurationSensorNetworkConnection configSensorGwIp;
	
	
	public ArrayList<ConfigurationChannel> getChannelList() {
		return channelList;
	}
	public void setChannelList(ArrayList<ConfigurationChannel> channelList) {
		this.channelList = channelList;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ConfigurationDatabase getConfigDB() {
		return configDB;
	}
	public void setConfigDB(ConfigurationDatabase configDB) {
		this.configDB = configDB;
	}
	public ConfigurationSensorNetworkConnection getConfigSensorGwIp() {
		return configSensorGwIp;
	}
	public void setConfigSensorGwIp(
		ConfigurationSensorNetworkConnection configSensorGwIp) {
		this.configSensorGwIp = configSensorGwIp;
	}
	
	
	
	public ConfigurationSensorNetwork(){
		channelList = new ArrayList<ConfigurationChannel>();
		configDB = new ConfigurationDatabase();
		configSensorGwIp = new ConfigurationSensorNetworkConnection();
	}
	
	
	
}
