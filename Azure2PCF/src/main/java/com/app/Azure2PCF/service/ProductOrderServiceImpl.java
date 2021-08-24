package com.app.Azure2PCF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.model.ProductOrder;
import com.app.Azure2PCF.repository.ProductOrderRepository;

@Service

public class ProductOrderServiceImpl implements ProductOrderService {
	@Autowired
	ProductOrderRepository productOrderRepository;

	@Override
	public ProductOrder saveproduct(ProductOrder p) {
		ProductOrder save = productOrderRepository.save(p);
		return save;
	}

}
