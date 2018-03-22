package com.petHospital.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.petHospital.backend.dao.MultimediaRepository;
import com.petHospital.backend.dto.MultimediaDTO;
import com.petHospital.backend.dto.ResponseDTO;
import com.petHospital.backend.model.Multimedia;

@Service
public class MultimediaServiceImpl implements MultimediaService{
	@Autowired
	MultimediaRepository multimediaRepository;

	public ResponseDTO<List<MultimediaDTO>> uploadPic(Map<String, MultipartFile> files, String url) {
		ResponseDTO<List<MultimediaDTO>> responseDTO = new ResponseDTO<List<MultimediaDTO>>();
		List<MultimediaDTO> list = new ArrayList<MultimediaDTO>();
		File dir = new File(url);
        System.out.println(url);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();
        for(MultipartFile file :files.values()){String fileName=file.getOriginalFilename();
            File tagetFile = new File(url + fileName);//创建文件对象
            Multimedia multimedia = new Multimedia();
            MultimediaDTO multimediaDTO = new MultimediaDTO();
            if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
                }
                try {
                    file.transferTo(tagetFile);
                } catch (IllegalStateException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
                } catch (IOException e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
                }
                try {
                	multimedia.setType(0);
                    multimedia.setUrl(url + fileName);
                    multimedia.setCaseType(0);
                    multimedia = multimediaRepository.save(multimedia);
                } catch (Exception e) {
                	responseDTO.setStatus("failed");
        			responseDTO.setMessage(e.getMessage());
                }
                multimediaDTO.setId(multimedia.getId());
                multimediaDTO.setType(0);
                multimediaDTO.setUrl(url + fileName);
                multimediaDTO.setCaseType(0);
                list.add(multimediaDTO);
            }
        }
    responseDTO.setMessage("success");
	responseDTO.setStatus("success");
    responseDTO.setData(list);
    System.out.println("接收完毕");
    return responseDTO;
	}
	
}
