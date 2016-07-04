package de.mchme.homedataplatform.controler;

import java.util.Date;

public class TemperatureExportBody {
	
	
	private int identifier ;
	private Date startdate ;
	private Date enddate ;
	
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}
