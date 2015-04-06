package se.dolutions.SmartHome.AlarmToMQTT.Visonic;

public enum EnumSystemState {
	pmSsReady(0),
	pmSsAlertMemory(1),
	pmSsTrouble(2),
	pmSsBypassOn(3),
	pmSsLast10SecDelay(4),
	pmSsZoneEvent(5),
	pmSsArmDisarmEvent(6),
	pmSsAlarmEvent(7);

	int value;
	
	private EnumSystemState(int value){
		this.value = value;
	}
	
	public int getValue(int value){
		return value;
	}
	
	public int getSystemStateValue(){
		return value;
	}
}
