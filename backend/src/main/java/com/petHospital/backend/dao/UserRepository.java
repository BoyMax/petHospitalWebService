package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User getUserByName(String userName);
}
