package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.petHospital.backend.controller.ExamController;
import com.petHospital.backend.dao.ExamRepository;
import com.petHospital.backend.dao.QuestionRepository;
import com.petHospital.backend.dto.ExamDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Question;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTest 
{
	 @Autowired
	 ExamRepository examRepository;
	 @Autowired
	 QuestionRepository questionRepository;

	//分支覆盖条件
	String existId = "2";
	String notExistId = "200";
	Long categoryId = (long) 2;
	Long categoryNotMatchId =(long)3;
	Long categoryNotExistId = (long) 100;
	
	@Autowired
    private ExamController examController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(examController).isNotNull();
    }
 
    @Test
    @Transactional  
    @Rollback(true)
    public void testListExam()
    {
    		ResponseEntity<ResponseDTO<List<ExamDTO>>> responseEntity = examController.listAllExam();
        assertTrue( responseEntity.getBody().getData().size() >= 0 );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testGetExam()
    {
    	    //id not in db
    		ResponseEntity<ResponseDTO<ExamDTO>> responseEntity1 = examController.getExam(notExistId);
        assertTrue( responseEntity1.getBody().getStatus() == "failed" );
    	    //id is in db
    		ResponseEntity<ResponseDTO<ExamDTO>> responseEntity2 = examController.getExam(existId);
        assertTrue( responseEntity2.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testEditExam(){
 //修改ID存在的试卷
    		ExamDTO examDTO = new ExamDTO();
    		//修改考试ID
    		examDTO.setId(Long.parseLong(existId));
    		
    		//修改时间
    		String str = "02:00:00"; 
    		 SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss"); 
    		 java.util.Date d = null; 
    		  try {
				d = format.parse(str);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
    		 java.sql.Time date = new java.sql.Time(d.getTime()); 
    		 examDTO.setTime(date);
    		
    		 //修改Category
    			Category category = new Category();
    			category.setId(categoryId);
    			examDTO.setCategory(category);
    		//修改Questions
				String questionArray ="[{\"id\":5},{\"id\":6},{\"id\":7},{\"id\":8},{\"id\":9},{\"id\":10},{\"id\":11},{\"id\":12},{\"id\":13},{\"id\":14}]";
                List<Question> questionList =getListByArray(Question.class,questionArray );
				examDTO.setQuestions((List<Question>) questionList );
    			
    	  
    	ResponseEntity<ResponseDTO<ExamDTO>> responseEntity = examController.editExam(examDTO);
        assertTrue( responseEntity.getBody().getStatus() == "success" );
        
        
//修改不存在的试卷
		ExamDTO examDTO2 = new ExamDTO();
		//修改考试ID
		examDTO2.setId(Long.parseLong(notExistId));
		
		//修改时间
		String str2 = "02:00:00"; 
		 SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss"); 
		 java.util.Date d2 = null; 
		 try { 
		  d2 = format2.parse(str2); 
		 } catch (Exception e) { 
		  e.printStackTrace(); 
		 } 
		 java.sql.Time date2 = new java.sql.Time(d2.getTime()); 
		 examDTO2.setTime(date2);
		
		 //修改Category
			Category category2 = new Category();
			category2.setId(categoryId);
			examDTO2.setCategory(category2);
		//修改Questions
			String questionArray2 ="[{\"id\":5},{\"id\":6},{\"id\":7},{\"id\":8},{\"id\":9},{\"id\":10},{\"id\":11},{\"id\":12},{\"id\":13},{\"id\":14}]";
            List<Question> questionList2 =getListByArray(Question.class,questionArray2 );
			examDTO2.setQuestions((List<Question>) questionList2 );
			
	  
	ResponseEntity<ResponseDTO<ExamDTO>> responseEntity2 = examController.editExam(examDTO2);
    assertTrue( responseEntity2.getBody().getStatus() == "failed" );
    
/*修改试卷时传入与category不存在的试题*/
/*修改试卷时传入与category不存在的试题*/
/*修改试卷时传入与category不存在的试题*/
	ExamDTO examDTO3 = new ExamDTO();
	//修改考试ID
	examDTO3.setId(Long.parseLong(existId));
	
	//修改时间
	String str3 = "02:00:00"; 
	 SimpleDateFormat format3 = new SimpleDateFormat("hh:mm:ss"); 
	 java.util.Date d3 = null; 
	 try { 
	  d3 = format3.parse(str3); 
	 } catch (Exception e) { 
	  e.printStackTrace(); 
	 } 
	 java.sql.Time date3 = new java.sql.Time(d3.getTime()); 
	 examDTO2.setTime(date3);
	
	 //修改Category
		Category category3 = new Category();
		category3.setId(categoryNotExistId);
		examDTO3.setCategory(category3);
	//修改Questions
		String questionArray3 ="[{\"id\":11},{\"id\":2},{\"id\":3},{\"id\":4},{\"id\":5},{\"id\":6},{\"id\":7},{\"id\":8},{\"id\":9},{\"id\":10},]";
        List<Question> questionList3 =getListByArray(Question.class,questionArray3 );
		examDTO3.setQuestions((List<Question>) questionList3 );
		
  
ResponseEntity<ResponseDTO<ExamDTO>> responseEntity3 = examController.editExam(examDTO3);
assertTrue( responseEntity3.getBody().getStatus() == "failed" );

    
/*修改试卷时传入与category不匹配的试题*/
/*修改试卷时传入与category不匹配的试题*/
/*修改试卷时传入与category不匹配的试题*/
	    ExamDTO examDTO4 = new ExamDTO();
		//设置修改的考试ID
		examDTO4.setId(Long.parseLong(existId));
		//设置时间
		String str4 = "02:00:00"; 
		 SimpleDateFormat format4 = new SimpleDateFormat("hh:mm:ss"); 
		 java.util.Date d4 = null; 
		 try { 
		  d4 = format4.parse(str4); 
		 } catch (Exception e) { 
		  e.printStackTrace(); 
		 } 
		 java.sql.Time date4 = new java.sql.Time(d4.getTime()); 
		 examDTO4.setTime(date4);
		//设置Category
			Category category4 = new Category();
			category4.setId(categoryNotMatchId);
			examDTO4.setCategory(category4);
		//设置Questions
			String questionArray4 ="[{\"id\":11},{\"id\":5},{\"id\":6},{\"id\":7},{\"id\":8},{\"id\":9},{\"id\":10},{\"id\":16},{\"id\":13},{\"id\":12}]";
	        List<Question> questionList4 =getListByArray(Question.class,questionArray4 );
			examDTO4.setQuestions((List<Question>) questionList4 );
			
			ResponseEntity<ResponseDTO<ExamDTO>> responseEntity4 = examController.editExam(examDTO4);
		    assertTrue( responseEntity4.getBody().getStatus() == "failed" );	    
//	    
	    
	    
        /*修改试卷输入试题数目不等于10*/    
	    /*修改试卷输入试题数目不等于10*/
	    /*修改试卷输入试题数目不等于10*/
	    ExamDTO examDTO5 = new ExamDTO();
		//设置修改的考试ID
		examDTO5.setId(Long.parseLong(existId));
		//设置时间
		String str5 = "02:00:00"; 
		 SimpleDateFormat format5 = new SimpleDateFormat("hh:mm:ss"); 
		 java.util.Date d5 = null; 
		 try { 
		  d5 = format5.parse(str5); 
		 } catch (Exception e) { 
		  e.printStackTrace(); 
		 } 
		 java.sql.Time date5 = new java.sql.Time(d5.getTime()); 
		 examDTO3.setTime(date5);
		//设置Category
			Category category5 = new Category();
			category5.setId(categoryId);
			examDTO5.setCategory(category5);
		//设置Questions
			String questionArray5="[{'id':11}]";
            List<Question> questionList5 =getListByArray(Question.class,questionArray5);
			examDTO3.setQuestions((List<Question>) questionList5);
			
			ResponseEntity<ResponseDTO<ExamDTO>> responseEntity5 = examController.editExam(examDTO5);
		    assertTrue( responseEntity5.getBody().getStatus() == "failed" );
	        
		    
		    /*修改试卷添加不存在的试题*/   
		    /*修改试卷添加不存在的试题*/  
		    /*修改试卷添加不存在的试题*/   
		    ExamDTO examDTO6 = new ExamDTO();
			//设置修改的考试ID
			examDTO6.setId(Long.parseLong(existId));
			//设置时间
			String str6 = "02:00:00"; 
			 SimpleDateFormat format6 = new SimpleDateFormat("hh:mm:ss"); 
			 java.util.Date d6 = null; 
			 try { 
			  d6 = format6.parse(str6); 
			 } catch (Exception e) { 
			  e.printStackTrace(); 
			 } 
			 java.sql.Time date6 = new java.sql.Time(d6.getTime()); 
			 examDTO6.setTime(date6);
			//设置Category
				Category category6 = new Category();
				category6.setId(categoryId);
				examDTO6.setCategory(category6);
			//设置Questions
				String questionArray6="[{\"id\":100},{\"id\":200},{\"id\":11},{\"id\":10},{\"id\":5},{\"id\":6},{\"id\":7},{\"id\":8},{\"id\":9},{\"id\":12},]";
                List<Question> questionList6 =getListByArray(Question.class,questionArray6);
				examDTO6.setQuestions((List<Question>) questionList6);
				
				ResponseEntity<ResponseDTO<ExamDTO>> responseEntity6 = examController.editExam(examDTO6);
			    assertTrue( responseEntity6.getBody().getStatus() == "failed" );
		
		
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testAddExam() {
    		ExamDTO examDTO = new ExamDTO();
    		//设置时间
    		String str = "02:00:00"; 
   		 SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss"); 
   		 java.util.Date d = null; 
   		 try { 
   		  d = format.parse(str); 
   		 } catch (Exception e) { 
   		  e.printStackTrace(); 
   		 } 
   		 java.sql.Time date = new java.sql.Time(d.getTime()); 
   		 examDTO.setTime(date);
   		 //设置category
			Category category = new Category();
			category.setId(categoryId);
			examDTO.setCategory(category);
		 //设置Question
			List<Question> questions=new ArrayList<Question>();
			List<Question> questions_random=new ArrayList<Question>();
			questions = (List<Question>) questionRepository.listQuestionsByCategory(examDTO.getCategory().getId());
			questions_random=com.petHospital.backend.service.ExamServieceImpl.getRandomList(questions,10);
			examDTO.setQuestions(questions_random);
			
		ResponseEntity<ResponseDTO<ExamDTO>> responseEntity = examController.addExam(examDTO);
		assertTrue(responseEntity.getBody().getStatus() == "success" );
    }
    
    @Test
    @Transactional  
    @Rollback(true)
    public void testDeleteExam() {
		ResponseEntity<ResponseDTO<ExamDTO>> responseEntity = examController.deleteExam(existId);
		assertTrue( responseEntity.getBody().getStatus() == "success" );
		ResponseEntity<ResponseDTO<ExamDTO>> responseEntity2 = examController.deleteExam(notExistId);
		assertTrue( responseEntity2.getBody().getStatus() == "failed" );
		
    }
    
    
    /**
     * 根据JSONArray String获取到List
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<Question> getListByArray(Class<T> Question,String jArrayStr) {
        List<Question> question = new ArrayList();
        JSONArray jsonArray = JSONArray.parseArray(jArrayStr);
        if (jsonArray==null || jsonArray.isEmpty()) {
            return question;//nerver return null
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Question question1 = JSONObject.toJavaObject(jsonObject, Question.class);
            question.add(question1);
        }
        return question;
    }

    
}
