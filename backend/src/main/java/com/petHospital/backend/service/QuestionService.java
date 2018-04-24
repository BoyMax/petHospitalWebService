package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.QuestionDTO;
import com.petHospital.backend.dto.ResponseDTO;

public interface QuestionService {
	public ResponseDTO<QuestionDTO> retreiveQuestion(Long id);
	public ResponseDTO<QuestionDTO> createQuestion(QuestionDTO user);
	public ResponseDTO<QuestionDTO> deleteQuestion(Long id);
	public ResponseDTO<QuestionDTO> editQuestion(QuestionDTO user);
	public ResponseDTO<List<QuestionDTO>> listAllQuestion();
	public ResponseDTO<List<QuestionDTO>> listQuestionByCategory(Long id);
	public ResponseDTO<List<QuestionDTO>> searchQuestionByName(String questionname);

}

