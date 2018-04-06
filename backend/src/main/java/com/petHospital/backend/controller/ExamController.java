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

import com.petHospital.backend.dao.ExamRepository;
import com.petHospital.backend.dto.ExamDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.ExamService;

@CrossOrigin(origins = "*", maxAge = 3600,methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping(path = "/Exam")
public class ExamController{

	@Autowired
	ExamService examService;
	@Autowired
	ExamRepository examRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<ExamDTO>> addExam(@RequestBody ExamDTO examDTO) {
		ResponseDTO<ExamDTO> response = examService.createExam(examDTO);	
		return new ResponseEntity<ResponseDTO<ExamDTO>>(response, getHttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<ExamDTO>> deleteExam(@PathVariable("id") String id) {
		ResponseDTO<ExamDTO> response = examService.deleteExam(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<ExamDTO>>(response, getHttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<ExamDTO>> editExam(@RequestBody ExamDTO examDTO) {
		ResponseDTO<ExamDTO> response = examService.editExam(examDTO);
		return new ResponseEntity<ResponseDTO<ExamDTO>>(response, getHttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<ExamDTO>> getExam(@PathVariable("id") String id) {
		ResponseDTO<ExamDTO> response = examService.retreiveExam(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<ExamDTO>>(response, getHttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<ExamDTO>>> listAllExam() {
		ResponseDTO<List<ExamDTO>> response = examService.listAllExam();
		return new ResponseEntity<ResponseDTO<List<ExamDTO>>>(response, getHttpHeaders(), HttpStatus.OK);
	}

}