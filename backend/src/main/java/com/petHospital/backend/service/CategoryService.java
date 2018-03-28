package com.petHospital.backend.service;

import java.util.List;

import com.petHospital.backend.dto.CategoryDTO;
import com.petHospital.backend.dto.ResponseDTO;

public interface CategoryService {
	public ResponseDTO<CategoryDTO> retreiveCategory(Long id);
	public ResponseDTO<CategoryDTO> createCategory(CategoryDTO category);
	public ResponseDTO<CategoryDTO> deleteCategory(Long id);
	public ResponseDTO<CategoryDTO> editCategory(CategoryDTO category);
	public ResponseDTO<List<CategoryDTO>> listAllCategory(); 
}