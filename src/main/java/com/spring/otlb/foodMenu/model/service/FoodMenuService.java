package com.spring.otlb.foodMenu.model.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.spring.otlb.foodMenu.model.vo.FoodMenu;

public interface FoodMenuService {


	public List<FoodMenu> selectYearMonth(Date date);

	public FoodMenu selectFoodMenu();
}
