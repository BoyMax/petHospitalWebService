package com.petHospital.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dto.UserDTO;
import com.petHospital.backend.service.UserService;

@RestController
@RequestMapping(path="/User")
public class UserController {

	@Autowired
    UserService userService;// = new UserServiceImpl();
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
    	    UserDTO response =userService.createUser(user.getName(),user.getPassword());
    		return new ResponseEntity<UserDTO>(response,null,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/deletet/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deletetUser(@PathVariable("id") String id) {
    	    UserDTO response = userService.deleteUser(Long.parseLong(id));
    	    return new ResponseEntity<UserDTO>(response,null,HttpStatus.OK);
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
    	    UserDTO response =userService.editUser(user);
    		return new ResponseEntity<UserDTO>(response,null,HttpStatus.OK);
    }
    
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("name") String name) {
    	    UserDTO response = userService.retreiveUser(name);
    	    return new ResponseEntity<UserDTO>(response,null,HttpStatus.OK);
    }
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ResponseEntity<UserDTO> login (@RequestBody UserDTO user) {
     	UserDTO response = userService.validateUser(user.getName(),user.getPassword());
    		return new ResponseEntity<UserDTO>(response,null,HttpStatus.OK);
    }
}