package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long>{
}
