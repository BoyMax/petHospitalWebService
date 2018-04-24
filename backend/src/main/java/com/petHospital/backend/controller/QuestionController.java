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

import com.petHospital.backend.dao.QuestionRepository;
import com.petHospital.backend.dto.IllnessDTO;
import com.petHospital.backend.dto.QuestionDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.QuestionService;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	@Autowired
	QuestionRepository questionRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuestionDTO>> addQuestion(@RequestBody QuestionDTO questionDTO) {
		ResponseDTO<QuestionDTO> response = questionService.createQuestion(questionDTO);	
		return new ResponseEntity<ResponseDTO<QuestionDTO>>(response,  HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<QuestionDTO>> deleteQuestion(@PathVariable("id") String id) {
		ResponseDTO<QuestionDTO> response = questionService.deleteQuestion(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<QuestionDTO>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<QuestionDTO>> editQuestion(@RequestBody QuestionDTO questionDTO) {
		ResponseDTO<QuestionDTO> response = questionService.editQuestion(questionDTO);
		return new ResponseEntity<ResponseDTO<QuestionDTO>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<QuestionDTO>> getQuestion(@PathVariable("id") String id) {
		ResponseDTO<QuestionDTO> response = questionService.retreiveQuestion(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<QuestionDTO>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<QuestionDTO>>> listAllQuestion() {
		ResponseDTO<List<QuestionDTO>> response = questionService.listAllQuestion();
		return new ResponseEntity<ResponseDTO<List<QuestionDTO>>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/getByCategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<QuestionDTO>>> listQuestionByCategory(@PathVariable("id") Long id) {
		ResponseDTO<List<QuestionDTO>> response = questionService.listQuestionByCategory(id);
		return new ResponseEntity<ResponseDTO<List<QuestionDTO>>>(response,  HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<List<QuestionDTO>>> getQuestionByName(@RequestBody QuestionDTO questionDTO) {
		ResponseDTO<List<QuestionDTO>> response = questionService.searchQuestionByName(questionDTO.getAskDescription());
		return new ResponseEntity<ResponseDTO<List<QuestionDTO>>>(response,  HttpStatus.OK);
	}
	

}