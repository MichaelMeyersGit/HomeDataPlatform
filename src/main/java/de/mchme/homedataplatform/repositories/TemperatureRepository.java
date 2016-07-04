package de.mchme.homedataplatform.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.mchme.homedataplatform.data.TemperatureData;

public interface TemperatureRepository extends CrudRepository<TemperatureData, Long>{
	
	List<TemperatureData> findByIdentifierAndLogDateBetween(int identifier, Date startdate, Date enddate) ;

}
