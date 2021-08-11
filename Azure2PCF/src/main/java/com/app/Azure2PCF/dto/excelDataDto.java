package com.app.Azure2PCF.dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class excelDataDto {
	@Id
	private int Id;
	private String Name;
	private String Salary;
	

}
