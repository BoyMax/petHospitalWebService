package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.petHospital.backend.controller.UserController;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.dto.UserDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest 
{
	String existId = "1";
	String notExistId = "2";
	
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
    	    UserDTO userDTO = new UserDTO();
    	    userDTO.setName("ken");
    	    userDTO.setPassword("123456");
    		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.login(userDTO);
        assertTrue( responseEntity.getBody().getMessage() == "success");
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditDepartment(){
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setId(Long.parseLong(existId));
	    	userDTO.setName("liqiqi");
	    	userDTO.setPassword("123456");
	    	ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.editUser(userDTO);
	    assertTrue( responseEntity.getBody().getStatus() == "success" );
	    assertTrue( responseEntity.getBody().getData().getName().equals("liqiqi"));
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddUser() {
    		UserDTO userDTO = new UserDTO();
    		userDTO.setName("Bob");
	    	userDTO.setPassword("123456");
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.addUser(userDTO);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteDepartment() {		
		ResponseEntity<ResponseDTO<UserDTO>> responseEntity = userController.deleteUser(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
    }
}
