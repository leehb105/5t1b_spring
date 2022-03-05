package com.spring.otlb.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.otlb.emp.model.dao.EmpDao;
import com.spring.otlb.emp.model.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private EmpDao empDao; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Emp emp = empDao.loadUserByUsername(username);
		
		log.debug("emp = {}", emp);
		
		if(emp == null) {
			throw new UsernameNotFoundException(username);
		}
		return emp;
	}

}
