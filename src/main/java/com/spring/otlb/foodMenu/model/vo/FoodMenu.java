package com.spring.otlb.foodMenu.model.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date menuDate;
	private String main;
	private String soup;
	private String side1;
	private String side2;
	private String side3;
	private String dessert;

}
