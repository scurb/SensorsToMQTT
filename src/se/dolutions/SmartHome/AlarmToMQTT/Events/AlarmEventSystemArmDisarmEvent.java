package se.dolutions.SmartHome.AlarmToMQTT.Events;

import java.util.EventObject;

import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxSimulatedEventArmDisarmEvent;

public class AlarmEventSystemArmDisarmEvent extends EventObject {

	PowerMaxSimulatedEventArmDisarmEvent _armDisarmEvent;
	
	public PowerMaxSimulatedEventArmDisarmEvent getArmDisarmEvent(){
		return _armDisarmEvent;
	}
	
	public AlarmEventSystemArmDisarmEvent(Object source, PowerMaxSimulatedEventArmDisarmEvent armDisarmEvent) {
		super(source);
		
		this._armDisarmEvent = armDisarmEvent;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611433495301988621L;

}
