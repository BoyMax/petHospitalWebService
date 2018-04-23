package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.VaccineDTO;

public interface VaccineService {
	public ResponseDTO<VaccineDTO> retreiveVaccine(Long id);
	public ResponseDTO<VaccineDTO> createVaccine(VaccineDTO medicine);
	public ResponseDTO<VaccineDTO> deleteVaccine(Long id);
	public ResponseDTO<VaccineDTO> editVaccine(VaccineDTO medicine);
	public ResponseDTO<List<VaccineDTO>> listVaccines();
	public ResponseDTO<List<VaccineDTO>> searchVaccines(String name);
}
