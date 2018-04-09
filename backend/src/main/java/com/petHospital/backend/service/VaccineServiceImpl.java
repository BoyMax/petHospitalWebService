package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dao.VaccineRepository;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.VaccineDTO;
import com.petHospital.backend.model.Illness;
import com.petHospital.backend.model.Vaccine;

@Service
public class VaccineServiceImpl implements VaccineService{

	@Autowired
	VaccineRepository vaccineRepository;
	@Autowired
	IllnessRepository illnessRepository;

	public ResponseDTO<VaccineDTO> retreiveVaccine(Long id) {
		ResponseDTO<VaccineDTO> responseDTO = new ResponseDTO<VaccineDTO>();
		Vaccine vaccine = new Vaccine();
		VaccineDTO vaccineDTO = new VaccineDTO();
//		try {
			vaccine = vaccineRepository.findOne(id);
			if (vaccine == null) {
				responseDTO.setMessage("Vaccine "+id.toString()+" does not exist.");
				responseDTO.setStatus("failed");
				responseDTO.setError_code("404");
				return responseDTO;
			}
			vaccineDTO.setId(vaccine.getId());
			vaccineDTO.setName(vaccine.getName());
			vaccineDTO.setPrice(vaccine.getPrice());
			vaccineDTO.setDescription(vaccine.getDescription());
			vaccineDTO.setProductionDate(vaccine.getProductionDate());
			vaccineDTO.setExpirationDate(vaccine.getExpirationDate());
			vaccineDTO.setIllness(vaccine.getIllness());
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(vaccineDTO);
//		} catch (Exception e) {
//			responseDTO.setMessage("failed");
//			responseDTO.setStatus(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<VaccineDTO> createVaccine(VaccineDTO vaccineDTO) {
		ResponseDTO<VaccineDTO> responseDTO = new ResponseDTO<VaccineDTO>();
		Vaccine vaccine = new Vaccine();
		vaccine.setName(vaccineDTO.getName());
		vaccine.setDescription(vaccineDTO.getDescription());
		vaccine.setPrice(vaccineDTO.getPrice());
		vaccine.setProductionDate(vaccineDTO.getProductionDate());
		vaccine.setExpirationDate(vaccineDTO.getExpirationDate());
		if(validateIllness(vaccineDTO, vaccine, responseDTO) == false) {
			return responseDTO;
		}
//		try {
			vaccine = vaccineRepository.save(vaccine);
			vaccineDTO.setId(vaccine.getId());
			vaccineDTO.setDescription(vaccine.getDescription());
			vaccineDTO.setName(vaccine.getName());
			vaccineDTO.setPrice(vaccine.getPrice());
			vaccineDTO.setProductionDate(vaccine.getProductionDate());
			vaccineDTO.setExpirationDate(vaccine.getExpirationDate());
			vaccineDTO.setIllness(vaccine.getIllness());
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(vaccineDTO);
//		}catch(Exception e){
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<VaccineDTO> deleteVaccine(Long id) {
		ResponseDTO<VaccineDTO> responseDTO = new ResponseDTO<VaccineDTO>();
		VaccineDTO vaccineDTO = new VaccineDTO();
		Vaccine vaccine;
//		try {
			vaccine = vaccineRepository.findOne(id);
//		}catch (Exception e) {
//			responseDTO.setMessage("Cannot find medicine with "+id.toString()+ e.getMessage());
//			responseDTO.setStatus("failed");
//			return responseDTO;
//		}
		if(vaccine == null) {
			responseDTO.setMessage("Department "+id+" does not exist");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
//		try {
			vaccineRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			vaccineDTO.setId(id);
			responseDTO.setData(vaccineDTO);
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
		return responseDTO;
	}

	public ResponseDTO<VaccineDTO> editVaccine(VaccineDTO vaccineDTO) {
		ResponseDTO<VaccineDTO> responseDTO = new ResponseDTO<VaccineDTO>();
		Vaccine vaccine;
//		try {
			vaccine = vaccineRepository.findOne(vaccineDTO.getId());
//		}catch (Exception e) {
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//			return responseDTO;
//		}
		vaccine.setName(vaccineDTO.getName());
		vaccine.setDescription(vaccineDTO.getDescription());
		vaccine.setPrice(vaccineDTO.getPrice());
		vaccine.setProductionDate(vaccineDTO.getProductionDate());
		vaccine.setExpirationDate(vaccineDTO.getExpirationDate());
		if(validateIllness(vaccineDTO,vaccine,responseDTO) == false) {
			return responseDTO;
		}
//		try {
			vaccineRepository.save(vaccine);
			vaccineDTO.setId(vaccine.getId());
			vaccineDTO.setDescription(vaccine.getDescription());
			vaccineDTO.setName(vaccine.getName());
			vaccineDTO.setPrice(vaccine.getPrice());
			vaccineDTO.setProductionDate(vaccine.getProductionDate());
			vaccineDTO.setExpirationDate(vaccine.getExpirationDate());
			vaccineDTO.setIllness(vaccine.getIllness());
			responseDTO.setData(vaccineDTO);
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
		return responseDTO;
	}

	
	
	public ResponseDTO<List<VaccineDTO>> listVaccines() {
		ResponseDTO<List<VaccineDTO>> responseDTO = new ResponseDTO<List<VaccineDTO>>();
		ArrayList<VaccineDTO> vaccineDTOs = new ArrayList<VaccineDTO>();
		List<Vaccine> vaccines = new ArrayList<Vaccine>();
		// try {
		vaccines = (List<Vaccine>) vaccineRepository.findAll();
		// } catch (Exception e) {
		// responseDTO.setStatus("failed");
		// responseDTO.setMessage(e.getMessage());
		// return responseDTO;
		// }
		for (Vaccine vaccine : vaccines) {
			VaccineDTO vaccineDTO = new VaccineDTO();
			vaccineDTO.setId(vaccine.getId());
			vaccineDTO.setDescription(vaccine.getDescription());
			vaccineDTO.setName(vaccine.getName());
			vaccineDTO.setPrice(vaccine.getPrice());
			vaccineDTO.setProductionDate(vaccine.getProductionDate());
			vaccineDTO.setExpirationDate(vaccine.getExpirationDate());
			vaccineDTO.setIllness(vaccine.getIllness());
			vaccineDTOs.add(vaccineDTO);
		}
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(vaccineDTOs);

		return responseDTO;
	}
	
	
	private boolean validateIllness(VaccineDTO vaccineDTO, Vaccine vaccine, ResponseDTO<VaccineDTO> responseDTO) {
		Illness illness = vaccineDTO.getIllness();
		
		if(illness != null && illness.getId() != null) {
			Long illnessId = illness.getId();
//			try {
				illness = illnessRepository.findOne(illness.getId());
//			}catch (Exception e) {
//				responseDTO.setStatus("failed");
//				responseDTO.setMessage(e.getMessage());
//			}
			if(illness==null) {
				responseDTO.setStatus("failed");
				responseDTO.setMessage("Illness ID "+ illnessId +"does not exist! ");
				return false;
			}
			vaccine.setIllness(illness);		
		}
		return true;
	}
}
