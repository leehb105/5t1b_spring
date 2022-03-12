package com.spring.otlb.foodMenu.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.foodMenu.model.vo.Survey;

@Repository
public class SurveyDaoImpl implements SurveyDao{
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public int insertSurvey(Survey survey) {
		return 0;
	}

}
