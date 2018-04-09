package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import com.petHospital.backend.model.Illness;
import com.petHospital.backend.model.Medicine;
import com.petHospital.backend.model.User;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest 
{
    String existId = "2";
    String notExistId = "1";
    Long existIllnessId = 1L;
    Long existIllnessId2 = 2L;
    Long notExistIllnessId = 100L;
    
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
            
            CategoryDTO categoryDTO2 = new CategoryDTO();
            categoryDTO2.setId(Long.parseLong(existId));
            categoryDTO2.setName("test3");
            List<Illness> illnesses = new ArrayList<Illness>();
            Illness illness = new Illness();
            illness.setId(existIllnessId);
            illnesses.add(illness);
            Illness illness2 = new Illness();
            illness.setId(notExistIllnessId);
            illnesses.add(illness2);
            categoryDTO2.setIllnesses(illnesses);
            
            ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity2 = categoryController.editCategory(categoryDTO2);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
        assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddCategory() {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName("test");
            List<Illness> illnesses = new ArrayList<Illness>();
            Illness illness = new Illness();
            illness.setId(existIllnessId);
            illnesses.add(illness);
            Illness illness2 = new Illness();
            illness.setId(notExistIllnessId);
            illnesses.add(illness2);
            categoryDTO.setIllnesses(illnesses);
        ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity = categoryController.addCategory(categoryDTO);
        
        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName("test");
        List<Illness> illnesses2 = new ArrayList<Illness>();
        Illness illness3 = new Illness();
        illness3.setId(existIllnessId);
        illnesses2.add(illness3);
        Illness illness4 = new Illness();
        illness4.setId(existIllnessId2);
        illnesses2.add(illness4);
        categoryDTO2.setIllnesses(illnesses2);
    ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity2 = categoryController.addCategory(categoryDTO2);
        
        assertTrue( responseEntity.getBody().getStatus() == "failed" );
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
        
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteCategory() {
        ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity = categoryController.deleteCategory(existId);
        ResponseEntity<ResponseDTO<CategoryDTO>> responseEntity2 = categoryController.deleteCategory(notExistId);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
        assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    }
}
