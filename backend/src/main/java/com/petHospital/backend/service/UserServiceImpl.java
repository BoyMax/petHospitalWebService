package com.petHospital.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.UserDTO;
import com.petHospital.backend.model.User;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 
	public UserDTO retreiveUser(String userName) {
		User user = new User();
		UserDTO userDTO = new UserDTO();
		try {
			user = userRepository.getUserByName(userName);
			userDTO.setMessage("success");
		}catch(Exception e) {
			userDTO.setMessage("failed");
		}
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setRole(user.getRole());
		return userDTO;
	}

	public UserDTO createUser(String name, String password) {
		User user = new User();
		UserDTO userDTO = new UserDTO();
		user.setName(name);
		user.setPassword(password);
		try {
			user = userRepository.save(user);
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			userDTO.setMessage("success");
		}catch(Exception e){
			userDTO.setMessage("failed");
		}
		return userDTO;
	}

	public UserDTO validateUser(String name,String password) {
		User user = userRepository.getUserByName(name);
		UserDTO userDTO = new UserDTO();
		if(user == null){
			userDTO.setMessage("User does not exist.");
		}
		else if(user.getPassword() != password) {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			userDTO.setMessage("password is not correct.");
		}
		else {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			userDTO.setMessage("success");
		}
		return userDTO;
	}

	public UserDTO deleteUser(Long id) {
		UserDTO userDTO = new UserDTO();
		try {
			userRepository.delete(id);
			userDTO.setMessage("success");
		}catch(Exception e){
			userDTO.setMessage("failed");
		}
		return userDTO;
	}

	public UserDTO editUser(UserDTO userDTO) {
		User user = userRepository.findOne(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setStatus(userDTO.getStatus());
		try {
			userRepository.save(user);
			userDTO.setMessage("success");
		}catch(Exception e){
			userDTO.setMessage("failed");
		}
		return userDTO;
	}
}
