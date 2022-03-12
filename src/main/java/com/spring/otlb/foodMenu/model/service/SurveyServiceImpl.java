package com.spring.otlb.foodMenu.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.foodMenu.model.dao.SurveyDao;
import com.spring.otlb.foodMenu.model.vo.Survey;

@Service
public class SurveyServiceImpl implements SurveyService{

	@Autowired
	private SurveyDao surveyDao;

	@Override
	public int insertSurvey(Survey survey) {
		return 0;
	}
}
