package de.mchme.homedataplatform.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimpleSensorStateData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093176115036046192L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	
	private Integer identifier ;
	
	private Integer state ;
	
	private Integer sensorType ;
	
	private Date logDate ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

    

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSensorType() {
		return sensorType;
	}

	public void setSensorType(Integer sensorType) {
		this.sensorType = sensorType;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	
	
	
}
