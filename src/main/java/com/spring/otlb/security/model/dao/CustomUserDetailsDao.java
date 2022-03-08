package com.spring.otlb.security.model.dao;

import com.spring.otlb.emp.model.vo.Emp;

public interface CustomUserDetailsDao {

	public Emp loadUserByUsername(int empNo);
}
