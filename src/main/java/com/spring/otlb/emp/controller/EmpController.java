package com.spring.otlb.emp.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.otlb.emp.model.service.EmpService;
import com.spring.otlb.emp.model.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController {
	@Autowired
	private EmpService empService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/empEnroll.do")
	public void empEnroll() {
		
	}
	@PostMapping("/empEnroll.do")
//	public String empEnroll(
//			@RequestParam int empNo,
//			@RequestParam String empName,
//			@RequestParam String email,
//			@RequestParam String password,
//			@RequestParam String passwordCheck,
//			@RequestParam String phone,
//			@RequestParam String gender,
//			@RequestParam int year,
//			@RequestParam int month,
//			@RequestParam int day) {
		public String empEnroll(
				@RequestParam int empNo,
				@RequestParam String empName,
				@RequestParam String email,
				@RequestParam String password,
				@RequestParam String phone,
				@RequestParam String gender,
				@RequestParam int year,
				@RequestParam int month,
				@RequestParam int day) {
		try {

			Calendar cal = new GregorianCalendar(year, month - 1, day);
			Date birthdate = new Date(cal.getTimeInMillis());

			Emp emp = new Emp();
			emp.setEmpNo(empNo);
			emp.setEmpName(empName);
			emp.setEmail(email);
			emp.setPassword(bCryptPasswordEncoder.encode(password));
			emp.setPhone(phone);
			emp.setGender(gender);
			emp.setBirthdate(birthdate);
			

			
			log.debug("emp = {}", emp);
			// 2. 업무로직
			int result = empService.insertEmp(emp);
			
			// 3. 응답처리
			if(result > 0) {
				// 회원가입 성공시 modal에 전달할 메세지 작성
//				modalMessage(request, response, SUCCESS_MESSAGE, "회원가입에 성공하셨습니다.");
//				return;
			}
			else {
				
				// 회원가입 실패시 modal에 전달할 메세지 작성
//				modalMessage(request, response, ERROR_MESSAGE, "이미 존재하는 회원입니다.");
//				return;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // tomcat이 error.jsp로 위임한다.
		}
	}
	
	@GetMapping("/empLogin.do")
	public void empLogin() {
		
	}
	@PostMapping("/empLogin.do")
	public String empLogin(
			@RequestParam int id,
			@RequestParam String password,
			@SessionAttribute(required=false) String next,
			Model model,
			RedirectAttributes redirectAttr) {
		
		Emp emp = empService.selectOneEmp(id);
		log.info("member = {}", emp);
		log.info("encodedPassword = {}", bCryptPasswordEncoder.encode(password));

		String location = "/";
		if(emp != null && bCryptPasswordEncoder.matches(password, emp.getPassword())) {
			// 로그인 성공시
			model.addAttribute("loginMember", emp);
			
			log.info("next = {}", next);
			location = next;
//			redirectAttr.addFlashAttribute("msg", "로그인을 성공했습니다.");
		}
		else {
			// 로그인 실패시
			redirectAttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		
		return "redirect:" + location;
	}

}
