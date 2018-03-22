package com.petHospital.backend.service;

import com.petHospital.backend.dto.ResponseDTO;

import java.util.Map;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.petHospital.backend.dto.MultimediaDTO;

public interface MultimediaService {
	ResponseDTO<List<MultimediaDTO>> uploadPic(Map<String, MultipartFile> files, String url);
}
