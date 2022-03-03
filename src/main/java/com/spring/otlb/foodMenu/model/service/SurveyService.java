package com.otlb.semi.foodMenu.model.service;

import static com.otlb.semi.common.JdbcTemplate.close;
import static com.otlb.semi.common.JdbcTemplate.commit;
import static com.otlb.semi.common.JdbcTemplate.getConnection;
import static com.otlb.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.otlb.semi.foodMenu.model.dao.SurveyDao;
import com.otlb.semi.foodMenu.model.vo.Survey;

public class SurveyService {
	
	private SurveyDao surveyDao = new SurveyDao();

	public int insertSurvey(Survey survey) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = surveyDao.insertSurvey(conn, survey);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
}
