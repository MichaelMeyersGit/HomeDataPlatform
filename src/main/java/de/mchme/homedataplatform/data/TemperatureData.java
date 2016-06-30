package de.mchme.homedataplatform.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TemperatureData  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6848712046815973749L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	
	private int identifier ;
	
	private double temperature ;
	
	private int unit ;
	
	private Date logDate ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	

}
