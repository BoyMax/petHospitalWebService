package com.petHospital.backend;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.MultimediaService;
import com.petHospital.backend.dto.MultimediaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultimediaTest {
	private HttpServletRequest request;
	@Autowired
	private MultimediaService multimediaService;

	@Test
	public void contexLoads() throws Exception {
		assertThat(multimediaService).isNotNull();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testPicUpload() throws IOException {;
		Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
		// 读入 文件   
        File file = new File("/Users/Vivien/Desktop/test.jpg");  
        FileInputStream in_file = new FileInputStream(file);  
          
        // 转 MultipartFile  
        MultipartFile multi = new MockMultipartFile("test.jpg", in_file);  
  
        // 创建文件夹  
        String dire = "/Users/Vivien/Desktop/test.jpg";  
        File file_dire = new File(dire);   
          
        //写入文件   
        multi.transferTo(file_dire);  
        files.put("test", multi);
		
		ResponseDTO<List<MultimediaDTO>> responseEntity = multimediaService.uploadPic(files, "/Users/Vivien/Documents/PetHospital/home/images/", 1, 0, 0); 
		assertTrue(responseEntity.getStatus() == "success");
		//upload failed assertTrue( responseEntity.getBody().getStatus() == "failed");
		 
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testVedioUpload() {
		// everything is fine
		request = new MockHttpServletRequest();
		request.setAttribute("caseType", 0);
		request.setAttribute("caseId", 0);
		Map<String, File> files = new HashMap<String, File>();
		File file = new File("test.mp4");
		files.put("test", file);
		request.setAttribute("file", files);
		/*
		 * ResponseEntity<ResponseDTO<List<MultimediaDTO>>> responseEntity =
		 * multimediaController.upload(request); assertTrue(
		 * responseEntity.getBody().getStatus() == "success");
		 * 
		 * //upload failed assertTrue( responseEntity.getBody().getStatus() ==
		 * "failed");
		 */
	}
}
