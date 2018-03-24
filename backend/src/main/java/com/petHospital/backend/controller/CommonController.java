package com.petHospital.backend.controller;

import org.springframework.http.HttpHeaders;

public class CommonController {

	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.set("Access-Control-Allow-Origin", "*");
		httpHeader.set("Access-Control-Allow-Methods","PUT,POST,GET,DELETE");
		return httpHeader;
	}
}
