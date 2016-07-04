package de.mchme.homedataplatform.units;

public class TemperatureUnits {
	
	public static final char KELVIN = 'K' ;
	public static final char FAHRENHEIT = 'F' ;
	public static final char CELSIUS = 'C' ;
	
	public static boolean isValid(char unit) {
		boolean isValid = false;
		
		if(unit == KELVIN || unit == FAHRENHEIT || unit == CELSIUS) {
			isValid = true;
		}
		
		return isValid;
	}
	
	public static boolean isInValidRange(char unit, double value) {
		boolean isValid = false;
		
		if ( unit == KELVIN) {
			if (value >= 0) {
				isValid = true;
			}
		} else if ( unit == FAHRENHEIT ) {
			if(value >= -459.67) {
				isValid = true;
			}
		} else if ( unit == CELSIUS ) {
			if (value >= -273.15) {
				isValid = true;
			}
		}
		
		return isValid ;
		
	}

}
