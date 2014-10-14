package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

public class ConfigurationDatabase {
	private String dbType;
	private String dbHost;
	private String dbLogin;
	private Integer dbPort;
	private String dbDatabase;
	private String dbPassword;
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbHost() {
		return dbHost;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	public String getDbLogin() {
		return dbLogin;
	}
	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}
	public Integer getDbPort() {
		return dbPort;
	}
	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbDatabase() {
		return dbDatabase;
	}
	public void setDbDatabase(String dbDatabase) {
		this.dbDatabase = dbDatabase;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

}
