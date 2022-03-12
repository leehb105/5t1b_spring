package com.spring.otlb.foodMenu.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.foodMenu.model.vo.FoodMenu;

@Service
public class FoodMenuServiceImpl implements FoodMenuService{
	@Autowired
	private FoodMenuService foodMenuService;

	@Override
	public List<FoodMenu> selectYearMonth(Date date) {
		return null;
	}

}
