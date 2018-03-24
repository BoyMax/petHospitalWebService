package com.petHospital.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.CategoryRepository;
import com.petHospital.backend.dto.CategoryDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryController extends CommonController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CategoryDTO>> addCategory(@RequestBody CategoryDTO categoryDTO) {
		ResponseDTO<CategoryDTO> response = categoryService.createCategory(categoryDTO);	
		return new ResponseEntity<ResponseDTO<CategoryDTO>>(response, getHttpHeaders(), HttpStatus.CREATED);
	}
	
}