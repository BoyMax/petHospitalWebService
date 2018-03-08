package com.petHospital.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.model.User;
import com.petHospital.backend.service.UserService;
import com.petHospital.backend.service.UserServiceImpl;

@RestController
@RequestMapping(path="/User")
public class UserController {

	@Autowired
    UserService userService;// = new UserServiceImpl();
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/",method=RequestMethod.GET)
    public User getUser(@RequestParam(value="name", defaultValue="Li Vivien") String name) {
    		return userService.retreiveUser(name);
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public User getUser(@RequestBody User user) {
    		return userService.createUser(user.getName(),user.getPassword());
    }
}