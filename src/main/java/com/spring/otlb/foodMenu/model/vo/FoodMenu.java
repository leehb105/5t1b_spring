package com.otlb.semi.foodMenu.model.vo;

import java.sql.Date;

public class FoodMenu {

	private Date menuDate;
	private String main;
	private String soup;
	private String side1;
	private String side2;
	private String side3;
	private String dessert;

	public FoodMenu() {
		super();
	}

	public FoodMenu(Date menuDate, String main, String soup, String side1, String side2, String side3, String dessert) {
		super();
		this.menuDate = menuDate;
		this.main = main;
		this.soup = soup;
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		this.dessert = dessert;
	}

	public Date getMenuDate() {
		return menuDate;
	}

	public void setMenuDate(Date menuDate) {
		this.menuDate = menuDate;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getSoup() {
		return soup;
	}

	public void setSoup(String soup) {
		this.soup = soup;
	}

	public String getSide1() {
		return side1;
	}

	public void setSide1(String side1) {
		this.side1 = side1;
	}

	public String getSide2() {
		return side2;
	}

	public void setSide2(String side2) {
		this.side2 = side2;
	}

	public String getSide3() {
		return side3;
	}

	public void setSide3(String side3) {
		this.side3 = side3;
	}

	public String getDessert() {
		return dessert;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	@Override
	public String toString() {
		return "FoodMenu [menuDate=" + menuDate + ", main=" + main + ", soup=" + soup + ", side1=" + side1 + ", side2="
				+ side2 + ", side3=" + side3 + ", dessert=" + dessert + "]";
	}
	
	public String listMenu() {
		return main + "<br />" + soup + "<br />" + side1 + "<br />" + side2 + "<br />" + side3 + "<br />" + dessert;
	}

}
