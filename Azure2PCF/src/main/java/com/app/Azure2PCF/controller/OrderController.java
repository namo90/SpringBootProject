package com.app.Azure2PCF.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.Azure2PCF.dao.OrderDao;
import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.jquery;
import com.app.Azure2PCF.dto.orderRequestDto;
import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.service.OrderServiceImpl;
import com.app.Azure2PCF.service.SendEmailServiceImpl;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@RestController
@SessionAttributes("name")
@CrossOrigin(origins = "*")
public class OrderController {
	@Autowired
	OrderServiceImpl customerOrderServiceImpl;
	@Autowired
	UserDataConvertorDto userDataConvertorDto;
	@Autowired
	UserDataServiceImpl userDataServiceImpl;
	@Autowired
	SendEmailServiceImpl sendEmailServiceImpl;
	@Autowired
	OrderDao orderDao;

	@PostMapping("/save")

	public ResponseEntity<Object> registerNewUser(@RequestBody orderRequestDto orderDto, HttpSession session)
			throws Exception {

		var order = userDataConvertorDto.dtoToEntityOrderData(orderDto);

		var u = customerOrderServiceImpl.saveOrder(order);
		orderRequestDto orderDtoVal = userDataConvertorDto.entityToDtoOrderDataDto(u);

		sendEmailServiceImpl.sendEmail(order);
		return ResponseEntity.ok(orderDtoVal);

	}

	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody orderRequestDto dto) {

		Order order = userDataConvertorDto.dtoToEntityOrderData(dto);
		return customerOrderServiceImpl.saveOrder(order);
	}

	@GetMapping("/findAllOrders")
	public List<Order> findAllOrders() {

		return customerOrderServiceImpl.getAllOrders();
	}

	@GetMapping("/findByOrder")
	public Optional<Order> getRecord(@RequestParam("id") int uId) {
		System.out.println("======= " + uId);
		return customerOrderServiceImpl.getOrderRecord(uId);

	}

	@GetMapping("/findByUser")
	public ResponseEntity<Object> getAllrecords(@RequestParam("id") int uId) {
		System.out.println("======= " + uId);
		List<Order> ListOfUserOrder = customerOrderServiceImpl.getAllOrderRecords(uId);
		return ResponseEntity.ok(ListOfUserOrder);

	}
	
	@GetMapping("/filterorder")
	public ResponseEntity<Object> getFilterRecord(@RequestParam("keyword") String keyword) {
		System.out.println("======= " + keyword);
		List<Order> ListOfUserOrder = customerOrderServiceImpl.getFilterRecords(keyword);
		return ResponseEntity.ok(ListOfUserOrder);

	}

	@DeleteMapping("/deleterecord")
	public String deleteOrder(@RequestParam("id") int id) {

		Optional<Order> orderid = customerOrderServiceImpl.getOrderRecord(id);
		if (orderid.isPresent()) {
			customerOrderServiceImpl.deleteRecord(id);
			return "Order deleted with id " + id;
		} else {
			throw new RuntimeException("Order not found for the id" + id);
		}
	}

	@PutMapping("/updateorder")
	public Order updateOrder(@RequestBody orderRequestDto dto) {
		Order updateorder = userDataConvertorDto.dtoToEntityOrderForUpdate(dto);
		return customerOrderServiceImpl.updateRecord(updateorder);
	}
	
	@GetMapping("/gettotalrecords")
	public List<Order> function() {
		
		return customerOrderServiceImpl.gettotalOrders();
	}
	
	@GetMapping("/getordersinfoprocedure")
	public ResponseEntity<Object> getordersinfo() {
		
		List<Order> ListOfUserOrder = orderDao.getorderInfo();
		return ResponseEntity.ok(ListOfUserOrder);

	}
	
	@GetMapping("/getinnerjoin")
	public ResponseEntity<Object> getinnerinfo() {
		
		Query ListOfUserOrder = orderDao.getinnerjoinop();
		return ResponseEntity.ok(ListOfUserOrder);

	}
	
	
	
	@GetMapping("/getordersinfoview")
	public ResponseEntity<Object> getordersinfo1() {
		
		Query ListOfUserOrder = orderDao.getorderView();
		return ResponseEntity.ok(ListOfUserOrder);

	}
	
	@GetMapping("/getorderfuncation")
	public int fun() {
		return orderDao.getorder();
	}

	/*
	 * //its calling get joinquryoutput
	 * 
	 * @GetMapping("/getjoinqueryop") public List<Object> getjoinqueryop(){
	 * List<Object> joinQuderyOutput =
	 * customerOrderServiceImpl.getJoinQuderyOutput(); if(joinQuderyOutput !=null) {
	 * return joinQuderyOutput; }
	 * 
	 * return null;
	 * 
	 * }
	 */
}
