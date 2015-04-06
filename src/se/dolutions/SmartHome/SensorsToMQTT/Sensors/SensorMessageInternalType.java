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

I_BATTERY_LEVEL	0	Use this to report the battery level (in percent 0-100).
I_TIME	1	Sensors can request the current time from the Controller using this message. The time will be reported as the seconds since 1970
I_VERSION	2	Sensors report their library version at startup using this message type
I_ID_REQUEST	3	Use this to request a unique node id from the controller.
I_ID_RESPONSE	4	Id response back to sensor. Payload contains sensor id.
I_INCLUSION_MODE	5	Start/stop inclusion mode of the Controller (1=start, 0=stop).
I_CONFIG	6	Config request from node. Reply with (M)etric or (I)mperal back to sensor.
I_FIND_PARENT	7	When a sensor starts up, it broadcast a search request to all neighbor nodes. They reply with a I_FIND_PARENT_RESPONSE.
I_FIND_PARENT_RESPONSE	8	Reply message type to I_FIND_PARENT request.
I_LOG_MESSAGE	9	Sent by the gateway to the Controller to trace-log a message
I_CHILDREN	10	A message that can be used to transfer child sensors (from EEPROM routing table) of a repeating node.
I_SKETCH_NAME	11	Optional sketch name that can be used to identify sensor in the Controller GUI
I_SKETCH_VERSION	12	Optional sketch version that can be reported to keep track of the version of sensor in the Controller GUI.
I_REBOOT	13	Used by OTA firmware updates. Request for node to reboot.
I_GATEWAY_READY	14	Send by gateway to controller when startup is complete.
	*
	*/
	BATTERY_LEVEL(0),
	TIME(1),
	VERSION(2),
	ID_REQUEST(3),
	ID_RESPONSE(4),
	INCLUSION_MODE(5),
	CONFIG(6),
	FIND_PARENT(7),
	FIND_PARENT_RESPONSE(8),
	LOG_MESSAGE(9),
	CHILDREN(10),
	SKETCH_NAME(11),
	SKETCH_VERSION(12),
	REBOOT(13),
	GATEWAY_READY(14);	
//	BATTERY_DATE(1),
//	LAST_TRIP(2),
//	TIME(3),
//	VERSION(4),
//	REQUEST_ID(5),
//	INCLUSION_MODE(6),
//	RELAY_NODE(7),
//	LAST_UPDATE(8),
//	PING(9),
//	PING_ACK(10),
//	LOG_MESSAGE(11),
//	CHILDREN(12),
//	UNIT(13),
//	SKETCH_NAME(14),
//	SKETCH_VERSION(15);
	
	private Integer value;
	public Integer getValue(){
		return this.value;
	}
	
	private SensorMessageInternalType(Integer value){
		this.value = value;
	}
	
	public static SensorMessageInternalType getSensorMessageInternalType(Integer messageInternalTypeInteger){
		switch(messageInternalTypeInteger){
		case 0: return BATTERY_LEVEL;
		case 1: return TIME;
		case 2: return VERSION;
		case 3: return ID_REQUEST;
		case 4: return ID_RESPONSE;
		case 5: return INCLUSION_MODE;
		case 6: return CONFIG;
		case 7: return FIND_PARENT;
		case 8: return FIND_PARENT_RESPONSE;
		case 9: return LOG_MESSAGE;
		case 10: return CHILDREN;
		case 11: return SKETCH_NAME;
		case 12: return SKETCH_VERSION;
		case 13: return REBOOT;
		case 14: return GATEWAY_READY;

		/*case 1: return BATTERY_DATE;
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
		*/
		
		default:
			return null;
		}
	}
	
}
