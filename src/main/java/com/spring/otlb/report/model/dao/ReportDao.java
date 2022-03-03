package com.otlb.semi.report.model.dao;

import static com.otlb.semi.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReportDao {

	private Properties prop = new Properties();
	
	public ReportDao() {
		String filepath = ReportDao.class.getResource("/report-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
