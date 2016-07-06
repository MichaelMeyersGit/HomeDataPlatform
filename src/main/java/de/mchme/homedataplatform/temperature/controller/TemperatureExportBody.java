package de.mchme.homedataplatform.temperature.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TemperatureExportBody {
	
	
	private int identifier ;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startdate ;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
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
