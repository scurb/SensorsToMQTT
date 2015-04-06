package se.dolutions.SmartHome.AlarmToMQTT.Events;

import java.util.EventObject;

import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxSimulatedEventSystemInformation;

public class AlarmEventSystemInformation extends EventObject {

	PowerMaxSimulatedEventSystemInformation _systemInfo;
	
	public PowerMaxSimulatedEventSystemInformation getSystemInformation(){
		return _systemInfo;
	}
	
	public AlarmEventSystemInformation(Object source, PowerMaxSimulatedEventSystemInformation systemInfo) {
		super(source);
		// TODO Auto-generated constructor stub
		this._systemInfo = systemInfo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7668775757454297125L;

}
