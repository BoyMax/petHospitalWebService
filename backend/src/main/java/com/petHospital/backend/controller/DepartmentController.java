package com.petHospital.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.DepartmentRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.service.DepartmentService;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	DepartmentRepository departmentRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
		DepartmentDTO response = departmentService.createDepartment(departmentDTO);
		return new ResponseEntity<DepartmentDTO>(response, null, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable("id") String id) {
		DepartmentDTO response = departmentService.deleteDepartment(Long.parseLong(id));
		return new ResponseEntity<DepartmentDTO>(response, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<DepartmentDTO> editDepartment(@RequestBody DepartmentDTO departmentDTO) {
		DepartmentDTO response = departmentService.editDepartment(departmentDTO);
		return new ResponseEntity<DepartmentDTO>(response, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("id") String id) {
		DepartmentDTO response = departmentService.retreiveDepartment(Long.parseLong(id));
		return new ResponseEntity<DepartmentDTO>(response, null, HttpStatus.OK);
	}

}