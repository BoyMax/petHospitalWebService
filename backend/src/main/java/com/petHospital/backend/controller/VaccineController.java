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

import com.petHospital.backend.dao.VaccineRepository;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.VaccineDTO;
import com.petHospital.backend.service.VaccineService;

@RestController
@RequestMapping(path="/vaccine")
public class VaccineController extends CommonController{
	@Autowired
    VaccineService vaccineService;// = new medicineServiceImpl();
    @Autowired
    VaccineRepository vaccineRepository;
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity<ResponseDTO<VaccineDTO>> addUser(@RequestBody VaccineDTO vaccine) {
    		ResponseDTO<VaccineDTO> response =vaccineService.createVaccine(vaccine);
    		return new ResponseEntity<ResponseDTO<VaccineDTO>>(response,getHttpHeaders(),HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO<VaccineDTO>> deleteUser(@PathVariable("id") String id) {
    		ResponseDTO<VaccineDTO> response = vaccineService.deleteVaccine(Long.parseLong(id));
    	    return new ResponseEntity<ResponseDTO<VaccineDTO>>(response,getHttpHeaders(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ResponseEntity<ResponseDTO<VaccineDTO>> editUser(@RequestBody VaccineDTO vaccine) {
    		ResponseDTO<VaccineDTO> response =vaccineService.editVaccine(vaccine);
    		return new ResponseEntity<ResponseDTO<VaccineDTO>>(response,getHttpHeaders(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResponseDTO<VaccineDTO>> getUser(@PathVariable("id") String id) {
    		ResponseDTO<VaccineDTO> response = vaccineService.retreiveVaccine(Long.parseLong(id));
    	    return new ResponseEntity<ResponseDTO<VaccineDTO>>(response,getHttpHeaders(),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<VaccineDTO>>> listIllnesses() {
		ResponseDTO<List<VaccineDTO>> response = vaccineService.listIllnesses();
		return new ResponseEntity<ResponseDTO<List<VaccineDTO>>>(response, getHttpHeaders(), HttpStatus.OK);
	}
}

