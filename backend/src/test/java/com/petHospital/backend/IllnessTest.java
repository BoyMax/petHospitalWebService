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

import com.petHospital.backend.controller.IllnessController;
import com.petHospital.backend.dto.IllnessDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Medicine;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class IllnessTest 
{
	String existId = "1";
	String notExistId = "100";
	Long medicineId = 1L;
	
	@Autowired
    private IllnessController illnessController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(illnessController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListIllness()
    {
    		ResponseEntity<ResponseDTO<List<IllnessDTO>>> responseEntity = illnessController.listIllnesss();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetIllness()
    {
    	    //id not in db
    		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity1 = illnessController.getIllness(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity2 = illnessController.getIllness(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditIllness(){
    		IllnessDTO illnessDTO = new IllnessDTO();
    		illnessDTO.setId(Long.parseLong(existId));
    		illnessDTO.setName("test2");
		illnessDTO.setDiseaseDescription("testDes2");
		illnessDTO.setProcess("testProcess2");
		illnessDTO.setResult("testResult2");
		illnessDTO.setTreatment("testTreat2");
    		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity = illnessController.editIllness(illnessDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddIllness() {
    		IllnessDTO illnessDTO = new IllnessDTO();
    		illnessDTO.setName("test");
    		illnessDTO.setDiseaseDescription("testDes");
    		illnessDTO.setProcess("testProcess");
    		illnessDTO.setResult("testResult");
    		illnessDTO.setTreatment("testTreat");
    		List<Medicine> medicines = new ArrayList<Medicine>();
    		Medicine medicine = new Medicine();
    		medicine.setId(medicineId);
    		medicines.add(medicine);
    		illnessDTO.setMedicines(medicines);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity = illnessController.addIllness(illnessDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteIllness() {
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity = illnessController.deleteIllness(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
