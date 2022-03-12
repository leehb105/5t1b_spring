package com.spring.otlb.foodMenu.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.spring.otlb.foodMenu.model.service.FoodMenuService;
import com.spring.otlb.foodMenu.model.vo.FoodMenu;

@Controller
public class FoodMenuController {

	@Autowired
	private FoodMenuService foodMenuService;


}
