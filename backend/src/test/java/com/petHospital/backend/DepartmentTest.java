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

import com.petHospital.backend.controller.DepartmentController;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTest 
{
	String existId = "2";
	String notExistId = "1";
	
	@Autowired
    private DepartmentController departmentController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(departmentController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListDepartment()
    {
    		ResponseEntity<ResponseDTO<List<DepartmentDTO>>> responseEntity = departmentController.listDepartments();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetDepartment()
    {
    	    //id not in db
    		ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity1 = departmentController.getDepartment(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity2 = departmentController.getDepartment(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditDepartment(){
    		DepartmentDTO departmentDTO = new DepartmentDTO();
    		departmentDTO.setId(Long.parseLong(existId));
    		departmentDTO.setName("档案馆2");
    		departmentDTO.setDescription("档案馆描述");
    		ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity = departmentController.editDepartment(departmentDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddDepartment() {
    		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setName("档案馆");
		departmentDTO.setDescription("档案馆描述");
		ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity = departmentController.addDepartment(departmentDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteDepartment() {
		ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity = departmentController.deleteDepartment(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
