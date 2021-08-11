package com.app.Azure2PCF.service;

import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.Order;

public interface SendEmailService {
	
	public void sendEmail(Order order);

}
