package com.app.Azure2PCF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Azure2PCF.model.ProductOrder;
import com.app.Azure2PCF.service.ProductOrderServiceImpl;

@RestController
public class ProductController {
	@Autowired
	ProductOrderServiceImpl productOrderServiceImpl;
//this method purpose of store product in database
	@PostMapping("/saveproduct")
	public ProductOrder saveProduct(@RequestBody ProductOrder productOrder){
		ProductOrder saveproduct = productOrderServiceImpl.saveproduct(productOrder);
		return saveproduct;
		
	}
}
