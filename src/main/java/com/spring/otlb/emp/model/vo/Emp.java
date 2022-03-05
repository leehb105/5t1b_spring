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
	
	public Emp(int empNo, String empName, String password, Date birthdate, String deptCode, String jobCode,
			String empRole, String gender, String email, String phone, String quitYn, String banYn, int enabled, String deptName,
			String jobName, List<SimpleGrantedAuthority> authorities) {
		super(empNo, empName, password, birthdate, deptCode, jobCode, empRole, gender, email, phone, quitYn, banYn, enabled);
		this.deptName = deptName;
		this.jobName = jobName;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	
	


}
