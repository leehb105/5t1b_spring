package com.spring.otlb.security.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomUserDetailsDaoImpl implements CustomUserDetailsDao{
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Emp loadUserByUsername(String username) {
		log.debug("dao empNo = {}", username);
		return session.selectOne("security.loadUserByUsername", username);
	}

}
