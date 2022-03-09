package com.spring.otlb.security.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.emp.model.vo.Emp;

@Repository
public class CustomUserDetailsDaoImpl implements CustomUserDetailsDao{
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Emp loadUserByUsername(String empNo) {
		return session.selectOne("session.loadUserByUsername", empNo);
	}

}
