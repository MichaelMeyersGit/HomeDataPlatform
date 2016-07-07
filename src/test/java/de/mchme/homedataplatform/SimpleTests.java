package de.mchme.homedataplatform;

import org.junit.Assert;
import org.junit.Test;

import de.mchme.homedataplatform.units.TemperatureUnits;

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

}
