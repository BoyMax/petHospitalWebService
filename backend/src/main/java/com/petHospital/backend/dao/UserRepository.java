package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User getUserByName(String userName);
	
	@Query(value = "select u from User u where u.name like %:name%")
	List<User> search(@Param("name") String name);
}
