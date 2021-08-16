package com.app.Azure2PCF.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.customException.EmptyIdException;
import com.app.Azure2PCF.customException.EmptyInputException;
import com.app.Azure2PCF.dto.jquery;
import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.repository.OrderRepository;

@Service("OrderService")

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository customerRepository;

	public List<Order> getAllOrders() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Order> getOrderRecord(int userid) {
		if (userid == 0) {
			throw new EmptyIdException();
		}

		return customerRepository.findById(userid);
	}

	@Override
	@javax.transaction.Transactional
	public void deleteRecord(int id) {
		if (id == 0) {
			throw new EmptyIdException();
		}

		customerRepository.deleteById(id);
	}

	@Override
	public Order saveOrder(Order order) {
		if (order.getPrice() == 0) {
			throw new EmptyInputException("600", "order is empty input");
		}
		return customerRepository.save(order);
	}

	@Override
	public Order updateRecord(Order order) {
		if (order.getPrice() == 0 || order.getProductName().isEmpty()) {
			throw new EmptyInputException("601", "order is empty input");
		}

		return customerRepository.save(order);
	}

	@Override
	public List<Order> getAllOrderRecords(int id) {
		// TODO Auto-generated method stub
		List<Order> ListOfallUserOrder = customerRepository.selectAllRecord(id);
		return ListOfallUserOrder;
	}

	@Override
	public List<Order> getFilterRecords(String keword) {
		List<Order> ListOfallFilterOrder = customerRepository.filterRecord(keword);

		return ListOfallFilterOrder;
	}

	@Override
	public List<Order> getordersinfo() {
		// TODO Auto-generated method stub
		List<Order> getordersDetails=OrderRepository.getorderInfo();
		return getordersDetails;
	}

	@Override
	public List<Order> gettotalOrders() {
		// TODO Auto-generated method stub
		return customerRepository.Nqgetfunctionop();
	}

	
	/*
	 * //its get joinqueryOutput
	 * 
	 * @Override public List<Object> getJoinQuderyOutput() {
	 * 
	 * return customerRepository.getJoinQuderyOutput(); }
	 */

}
