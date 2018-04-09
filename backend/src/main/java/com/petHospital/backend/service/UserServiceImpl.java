package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.UserDTO;
import com.petHospital.backend.model.Department;
import com.petHospital.backend.model.User;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 @Autowired
	 DepartmentRepository departmentRepository;
	 
	public ResponseDTO<UserDTO> retreiveUser(String userName) {
		ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
		User user = new User();
		UserDTO userDTO = new UserDTO();
//		try {
			user = userRepository.getUserByName(userName);
			if (user == null) {
				responseDTO.setMessage("User "+userName+" does not exist.");
				responseDTO.setStatus("failed");
				responseDTO.setError_code("404");
				return responseDTO;
			}
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			userDTO.setStatus(user.getStatus());
			Department department = user.getDepartment();
			if (department != null) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(department.getId());
				departmentDTO.setName(department.getName());
				departmentDTO.setDescription(department.getDescription());
				userDTO.setDepartment(departmentDTO);
			}
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(userDTO);
//		} catch (Exception e) {
//			responseDTO.setMessage("failed");
//			responseDTO.setStatus(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<UserDTO> createUser(UserDTO userDTO) {
		ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
		User user = new User();
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		Department department = null;
		if(userDTO.getDepartment() != null && userDTO.getDepartment().getId() != null) {
			department = departmentRepository.findOne(userDTO.getDepartment().getId());
			user.setDepartment(department);
		}
//		try {
			user = userRepository.save(user);
			userDTO.setId(user.getId());
			if(department != null) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(department.getId());
				departmentDTO.setName(department.getName());
				departmentDTO.setDescription(department.getDescription());
				userDTO.setDepartment(departmentDTO);
			}
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(userDTO);
//		}catch(Exception e){
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<UserDTO> validateUser(String name,String password) {
		ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
		User user = userRepository.getUserByName(name);
		UserDTO userDTO = new UserDTO();
		if(user == null){
			responseDTO.setMessage("User "+name+" does not exist.");
			responseDTO.setStatus("failed");
			responseDTO.setError_code("404");
			return responseDTO;
		}
		else if(!user.getPassword().equals(password)) {
			responseDTO.setMessage("password is not correct.");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
		else {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(userDTO);
		}
		return responseDTO;
	}

	public ResponseDTO<UserDTO> deleteUser(Long id) {
		ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
		UserDTO userDTO = new UserDTO();
		User user = userRepository.findOne(id);
		if(user == null) {
			responseDTO.setMessage("User "+id+" does not exist.");
			responseDTO.setStatus("failed");
			responseDTO.setError_code("404");
			return responseDTO;
		}
//		try {
			// 被维护表删除关联外键
			for(Department department : user.getDepartments()) {
				department.getManagers().remove(user);
				departmentRepository.save(department);
			}
			userRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(userDTO);
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
		return responseDTO;
	}

	public ResponseDTO<UserDTO> editUser(UserDTO userDTO) {
		ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
		User user = userRepository.findOne(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setStatus(userDTO.getStatus());
		Department department = null;
		if(userDTO.getDepartment() != null && userDTO.getDepartment().getId() != null) {
			department = departmentRepository.findOne(userDTO.getDepartment().getId());
			if(department == null) {
				responseDTO.setMessage("Department does not exist.");
				responseDTO.setStatus("failed");
				responseDTO.setError_code("404");
				return responseDTO;
			}
			user.setDepartment(department);
		}
		else {
			user.setDepartment(null);
		}
//		try {
			user = userRepository.save(user);
			userDTO.setId(user.getId());
			if(user.getDepartment() != null) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(department.getId());
				departmentDTO.setName(department.getName());
				departmentDTO.setDescription(department.getDescription());
				userDTO.setDepartment(departmentDTO);
			}
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(userDTO);
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//			return responseDTO;
//		}
		return responseDTO;
	}
	
	public ResponseDTO<List<UserDTO>> listAllUsers() {
		ResponseDTO<List<UserDTO>> responseDTO = new ResponseDTO<List<UserDTO>>();
		ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
		List<User> users = new ArrayList<User>();
//		try {
			users = (List<User>) userRepository.findAll();
//		} catch (Exception e) {
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//			return responseDTO;
//		}
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
			userDTO.setStatus(user.getStatus());
			Department department = user.getDepartment();
			if (department != null) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(user.getDepartment().getId());
				departmentDTO.setDescription(user.getDepartment().getDescription());
				departmentDTO.setName(user.getDepartment().getName());
				userDTO.setDepartment(departmentDTO);
			}
			userDTOs.add(userDTO);
		}
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(userDTOs);
		
		return responseDTO;
	}
}
