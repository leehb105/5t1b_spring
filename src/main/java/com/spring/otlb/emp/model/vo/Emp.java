package com.spring.otlb.emp.model.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Emp extends EmpEntity implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String deptName;
	private String jobName;
	
	private List<SimpleGrantedAuthority> authorities;
	
	public Emp(String empNo, String empName, String password, Date birthdate, String deptCode, String jobCode,
			String empRole, String gender, String email, String phone, String quitYn, String banYn, String profileImage, 
			String deptName, String jobName, List<SimpleGrantedAuthority> authorities) {
		super(empNo, empName, password, birthdate, deptCode, jobCode, empRole, gender, email, phone, quitYn, banYn, profileImage);
		this.deptName = deptName;
		this.jobName = jobName;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return getEmpNo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getQuitYn().equals("N") ? true : false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	
	


}
