package com.app.Azure2PCF.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.model.Order;

@Repository

public class OrderDao {
	
	@Autowired
	private EntityManager em;
	 @SuppressWarnings("unchecked")
	public List<Order> getorderInfo(){
		 List<Order> data= em.createNamedStoredProcedureQuery("firstprocedure").getResultList();
		 System.out.println("procdure o/p"+"-------"+data);
		return data;
		
		
	}
	 
	 @SuppressWarnings("unchecked")
	public List<Order> getorderInfofun(){
		 List<Order> data= em.createNamedStoredProcedureQuery("firstprocedure1").getResultList();
		 System.out.println("procdure o/p"+"-------"+data);
		return data;
		
		
	}
	 
	 public int getorder() {
	int count=(int) em.createNativeQuery("select gettotalorders()").getSingleResult();
	return count;
		 
	 }
	 
	 public Query getorderView(){
	@SuppressWarnings("unchecked")
	Query orderlist=	 em.createNativeQuery("select orders_view()");
		return orderlist;
		 
	 }

}
