package se.dolutions.SmartHome.AlarmToMQTT.Visonic;




public class PowerMaxGeneralEventEvent {
	
	protected static final int sseReady = 0;
	protected static final int sseAlertMemory = 1;
	protected static final int sseTrouble = 2;
	protected static final int sseBypassOn = 3;
	protected static final int sseLast10SecDelay = 4;
	protected static final int sseZoneEvent = 5;
	protected static final int sseArmDisarmEvent = 6;
	protected static final int sseAlarmEvent = 7;
	
	
	protected static final int ssDisarm = (0x00);
	protected static final int ssExitDelay = (0x01);
	protected static final int ssExitDelay2 = (0x02);
	protected static final int ssEntryDelay = (0x03);
	protected static final int ssArmedHome = (0x04);
	protected static final int ssArmedAway = (0x05);
	protected static final int ssUserText = (0x06);
	protected static final int ssDownload = (0x07);
	protected static final int ssProgramming = (0x08);
	protected static final int ssInstaller = (0x09);
	protected static final int ssHomeBypass = (0x0A);
	protected static final int ssAwayBypass = (0x0B);
	protected static final int ssReady = (0x0C);
	protected static final int ssNotReady = (0x0D);

	
	
	
	private int generalEventType;
	public int getGeneralEventType() {
		return generalEventType;
	}
	public void setGeneralEventType(int generalEventType) {
		this.generalEventType = generalEventType;
	}
	public int getSystemStateValue() {
		return systemStateValue;
	}
	public void setSystemStateValue(int systemStateValue) {
		this.systemStateValue = systemStateValue;
	}
	public int getSystemStatus() {
		return systemStatusValue;
	}
	public void setSystemStatus(int systemStatusValue) {
		this.systemStatusValue = systemStatusValue;
	}
	public int getZoneEventTypeValue() {
		return zoneEventTypeValue;
	}
	public void setZoneEventTypeValue(int zoneEventTypeValue) {
		this.zoneEventTypeValue = zoneEventTypeValue;
	}
	public int getZoneValue() {
		return zoneValue;
	}
	public void setZoneValue(int zoneValue) {
		this.zoneValue = zoneValue;
	}
	private int systemStateValue;
	private int systemStatusValue;
	private int zoneEventTypeValue;
	private int zoneValue;
	
	private EnumZoneUser zone;
	private EnumZoneEventType zoneEventType;
	public EnumZoneUser getZone() {
		return zone;
	}
	public void setZone(int zoneValue) {
		switch(zoneValue){
		case 0x00: zone = EnumZoneUser.PMSystem; break;
		case 0x01: zone = EnumZoneUser.PMZone1; break;
		case 0x02: zone = EnumZoneUser.PMZone2; break;
		case 0x03: zone = EnumZoneUser.PMZone3; break;
		case 0x04: zone = EnumZoneUser.PMZone4; break;
		case 0x05: zone = EnumZoneUser.PMZone5; break;
		case 0x06: zone = EnumZoneUser.PMZone6; break;
		case 0x07: zone = EnumZoneUser.PMZone7; break;
		case 0x08: zone = EnumZoneUser.PMZone8; break;
		case 0x09: zone = EnumZoneUser.PMZone9; break;
		case 0x0A: zone = EnumZoneUser.PMZone10; break;
		case 0x0B: zone = EnumZoneUser.PMZone11; break;
		case 0x0C: zone = EnumZoneUser.PMZone12; break;
		case 0x0D: zone = EnumZoneUser.PMZone13; break;
		case 0x0E: zone = EnumZoneUser.PMZone14; break;
		case 0x0F: zone = EnumZoneUser.PMZone15; break;
		case 0x10: zone = EnumZoneUser.PMZone16; break;
		case 0x11: zone = EnumZoneUser.PMZone17; break;
		case 0x12: zone = EnumZoneUser.PMZone18; break;
		case 0x13: zone = EnumZoneUser.PMZone19; break;
		case 0x14: zone = EnumZoneUser.PMZone20; break;
		case 0x15: zone = EnumZoneUser.PMZone21; break;
		case 0x16: zone = EnumZoneUser.PMZone22; break;
		case 0x17: zone = EnumZoneUser.PMZone23; break;
		case 0x18: zone = EnumZoneUser.PMZone24; break;
		case 0x19: zone = EnumZoneUser.PMZone25; break;
		case 0x1A: zone = EnumZoneUser.PMZone26; break;
		case 0x1B: zone = EnumZoneUser.PMZone27; break;
		case 0x1C: zone = EnumZoneUser.PMZone28; break;
		case 0x1D: zone = EnumZoneUser.PMZone29; break;
		case 0x1E: zone = EnumZoneUser.PMZone30; break;
		case 0x4C: zone = EnumZoneUser.PMPGM; break;
		case 0x4D: zone = EnumZoneUser.PMGSM; break;
		case 0x4E: zone = EnumZoneUser.PMPowerLink; break;
		}
	}
	public EnumZoneEventType getZoneEventType() {
		return zoneEventType;
	}
	public void setZoneEventType(int zoneEventTypeValue) {
		switch(zoneEventTypeValue){
		case 0x00: zoneEventType = EnumZoneEventType.zeNone;break;
		case 0x01: zoneEventType = EnumZoneEventType.zeTamperAlarm;break;
		case 0x02: zoneEventType = EnumZoneEventType.zeTamperRestore;break;
		case 0x03: zoneEventType = EnumZoneEventType.zeOpen;break;
		case 0x04: zoneEventType = EnumZoneEventType.zeClosed;break;
		case 0x05: zoneEventType = EnumZoneEventType.zeViolatedMotion;break;
		case 0x06: zoneEventType = EnumZoneEventType.zePanicAlarm;break;
		case 0x07: zoneEventType = EnumZoneEventType.zeRFJamming;break;
		case 0x08: zoneEventType = EnumZoneEventType.zeTamperOpen;break;
		case 0x09: zoneEventType = EnumZoneEventType.zeCommFailure;break;
		case 0x0A: zoneEventType = EnumZoneEventType.zeLineFailure;break;
		case 0x0B: zoneEventType = EnumZoneEventType.zeFuse;break;
		case 0x0C: zoneEventType = EnumZoneEventType.zeNotActive;break;
		case 0x0D: zoneEventType = EnumZoneEventType.zeLowBattery;break;
		case 0x0E: zoneEventType = EnumZoneEventType.zeACFailure;break;
		case 0x0F: zoneEventType = EnumZoneEventType.zeFireAlarm;break;
		case 0x10: zoneEventType = EnumZoneEventType.zeEmergency;break;
		case 0x11: zoneEventType = EnumZoneEventType.zeSirenTamper;break;
		case 0x12: zoneEventType = EnumZoneEventType.zeSirenTamperRestore;break;
		case 0x13: zoneEventType = EnumZoneEventType.zeSirenLowBatt;break;
		case 0x14: zoneEventType = EnumZoneEventType.zeSirenACFail;break;
		
		}
		
		//this.zoneEventType = zoneEventType;
	}

	
	public String getSystemStateText(){

		String resultString = "";
		
		if((this.getSystemStateValue() & (1<<sseReady)) != 0){
			resultString = resultString + "Ready, ";
		}
		if((this.getSystemStateValue() & (1<<sseAlertMemory)) != 0){ 
			resultString = resultString + "Alert in memory, ";		
		}
		if((this.getSystemStateValue() & (1<<sseTrouble))!=0){ 
			resultString = resultString + "Trouble, ";		
		}
		if((this.getSystemStateValue() & (1<<sseBypassOn)) != 0){ 
			resultString = resultString + "Bypass On, ";		
		}
		if((this.getSystemStateValue() & (1<<sseLast10SecDelay)) != 0){ 
			resultString = resultString + "Last 10 seconds delay, ";		
		}
		if((this.getSystemStateValue() & (1<<sseZoneEvent)) != 0){ 
			resultString = resultString + "Zone event, ";		
		}
		if((this.getSystemStateValue() & (1<<sseArmDisarmEvent)) != 0){ 
			resultString = resultString + "Arm or Disarm event, ";				
		}
		
		if (resultString.equalsIgnoreCase(""))
			return "WARNING: Unkown System State!";
		else {
			//Remove last , sign.
			resultString = resultString.trim().substring(0, resultString.length()-2);		
			return resultString;
		}
		
	}

	public String getZoneEventZoneText(){
		
		EnumZoneUser ezu = this.getZone();
		
		if(ezu == EnumZoneUser.PMSystem) return "ZoneSystem";
		else if (ezu == EnumZoneUser.PMZone1) return "Zone1";
		else if (ezu == EnumZoneUser.PMZone2) return "Zone2";
		else if (ezu == EnumZoneUser.PMZone3) return "Zone3";
		else if (ezu == EnumZoneUser.PMZone4) return "Zone4";
		else if (ezu == EnumZoneUser.PMZone5) return "Zone5";
		else if (ezu == EnumZoneUser.PMZone6) return "Zone6";
		else if (ezu == EnumZoneUser.PMZone7) return "Zone7";
		else if (ezu == EnumZoneUser.PMZone8) return "Zone8";
		else if (ezu == EnumZoneUser.PMZone9) return "Zone9";
		else if (ezu == EnumZoneUser.PMZone10) return "Zone10";
		else if (ezu == EnumZoneUser.PMZone11) return "Zone11";
		else if (ezu == EnumZoneUser.PMZone12) return "Zone12";
		else if (ezu == EnumZoneUser.PMZone13) return "Zone13";
		else if (ezu == EnumZoneUser.PMZone14) return "Zone14";
		else if (ezu == EnumZoneUser.PMZone15) return "Zone15";
		else if (ezu == EnumZoneUser.PMZone16) return "Zone16";
		else if (ezu == EnumZoneUser.PMZone17) return "Zone17";
		else if (ezu == EnumZoneUser.PMZone18) return "Zone18";
		else if (ezu == EnumZoneUser.PMZone19) return "Zone19";
		else if (ezu == EnumZoneUser.PMZone20) return "Zone20";
		else if (ezu == EnumZoneUser.PMZone21) return "Zone21";
		else if (ezu == EnumZoneUser.PMZone22) return "Zone22";
		else if (ezu == EnumZoneUser.PMZone23) return "Zone23";
		else if (ezu == EnumZoneUser.PMZone24) return "Zone24";
		else if (ezu == EnumZoneUser.PMZone25) return "Zone25";
		else if (ezu == EnumZoneUser.PMZone26) return "Zone26";
		else if (ezu == EnumZoneUser.PMZone27) return "Zone27";
		else if (ezu == EnumZoneUser.PMZone28) return "Zone28";
		else if (ezu == EnumZoneUser.PMZone29) return "Zone29";
		else if (ezu == EnumZoneUser.PMZone30) return "Zone30";
		else if (ezu == EnumZoneUser.PMPGM) return "PGM";
		else if (ezu == EnumZoneUser.PMGSM) return "GSM";
		else if (ezu == EnumZoneUser.PMPowerLink) return "PowerLink";
		else return "WARNING: Unkown Zone!";
		
	}
	
	public String getZoneEventTypeText(){
		
		EnumZoneEventType zet = this.getZoneEventType();
		
		if(zet == EnumZoneEventType.zeNone) return "None";
		else if(zet == EnumZoneEventType.zeTamperAlarm) return "Tamper Alarm";
		else if(zet == EnumZoneEventType.zeTamperRestore) return "Tamper Restore";
		else if(zet == EnumZoneEventType.zeOpen) return "Open";
		else if(zet == EnumZoneEventType.zeClosed) return "Closed";
		else if(zet == EnumZoneEventType.zeViolatedMotion) return "Open"; //"Violated Motion";
		else if(zet == EnumZoneEventType.zePanicAlarm) return "Panic Alarm";
		else if(zet == EnumZoneEventType.zeRFJamming) return "RF Jamming";
		else if(zet == EnumZoneEventType.zeTamperOpen) return "Tamper Ooen";
		else if(zet == EnumZoneEventType.zeCommFailure) return "Communication Failure";
		else if(zet == EnumZoneEventType.zeLineFailure) return "Line Failure";
		else if(zet == EnumZoneEventType.zeFuse) return "Fuse";
		else if(zet == EnumZoneEventType.zeNotActive) return "Not Active";
		else if(zet == EnumZoneEventType.zeLowBattery) return "Low Battery";
		else if(zet == EnumZoneEventType.zeACFailure) return "AC Failure";
		else if(zet == EnumZoneEventType.zeFireAlarm) return "Fire Alarm";
		else if(zet == EnumZoneEventType.zeEmergency) return "Emergency";
		else if(zet == EnumZoneEventType.zeSirenTamper) return "Siren Tamper";
		else if(zet == EnumZoneEventType.zeSirenTamperRestore) return "Siren Tamper Restore";
		else if(zet == EnumZoneEventType.zeSirenLowBatt) return "Siren Low Battery";
		else if(zet == EnumZoneEventType.zeSirenACFail) return "Siren AC Failure";
		else return "WARNING: Unkown Zone Event Type!";		
	}

	public String getSystemStatusText(){
		switch(this.getSystemStatus()){
		case ssDisarm: return "Disarm";
		case ssExitDelay: return "ExitDelay";
		case ssExitDelay2: return "ExitDelay2";
		case ssEntryDelay: return "EntryDelay";
		case ssArmedHome: return "ArmedHome";
		case ssArmedAway: return "ArmedAway";
		case ssUserText : return "UserText";
		case ssDownload: return "Download";
		case ssProgramming: return "Programming";
		case ssInstaller: return "Installer";
		case ssHomeBypass: return "Home bypass";
		case ssAwayBypass: return "Away bypass";
		case ssReady: return "Ready";
		case ssNotReady: return "Not Ready";
		default:
			return "WARNING: Unkown System Status!";
		}
	}
	
	
}
