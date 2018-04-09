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

import com.petHospital.backend.controller.UserController;
import com.petHospital.backend.dto.DepartmentDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.UserDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest 
{
	String existId = "1";
	String existUserName = "ken";
	String existPassword = "123456";	
	
	Long existDepartmentId = 2L;
	
	String notExistId = "100";
	String notExistUserName = "pappa";
	String notExistPassword = "000000";
	 Long notExistDepartmentId=100L;
	
	String userNameWithoutDepartment = "minna";
	 
	@Autowired
    private UserController userController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testUserLogin()
    {
    	    //everything is fine:
    	    UserDTO userDTO = new UserDTO();
    	    userDTO.setName(existUserName);
    	    userDTO.setPassword(existPassword);
    		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.login(userDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success");
        
        //user does not exist
        userDTO.setName(notExistUserName);
	    userDTO.setPassword(existPassword);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity2 = userController.login(userDTO);
		assertTrue( responseEntity2.getBody().getStatus() == "failed");
		
		//password is not correct:
		 userDTO.setName(existUserName);
		 userDTO.setPassword(notExistPassword);
	     ResponseEntity<ResponseDTO<UserDTO>> responseEntity3 = userController.login(userDTO);
	     assertTrue( responseEntity3.getBody().getStatus() == "failed");
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testListUsers()
    {
    		ResponseEntity<ResponseDTO<List<UserDTO>>> responseEntity = userController.listAllUsers();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetUser()
    {
    	    //id in db
    		ResponseEntity<ResponseDTO<UserDTO>> responseEntity1 = userController.getUser(existUserName);
        assertTrue( responseEntity1.getBody().getStatus() == "success" );
        
    	    //id not in db
    		ResponseEntity<ResponseDTO<UserDTO>> responseEntity2 = userController.getUser(notExistUserName);
        assertTrue( responseEntity2.getBody().getStatus() == "failed" );
       
        //userNameWithoutDepartment
        ResponseEntity<ResponseDTO<UserDTO>> responseEntity3 = userController.getUser(userNameWithoutDepartment);
        assertTrue( responseEntity3.getBody().getStatus() == "success" );
        
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditUser(){
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setId(Long.parseLong(existId));
	    	userDTO.setName("liqiqi");
	    	userDTO.setPassword("123456");
	    	DepartmentDTO departmentDTO = new DepartmentDTO();
	    	departmentDTO.setId(existDepartmentId);
	    	userDTO.setDepartment(departmentDTO);
	    	ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.editUser(userDTO);
	    assertTrue( responseEntity.getBody().getStatus() == "success" );
	    assertTrue( responseEntity.getBody().getData().getName().equals("liqiqi"));
	    
		departmentDTO.setId(notExistDepartmentId);
		userDTO.setDepartment(departmentDTO);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity2 = userController.editUser(userDTO);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
		
		
		userDTO.setDepartment(null);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity3 = userController.editUser(userDTO);
		assertTrue( responseEntity3.getBody().getStatus() == "success" );
		
		//userNameWithoutDepartment withoutid	
		DepartmentDTO departmentDTO3 = new DepartmentDTO();
		userDTO.setDepartment(departmentDTO3);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity4 = userController.editUser(userDTO);
		assertTrue( responseEntity4.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddUser() {
    		UserDTO userDTO = new UserDTO();
    		userDTO.setName("Bob111");
	    	userDTO.setPassword("123456");
	    DepartmentDTO departmentDTO = new DepartmentDTO();
	    	departmentDTO.setId(existDepartmentId);
	    	userDTO.setDepartment(departmentDTO);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.addUser(userDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
		
		//userNameWithoutDepartment
		UserDTO userDTO2 = new UserDTO();
		userDTO2.setName("Ben111");
		userDTO2.setPassword("123456");
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity2 = userController.addUser(userDTO2);
		assertTrue( responseEntity2.getBody().getStatus() == "success" );
		
		//userNameWithoutDepartment withoutid
		UserDTO userDTO3 = new UserDTO();
		userDTO3.setName("Ben333");
		userDTO3.setPassword("123456");
		DepartmentDTO departmentDTO3 = new DepartmentDTO();
		userDTO3.setDepartment(departmentDTO3);
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity3 = userController.addUser(userDTO3);
		assertTrue( responseEntity3.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteDepartment() {		
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.deleteUser(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
		
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity2 = userController.deleteUser(notExistId);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    }
}
