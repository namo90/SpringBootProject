package com.app.Azure2PCF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Azure2PCF.service.ExcelToDbServiceImpl;

@RestController
public class ExcelToDbController {
	
	@Autowired
	ExcelToDbServiceImpl excelToDbServiceImpl;
@PostMapping("/savedataexceltodb")
	public void savdataExcelToDb() {
		excelToDbServiceImpl.saveCustomerData();
	}
}
