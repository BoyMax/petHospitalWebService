package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Illness;

public interface IllnessRepository extends CrudRepository<Illness, Long>{

}