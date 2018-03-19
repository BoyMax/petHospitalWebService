package com.petHospital.backend.controller;

import org.springframework.http.HttpHeaders;

public class CommonController {

	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.set("Access-Control-Allow-Origin", "*");
		return httpHeader;
	}
}
