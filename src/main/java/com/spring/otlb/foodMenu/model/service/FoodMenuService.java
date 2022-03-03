package com.otlb.semi.foodMenu.model.service;

import static com.otlb.semi.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.otlb.semi.foodMenu.model.dao.FoodMenuDao;
import com.otlb.semi.foodMenu.model.vo.FoodMenu;

public class FoodMenuService {
	private FoodMenuDao foodMenuDao = new FoodMenuDao();

	public List<FoodMenu> selectYearMonth(Date date) {
		Connection conn = getConnection();
		List<FoodMenu> list = foodMenuDao.selectYearMonth(conn, date);
		close(conn);
		return list;
	}
}
