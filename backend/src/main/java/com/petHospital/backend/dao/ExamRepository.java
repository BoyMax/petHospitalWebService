package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long>{
}