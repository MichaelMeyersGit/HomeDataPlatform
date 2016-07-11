package de.mchme.homedataplatform;

import org.junit.Assert;
import org.junit.Test;

import de.mchme.homedataplatform.units.TemperatureUnits;
import de.mchme.homedataplatform.utils.SensorStateUtils;

public class SimpleTests {
	
	@Test
	public void testCelsiusRangeFail() {
		
		boolean valid = TemperatureUnits.isInValidRange('C', -300.00);		
		Assert.assertTrue( !valid );
	}
	
	@Test
	public void testFahrenheitSuccess() {
		
		boolean valid = TemperatureUnits.isInValidRange('F', -459.67);		
		Assert.assertTrue(valid);		
	}
	
	@Test
	public void testKelvinFail() {
		boolean valid = TemperatureUnits.isInValidRange('K', -1.0);		
		Assert.assertTrue(!valid);	
	}
	
	@Test
	public void testSensorState1() {
		int state = 0 ;
		
		state = SensorStateUtils.getSensorState(state) ;
		
		Assert.assertTrue(state == SensorStateUtils.SENSOR_OFF_STATE);
	}
	
	@Test
	public void testSensorState2() {
		int state = 1 ;
		
		state = SensorStateUtils.getSensorState(state) ;
		
		Assert.assertTrue(state == SensorStateUtils.SENSOR_ON_STATE);
	}
	
	@Test
	public void testSensorState3() {
		int state = 4 ;
		
		state = SensorStateUtils.getSensorState(state) ;
		
		Assert.assertTrue(state == SensorStateUtils.SENSOR_UNDEFINED); 
	}

}
