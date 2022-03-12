package com.spring.otlb.foodMenu.model.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.foodMenu.model.dao.FoodMenuDao;
import com.spring.otlb.foodMenu.model.vo.FoodMenu;

@Service
public class FoodMenuServiceImpl implements FoodMenuService{
	
	@Autowired
	private FoodMenuDao foodMenuDao;

	@Override
	public List<FoodMenu> selectYearMonth(Date date) {
		return null;
	}

	@Override
	public FoodMenu selectFoodMenu() {
		return foodMenuDao.selectFoodMenu();
	}

}
