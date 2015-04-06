package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

import java.util.ArrayList;

public class ConfigurationAlarm {

	private ArrayList<ConfigurationChannel> channelList;

	private int ipport;
	private String ip;
	private  String alarmType;
	
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public ArrayList<ConfigurationChannel> getChannelList() {
		return channelList;
	}
	public void setChannelList(ArrayList<ConfigurationChannel> channelList) {
		this.channelList = channelList;
	}
	public int getIpport() {
		return ipport;
	}
	public void setIpport(int ipport) {
		this.ipport = ipport;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public ConfigurationAlarm(){
		channelList = new ArrayList<ConfigurationChannel>();
	}

	
	
}
