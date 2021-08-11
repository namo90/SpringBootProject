package com.app.Azure2PCF.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.model.ExcelToDb;
import com.app.Azure2PCF.repository.ExcelToDbRepository;

@Service
public class ExcelToDbServiceImpl implements ExcelToDbService{
	
	@Autowired
	ExcelToDbRepository excelToDbRepository;
	
	String readLine="";

	@Override
	public void saveCustomerData() {
		// TODO Auto-generated method stub
		
		try {
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\namo.shelke\\excel\\ExceltoDb.xlsx"));
			while((readLine=br.readLine())!=null) {
				
				String data []=readLine.split(",");
				ExcelToDb db=new ExcelToDb();
				db.setId(Integer.parseInt(data[0]));
				db.setName((String) data[1]);
				//db.setSalary( data[2]);
				excelToDbRepository.save(db);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
