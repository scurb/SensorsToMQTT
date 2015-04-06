package se.dolutions.SmartHome.AlarmToMQTT.Visonic;

public enum EnumGeneralEventType {

	geLogEvent(0x01),
	geKeepAlive(0x02),
	geTamperEvent(0x03),
	geEvent(0x04);
	
	private int value;
	
	private EnumGeneralEventType(int value){
		this.value = value;
	}
	
	public int getValue() {
        return value;
    }
}
