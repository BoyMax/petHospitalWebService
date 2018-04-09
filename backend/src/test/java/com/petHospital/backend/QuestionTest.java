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

import com.petHospital.backend.controller.QuestionController;
import com.petHospital.backend.dto.QuestionDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.User;
 



@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTest 
{
	//分支覆盖条件
	String existId = "1";
	String notExistId = "100";
	Long categoryId = (long) 1;
	Long categoryNotExistId = (long) 100;
	
	@Autowired
    private QuestionController questionController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(questionController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListDepartment()
    {
    		ResponseEntity<ResponseDTO<List<QuestionDTO>>> responseEntity = questionController.listAllQuestion();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetQuestion()
    {
    	    //id not in db
		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity1 =questionController.getQuestion(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity2 = questionController.getQuestion(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testlistQuestionByCategory()
    {
    	    //id not in db
		ResponseEntity<ResponseDTO<List<QuestionDTO>>> responseEntity1 =questionController.listQuestionByCategory(categoryNotExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    	ResponseEntity<ResponseDTO<List<QuestionDTO>>> responseEntity2 = questionController.listQuestionByCategory(categoryId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditQuestion(){
    		QuestionDTO questionDTO = new QuestionDTO();
    		questionDTO.setId(Long.parseLong(existId));
    		questionDTO.setAskDescription("以下哪个选项可能是红眼病的病因？");
    		questionDTO.setAdescription("A宠物用品不卫生");
    		questionDTO.setBdescription("B饲料不卫生");
    		questionDTO.setCdescription("C流感引起");
    		questionDTO.setDdescription("D宠物没吃饱");
    		questionDTO.setAnswer("A");
    		questionDTO.setQuestionType(0);
    		questionDTO.setUserType(1);
    		
    		
    		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity = questionController.editQuestion(questionDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
        
        
        QuestionDTO questionDTO2 = new QuestionDTO();
		questionDTO2.setId(Long.parseLong(notExistId));
		questionDTO.setAskDescription("以下哪个选项可能是红眼病的病因？");
		questionDTO.setAdescription("A宠物用品不卫生");
		questionDTO.setBdescription("B饲料不卫生");
		questionDTO.setCdescription("C流感引起");
		questionDTO.setDdescription("D宠物没吃饱");
		questionDTO.setAnswer("A");
		questionDTO.setQuestionType(0);
		questionDTO.setUserType(1);
		
		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity2 = questionController.editQuestion(questionDTO2);
    assertTrue( responseEntity2.getBody().getStatus() == "failed" );
        
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddQuestion() {
    		QuestionDTO questionDTO = new QuestionDTO();
    		questionDTO.setAskDescription("以下哪个选项可能是红眼病的病因？");
    		questionDTO.setAdescription("A宠物用品不卫生");
    		questionDTO.setBdescription("B饲料不卫生");
    		questionDTO.setCdescription("C流感引起");
    		questionDTO.setDdescription("D宠物没吃饱");
    		questionDTO.setAnswer("A");
    		questionDTO.setQuestionType(0);
    		questionDTO.setUserType(1);
		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity = questionController.addQuestion(questionDTO);
		assertTrue(responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteQuestion() {
		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity = questionController.deleteQuestion(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
		
		ResponseEntity<ResponseDTO<QuestionDTO>> responseEntity2 = questionController.deleteQuestion(notExistId);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    }
}
