package com.petHospital.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	 @Autowired
	 DepartmentRepository departmentRepository;
	 
	 public DepartmentDTO retreiveDepartment(Long id) {
		Department department = new Department();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		try {
			department = departmentRepository.findOne(id);
			departmentDTO.setMessage("success");
		}catch(Exception e) {
			departmentDTO.setMessage("failed");
		}
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		departmentDTO.setDescription(department.getDescription());
		departmentDTO.setManagers(department.getManagers());
		return departmentDTO;
	}

	 public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		department.setManagers(departmentDTO.getManagers());
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
		department.setManagers(departmentDTO.getManagers());
		try {
			departmentRepository.save(department);
			departmentDTO.setMessage("success");
		}catch(Exception e){
			departmentDTO.setMessage("failed");
		}
		return departmentDTO;
	}

	
}
