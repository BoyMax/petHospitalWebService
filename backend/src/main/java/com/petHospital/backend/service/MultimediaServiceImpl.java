package com.petHospital.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.petHospital.backend.dao.IllnessRepository;
import com.petHospital.backend.dao.MultimediaRepository;
import com.petHospital.backend.dto.MultimediaDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Illness;
import com.petHospital.backend.model.Multimedia;

@Service
public class MultimediaServiceImpl implements MultimediaService{
	@Autowired
	MultimediaRepository multimediaRepository;
	
	@Autowired
	IllnessRepository illnessRepository;
	public synchronized ResponseDTO<List<MultimediaDTO>> uploadPic(Map<String, MultipartFile> files, String url, long caseId, int caseType) {
		Illness illness = illnessRepository.findOne(caseId);
		ResponseDTO<List<MultimediaDTO>> responseDTO = new ResponseDTO<List<MultimediaDTO>>();
		//存储返回多媒体DTO
		List<MultimediaDTO> list = new ArrayList<MultimediaDTO>();
		//存储病例多媒体一对多的多媒体列表
		List<Multimedia> multimedias = illness.getMultimedias();
		File dir = new File(url);
        System.out.println(url);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();
        for(MultipartFile file :files.values()){
        	//String fileName=file.getOriginalFilename();
        	String fileName = String.valueOf(new Date().getTime()) + file.getOriginalFilename();
            File tagetFile = new File(url + fileName);//创建文件对象
            Multimedia multimedia = new Multimedia();
            MultimediaDTO multimediaDTO = new MultimediaDTO();
            if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
        			return responseDTO;
                }
                try {
                    file.transferTo(tagetFile);
                } catch (IllegalStateException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
        			return responseDTO;
                } catch (IOException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
        			return responseDTO;
                }
                try {
                	multimedia.setType(0);
                    multimedia.setUrl(url + fileName);
                    multimedia.setCaseType(caseType);
                    multimedia = multimediaRepository.save(multimedia);
                    multimedias.add(multimedia);
                } catch (Exception e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
        			return responseDTO;
                }
                multimediaDTO.setId(multimedia.getId());
                multimediaDTO.setType(0);
                multimediaDTO.setUrl(url + fileName);
                multimediaDTO.setCaseType(0);
                list.add(multimediaDTO);
            }
            illness.setMultimedias(multimedias);
            illnessRepository.save(illness);
        }
    responseDTO.setMessage("success");
	responseDTO.setStatus("success");
    responseDTO.setData(list);
    System.out.println("接收完毕");
    return responseDTO;
	}
	
}
