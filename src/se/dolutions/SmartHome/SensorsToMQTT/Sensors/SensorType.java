package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

/**
 * S_DOOR	0	Door and window sensors
 * S_MOTION	1	Motion sensors
 * S_SMOKE	2	Smoke sensor
 * S_LIGHT	3	Light Actuator (on/off)
 * S_DIMMER	4	Dimmable device of some kind
 * S_COVER	5	Window covers or shades
 * S_TEMP	6	Temperature sensor
 * S_HUM	7	Humidity sensor
 * S_BARO	8	Barometer sensor (Pressure)
 * S_WIND	9	Wind sensor
 * S_RAIN	10	Rain sensor
 * S_UV	11	UV sensor
 * S_WEIGHT	12	Weight sensor for scales etc.
 * S_POWER	13	Power measuring device, like power meters
 * S_HEATER	14	Heater device
 * S_DISTANCE	15	Distance sensor
 * S_LIGHT_LEVEL	16	Light sensor
 * S_ARDUINO_NODE	17	Arduino node device
 * S_ARDUINO_RELAY	18	Arduino relaying node device
 * S_LOCK	19	Lock device
 * S_IR	20	Ir sender/receiver device
 * S_WATER	21	Water meter
 * @author scurb
 *
 */
public enum SensorType {
	S_DOOR, 
	S_MOTION, 
	S_SMOKE,
	S_LIGHT,
	S_DIMMER,
	S_COVER,
	S_TEMP,
	S_HUM,
	S_BARO,
	S_WIND,
	S_RAIN,
	S_UV,
	S_WEIGHT,
	S_POWER,
	S_HEATER,
	S_DISTANCE,
	S_LIGHT_LEVEL,
	S_ARDUINO_NODE,
	S_ARDUINO_RELAY,
	S_LOCK,
	S_WATER,
	
	S_UNDEFINED;
	
	
	public static SensorType getSensorType(Integer sensorTypeValue){
	
		switch(sensorTypeValue){
			case 0: return S_DOOR;
			case 1: return S_MOTION;
			case 2: return S_SMOKE;
			case 3: return S_LIGHT;
			case 4: return S_DIMMER;
			case 5: return S_COVER;
			case 6: return S_TEMP;
			case 7: return S_HUM;
			case 8: return S_BARO;
			case 9: return S_WIND;
			case 10: return S_RAIN;
			case 11: return S_UV;
			case 12: return S_WEIGHT;
			case 13: return S_POWER;
			case 14: return S_HEATER;
			case 15: return S_DISTANCE;
			case 16: return S_LIGHT_LEVEL;
			case 17: return S_ARDUINO_NODE;
			case 18: return S_ARDUINO_RELAY;
			case 19: return S_LOCK;
			case 20: return S_WATER;
			default: return S_UNDEFINED;
		}
	
	}
	
}
