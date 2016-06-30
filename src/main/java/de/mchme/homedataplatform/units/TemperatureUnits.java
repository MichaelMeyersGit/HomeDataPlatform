package de.mchme.homedataplatform.units;

public class TemperatureUnits {
	
	public static final int KELVIN = 1 ;
	public static final int FAHRENHEIT = 2 ;
	public static final int CELSIUS = 3;
	
	public static boolean isValid(int unit) {
		boolean isValid = false;
		
		if(unit == KELVIN || unit == FAHRENHEIT || unit == CELSIUS) {
			isValid = true;
		}
		
		return isValid;
	}

}
