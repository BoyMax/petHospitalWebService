package com.petHospital.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dto.IllnessDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.IllnessService;

@RestController
@RequestMapping(path = "/illness")
public class IllnessController {

	@Autowired
	IllnessService illnessService;
	@Autowired
	IllnessRepository illnessRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<IllnessDTO>> addIllness(@RequestBody IllnessDTO IllnessDTO) {
		ResponseDTO<IllnessDTO> response = illnessService.createIllness(IllnessDTO);	
		return new ResponseEntity<ResponseDTO<IllnessDTO>>(response,  HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<IllnessDTO>> deleteIllness(@PathVariable("id") String id) {
		ResponseDTO<IllnessDTO> response = illnessService.deleteIllness(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<IllnessDTO>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<IllnessDTO>> editIllness(@RequestBody IllnessDTO illnessDTO) {
		ResponseDTO<IllnessDTO> response = illnessService.editIllness(illnessDTO);
		return new ResponseEntity<ResponseDTO<IllnessDTO>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<IllnessDTO>> getIllnessById(@PathVariable("id") String id) {
		ResponseDTO<IllnessDTO> response = illnessService.retreiveIllnessById(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<IllnessDTO>>(response,  HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<IllnessDTO>>> getIllnessByName(@PathVariable("name") String name) {
		ResponseDTO<List<IllnessDTO>> response = illnessService.retreiveIllnessByName(name);
		return new ResponseEntity<ResponseDTO<List<IllnessDTO>>>(response,  HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<IllnessDTO>>> listIllnesss() {
		ResponseDTO<List<IllnessDTO>> response = illnessService.listAllIllness();
		return new ResponseEntity<ResponseDTO<List<IllnessDTO>>>(response,  HttpStatus.OK);
	}

}