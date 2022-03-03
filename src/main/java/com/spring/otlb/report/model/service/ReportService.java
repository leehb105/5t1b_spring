package com.otlb.semi.report.model.service;

import static com.otlb.semi.common.JdbcTemplate.*;

import com.otlb.semi.report.model.dao.ReportDao;

public class ReportService {

	private static final String issolved = "Y";
	private static final String isNotSolved = "N";
	
	private ReportDao reportDao = new ReportDao();
}
