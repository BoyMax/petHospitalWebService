package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.CategoryRepository;
import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dao.MedicineRepository;
import com.petHospital.backend.dao.MultimediaRepository;
import com.petHospital.backend.dao.VaccineRepository;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.IllnessDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Department;
import com.petHospital.backend.model.Illness;
import com.petHospital.backend.model.Medicine;
import com.petHospital.backend.model.Multimedia;
import com.petHospital.backend.model.User;
import com.petHospital.backend.model.Vaccine;

@Service
public class IllnessServiceImpl implements IllnessService {

	 @Autowired
	 IllnessRepository illnessRepository;
	 @Autowired
	 CategoryRepository categoryRepository;
	 @Autowired
	 MedicineRepository medicineRepository;
	 @Autowired
	 MultimediaRepository multimediaRepository;
	 @Autowired
	 VaccineRepository vaccineRepository;
	 

	public ResponseDTO<IllnessDTO> retreiveIllness(Long id) {
		ResponseDTO<IllnessDTO> responseDTO = new ResponseDTO<IllnessDTO>();
		Illness illness = new Illness();
		IllnessDTO illnessDTO = new IllnessDTO();
		try {
			illness = illnessRepository.findOne(id);
			if (illness == null) {
				responseDTO.setMessage("Illness"+id.toString()+"does not exist.");
				responseDTO.setStatus("failed");
				return responseDTO;
			}
		}catch(Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		illnessDTO.setId(illness.getId());
		illnessDTO.setName(illness.getName());
		illnessDTO.setDiseaseDescription(illness.getDiseaseDescription());
		illnessDTO.setProcess(illness.getProcess());
		illnessDTO.setTreatment(illness.getTreatment());
		illnessDTO.setResult(illness.getResult());
		//illnessDTO.setCategory(illness.getCategory());
		illnessDTO.setMedicines(illness.getMedicines());
		illnessDTO.setMultimedias(illness.getMultimedias());
	    illnessDTO.setVaccines(illness.getVaccines());
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(illnessDTO);
		return responseDTO;
	}

	public ResponseDTO<IllnessDTO> createIllness(IllnessDTO illnessDTO) {
		    ResponseDTO<IllnessDTO> responseDTO = new ResponseDTO<IllnessDTO>();
		    Illness illness = new Illness();
		    illness.setName(illnessDTO.getName());
		    illness.setDiseaseDescription(illnessDTO.getDiseaseDescription());
		    illness.setProcess(illnessDTO.getProcess());
		    illness.setTreatment(illnessDTO.getTreatment());
		    illness.setResult(illnessDTO.getResult());
			if(validateIllness(illnessDTO,illness,responseDTO) == false) {
				return responseDTO;
			}
			try {
				illness = illnessRepository.save(illness);
				illnessDTO.setId(illness.getId());
				illnessDTO.setName(illness.getName());
				illnessDTO.setDiseaseDescription(illness.getDiseaseDescription());
				illnessDTO.setProcess(illness.getProcess());
				//illnessDTO.setCategory(illness.getCategory());
				illnessDTO.setMedicines(illness.getMedicines());
				illnessDTO.setMultimedias(illness.getMultimedias());
				illnessDTO.setResult(illness.getResult());
				illnessDTO.setTreatment(illness.getTreatment());
				illnessDTO.setVaccines(illness.getVaccines());
				responseDTO.setStatus("success");
				responseDTO.setMessage("success");
				responseDTO.setData(illnessDTO);
			}catch(Exception e){
				responseDTO.setStatus("failed");
				responseDTO.setMessage(e.getMessage());
			}
			return responseDTO;
	}

	public ResponseDTO<IllnessDTO> deleteIllness(Long id) {
		ResponseDTO<IllnessDTO> responseDTO = new ResponseDTO<IllnessDTO>();
		IllnessDTO illnessDTO = new IllnessDTO();
		Illness illness = illnessRepository.findOne(id);
		if(illness == null) {
			responseDTO.setMessage("Illness "+id+" does not exist");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
		try {
			illnessRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(illnessDTO);
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}

	public ResponseDTO<IllnessDTO> editIllness(IllnessDTO illnessDTO) {
		ResponseDTO<IllnessDTO> responseDTO = new ResponseDTO<IllnessDTO>();
		Illness illness = illnessRepository.findOne(illnessDTO.getId());
		illness.setName(illnessDTO.getName());
	    illness.setDiseaseDescription(illnessDTO.getDiseaseDescription());
	    illness.setProcess(illnessDTO.getProcess());
	    illness.setTreatment(illnessDTO.getTreatment());
	    illness.setResult(illnessDTO.getResult());
		if(validateIllness(illnessDTO,illness,responseDTO) == false) {
			return responseDTO;
		}
		try {
			illnessRepository.save(illness);
			illnessDTO.setId(illness.getId());
			illnessDTO.setName(illness.getName());
			illnessDTO.setDiseaseDescription(illness.getDiseaseDescription());
			illnessDTO.setProcess(illness.getProcess());
			//illnessDTO.setCategory(illness.getCategory());
			illnessDTO.setMedicines(illness.getMedicines());
			illnessDTO.setMultimedias(illness.getMultimedias());
			illnessDTO.setResult(illness.getResult());
			illnessDTO.setTreatment(illness.getTreatment());
			illnessDTO.setVaccines(illness.getVaccines());
			responseDTO.setMessage("success");
			responseDTO.setData(illnessDTO);
			responseDTO.setStatus("success");
		}catch(Exception e){
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus("failed");
		}
		return responseDTO;
	}

	public ResponseDTO<List<IllnessDTO>> listAllIllness() {
		ResponseDTO<List<IllnessDTO>> responseDTO = new ResponseDTO<List<IllnessDTO>>();
		ArrayList<IllnessDTO> illnessDTOs = new ArrayList<IllnessDTO>();
		List<Illness> illnesses = new ArrayList<Illness>();
		try {
			illnesses = (List<Illness>) illnessRepository.findAll();
		} catch (Exception e) {
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
			return responseDTO;
		}
		for (Illness illness : illnesses) {
			IllnessDTO illnessDTO = new IllnessDTO();
			illnessDTO.setId(illness.getId());
			illnessDTO.setName(illness.getName());
			illnessDTO.setDiseaseDescription(illness.getDiseaseDescription());
			illnessDTO.setProcess(illness.getProcess());
			//illnessDTO.setCategory(illness.getCategory());
			illnessDTO.setMedicines(illness.getMedicines());
			illnessDTO.setMultimedias(illness.getMultimedias());
			illnessDTO.setResult(illness.getResult());
			illnessDTO.setTreatment(illness.getTreatment());
			illnessDTO.setVaccines(illness.getVaccines());
			illnessDTOs.add(illnessDTO);
		}
		responseDTO.setMessage("status");
		responseDTO.setStatus("success");
		responseDTO.setData(illnessDTOs);
		
		return responseDTO;
	}
	
	// Validate illness id from CategoryDTO.getIllnesses and then set valid illness to category.
	private boolean validateIllness(IllnessDTO illnessDTO, Illness illness, ResponseDTO<IllnessDTO> responseDTO) {

		//medicine
		List<Medicine> medicines = illnessDTO.getMedicines();
		List<Medicine> medicineEntity = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			if(medicine != null) {
				try {
					medicine=medicineRepository.findOne(medicine.getId());
					
				}catch (Exception e) {
					responseDTO.setError_code("404");
					responseDTO.setStatus("failed");
					responseDTO.setMessage("medicine does not exist who's id =" + medicine.getId().toString());
					return false;
				}
				medicineEntity.add(medicine);
			}
		}
		illness.setMedicines(medicineEntity);
		
		//multi_media
		List<Multimedia> multimedias = illnessDTO.getMultimedias();
		List<Multimedia> multimediasEntity = new ArrayList<Multimedia>();
		for(Multimedia multimedia : multimedias) {
			if(multimedia != null && multimedia.getId() != null) {
				try {
					multimedia=multimediaRepository.findOne(multimedia.getId());				
				}catch (Exception e) {
					responseDTO.setError_code("404");
					responseDTO.setStatus("failed");
					responseDTO.setMessage("multimedia does not exist who's id =" + multimedia.getId().toString());
					return false;
				}
				multimediasEntity.add(multimedia);
			}
		}
		illness.setMultimedias(multimediasEntity);
		
		//vaccine
		List<Vaccine> vaccines = illnessDTO.getVaccines(); 
		List<Vaccine> vaccinesEntity = new ArrayList<Vaccine>();
		for(Vaccine vaccine : vaccines) {
			if(vaccine != null && vaccine.getId() != null) {
				try {
					vaccine=vaccineRepository.findOne(vaccine.getId());				
				}catch (Exception e) {
					responseDTO.setError_code("404");
					responseDTO.setStatus("failed");
					responseDTO.setMessage("vaccine does not exist who's id =" + vaccine.getId().toString());
					return false;
				}
				vaccinesEntity.add(vaccine);
			}
		}
		illness.setVaccines(vaccinesEntity);
		
		
		
		
		return true;
		
		
	} 
	
}
