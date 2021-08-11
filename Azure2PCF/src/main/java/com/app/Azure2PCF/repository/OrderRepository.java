package com.app.Azure2PCF.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.dto.orderResponseDto;
import com.app.Azure2PCF.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Autowired
	public static final EntityManager em1 = null;
	/*
	 * @Query("SELECT new com.app.Azure2PCF.dto.orderResponse(c.name , p.productName) FROM CustomerOrder c JOIN c.ProductOrder p"
	 * ) public List<orderResponseDto> getJoinInformation();
	 */
	@Query("from Order o where lower(o.ui_fk)=lower(:userId)")
	public List<Order> selectAllRecord(@Param("userId") int id);
	
	@Query("from Order o where o.productName LIKE %?1%"
			+"OR o.price LIKE %?1%"
			+"OR o.qty LIKE %?1%")
	public List<Order> filterRecord(String keyword);
	
	//i have tried calling funtion but not working
	@Query(nativeQuery = true, value = "SELECT  gettotalorders()")
	public List<Order> Nqgetfunctionop();
	
	//i have tried calling procedure but not working
	 @SuppressWarnings("unchecked")
	public static List<Order> getorderInfo(){
		 List<Order> data= em1.createNamedStoredProcedureQuery("firstprocedure").getResultList();
		 System.out.println("procdure o/p"+"-------"+data);
		return data;
		
		
	}
}
