package com.petHospital.backend.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.petHospital.backend.dto.MultimediaDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.service.MultimediaService;

@RestController
@RequestMapping(path = "/multimedia")
public class MultimediaController extends CommonController{
	@Autowired
	MultimediaService multimediaService;
	
	@RequestMapping(value = "/uploader", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO<List<MultimediaDTO>>> upload(HttpServletRequest request){
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        String upaloadUrl = "/home/images/";//得到当前工程路径拼接上文件名
        int caseType = Integer.valueOf(request.getParameter("caseType"));
        long caseId = Long.valueOf(request.getParameter("caseId"));
        /*System.out.println(request.getParameter("caseId"));
        System.out.println(request.getParameter("caseType"));*/
        ResponseDTO<List<MultimediaDTO>> response = multimediaService.uploadPic(files, upaloadUrl, caseId, caseType);
        return new ResponseEntity<ResponseDTO<List<MultimediaDTO>>>(response, getHttpHeaders(), HttpStatus.OK);
	}
}
