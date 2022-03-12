package com.otlb.semi.foodMenu.model.vo;

import java.sql.Date;

public class Survey {
	private Date surveyDate;
	private int empNo;
	private int answer1;
	private int answer2;
	private int answer3;
	private int answer4;
	private int answer5;
	private String shortAnswer;

	public Survey() {
		super();
	}

	public Survey(Date surveyDate, int empNo, int answer1, int answer2, int answer3, int answer4, int answer5,
			String shortAnswer) {
		super();
		this.surveyDate = surveyDate;
		this.empNo = empNo;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
		this.shortAnswer = shortAnswer;
	}

	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getAnswer1() {
		return answer1;
	}

	public void setAnswer1(int answer1) {
		this.answer1 = answer1;
	}

	public int getAnswer2() {
		return answer2;
	}

	public void setAnswer2(int answer2) {
		this.answer2 = answer2;
	}

	public int getAnswer3() {
		return answer3;
	}

	public void setAnswer3(int answer3) {
		this.answer3 = answer3;
	}

	public int getAnswer4() {
		return answer4;
	}

	public void setAnswer4(int answer4) {
		this.answer4 = answer4;
	}

	public int getAnswer5() {
		return answer5;
	}

	public void setAnswer5(int answer5) {
		this.answer5 = answer5;
	}

	public String getShortAnswer() {
		return shortAnswer;
	}

	public void setShortAnswer(String shortAnswer) {
		this.shortAnswer = shortAnswer;
	}

	@Override
	public String toString() {
		return "Survey [surveyDate=" + surveyDate + ", empNo=" + empNo + ", answer1=" + answer1 + ", answer2="
				+ answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", answer5=" + answer5 + ", shortAnswer="
				+ shortAnswer + "]";
	}
}
