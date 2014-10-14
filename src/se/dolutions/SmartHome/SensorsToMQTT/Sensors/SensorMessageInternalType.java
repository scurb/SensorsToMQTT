package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

public enum SensorMessageInternalType {
	/*BATTERY_LEVEL = {0, "urn:micasaverde-com:serviceId:HaDevice1", "BatteryLevel", "" },
	BATTERY_DATE = 	{1, "urn:micasaverde-com:serviceId:HaDevice1", "BatteryDate", "" },
	LAST_TRIP = 	{2, "urn:micasaverde-com:serviceId:SecuritySensor1", "LastTrip", "" },
	TIME = 			{3, nil, nil, nil},
 	VERSION = 		{4, "urn:upnp-arduino-cc:serviceId:arduinonode1", "ArduinoLibVersion", ""},
 	REQUEST_ID = 	{5, nil, nil, nil},
 	INCLUSION_MODE ={6, "urn:upnp-arduino-cc:serviceId:arduino1", "InclusionMode", "0"},
  	RELAY_NODE=     {7, "urn:upnp-arduino-cc:serviceId:arduinonode1", "RelayNode", ""},
    LAST_UPDATE = 	{8, "urn:micasaverde-com:serviceId:HaDevice1", "LastUpdate", "" },
    PING = 			{9, nil, nil, nil },
    PING_ACK =      {10, nil, nil, nil },
    LOG_MESSAGE =   {11, nil, nil, nil },
    CHILDREN =  	{12, "urn:upnp-arduino-cc:serviceId:arduinonode1", "Children", "0"},
 	UNIT =			{13, "urn:upnp-arduino-cc:serviceId:arduino1", "Unit", "M"},  -- M = Metric / I = Imperial
	SKETCH_NAME    = {14, "urn:upnp-arduino-cc:serviceId:arduinonode1", "SketchName", ""},
	SKETCH_VERSION = {15, "urn:upnp-arduino-cc:serviceId:arduinonode1", "SketchVersion", ""}
	*/
	BATTERY_DATE(1),
	LAST_TRIP(2),
	TIME(3),
	VERSION(4),
	REQUEST_ID(5),
	INCLUSION_MODE(6),
	RELAY_NODE(7),
	LAST_UPDATE(8),
	PING(9),
	PING_ACK(10),
	LOG_MESSAGE(11),
	CHILDREN(12),
	UNIT(13),
	SKETCH_NAME(14),
	SKETCH_VERSION(15);
	
	private Integer value;
	public Integer getValue(){
		return this.value;
	}
	
	private SensorMessageInternalType(Integer value){
		this.value = value;
	}
	
	public static SensorMessageInternalType getSensorMessageInternalType(Integer messageInternalTypeInteger){
		switch(messageInternalTypeInteger){
		case 1: return BATTERY_DATE;
		case 2: return LAST_TRIP;
		case 3: return TIME;
		case 4: return VERSION;
		case 5: return REQUEST_ID;
		case 6: return INCLUSION_MODE;
		case 7: return RELAY_NODE;
		case 8: return LAST_UPDATE;
		case 9: return PING;
		case 10: return PING_ACK;
		case 11: return LOG_MESSAGE;
		case 12: return CHILDREN;
		case 13: return UNIT;
		case 14: return SKETCH_NAME;
		case 15: return SKETCH_VERSION;
		default:
			return null;
		}
	}
	
}
