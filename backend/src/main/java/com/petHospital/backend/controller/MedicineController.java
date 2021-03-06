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

import com.petHospital.backend.dao.MedicineRepository;
import com.petHospital.backend.dto.MedicineDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.MedicineService;

@RestController
@RequestMapping(path="/medicine")
public class MedicineController {
	
	@Autowired
    MedicineService medicineService;// = new medicineServiceImpl();
    @Autowired
    MedicineRepository medicineRepository;
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity<ResponseDTO<MedicineDTO>> addMedicine(@RequestBody MedicineDTO medicine) {
    		ResponseDTO<MedicineDTO> response =medicineService.createMedicine(medicine);
    		return new ResponseEntity<ResponseDTO<MedicineDTO>>(response, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO<MedicineDTO>> deleteMedicine(@PathVariable("id") String id) {
    		ResponseDTO<MedicineDTO> response = medicineService.deleteMedicine(Long.parseLong(id));
    	    return new ResponseEntity<ResponseDTO<MedicineDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ResponseEntity<ResponseDTO<MedicineDTO>> editMedicine(@RequestBody MedicineDTO medicine) {
    		ResponseDTO<MedicineDTO> response =medicineService.editMedicine(medicine);
    		return new ResponseEntity<ResponseDTO<MedicineDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResponseDTO<MedicineDTO>> getMedicine(@PathVariable("id") String id) {
    		ResponseDTO<MedicineDTO> response = medicineService.retreiveMedicine(Long.parseLong(id));
    	    return new ResponseEntity<ResponseDTO<MedicineDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<MedicineDTO>>> listMedicines() {
		ResponseDTO<List<MedicineDTO>> response = medicineService.listMedicines();
		return new ResponseEntity<ResponseDTO<List<MedicineDTO>>>(response,  HttpStatus.OK);
	}
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
   	public ResponseEntity<ResponseDTO<List<MedicineDTO>>> searchMedicines(@RequestBody MedicineDTO medicine) {
   		ResponseDTO<List<MedicineDTO>> response = medicineService.searchMedicines(medicine.getName());
   		return new ResponseEntity<ResponseDTO<List<MedicineDTO>>>(response,  HttpStatus.OK);
   	}
}

