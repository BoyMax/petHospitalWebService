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

import com.petHospital.backend.controller.VaccineController;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.VaccineDTO;
import com.petHospital.backend.model.Illness;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VaccineTest 
{
	String existId = "2";
	String notExistId = "1";
	Long illnessId = 1L;
	
	@Autowired
    private VaccineController vaccineController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(vaccineController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testLisVaccine()
    {
    		ResponseEntity<ResponseDTO<List<VaccineDTO>>> responseEntity = vaccineController.listVaccines();
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetVaccine()
    {
    	    //id not in db
    		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity1 = vaccineController.getVaccine(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity2 = vaccineController.getVaccine(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditVaccine(){
    		VaccineDTO VaccineDTO = new VaccineDTO();
    		VaccineDTO.setId(Long.parseLong(existId));
    		VaccineDTO.setName("乙肝疫苗");
    		VaccineDTO.setDescription("预防乙肝");
    		VaccineDTO.setPrice(88.9);
    		VaccineDTO.setProductionDate(Date.valueOf("2018-01-01"));
    		VaccineDTO.setExpirationDate(Date.valueOf("2018-12-31"));
    		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity = vaccineController.editVaccine(VaccineDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddVaccine() {
    		VaccineDTO VaccineDTO = new VaccineDTO();
    		VaccineDTO.setName("甲肝疫苗");
		VaccineDTO.setDescription("预防甲肝");
		VaccineDTO.setPrice(88.9);
		VaccineDTO.setProductionDate(Date.valueOf("2018-01-01"));
		VaccineDTO.setExpirationDate(Date.valueOf("2018-12-31"));
		Illness illness = new Illness();
		illness.setId(illnessId);
		VaccineDTO.setIllness(illness);
		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity = vaccineController.addVaccine(VaccineDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteVaccine() {
		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity = vaccineController.deleteVaccine(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
		
		ResponseEntity<ResponseDTO<VaccineDTO>> responseEntity2 = vaccineController.deleteVaccine(notExistId);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    }
}
