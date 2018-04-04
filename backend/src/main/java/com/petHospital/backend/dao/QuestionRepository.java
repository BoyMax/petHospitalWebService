package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

	@Query(value = "select q from Question q JOIN q.category c where c.id=:id")  
    List<Question> getQuestionsByCatagory(@Param("id") Long id);  
}
