package com.otlb.semi.foodMenu.model.dao;

import static com.otlb.semi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.otlb.semi.foodMenu.model.vo.FoodMenu;

public class FoodMenuDao {
	private Properties prop = new Properties();
	
	public FoodMenuDao() {
		String filepath = FoodMenuDao.class.getResource("/foodMenu-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<FoodMenu> selectYearMonth(Connection conn, Date date) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectYearMonth");
		ResultSet rset = null;
		List<FoodMenu> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setDate(2, date);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				FoodMenu foodMenu = new FoodMenu();
				
				foodMenu.setMenuDate(rset.getDate("menu_date"));
				foodMenu.setMain(rset.getString("main"));
				foodMenu.setSoup(rset.getString("soup"));
				foodMenu.setSide1(rset.getString("side1"));
				foodMenu.setSide2(rset.getString("side2"));
				foodMenu.setSide3(rset.getString("side3"));
				foodMenu.setDessert(rset.getString("dessert"));
				
				list.add(foodMenu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}
