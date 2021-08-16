package com.app.Azure2PCF.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="login")
@Entity
@ToString
public class UserData {
	
	@Id

	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ui_fk",referencedColumnName = "id")
	private List<Order>orders=new ArrayList<>();

}