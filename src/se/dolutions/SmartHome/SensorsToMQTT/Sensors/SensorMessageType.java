package se.dolutions.SmartHome.SensorsToMQTT.Sensors;

public enum SensorMessageType {
	MT_Presentation,
	MT_SetVariable,
	MT_RequestVariable,
	MT_VariableAcknowledge,
	MT_Internal,
	MT_Stream;
	
	
	public static SensorMessageType getSensorMessageType(Integer messageTypeInteger){
		switch(messageTypeInteger){
		case 0: return MT_Presentation;
		case 1: return MT_SetVariable;
		case 2: return MT_RequestVariable;
		//case 3: return MT_VariableAcknowledge;
		//case 4: return MT_Internal;
		case 3: return MT_Internal;
		case 4: return MT_Stream;
		default:
			return null;
		}
	}
}
