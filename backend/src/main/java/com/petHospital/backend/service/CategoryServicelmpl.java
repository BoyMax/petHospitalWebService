package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.CategoryRepository;
import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dto.CategoryDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Illness;

@Service
public class CategoryServicelmpl implements CategoryService {
	
	 @Autowired
	 CategoryRepository categoryRepository;
	 @Autowired
	 IllnessRepository illnessRepository;
	 
	 public ResponseDTO<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
		 ResponseDTO<CategoryDTO> responseDTO = new ResponseDTO<CategoryDTO>();
		 Category category = new Category();
		 category.setName(categoryDTO.getName());
		 if(validateIllnesses(categoryDTO,category,responseDTO) == false) {
				return responseDTO;
			}
		try {
			category = categoryRepository.save(category);
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setIllnesses(category.getIllnesses());
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
			responseDTO.setData(categoryDTO);
		}catch(Exception e){
			responseDTO.setStatus("failed");
			responseDTO.setMessage(e.getMessage());
		}
		return responseDTO;
	}
	 
	// Validate illness id from CategoryDTO.getIllnesses and then set valid illness to category.
		private boolean validateIllnesses(CategoryDTO categoryDTO, Category category, ResponseDTO<CategoryDTO> responseDTO) {
			List<Illness> illnesses = categoryDTO.getIllnesses();
			List<Illness> illnessEntitys = new ArrayList<Illness>();
			if (illnesses != null) {
				for (Iterator<Illness> it = illnesses.iterator(); it.hasNext();) {
					Illness illness = it.next();
					Long illnessId = illness.getId();
					illness = illnessRepository.findOne(illnessId);
					if (illness == null) {
						it.remove();
						responseDTO.setError_code("404");
						responseDTO.setStatus("failed");
						responseDTO.setMessage("illness does not exist who's id =" + illnessId);
						return false;
					}
					illnessEntitys.add(illness);
				}
			}
			category.setIllnesses(illnessEntitys);
			return true;
		} 
}