package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.petHospital.backend.controller.CategoryController;
import com.petHospital.backend.dto.CategoryDTO;
import com.petHospital.backend.dto.ResponseDTO;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest 
{
    String existId = "2";
    String notExistId = "1";
    
    @Autowired
    private CategoryController categoryController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(categoryController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListCategory()
    {
            ResponseEntity<ResponseDTO<List<CategoryDTO>>> responseEntity = categoryController.listCategorys();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetCategory()
    {
            //id not in db
            ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity1 = categoryController.getCategory(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
            //id is in db
            ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity2 = categoryController.getCategory(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditCategory(){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(Long.parseLong(existId));
            categoryDTO.setName("test2");
            ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity = categoryController.editCategory(categoryDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddCategory() {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName("test");
        ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity = categoryController.addCategory(categoryDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteCategory() {
        ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity = categoryController.deleteCategory(existId);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
