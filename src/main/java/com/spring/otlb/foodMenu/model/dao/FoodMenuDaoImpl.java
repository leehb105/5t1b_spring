package com.spring.otlb.foodMenu.model.dao;

import java.sql.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.foodMenu.model.vo.FoodMenu;

@Repository
public class FoodMenuDaoImpl implements FoodMenuDao{
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<FoodMenu> selectYearMonth(Date date) {
		return null;
	}

}
