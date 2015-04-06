package se.dolutions.SmartHome.AlarmToMQTT.Events;

import java.util.EventObject;

import se.dolutions.SmartHome.AlarmToMQTT.Visonic.PowerMaxGeneralEventEvent;


public class AlarmEventZoneEvent extends EventObject {

	PowerMaxGeneralEventEvent _pmEvent;
	
	public PowerMaxGeneralEventEvent getPowerMaxEvent() {
		return _pmEvent;
	}

	public AlarmEventZoneEvent(Object source, PowerMaxGeneralEventEvent pmEvent) {
		super(source);
		this._pmEvent = pmEvent;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466465047610874283L;

}
