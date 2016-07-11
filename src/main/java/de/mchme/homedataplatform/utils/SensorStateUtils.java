package de.mchme.homedataplatform.utils;

public class SensorStateUtils {
	
	public static final int SENSOR_OFF_STATE = 0 ;
	public static final int SENSOR_ON_STATE = 1;
	public static final int SENSOR_UNDEFINED = 100 ;
	
	public static int getSensorState(int state) {
		int mystate = state ;
		
		if(state != SENSOR_OFF_STATE && state != SENSOR_ON_STATE ) {
			mystate = SENSOR_UNDEFINED ;
		}
		
		return mystate ;
	}

}
