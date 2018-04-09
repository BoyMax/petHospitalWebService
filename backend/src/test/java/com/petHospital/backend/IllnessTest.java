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
import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Medicine;
import com.petHospital.backend.model.Vaccine;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class IllnessTest 
{
	String existId = "1";
	String notExistId = "100";
	Long existMedicineId = 1L;
	Long notExistMedicineId = 100L;
	Long existCategoryId = 2L;
	Long notExistCategoryId = 100L;
	Long existVaccineId = 1L;
	Long notExistVaccineId = 100L;
	
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
    		IllnessDTO illnessDTO2 = new IllnessDTO();
    		illnessDTO2.setId(Long.parseLong(existId));
    		List<Vaccine> vaccines = new ArrayList<Vaccine>();
    		Vaccine vaccine = new Vaccine();
    		vaccine.setId(notExistVaccineId);
    	    vaccines.add(vaccine);   		
    		illnessDTO2.setVaccines(vaccines);
    		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity2 = illnessController.editIllness(illnessDTO2);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
        assertTrue( responseEntity2.getBody().getStatus() == "failed" );
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
    		medicine.setId(existMedicineId);
    		medicines.add(medicine);   		
    		Medicine medicine2 = new Medicine();
    		medicine2.setId(notExistMedicineId);
    		medicines.add(medicine2);
    		illnessDTO.setMedicines(medicines);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity = illnessController.addIllness(illnessDTO);
		
		IllnessDTO illnessDTO3 = new IllnessDTO();
		Category category = new Category();
		category.setId(notExistCategoryId);
		illnessDTO3.setCategory(category);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity3 = illnessController.addIllness(illnessDTO3);
		
		IllnessDTO illnessDTO4 = new IllnessDTO();
		List<Vaccine> vaccines = new ArrayList<Vaccine>();
		Vaccine vaccine = new Vaccine();
		vaccine.setId(notExistVaccineId);
	    vaccines.add(vaccine);   		
		illnessDTO4.setVaccines(vaccines);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity4 = illnessController.addIllness(illnessDTO4);
		
		
		IllnessDTO illnessDTO2 = new IllnessDTO();
		List<Medicine> medicines2 = new ArrayList<Medicine>();
		Medicine medicine3 = new Medicine();
		medicine3.setId(existMedicineId);
		medicines2.add(medicine3);
		illnessDTO2.setMedicines(medicines2);
		List<Vaccine> vaccines2 = new ArrayList<Vaccine>();
		Vaccine vaccine3 = new Vaccine();
		vaccine3.setId(existVaccineId);
		vaccines2.add(vaccine3);
		illnessDTO2.setVaccines(vaccines2);
		Category category2 = new Category();
		category2.setId(existCategoryId);
		illnessDTO2.setCategory(category2);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity2 = illnessController.addIllness(illnessDTO2);
		
		assertTrue( responseEntity.getBody().getStatus() == "failed" );
		assertTrue( responseEntity2.getBody().getStatus() == "success" );
		assertTrue( responseEntity3.getBody().getStatus() == "failed" );
		assertTrue( responseEntity4.getBody().getStatus() == "failed" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteIllness() {
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity = illnessController.deleteIllness(existId);
		ResponseEntity<ResponseDTO<IllnessDTO>> responseEntity2 = illnessController.deleteIllness(notExistId);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
