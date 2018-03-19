package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;

public interface DepartmentService {
	public ResponseDTO<DepartmentDTO> retreiveDepartment(Long id);
	public ResponseDTO<DepartmentDTO> createDepartment(DepartmentDTO user);
	public ResponseDTO<DepartmentDTO> deleteDepartment(Long id);
	public ResponseDTO<DepartmentDTO> editDepartment(DepartmentDTO user);
	public ResponseDTO<List<DepartmentDTO>> listAllDepartment();
}
