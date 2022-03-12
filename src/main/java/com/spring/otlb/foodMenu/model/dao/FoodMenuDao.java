package com.spring.otlb.foodMenu.model.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.spring.otlb.foodMenu.model.vo.FoodMenu;

public interface FoodMenuDao {

	public List<FoodMenu> selectYearMonth(Date date);

	public FoodMenu selectFoodMenu();
}
