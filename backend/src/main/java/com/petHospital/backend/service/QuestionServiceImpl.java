package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.*;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.petHospital.backend.dao.QuestionRepository;
import com.petHospital.backend.dto.QuestionDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	 @Autowired
	 QuestionRepository questionRepository;
	 
	 
	 public ResponseDTO<QuestionDTO> retreiveQuestion(Long id) {
		ResponseDTO<QuestionDTO> responseDTO = new ResponseDTO<QuestionDTO>();
		Question question = new Question();
		QuestionDTO questionDTO = new QuestionDTO();
		try {
			question = questionRepository.findOne(id);
			if (question == null) {
				responseDTO.setMessage("Question"+id+"does not exist.");
				responseDTO.setStatus("failed");
				return responseDTO;
			}
		}catch(Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		questionDTO.setId(question.getId());
		questionDTO.setCategory(question.getCategory());
		questionDTO.setUserType(question.getUserType());
		questionDTO.setQuestionType(question.getQuestionType());
		questionDTO.setAskDescription(question.getAskDescription());
		questionDTO.setAdescription(question.getAdescription());
		questionDTO.setBdescription(question.getBdescription());
		questionDTO.setCdescription(question.getCdescription());
		questionDTO.setDdescription(question.getDdescription());
		questionDTO.setAnswer(question.getAnswer());
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(questionDTO);
		return responseDTO;
	}

	 public ResponseDTO<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
		 ResponseDTO<QuestionDTO> responseDTO = new ResponseDTO<QuestionDTO>();
		 Question question = new Question();
		
		question.setAskDescription(questionDTO.getAskDescription());
		question.setAdescription(questionDTO.getAdescription());
		question.setBdescription(questionDTO.getBdescription());
		question.setCdescription(questionDTO.getCdescription());
		question.setDdescription(questionDTO.getDdescription());
		question.setAnswer(questionDTO.getAnswer());
		
		question.setCategory(questionDTO.getCategory());
		question.setUserType(questionDTO.getUserType());
		question.setQuestionType(questionDTO.getQuestionType());
		try {
			question = questionRepository.save(question);
			questionDTO.setId(question.getId());
			questionDTO.setCategory(question.getCategory());
			questionDTO.setUserType(question.getUserType());
			questionDTO.setQuestionType(question.getQuestionType());
			questionDTO.setAskDescription(question.getAskDescription());
			questionDTO.setAdescription(question.getAdescription());
			questionDTO.setBdescription(question.getBdescription());
			questionDTO.setCdescription(question.getCdescription());
			questionDTO.setDdescription(question.getDdescription());
			questionDTO.setAnswer(question.getAnswer());
			
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
			responseDTO.setData(questionDTO);
		}catch(Exception e){
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
		}
		return responseDTO;
	}
	
	public ResponseDTO<QuestionDTO> deleteQuestion(Long id) {
		ResponseDTO<QuestionDTO> responseDTO = new ResponseDTO<QuestionDTO>();
		QuestionDTO questionDTO = new QuestionDTO();
		Question question = questionRepository.findOne(id);
		if(question == null) {
			responseDTO.setMessage("Question "+id+" does not exist");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
		try {
			questionRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(questionDTO);
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}

	public ResponseDTO<QuestionDTO> editQuestion(QuestionDTO questionDTO) {
		ResponseDTO<QuestionDTO> responseDTO = new ResponseDTO<QuestionDTO>();
		Question question = questionRepository.findOne(questionDTO.getId());
		
		question.setAskDescription(questionDTO.getAskDescription());
		question.setAdescription(questionDTO.getAdescription());
		question.setBdescription(questionDTO.getBdescription());
		question.setCdescription(questionDTO.getCdescription());
		question.setDdescription(questionDTO.getDdescription());
		question.setAnswer(questionDTO.getAnswer());
		
		question.setCategory(questionDTO.getCategory());
		question.setUserType(questionDTO.getUserType());
		question.setQuestionType(questionDTO.getQuestionType());
		try {
			question = questionRepository.save(question);
			questionDTO.setId(question.getId());
			questionDTO.setCategory(question.getCategory());
			questionDTO.setUserType(question.getUserType());
			questionDTO.setQuestionType(question.getQuestionType());
			questionDTO.setAskDescription(question.getAskDescription());
			questionDTO.setAdescription(question.getAdescription());
			questionDTO.setBdescription(question.getBdescription());
			questionDTO.setCdescription(question.getCdescription());
			questionDTO.setDdescription(question.getDdescription());
			questionDTO.setAnswer(question.getAnswer());

			
			responseDTO.setMessage("success");
			responseDTO.setData(questionDTO);
			responseDTO.setStatus("success");
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}


	public ResponseDTO<List<QuestionDTO>> listAllQuestion() {
		ResponseDTO<List<QuestionDTO>> responseDTO = new ResponseDTO<List<QuestionDTO>>();
		ArrayList<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		List<Question> question = new ArrayList<Question>();
		try {
			question = (List<Question>) questionRepository.findAll();
		} catch (Exception e) {
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
			return responseDTO;
		}
		for (Question question1 : question) {
			QuestionDTO questionDTO = new QuestionDTO();
			questionDTO.setId(question1.getId());
			questionDTO.setCategory(question1.getCategory());
			questionDTO.setUserType(question1.getUserType());
			questionDTO.setQuestionType(question1.getQuestionType());
			questionDTO.setAskDescription(question1.getAskDescription());
			questionDTO.setAdescription(question1.getAdescription());
			questionDTO.setBdescription(question1.getBdescription());
			questionDTO.setCdescription(question1.getCdescription());
			questionDTO.setDdescription(question1.getDdescription());
			questionDTO.setAnswer(question1.getAnswer());

			
			questionDTOs.add(questionDTO);
		}
		responseDTO.setMessage("status");
		responseDTO.setStatus("success");
		responseDTO.setData(questionDTOs);
		
		return responseDTO;
	}

	@Override
	public ResponseDTO<List<QuestionDTO>> listQuestionByCategory(Long catagoryId) {
		ResponseDTO<List<QuestionDTO>> responseDTO = new ResponseDTO<List<QuestionDTO>>();
		ArrayList<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		List<Question> question = new ArrayList<Question>();
		try {
			question = (List<Question>) questionRepository.getQuestionsByCatagory(catagoryId);
		} catch (Exception e) {
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
			return responseDTO;
		}
		for (Question question1 : question) {
			QuestionDTO questionDTO = new QuestionDTO();
			questionDTO.setId(question1.getId());
			questionDTO.setCategory(question1.getCategory());
			questionDTO.setUserType(question1.getUserType());
			questionDTO.setQuestionType(question1.getQuestionType());
			questionDTO.setAskDescription(question1.getAskDescription());
			questionDTO.setAdescription(question1.getAdescription());
			questionDTO.setBdescription(question1.getBdescription());
			questionDTO.setCdescription(question1.getCdescription());
			questionDTO.setDdescription(question1.getDdescription());
			questionDTO.setAnswer(question1.getAnswer());
			
			questionDTOs.add(questionDTO);
		}
		responseDTO.setMessage("status");
		responseDTO.setStatus("success");
		responseDTO.setData(questionDTOs);
		
		return responseDTO;
	}
	
	
	

	}


	
	// Validate manager id from DepartmentDTO.getManagers and then set valid manager
	// to department.

