package com.spring.otlb.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.otlb.emp.model.dao.EmpDao;
import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.security.model.dao.CustomUserDetailsDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomUserDetailsDao customUserDetailsDao; 
	
	@Override
	public UserDetails loadUserByUsername(String empNo) throws UsernameNotFoundException {
		Emp emp = customUserDetailsDao.loadUserByUsername(empNo);
		
		log.debug("emp = {}", emp);
		
		if(emp == null) {
			throw new UsernameNotFoundException(empNo);
		}
		return emp;
	}

}
