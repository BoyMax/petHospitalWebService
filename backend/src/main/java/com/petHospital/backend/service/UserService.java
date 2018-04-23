package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.UserDTO;

public interface UserService {
	public ResponseDTO<UserDTO> retreiveUser(String userName);
	public ResponseDTO<UserDTO> createUser(UserDTO user);
	public ResponseDTO<UserDTO> deleteUser(Long id);
	public ResponseDTO<UserDTO> editUser(UserDTO user);
	public ResponseDTO<UserDTO> validateUser(String name,String password);
	public ResponseDTO<List<UserDTO>> listAllUsers();
	public ResponseDTO<List<UserDTO>> searchUsers(String name);
}
