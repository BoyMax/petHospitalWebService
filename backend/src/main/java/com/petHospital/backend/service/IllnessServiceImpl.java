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
	 
	 

//	 public ResponseDTO<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO) {
//		 ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
//		Department department = new Department();
//		department.setName(departmentDTO.getName());
//		department.setDescription(departmentDTO.getDescription());
//		if(validateManagers(departmentDTO,department,responseDTO) == false) {
//			return responseDTO;
//		}
//		try {
//			department = departmentRepository.save(department);
//			departmentDTO.setId(department.getId());
//			departmentDTO.setName(department.getName());
//			departmentDTO.setDescription(department.getDescription());
//			departmentDTO.setManagers(department.getManagers());
//			responseDTO.setStatus("success");
//			responseDTO.setMessage("success");
//			responseDTO.setData(departmentDTO);
//		}catch(Exception e){
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//		}
//		return responseDTO;
//	}
//	
//	public ResponseDTO<DepartmentDTO> deleteDepartment(Long id) {
//		ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
//		DepartmentDTO departmentDTO = new DepartmentDTO();
//		Department department = departmentRepository.findOne(id);
//		if(department == null) {
//			responseDTO.setMessage("Department "+id+" does not exist");
//			responseDTO.setStatus("failed");
//			return responseDTO;
//		}
//		try {
//			departmentRepository.delete(id);
//			responseDTO.setMessage("success");
//			responseDTO.setStatus("success");
//			responseDTO.setData(departmentDTO);
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
//		return responseDTO;
//	}
//
//	public ResponseDTO<DepartmentDTO> editDepartment(DepartmentDTO departmentDTO) {
//		ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<DepartmentDTO>();
//		Department department = departmentRepository.findOne(departmentDTO.getId());
//		department.setName(departmentDTO.getName());
//		department.setDescription(departmentDTO.getDescription());
//		if(validateManagers(departmentDTO,department,responseDTO) == false) {
//			return responseDTO;
//		}
//		try {
//			departmentRepository.save(department);
//			departmentDTO.setId(department.getId());
//			departmentDTO.setName(department.getName());
//			departmentDTO.setDescription(department.getDescription());
//			departmentDTO.setManagers(department.getManagers());
//			responseDTO.setMessage("success");
//			responseDTO.setData(departmentDTO);
//			responseDTO.setStatus("success");
//		}catch(Exception e){
//			responseDTO.setMessage(e.getMessage());
//			responseDTO.setStatus("failed");
//		}
//		return responseDTO;
//	}
//
//
//	public ResponseDTO<List<DepartmentDTO>> listAllDepartment() {
//		ResponseDTO<List<DepartmentDTO>> responseDTO = new ResponseDTO<List<DepartmentDTO>>();
//		ArrayList<DepartmentDTO> departmentsDTOs = new ArrayList<DepartmentDTO>();
//		List<Department> departments = new ArrayList<Department>();
//		try {
//			departments = (List<Department>) departmentRepository.findAll();
//		} catch (Exception e) {
//			responseDTO.setStatus("failed");
//			responseDTO.setMessage(e.getMessage());
//			return responseDTO;
//		}
//		for (Department department : departments) {
//			DepartmentDTO departmentDTO = new DepartmentDTO();
//			departmentDTO.setId(department.getId());
//			departmentDTO.setName(department.getName());
//			departmentDTO.setDescription(department.getDescription());
//			departmentDTO.setManagers(department.getManagers());
//			departmentsDTOs.add(departmentDTO);
//		}
//		responseDTO.setMessage("status");
//		responseDTO.setStatus("success");
//		responseDTO.setData(departmentsDTOs);
//		
//		return responseDTO;
//	}
//	
//	// Validate manager id from DepartmentDTO.getManagers and then set valid manager
//	// to department.
//	private boolean validateManagers(DepartmentDTO departmentDTO, Department department, ResponseDTO<DepartmentDTO> responseDTO) {
//		List<User> managers = departmentDTO.getManagers();
//		List<User> managerEntitys = new ArrayList<User>();
//		if (managers != null) {
//			for (Iterator<User> it = managers.iterator(); it.hasNext();) {
//				User manager = it.next();
//				Long managerId = manager.getId();
//				manager = userRepository.findOne(managerId);
//				if (manager == null) {
//					it.remove();
//					responseDTO.setError_code("404");
//					responseDTO.setStatus("failed");
//					responseDTO.setMessage("manager does not exist who's id =" + managerId);
//					return false;
//				}
//				managerEntitys.add(manager);
//			}
//		}
//		department.setManagers(managerEntitys);
//		return true;
//	}

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
		illnessDTO.setMedicines(illness.getMedicines());
		illnessDTO.setMultimedias(illness.getMultimedias());
	    //illnessDTO.setVaccines(illness.getVaccines());
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
			if(validateIllness(illnessDTO,illness,responseDTO) == false) {
				return responseDTO;
			}
			try {
				illness = illnessRepository.save(illness);
				illnessDTO.setId(illness.getId());
				illnessDTO.setName(illness.getName());
				illnessDTO.setDiseaseDescription(illness.getDiseaseDescription());
				illnessDTO.setProcess(illness.getProcess());
				illnessDTO.setCategory(illness.getCategory());
				illnessDTO.setMedicines(illness.getMedicines());
				illnessDTO.setMultimedias(illness.getMultimedias());
				illnessDTO.setResult(illness.getResult());
				illnessDTO.setTreatment(illness.getTreatment());
				//illnessDTO.setVaccines(illness.getVaccines());
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
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseDTO<IllnessDTO> editIllness(IllnessDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseDTO<List<IllnessDTO>> listAllIllness() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Validate illness id from CategoryDTO.getIllnesses and then set valid illness to category.
	private boolean validateIllness(IllnessDTO illnessDTO, Illness illness, ResponseDTO<IllnessDTO> responseDTO) {
//		List<Illness> illnesses = illnessDTO.getCategory()();
//		List<Illness> illnessEntitys = new ArrayList<Illness>();
//		if (illnesses != null) {
//			for (Iterator<Illness> it = illnesses.iterator(); it.hasNext();) {
//				Illness illness = it.next();
//				Long illnessId = illness.getId();
//				illness = illnessRepository.findOne(illnessId);
//				if (illness == null) {
//					it.remove();
//					responseDTO.setError_code("404");
//					responseDTO.setStatus("failed");
//					responseDTO.setMessage("illness does not exist who's id =" + illnessId);
//					return false;
//				}
//				illnessEntitys.add(illness);
//			}
//		}
//		category.setIllnesses(illnessEntitys);
//		return true;
		
		
		//catetory
		Category category = illnessDTO.getCategory();
		if(category != null && category.getId() != null) {
			try {
				category=categoryRepository.findOne(category.getId());
				
			}catch (Exception e) {
				responseDTO.setError_code("404");
				responseDTO.setStatus("failed");
				responseDTO.setMessage("illness does not exist who's id =" + category.getId().toString());
				return false;
			}
			illness.setCategory(category);
		}
		
		
		//medicine
		List<Medicine> medicines = illnessDTO.getMedicines();
		List<Medicine> medicineEntity = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			if(medicine != null && medicine.getId() != null) {
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
					responseDTO.setMessage("medicine does not exist who's id =" + multimedia.getId().toString());
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
					responseDTO.setMessage("medicine does not exist who's id =" + vaccine.getId().toString());
					return false;
				}
				vaccinesEntity.add(vaccine);
			}
		}
		//illness.setVaccines(vaccinesEntity);
		
		
		
		
		return true;
		
		
	} 
	
}
