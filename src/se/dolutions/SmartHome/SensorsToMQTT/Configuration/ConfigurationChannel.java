package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

public class ConfigurationChannel {
	private String channelType;
	private String channelUrl;
	private String rootTopic;
	private String clientID;
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getRootTopic() {
		return rootTopic;
	}
	public void setRootTopic(String rootTopic) {
		this.rootTopic = rootTopic;
	}

}
