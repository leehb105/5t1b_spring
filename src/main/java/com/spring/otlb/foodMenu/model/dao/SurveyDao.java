package com.otlb.semi.foodMenu.model.dao;

import static com.otlb.semi.common.JdbcTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.otlb.semi.bulletin.model.dao.BulletinDao;
import com.otlb.semi.foodMenu.model.exception.SurveyException;
import com.otlb.semi.foodMenu.model.vo.Survey;

public class SurveyDao {
	private Properties prop = new Properties();
	
	public SurveyDao() {
		String filepath = BulletinDao.class.getResource("/survey-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertSurvey(Connection conn, Survey survey) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertSurvey");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, survey.getSurveyDate());
			pstmt.setInt(2, survey.getEmpNo());
			pstmt.setInt(3, survey.getAnswer1());
			pstmt.setInt(4, survey.getAnswer2());
			pstmt.setInt(5, survey.getAnswer3());
			pstmt.setInt(6, survey.getAnswer4());
			pstmt.setInt(7, survey.getAnswer5());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new SurveyException("설문조사 응답 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}
