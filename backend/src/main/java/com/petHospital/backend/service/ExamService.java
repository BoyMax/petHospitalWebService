package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.ExamDTO;

public interface ExamService {
	public ResponseDTO<ExamDTO> retreiveExam(Long id);
	public ResponseDTO<ExamDTO> createExam(ExamDTO exam);
	public ResponseDTO<ExamDTO> deleteExam(Long id);
	public ResponseDTO<ExamDTO> editExam(ExamDTO exam);
	public ResponseDTO<List<ExamDTO>> listAllExam();
}

