package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.petHospital.backend.controller.MedicineController;
import com.petHospital.backend.dto.MedicineDTO;
import com.petHospital.backend.dto.ResponseDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineTest 
{
	String existId = "3";
	String notExistId = "2";
	
	@Autowired
    private MedicineController medicineController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(medicineController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListMedicine()
    {
    		ResponseEntity<ResponseDTO<List<MedicineDTO>>> responseEntity = medicineController.listMedicines();
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetMedicine()
    {
    	    //id not in db
    		ResponseEntity<ResponseDTO<MedicineDTO>> responseEntity1 = medicineController.getMedicine(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<MedicineDTO>> responseEntity2 = medicineController.getMedicine(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditMedicine(){
    		MedicineDTO medicineDTO = new MedicineDTO();
    		medicineDTO.setId(Long.parseLong(existId));
    		medicineDTO.setName("布洛芬");
    		medicineDTO.setDescription("治疗头痛发热等问题");
    		medicineDTO.setPrice(88.9);
    		medicineDTO.setProductionDate(Date.valueOf("2018-01-01"));
    		medicineDTO.setExpirationDate(Date.valueOf("2018-12-31"));
    		ResponseEntity<ResponseDTO<MedicineDTO>> responseEntity = medicineController.editMedicine(medicineDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddMedicine() {
    		MedicineDTO medicineDTO = new MedicineDTO();
    		medicineDTO.setName("布洛芬");
		medicineDTO.setDescription("治疗头痛发热等问题");
		medicineDTO.setPrice(88.9);
		medicineDTO.setProductionDate(Date.valueOf("2018-01-01"));
		medicineDTO.setExpirationDate(Date.valueOf("2018-12-31"));
		ResponseEntity<ResponseDTO<MedicineDTO>> responseEntity = medicineController.addMedicine(medicineDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteMedicine() {
		ResponseEntity<ResponseDTO<MedicineDTO>> responseEntity = medicineController.deleteMedicine(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
