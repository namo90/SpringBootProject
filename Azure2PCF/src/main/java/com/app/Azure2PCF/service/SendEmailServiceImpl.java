package com.app.Azure2PCF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.config.MailConfiguration;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.Order;

@Service
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	MailConfiguration mailconf;

	@Override
	public void sendEmail(Order order) {
		// create mail sender
		JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
		mailsender.setHost(this.mailconf.getHost());

		mailsender.setPort(this.mailconf.getPort());

		mailsender.setUsername(this.mailconf.getUsername());

		mailsender.setPassword(this.mailconf.getPassword());

		// create email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(this.mailconf.getFromemail());
		mailMessage.setTo(this.mailconf.getEmail());
		mailMessage.setSubject(this.mailconf.getMailSubject() + " " + order.getProductName());
		mailMessage.setText("Product Name :"+order.getProductName() +" "+"Product Qty : "+order.getQty()+" "+"Product Price : "+ order.getPrice()+" "+this.mailconf.getMailText() );

		// send mail
		mailsender.send(mailMessage);

	}

}
