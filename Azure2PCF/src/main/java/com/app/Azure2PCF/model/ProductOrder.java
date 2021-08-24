package com.app.Azure2PCF.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Product")
public class ProductOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String productName2;
	private int qty2;
	private int price2;
	private int ui_fk;
}
