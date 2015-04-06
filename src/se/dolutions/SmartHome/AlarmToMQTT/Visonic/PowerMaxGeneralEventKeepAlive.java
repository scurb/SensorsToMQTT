package se.dolutions.SmartHome.AlarmToMQTT.Visonic;

public class PowerMaxGeneralEventKeepAlive {
	
	private Integer zoneState1; //Bit values for zone 1-8
	private Integer zoneState2; //Bit values for zone 9-16
	private Integer zoneState3; //Bit values for zone 17-24
	private Integer zoneState4; //Bit values for zone 25-30
	
	private Integer zoneBatteryLevel1; //Bit values for zone 1-8
	private Integer zoneBatteryLevel2; //Bit values for zone 9-16
	private Integer zoneBatteryLevel3; //Bit values for zone 17-24
	private Integer zoneBatteryLevel4; //Bit values for zone 25-30
	
	public Integer getZoneState1() {
		return zoneState1;
	}
	public void setZoneState1(Integer zoneState1) {
		this.zoneState1 = zoneState1;
	}
	public Integer getZoneState2() {
		return zoneState2;
	}
	public void setZoneState2(Integer zoneState2) {
		this.zoneState2 = zoneState2;
	}
	public Integer getZoneState3() {
		return zoneState3;
	}
	public void setZoneState3(Integer zoneState3) {
		this.zoneState3 = zoneState3;
	}
	public Integer getZoneState4() {
		return zoneState4;
	}
	public void setZoneState4(Integer zoneState4) {
		this.zoneState4 = zoneState4;
	}
	public Integer getZoneBatteryLevel1() {
		return zoneBatteryLevel1;
	}
	public void setZoneBatteryLevel1(Integer zoneBatteryLevel1) {
		this.zoneBatteryLevel1 = zoneBatteryLevel1;
	}
	public Integer getZoneBatteryLevel2() {
		return zoneBatteryLevel2;
	}
	public void setZoneBatteryLevel2(Integer zoneBatteryLevel2) {
		this.zoneBatteryLevel2 = zoneBatteryLevel2;
	}
	public Integer getZoneBatteryLevel3() {
		return zoneBatteryLevel3;
	}
	public void setZoneBatteryLevel3(Integer zoneBatteryLevel3) {
		this.zoneBatteryLevel3 = zoneBatteryLevel3;
	}
	public Integer getZoneBatteryLevel4() {
		return zoneBatteryLevel4;
	}
	public void setZoneBatteryLevel4(Integer zoneBatteryLevel4) {
		this.zoneBatteryLevel4 = zoneBatteryLevel4;
	}

	
	
	
	/*
 *   TPMGeneralEventKeepAlive = class
  private
    FZoneState_1 : integer; //Bit values for zone 1-8
    FZoneState_2 : integer; //Bit values for zone 9-16
    FZoneState_3 : integer; //Bit values for zone 17-24
    FZoneState_4 : integer; //Bit values for zone 25-30
    FZoneBatteryLevel_1 : integer; //Bit values for zone 1-8
    FZoneBatteryLevel_2 : integer; //Bit values for zone 9-16
    FZoneBatteryLevel_3 : integer; //Bit values for zone 17-24
    FZoneBatteryLevel_4 : integer; //Bit values for zone 25-30
  public
    property ZoneState_1 : integer read FZoneState_1 write FZoneState_1;
    property ZoneState_2 : integer read FZoneState_2 write FZoneState_2;
    property ZoneState_3 : integer read FZoneState_3 write FZoneState_3;
    property ZoneState_4 : integer read FZoneState_4 write FZoneState_4;

    property ZoneBatteryLevel_1 : integer read FZoneBatteryLevel_1 write FZoneBatteryLevel_1;
    property ZoneBatteryLevel_2 : integer read FZoneBatteryLevel_2 write FZoneBatteryLevel_2;
    property ZoneBatteryLevel_3 : integer read FZoneBatteryLevel_3 write FZoneBatteryLevel_3;
    property ZoneBatteryLevel_4 : integer read FZoneBatteryLevel_4 write FZoneBatteryLevel_4;
  end;

 */
}
