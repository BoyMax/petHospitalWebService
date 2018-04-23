package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.IllnessDTO;
import com.petHospital.backend.dto.ResponseDTO;

public interface IllnessService {
	public ResponseDTO<IllnessDTO> retreiveIllnessById(Long id);
	public ResponseDTO<List<IllnessDTO>> retreiveIllnessByName(String illnessName);
	public ResponseDTO<IllnessDTO> createIllness(IllnessDTO user);
	public ResponseDTO<IllnessDTO> deleteIllness(Long id);
	public ResponseDTO<IllnessDTO> editIllness(IllnessDTO illnessDTO);
	public ResponseDTO<List<IllnessDTO>> listAllIllness();
}
