package de.mchme.homedataplatform.repositories;

import org.springframework.data.repository.CrudRepository;

import de.mchme.homedataplatform.data.PressureData;

public interface PressureRepository extends CrudRepository<PressureData, Long>{

}
