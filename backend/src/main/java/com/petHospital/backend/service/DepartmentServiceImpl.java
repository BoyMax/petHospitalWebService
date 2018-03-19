package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Department;
import com.petHospital.backend.model.User;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	 @Autowired
	 DepartmentRepository departmentRepository;
	 @Autowired
	 UserRepository userRepository;
	 
	 public ResponseDTO<DepartmentDTO> retreiveDepartment(Long id) {
		ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
		Department department = new Department();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		try {
			department = departmentRepository.findOne(id);
			if (department == null) {
				responseDTO.setMessage("Department"+id+"does not exist.");
				responseDTO.setStatus("failed");
				return responseDTO;
			}
		}catch(Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		departmentDTO.setDescription(department.getDescription());
		departmentDTO.setManagers(department.getManagers());
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(departmentDTO);
		return responseDTO;
	}

	 public ResponseDTO<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO) {
		 ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
		Department department = new Department();
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		if(validateManagers(departmentDTO,department,responseDTO) == false) {
			return responseDTO;
		}
		try {
			department = departmentRepository.save(department);
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setManagers(department.getManagers());
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
			responseDTO.setData(departmentDTO);
		}catch(Exception e){
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
		}
		return responseDTO;
	}
	
	public ResponseDTO<DepartmentDTO> deleteDepartment(Long id) {
		ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		Department department = departmentRepository.findOne(id);
		if(department == null) {
			responseDTO.setMessage("Department "+id+" does not exist");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
		try {
			departmentRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(departmentDTO);
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}

	public ResponseDTO<DepartmentDTO> editDepartment(DepartmentDTO departmentDTO) {
		ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
		Department department = departmentRepository.findOne(departmentDTO.getId());
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		if(validateManagers(departmentDTO,department,responseDTO) == false) {
			return responseDTO;
		}
		try {
			departmentRepository.save(department);
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setManagers(department.getManagers());
			responseDTO.setMessage("success");
			responseDTO.setData(departmentDTO);
			responseDTO.setStatus("success");
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}


	public ResponseDTO<List<DepartmentDTO>> listAllDepartment() {
		ResponseDTO<List<DepartmentDTO>> responseDTO = new ResponseDTO<List<DepartmentDTO>>();
		ArrayList<DepartmentDTO> departmentsDTOs = new ArrayList<DepartmentDTO>();
		List<Department> departments = new ArrayList<Department>();
		try {
			departments = (List<Department>) departmentRepository.findAll();
		} catch (Exception e) {
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
			return responseDTO;
		}
		for (Department department : departments) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setManagers(department.getManagers());
			departmentsDTOs.add(departmentDTO);
		}
		responseDTO.setMessage("status");
		responseDTO.setStatus("success");
		responseDTO.setData(departmentsDTOs);
		
		return responseDTO;
	}
	
	// Validate manager id from DepartmentDTO.getManagers and then set valid manager
	// to department.
	private boolean validateManagers(DepartmentDTO departmentDTO, Department department, ResponseDTO<DepartmentDTO> responseDTO) {
		List<User> managers = departmentDTO.getManagers();
		List<User> managerEntitys = new ArrayList<User>();
		if (managers != null) {
			for (Iterator<User> it = managers.iterator(); it.hasNext();) {
				User manager = it.next();
				Long managerId = manager.getId();
				manager = userRepository.findOne(managerId);
				if (manager == null) {
					it.remove();
					responseDTO.setError_code("404");
					responseDTO.setStatus("failed");
					responseDTO.setMessage("manager does not exist who's id =" + managerId);
					return false;
				}
				managerEntitys.add(manager);
			}
		}
		department.setManagers(managerEntitys);
		return true;
	}
	
}
