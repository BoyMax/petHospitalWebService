package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.MedicineDTO;
import com.petHospital.backend.dto.ResponseDTO;

public interface MedicineService {
	public ResponseDTO<MedicineDTO> retreiveMedicine(Long id);
	public ResponseDTO<MedicineDTO> createMedicine(MedicineDTO medicine);
	public ResponseDTO<MedicineDTO> deleteMedicine(Long id);
	public ResponseDTO<MedicineDTO> editMedicine(MedicineDTO medicine);
	public ResponseDTO<List<MedicineDTO>> listIllnesses();
}
