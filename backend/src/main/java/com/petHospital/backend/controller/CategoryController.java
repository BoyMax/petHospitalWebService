package com.petHospital.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CategoryController{
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CategoryDTO>> addCategory(@RequestBody CategoryDTO categoryDTO) {
		ResponseDTO<CategoryDTO> response = categoryService.createCategory(categoryDTO);	
		return new ResponseEntity<ResponseDTO<CategoryDTO>>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<CategoryDTO>> deleteCategory(@PathVariable("id") String id) {
		ResponseDTO<CategoryDTO> response = categoryService.deleteCategory(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<CategoryDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<CategoryDTO>> editCategory(@RequestBody CategoryDTO categoryDTO) {
		ResponseDTO<CategoryDTO> response = categoryService.editCategory(categoryDTO);
		return new ResponseEntity<ResponseDTO<CategoryDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<CategoryDTO>> getCategory(@PathVariable("id") String id) {
		ResponseDTO<CategoryDTO> response = categoryService.retreiveCategory(Long.parseLong(id));
		return new ResponseEntity<ResponseDTO<CategoryDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<CategoryDTO>>> listCategorys() {
		ResponseDTO<List<CategoryDTO>> response = categoryService.listAllCategory();
		return new ResponseEntity<ResponseDTO<List<CategoryDTO>>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<List<CategoryDTO>>> searchCategorys(@RequestBody CategoryDTO categoryDTO) {
		ResponseDTO<List<CategoryDTO>> response = categoryService.searchCategorys(categoryDTO.getName());
		return new ResponseEntity<ResponseDTO<List<CategoryDTO>>>(response, HttpStatus.OK);
	}
	
}