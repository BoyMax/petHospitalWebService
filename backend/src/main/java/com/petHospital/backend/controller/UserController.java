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

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.UserDTO;
import com.petHospital.backend.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserController{

	@Autowired
    UserService userService;// = new UserServiceImpl();
    @Autowired
    UserRepository userRepository;
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity<ResponseDTO<UserDTO>> addUser(@RequestBody UserDTO user) {
    		ResponseDTO<UserDTO> response =userService.createUser(user);
    		return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO<UserDTO>> deleteUser(@PathVariable("id") String id) {
    		ResponseDTO<UserDTO> response = userService.deleteUser(Long.parseLong(id));
    	    return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ResponseEntity<ResponseDTO<UserDTO>> editUser(@RequestBody UserDTO user) {
    		ResponseDTO<UserDTO> response =userService.editUser(user);
    		return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable("name") String name) {
    		ResponseDTO<UserDTO> response = userService.retreiveUser(name);
    	    return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ResponseEntity<ResponseDTO<UserDTO>> login (@RequestBody UserDTO user) {
    		ResponseDTO<UserDTO> response = userService.validateUser(user.getName(),user.getPassword());
    		return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<UserDTO>>> listAllUsers() {
		ResponseDTO<List<UserDTO>> response = userService.listAllUsers();
		return new ResponseEntity<ResponseDTO<List<UserDTO>>>(response,  HttpStatus.OK);
	}
}