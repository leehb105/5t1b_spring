package com.spring.otlb.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.otlb.emp.model.dao.EmpDao;
import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.security.model.dao.CustomUserDetailsDao;
import com.spring.otlb.security.vo.CustomUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomUserDetailsDao customUserDetailsDao; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("empNo = {}", username);
		Emp emp = customUserDetailsDao.loadUserByUsername(username);

		
		if(emp == null) {
			throw new UsernameNotFoundException(username);
		}
		return emp;
	}

}
