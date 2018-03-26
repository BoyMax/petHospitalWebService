package com.petHospital.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.DepartmentService;

@CrossOrigin(origins = "*", maxAge = 3600,methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping(path = "/department", method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<DepartmentDTO>> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
		ResponseDTO<DepartmentDTO> response = departmentService.createDepartment(departmentDTO);	
		return new ResponseEntity<ResponseDTO<DepartmentDTO>>(response, null, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<DepartmentDTO>> deleteDepartment(@PathVariable("id") String id) {
		ResponseDTO<DepartmentDTO> response = departmentService.deleteDepartment(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<DepartmentDTO>>(response, null, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<DepartmentDTO>> editDepartment(@RequestBody DepartmentDTO departmentDTO) {
		ResponseDTO<DepartmentDTO> response = departmentService.editDepartment(departmentDTO);
		return new ResponseEntity<ResponseDTO<DepartmentDTO>>(response, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<DepartmentDTO>> getDepartment(@PathVariable("id") String id) {
		ResponseDTO<DepartmentDTO> response = departmentService.retreiveDepartment(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<DepartmentDTO>>(response, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<DepartmentDTO>>> listDepartments() {
		ResponseDTO<List<DepartmentDTO>> response = departmentService.listAllDepartment();
		return new ResponseEntity<ResponseDTO<List<DepartmentDTO>>>(response, null, HttpStatus.OK);
	}

}