package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dao.MedicineRepository;
import com.petHospital.backend.dto.MedicineDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Illness;
import com.petHospital.backend.model.Medicine;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	MedicineRepository medicineRepository;
	@Autowired
	IllnessRepository illnessRepository;
	
	public ResponseDTO<MedicineDTO> retreiveMedicine(Long id) {
		ResponseDTO<MedicineDTO> responseDTO = new ResponseDTO<MedicineDTO>();
		Medicine medicine = new Medicine();
		MedicineDTO medicineDTO = new MedicineDTO();
//		try {
			medicine = medicineRepository.findOne(id);
			if (medicine == null) {
				responseDTO.setMessage("Medicine "+id.toString()+" does not exist.");
				responseDTO.setStatus("failed");
				responseDTO.setError_code("404");
				return responseDTO;
			}
			medicineDTO.setId(medicine.getId());
			medicineDTO.setName(medicine.getName());
			medicineDTO.setPrice(medicine.getPrice());
			medicineDTO.setDescription(medicine.getDescription());
			medicineDTO.setProductionDate(medicine.getProductionDate());
			medicineDTO.setExpirationDate(medicine.getExpirationDate());
			medicineDTO.setIllnesses(medicine.getIllnesses());
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(medicineDTO);
//		} catch (Exception e) {
//			responseDTO.setMessage("failed");
//			responseDTO.setStatus(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<MedicineDTO> createMedicine(MedicineDTO medicineDTO) {
		ResponseDTO<MedicineDTO> responseDTO = new ResponseDTO<MedicineDTO>();
		Medicine medicine = new Medicine();
		medicine.setName(medicineDTO.getName());
		medicine.setDescription(medicineDTO.getDescription());
		medicine.setPrice(medicineDTO.getPrice());
		medicine.setProductionDate(medicineDTO.getProductionDate());
		medicine.setExpirationDate(medicineDTO.getExpirationDate());
		if(validateIllnesses(medicineDTO, medicine, responseDTO) == false) {
			return responseDTO;
		}
//		try {
			medicine = medicineRepository.save(medicine);
			medicineDTO.setId(medicine.getId());
			medicineDTO.setDescription(medicine.getDescription());
			medicineDTO.setName(medicine.getName());
			medicineDTO.setPrice(medicine.getPrice());
			medicineDTO.setProductionDate(medicine.getProductionDate());
			medicineDTO.setExpirationDate(medicine.getExpirationDate());
			medicineDTO.setIllnesses(medicine.getIllnesses());
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
			responseDTO.setData(medicineDTO);
//		}catch(Exception e){
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//		}
		return responseDTO;
	}

	public ResponseDTO<MedicineDTO> deleteMedicine(Long id) {
		ResponseDTO<MedicineDTO> responseDTO = new ResponseDTO<MedicineDTO>();
		Medicine medicine;
//		try {
			medicine = medicineRepository.findOne(id);
//		}catch (Exception e) {
//			responseDTO.setMessage("Cannot find medicine with "+id.toString()+ e.getMessage());
//			responseDTO.setStatus("failed");
//			return responseDTO;
//		}
		if(medicine == null) {
			responseDTO.setMessage("Department "+id+" does not exist");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
//		try {
			medicineRepository.delete(id);
			responseDTO.setMessage("success");
			responseDTO.setStatus("success");
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
		return responseDTO;
	}

	public ResponseDTO<MedicineDTO> editMedicine(MedicineDTO medicineDTO) {
		ResponseDTO<MedicineDTO> responseDTO = new ResponseDTO<MedicineDTO>();
		Medicine medicine = medicineRepository.findOne(medicineDTO.getId());
		medicine.setName(medicineDTO.getName());
		medicine.setDescription(medicineDTO.getDescription());
		medicine.setPrice(medicineDTO.getPrice());
		medicine.setProductionDate(medicineDTO.getProductionDate());
		medicine.setExpirationDate(medicineDTO.getExpirationDate());
		if(validateIllnesses(medicineDTO,medicine,responseDTO) == false) {
			return responseDTO;
		}
//		try {
			medicineRepository.save(medicine);
			medicineDTO.setId(medicine.getId());
			medicineDTO.setDescription(medicine.getDescription());
			medicineDTO.setName(medicine.getName());
			medicineDTO.setPrice(medicine.getPrice());
			medicineDTO.setProductionDate(medicine.getProductionDate());
			medicineDTO.setExpirationDate(medicine.getExpirationDate());
			medicineDTO.setIllnesses(medicine.getIllnesses());
			responseDTO.setData(medicineDTO);
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
		return responseDTO;
	}

	public ResponseDTO<List<MedicineDTO>> listMedicines() {
		ResponseDTO<List<MedicineDTO>> responseDTO = new ResponseDTO<List<MedicineDTO>>();
		ArrayList<MedicineDTO> medicineDTOs = new ArrayList<MedicineDTO>();
		List<Medicine> medicines = new ArrayList<Medicine>();
//		try {
			medicines = (List<Medicine>) medicineRepository.findAll();
//		} catch (Exception e) {
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//			return responseDTO;
//		}
		for (Medicine medicine : medicines) {
			MedicineDTO medicineDTO = new MedicineDTO();
			medicineDTO.setId(medicine.getId());
			medicineDTO.setDescription(medicine.getDescription());
			medicineDTO.setName(medicine.getName());
			medicineDTO.setPrice(medicine.getPrice());
			medicineDTO.setProductionDate(medicine.getProductionDate());
			medicineDTO.setExpirationDate(medicine.getExpirationDate());
			medicineDTO.setIllnesses(medicine.getIllnesses());
			medicineDTOs.add(medicineDTO);
		}
		responseDTO.setMessage("status");
		responseDTO.setStatus("success");
		responseDTO.setData(medicineDTOs);
		
		return responseDTO;
	}
	
	// Validate illness id from MedicineDTO
		private boolean validateIllnesses(MedicineDTO medicineDTO, Medicine medicine, ResponseDTO<MedicineDTO> responseDTO) {
			List<Illness> illnesses = medicineDTO.getIllnesses();
			List<Illness> illnessEntitys = new ArrayList<Illness>();
			if (illnesses != null) {
				for (Iterator<Illness> it = illnesses.iterator(); it.hasNext();) {
					Illness illness = it.next();
					Long IllnessId = illness.getId();
					illness = illnessRepository.findOne(IllnessId);
					if (illness == null) {
						it.remove();
						responseDTO.setError_code("404");
						responseDTO.setStatus("failed");
						responseDTO.setMessage("Illness ID "+IllnessId+" does not exist");
						return false;
					}
					illnessEntitys.add(illness);
				}
			}
			medicine.setIllnesses(illnessEntitys);
			return true;
		}

		public ResponseDTO<List<MedicineDTO>> searchMedicines(String name) {
			ResponseDTO<List<MedicineDTO>> responseDTO = new ResponseDTO<List<MedicineDTO>>();
			ArrayList<MedicineDTO> medicineDTOs = new ArrayList<MedicineDTO>();
			List<Medicine> medicines = new ArrayList<Medicine>();
//			try {
				medicines = (List<Medicine>) medicineRepository.search(name);
//			} catch (Exception e) {
//				responseDTO.setStatus("failed");
//				responseDTO.setMessage(e.getMessage());
//				return responseDTO;
//			}
			for (Medicine medicine : medicines) {
				MedicineDTO medicineDTO = new MedicineDTO();
				medicineDTO.setId(medicine.getId());
				medicineDTO.setDescription(medicine.getDescription());
				medicineDTO.setName(medicine.getName());
				medicineDTO.setPrice(medicine.getPrice());
				medicineDTO.setProductionDate(medicine.getProductionDate());
				medicineDTO.setExpirationDate(medicine.getExpirationDate());
				medicineDTO.setIllnesses(medicine.getIllnesses());
				medicineDTOs.add(medicineDTO);
			}
			responseDTO.setMessage("status");
			responseDTO.setStatus("success");
			responseDTO.setData(medicineDTOs);
			
			return responseDTO;
		}

}
