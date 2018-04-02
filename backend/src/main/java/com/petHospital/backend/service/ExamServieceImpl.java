package com.petHospital.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.petHospital.backend.dao.ExamRepository;
import com.petHospital.backend.dao.QuestionRepository;
import com.petHospital.backend.dto.ExamDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Exam;
import com.petHospital.backend.model.Question;



@Service
public class ExamServieceImpl implements ExamService{
	

	 @Autowired
	 ExamRepository examRepository;
	 @Autowired
	 QuestionRepository questionRepository;
	 
	 
	public ResponseDTO<ExamDTO> retreiveExam(Long id) {	
	ResponseDTO<ExamDTO> responseDTO = new ResponseDTO<ExamDTO>();
	Exam exam = new Exam();
	ExamDTO examDTO = new ExamDTO();
	try {
		exam = examRepository.findOne(id);
		if (exam == null) {
			responseDTO.setMessage("Exam"+id+"does not exist.");
			responseDTO.setStatus("failed");
			return responseDTO;
		}
	}catch(Exception e) {
		responseDTO.setMessage(e.getMessage());
		responseDTO.setStatus("failed");
	}
	examDTO.setId(exam.getId());
	examDTO.setTime(exam.getTime());
	examDTO.setCategory(exam.getCategory());
	examDTO.setQuestions(exam.getQuestion());
	responseDTO.setMessage("success");
	responseDTO.setStatus("success");
	responseDTO.setData(examDTO);
	return responseDTO;
}

 public ResponseDTO<ExamDTO> createExam(ExamDTO examDTO) {
	ResponseDTO<ExamDTO> responseDTO = new ResponseDTO<ExamDTO>();
	Exam exam = new Exam();
	List<Question> questions=new ArrayList<Question>();
	List<Question> questions_random=new ArrayList<Question>();
	
	questions = (List<Question>) questionRepository.getQuestionsByCatagory(examDTO.getCategory().getId());
	questions_random=getRandomList(questions,10);
	
	exam.setTime(examDTO.getTime());
	exam.setCategory(examDTO.getCategory());
    exam.setQuestion(questions_random);

	try {
		exam = examRepository.save(exam);
		examDTO.setId(exam.getId());
		examDTO.setTime(exam.getTime());
		examDTO.setCategory(exam.getCategory());
		examDTO.setQuestions(exam.getQuestion());
		responseDTO.setStatus("success");
		responseDTO.setMessage("success");
		responseDTO.setData(examDTO);
	}catch(Exception e){
		responseDTO.setStatus("failed");
		responseDTO.setMessage(e.getMessage());
	}
	return responseDTO;
}

 
 
 
 
 
 
 
 
 
public ResponseDTO<ExamDTO> deleteExam(Long id) {
	ResponseDTO<ExamDTO> responseDTO = new ResponseDTO<ExamDTO>();
	ExamDTO examDTO = new ExamDTO();
	Exam exam = examRepository.findOne(id);
	if(exam == null) {
		responseDTO.setMessage("Exam "+id+" does not exist");
		responseDTO.setStatus("failed");
		return responseDTO;
	}
	try {
		examRepository.delete(id);
		responseDTO.setMessage("success");
		responseDTO.setStatus("success");
		responseDTO.setData(examDTO);
	}catch(Exception e){
		responseDTO.setMessage(e.getMessage());
		responseDTO.setStatus("failed");
	}
	return responseDTO;
}

public ResponseDTO<ExamDTO> editExam(ExamDTO examDTO) {
	ResponseDTO<ExamDTO> responseDTO = new ResponseDTO<ExamDTO>();
	Exam exam = examRepository.findOne(examDTO.getId());//得到要修改的考试ID
	exam.setTime(examDTO.getTime());
	exam.setCategory(examDTO.getCategory());
//	exam.setQuestion(examDTO.getQuestions());
	
	if(validatQuestions(examDTO,exam,responseDTO) == false) {
		return responseDTO;
	}
	try {
		examRepository.save(exam);
		examDTO.setId(exam.getId());
		examDTO.setTime(exam.getTime());
		examDTO.setCategory(exam.getCategory());
		examDTO.setQuestions(exam.getQuestion());
		responseDTO.setMessage("success");
		responseDTO.setData(examDTO);
		responseDTO.setStatus("success");
	}catch(Exception e){
		responseDTO.setMessage(e.getMessage());
		responseDTO.setStatus("failed");
	}
	return responseDTO;
}




// Validate manager id from DepartmentDTO.getManagers and then set valid manager
// to department.
private boolean validatQuestions(ExamDTO examDTO, Exam exam, ResponseDTO<ExamDTO> responseDTO) {
	List<Question> questions = examDTO.getQuestions();
	List<Question> questionEntitys = new ArrayList<Question>();
	
	int count=0;//试题个数
	if (questions != null) {
		
		
		for (Iterator<Question> it = questions.iterator(); it.hasNext();) {
			Question question = it.next();
			
			Long questionId = question.getId();
			question = questionRepository.findOne(questionId);
			if (question == null) {
				it.remove();
				responseDTO.setError_code("404");
				responseDTO.setStatus("failed");
				responseDTO.setMessage("Question does not exist whoses id =" + questionId);
				return false;
			}
		//验证每个question的category_id 和 exam的category_id是否一致
			if(examDTO.getCategory().getId()!=question.getCategory().getId()) {
				it.remove();
				responseDTO.setError_code("404");
				responseDTO.setStatus("failed");
				responseDTO.setMessage("Question " + questionId +" doesn't match the category");
							return false;}
			
			
			questionEntitys.add(question);
			count++;
		}
	}
	
	if(count!=10) {
		responseDTO.setError_code("404");
		responseDTO.setStatus("failed");
		responseDTO.setMessage("Each exam needs 10 questions");
					return false;}
	
	
	exam.setQuestion(questionEntitys);
	return true;
}


	



//随机抽取题目
/**
 * @function:从list中随机抽取若干不重复元素
 *
 * @param paramList:被抽取list
 * @param count:抽取元素的个数
 * @return:由抽取元素组成的新list
 */
public static List<Question> getRandomList(List<Question> paramList,int count){

//   if(paramList.size()<count){
//        return paramList;
//    }
    Random random=new Random();
    List<Integer> tempList=new ArrayList<Integer>();
    List<Question> newList=new ArrayList<Question>();
    int temp=0;
    for(int i=0;i<count && i<=paramList.size();i++){
        temp=random.nextInt(paramList.size());//将产生的随机数作为被抽list的索引
        if(!tempList.contains(temp)){
            tempList.add(temp);
            newList.add(paramList.get(temp));
        }
        else{
            i--;
        }   
    }
    return newList;
}

@Override
public ResponseDTO<List<ExamDTO>> listAllExam() {
	ResponseDTO<List<ExamDTO>> responseDTO = new ResponseDTO<List<ExamDTO>>();
	ArrayList<ExamDTO> examDTOs = new ArrayList<ExamDTO>();
	List<Exam> exam= new ArrayList<Exam>();
	try {
		exam = (List<Exam>) examRepository.findAll();
	} catch (Exception e) {
		responseDTO.setStatus("failed");
		responseDTO.setMessage(e.getMessage());
		return responseDTO;
	}
	for (Exam exam1 : exam) {
		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(exam1.getId());
		examDTO.setTime(exam1.getTime());
		examDTO.setCategory(exam1.getCategory());
		examDTO.setQuestions(exam1.getQuestion());
		examDTOs.add(examDTO);
	}
	responseDTO.setMessage("status");
	responseDTO.setStatus("success");
	responseDTO.setData(examDTOs);
	
	return responseDTO;
	
}
}


