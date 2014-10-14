package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

public class ConfigurationSensorNetworkConnection {
	private String ip_address;
	private Integer ip_port;
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public Integer getIp_port() {
		return ip_port;
	}
	public void setIp_port(Integer ip_port) {
		this.ip_port = ip_port;
	}

}
