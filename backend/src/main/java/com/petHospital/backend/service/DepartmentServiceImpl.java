package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.model.Department;
import com.petHospital.backend.model.User;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	 @Autowired
	 DepartmentRepository departmentRepository;
	 @Autowired
	 UserRepository userRepository;
	 
	 public DepartmentDTO retreiveDepartment(Long id) {
		Department department = new Department();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		try {
			department = departmentRepository.findOne(id);
			if (department == null) {
				departmentDTO.setMessage("department does not exist.");
				return departmentDTO;
			}
		}catch(Exception e) {
			departmentDTO.setMessage("failed");
		}
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		departmentDTO.setDescription(department.getDescription());
		departmentDTO.setManagers(department.getManagers());
		departmentDTO.setMessage("success");
		return departmentDTO;
	}

	 public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		if(validateManagers(departmentDTO,department) == false) {
			return departmentDTO;
		}
		try {
			department = departmentRepository.save(department);
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setManagers(department.getManagers());
			departmentDTO.setMessage("success");
		}catch(Exception e){
			departmentDTO.setMessage("failed");
		}
		return departmentDTO;
	}
	
	public DepartmentDTO deleteDepartment(Long id) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		Department department = departmentRepository.findOne(id);
		if(department == null) {
			departmentDTO.setMessage("Department does not exist");
			return departmentDTO;
		}
		try {
			departmentRepository.delete(id);
			departmentDTO.setMessage("success");
		}catch(Exception e){
			departmentDTO.setMessage("failed");
		}
		return departmentDTO;
	}

	public DepartmentDTO editDepartment(DepartmentDTO departmentDTO) {
		Department department = departmentRepository.findOne(departmentDTO.getId());
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		if(validateManagers(departmentDTO,department) == false) {
			return departmentDTO;
		}
		try {
			departmentRepository.save(department);
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setManagers(department.getManagers());
			departmentDTO.setMessage("success");
		}catch(Exception e){
			departmentDTO.setMessage("failed");
		}
		return departmentDTO;
	}
//Validate manager id from DepartmentDTO.getManagers and then set valid manager to department.
	private boolean validateManagers(DepartmentDTO departmentDTO, Department department) {
		List<User> managers = departmentDTO.getManagers();
		List<User> managerEntitys = new ArrayList<User>();
		if (managers != null) {
			for (Iterator<User> it = managers.iterator(); it.hasNext();) {
				User manager = it.next();
				Long managerId = manager.getId();
				manager = userRepository.findOne(managerId);
				if (manager == null) {
					it.remove();
					departmentDTO.setMessage("manager does not exist who's id =" + managerId);
					return false;
				}
				managerEntitys.add(manager);
			}
		}
		department.setManagers(managerEntitys);
		return true;
	}
}
