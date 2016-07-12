package de.mchme.homedataplatform.utils;

import java.util.Calendar;
import java.util.Date;

public class RulesUtils {
	
	/**
	 * will return the Date of now - a dedicated timespan in Minutes 
	 *  
	 * @param timespan
	 * @return
	 */
	public static Date getTimespanDate(int timespan) {
		 Calendar cal = Calendar.getInstance(); 
	     cal.add(Calendar.MINUTE, -timespan);
	     
	     Date startdate = cal.getTime();
	     
	     return startdate ;
	}

}
