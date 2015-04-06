package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

/**
 * V_TEMP	0	Temperature
 * V_HUM	1	Humidity
 * V_LIGHT	2	Light status. 0=off 1=on
 * V_DIMMER	3	Dimmer value. 0-100%
 * V_PRESSURE	4	Atmospheric Pressure
 * V_FORECAST	5	Whether forecast. One of "stable", "sunny", "cloudy", "unstable", "thunderstorm" or "unknown"
 * V_RAIN	6	Amount of rain
 * V_RAINRATE	7	Rate of rain
 * V_WIND	8	Windspeed
 * V_GUST	9	Gust
 * V_DIRECTION	10	Wind direction
 * V_UV	11	UV light level
 * V_WEIGHT	12	Weight (for scales etc)
 * V_DISTANCE	13	Distance
 * V_IMPEDANCE	14	Impedance value
 * V_ARMED	15	Armed status of a security sensor. 1=Armed, 0=Bypassed
 * V_TRIPPED	16	Tripped status of a security sensor. 1=Tripped, 0=Untripped
 * V_WATT	17	Watt value for power meters
 * V_KWH	18	Accumulated number of KWH for a power meter
 * V_SCENE_ON	19	Turn on a scene
 * V_SCENE_OFF	20	Turn of a scene
 * V_HEATER	21	Mode of header. One of "Off", "HeatOn", "CoolOn", or "AutoChangeOver"
 * V_HEATER_SW	22	Heater switch power. 1=On, 0=Off
 * V_LIGHT_LEVEL	23	Light level. 0-100%
 * V_VAR1	24	Custom value
 * V_VAR2	25	Custom value
 * V_VAR3	26	Custom value
 * V_VAR4	27	Custom value
 * V_VAR5	28	Custom value
 * V_UP	29	Window covering. Up.
 * V_DOWN	30	Window covering. Down.
 * V_STOP	31	Window covering. Stop.
 * V_IR_SEND	32	Send out an IR-command
 * V_IR_RECEIVE	33	This message contains a received IR-command
 * V_FLOW	34	Flow of water (in meter)
 * V_VOLUME	35	Water volume
 * V_LOCK_STATUS	36	Set or get lock status. 1=Locked, 0=Unlocked
 * 
 * 
 * @author scurb
 *
 */
public enum SensorValueType {
	V_TEMP,
	V_HUM,
	V_LIGHT,
	V_DIMMER,
	V_PRESSURE,
	V_FORECAST,
	V_RAIN,
	V_RAINRATE,
	V_WIND,
	V_GUST,
	V_DIRECTION,
	V_UV,
	V_WEIGHT,
	V_DISTANCE,
	V_IMPEDANCE,
	V_ARMED,
	V_TRIPPED,
	V_WATT,
	V_KWH,
	V_SCENE_ON,
	V_SCENE_OFF,
	V_HEATER,
	V_HEATER_SW,
	V_LIGHT_LEVEL,
	V_VAR1,
	V_VAR2,
	V_VAR3,
	V_VAR4,
	V_VAR5,
	V_UP,
	V_DOWN,
	V_STOP,
	V_IR_SEND,
	V_IR_RECEIVE,
	V_FLOW,
	V_VOLUME,
	V_LOCK_STATUS,
	V_DUST_LEVEL,
	V_VOLTAGE,
	V_CURRENT;
	
	public static SensorValueType getSensorValueType(Integer valueTypeValue){
	
		switch(valueTypeValue){
		case 0: return V_TEMP;
		case 1: return V_HUM;
		case 2: return V_LIGHT;
		case 3: return V_DIMMER;
		case 4: return V_PRESSURE;
		case 5: return V_FORECAST;
		case 6: return V_RAIN;
		case 7: return V_RAINRATE;
		case 8: return V_WIND;
		case 9: return V_GUST;
		case 10: return V_DIRECTION;
		case 11: return V_UV;
		case 12: return V_WEIGHT;
		case 13: return V_DISTANCE;
		case 14: return V_IMPEDANCE;
		case 15: return V_ARMED;
		case 16: return V_TRIPPED;
		case 17: return V_WATT;
		case 18: return V_KWH;
		case 19: return V_SCENE_ON;
		case 20: return V_SCENE_OFF;
		case 21: return V_HEATER;
		case 22: return V_HEATER_SW;
		case 23: return V_LIGHT_LEVEL;
		case 24: return V_VAR1;
		case 25: return V_VAR2;
		case 26: return V_VAR3;
		case 27: return V_VAR4;
		case 28: return V_VAR5;
		case 29: return V_UP;
		case 30: return V_DOWN;
		case 31: return V_STOP;
		case 32: return V_IR_SEND;
		case 33: return V_IR_RECEIVE;
		case 34: return V_FLOW;
		case 35: return V_VOLUME;
		case 36: return V_LOCK_STATUS;
		case 37: return V_DUST_LEVEL;
		case 38: return V_VOLTAGE;
		case 39: return V_CURRENT;
		default: return null;
		}
	}
}
