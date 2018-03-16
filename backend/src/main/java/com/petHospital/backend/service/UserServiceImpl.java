package com.petHospital.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.UserDTO;
import com.petHospital.backend.model.Department;
import com.petHospital.backend.model.User;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 @Autowired
	 DepartmentRepository departmentRepository;
	 
	public UserDTO retreiveUser(String userName) {
		User user = new User();
		UserDTO userDTO = new UserDTO();
		try {
			user = userRepository.getUserByName(userName);
			if (user == null) {
				userDTO.setMessage("user does not exist.");
				return userDTO;
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
			userDTO.setMessage("success");
		} catch (Exception e) {
			userDTO.setMessage("failed");
		}
		return userDTO;
	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		Department department = null;
		if(userDTO.getDepartment() != null && userDTO.getDepartment().getId() != null) {
			department = departmentRepository.findOne(userDTO.getDepartment().getId());
			user.setDepartment(department);
		}
		try {
			user = userRepository.save(user);
			userDTO.setId(user.getId());
			if(department != null) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(department.getId());
				departmentDTO.setName(department.getName());
				departmentDTO.setDescription(department.getDescription());
				userDTO.setDepartment(departmentDTO);
			}
			userDTO.setMessage("success");
		}catch(Exception e){
			userDTO.setMessage("failed:" + e.getMessage());
		}
		return userDTO;
	}

	public UserDTO validateUser(String name,String password) {
		User user = userRepository.getUserByName(name);
		UserDTO userDTO = new UserDTO();
		if(user == null){
			userDTO.setMessage("User does not exist.");
		}
		else if(user.getPassword().equals(password)) {
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
		User user = userRepository.findOne(id);
		if(user == null) {
			userDTO.setMessage("User does not exist");
			return userDTO;
		}
		try {
			// 被维护表删除关联外键
			for(Department department : user.getDepartments()) {
				department.getManagers().remove(user);
				departmentRepository.save(department);
			}
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
		Department department = null;
		if(userDTO.getDepartment() != null && userDTO.getDepartment().getId() != null) {
			department = departmentRepository.findOne(userDTO.getDepartment().getId());
			if(department == null) {
				userDTO.setMessage("Department does not exist");
				return userDTO;
			}
			user.setDepartment(department);
			
		}
		try {
			user = userRepository.save(user);
			userDTO.setId(user.getId());
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			userDTO.setDepartment(departmentDTO);
			userDTO.setMessage("success");
		}catch(Exception e){
			userDTO.setMessage("failed"+e.getMessage());
		}
		return userDTO;
	}
}
