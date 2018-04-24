package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Question;
import com.petHospital.backend.model.Vaccine;

public interface QuestionRepository extends CrudRepository<Question, Long>{

	@Query(value = "select q from Question q JOIN q.category c where c.id=:id")  
    List<Question> listQuestionsByCategory(@Param("id") Long categoryId);
	
	@Query(value = "select v from Question v where v.askDescription like %:askDescription%")
	List<Question> search(@Param("askDescription") String askDescription);
}

