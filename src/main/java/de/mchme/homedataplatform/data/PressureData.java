package de.mchme.homedataplatform.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.mchme.homedataplatform.utils.RestUtils;

@Entity
public class PressureData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4951266453534483137L;
	
	
	
	
protected Integer identifier ;
	
	protected Double pressure ;
	
	protected char unit ;
	
	@JsonFormat(pattern=RestUtils.JSON_DATE_FORMAT)
	protected Date logDate ;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	
	public char getUnit() {
		return unit;
	}

	public void setUnit(char unit) {
		this.unit = unit;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	
}
