package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, Long>{
}
