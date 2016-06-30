package de.mchme.homedataplatform.repositories;

import org.springframework.data.repository.CrudRepository;

import de.mchme.homedataplatform.data.TemperatureData;

public interface TemperatureRepository extends CrudRepository<TemperatureData, Long>{

}
