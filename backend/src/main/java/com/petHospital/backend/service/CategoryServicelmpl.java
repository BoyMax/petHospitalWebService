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
	 
	 public ResponseDTO<CategoryDTO> retreiveCategory(Long id) {
			ResponseDTO<CategoryDTO> responseDTO = new ResponseDTO<CategoryDTO>();
			Category category = new Category();
			CategoryDTO categoryDTO = new CategoryDTO();
			try {
				category = categoryRepository.findOne(id);
				if (category == null) {
					responseDTO.setMessage("Category"+id+"does not exist.");
					responseDTO.setStatus("failed");
					return responseDTO;
				}
			}catch(Exception e) {
				responseDTO.setMessage(e.getMessage());
				responseDTO.setStatus("failed");
			}
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setIllnesses(category.getIllnesses());
			responseDTO.setStatus("success");
			responseDTO.setMessage("success");
			responseDTO.setData(categoryDTO);
			return responseDTO;
		}
	 
	 public ResponseDTO<CategoryDTO> deleteCategory(Long id) {
			ResponseDTO<CategoryDTO> responseDTO = new ResponseDTO<CategoryDTO>();
			CategoryDTO categoryDTO = new CategoryDTO();
			Category category = categoryRepository.findOne(id);
			if(category == null) {
				responseDTO.setMessage("Category "+id+" does not exist");
				responseDTO.setStatus("failed");
				return responseDTO;
			}
			try {
				categoryRepository.delete(id);
				responseDTO.setMessage("success");
				responseDTO.setStatus("success");
				responseDTO.setData(categoryDTO);
			}catch(Exception e){
				responseDTO.setMessage(e.getMessage());
				responseDTO.setStatus("failed");
			}
			return responseDTO;
		}

		public ResponseDTO<CategoryDTO> editCategory(CategoryDTO categoryDTO) {
			ResponseDTO<CategoryDTO> responseDTO = new ResponseDTO<CategoryDTO>();
			Category category = categoryRepository.findOne(categoryDTO.getId());
			category.setName(categoryDTO.getName());
			if(validateIllnesses(categoryDTO,category,responseDTO) == false) {
				return responseDTO;
			}
			try {
				categoryRepository.save(category);
				categoryDTO.setId(category.getId());
				categoryDTO.setName(category.getName());
				categoryDTO.setIllnesses(category.getIllnesses());
				responseDTO.setMessage("success");
				responseDTO.setData(categoryDTO);
				responseDTO.setStatus("success");
			}catch(Exception e){
				responseDTO.setMessage(e.getMessage());
				responseDTO.setStatus("failed");
			}
			return responseDTO;
		}


		public ResponseDTO<List<CategoryDTO>> listAllCategory() {
			ResponseDTO<List<CategoryDTO>> responseDTO = new ResponseDTO<List<CategoryDTO>>();
			ArrayList<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
			List<Category> categorys = new ArrayList<Category>();
			try {
				categorys = (List<Category>) categoryRepository.findAll();
			} catch (Exception e) {
				responseDTO.setStatus("failed");
				responseDTO.setMessage(e.getMessage());
				return responseDTO;
			}
			for (Category category : categorys) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId(category.getId());
				categoryDTO.setName(category.getName());
				categoryDTO.setIllnesses(category.getIllnesses());
				responseDTO.setMessage("success");
				categoryDTOs.add(categoryDTO);
			}
			responseDTO.setMessage("status");
			responseDTO.setStatus("success");
			responseDTO.setData(categoryDTOs);
			
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