package com.app.Azure2PCF.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
	// and lower(u.password)=lower(:Password)
	//,@Param("Password")String password
	Optional<UserData> findByusername(String username);
	@Query("select u.id from UserData u where lower(u.username)=lower(:userName)")
	String selectRecordByuserId(@Param("userName")String name);

	//Optional findById(String username);
	
	 //its writing for custom query joinquery
	 
	 @Query("select u.username,o.productName,o.qty from UserData u inner join u.orders o where u.id=1")
		 public List<Object> getJoinQuderyOutput();

}
