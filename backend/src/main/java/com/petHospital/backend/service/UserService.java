package com.petHospital.backend.service;

import com.petHospital.backend.dto.UserDTO;

public interface UserService {
	public UserDTO retreiveUser(String userName);
	public UserDTO createUser(UserDTO user);
	public UserDTO deleteUser(Long id);
	public UserDTO editUser(UserDTO user);
	public UserDTO validateUser(String name,String password);
}
