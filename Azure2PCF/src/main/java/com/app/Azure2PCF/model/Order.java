package com.app.Azure2PCF.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="firstprocedure",procedureName = "getordersinfo"),
	@NamedStoredProcedureQuery(name="firstprocedure1",procedureName = "gettotalorders",parameters = {
			@StoredProcedureParameter(mode=ParameterMode.OUT,name="totalorders", type = Integer.class)
	})	})
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String productName;
	private int qty;
	private int price;
	private int ui_fk;
}
