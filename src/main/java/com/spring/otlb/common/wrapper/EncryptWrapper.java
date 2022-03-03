package com.otlb.semi.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.otlb.semi.common.EmpUtils;

public class EncryptWrapper extends HttpServletRequestWrapper {
	
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		switch(name) {
		case "password":
		case "oldPassword": 
		case "newPassword":
			System.out.println("super.getParameter(name) = " + super.getParameter(name));
			return EmpUtils.getEncryptedPassword(super.getParameter(name));
		default:
			return super.getParameter(name);
		}
	}
	
	
}
