package com.spring.otlb.security.model.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.otlb.emp.model.vo.Emp;

public interface CustomUserDetailsDao {

	public Emp loadUserByUsername(String empNo);
}
