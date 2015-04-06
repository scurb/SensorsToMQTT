package se.dolutions.SmartHome.AlarmToMQTT.Visonic;

public enum EnumZoneEventType {
	zeNone(0x00),
	zeTamperAlarm(0x01),
	zeTamperRestore(0x02),
	zeOpen(0x03),
	zeClosed(0x04),
	zeViolatedMotion(0x05),
	zePanicAlarm(0x06),
	zeRFJamming(0x07),
	zeTamperOpen(0x08),
	zeCommFailure(0x09),
	zeLineFailure(0x0A),
	zeFuse(0x0B),
	zeNotActive(0x0C),
	zeLowBattery(0x0D),
	zeACFailure(0x0E),
	zeFireAlarm(0x0F),
	zeEmergency(0x10),
	zeSirenTamper(0x11),
	zeSirenTamperRestore(0x12),
	zeSirenLowBatt(0x13),
	zeSirenACFail(0x14);

	
	private EnumZoneEventType(int value){
	}
}
