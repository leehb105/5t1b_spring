package com.spring.otlb.emp.controller;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.spring.otlb.security.model.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController {
	@Autowired
	private EmpService empService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/empEnroll.do")
	public void empEnroll() {
		
	}
	@PostMapping("/empEnroll.do")
		public String empEnroll(
				@RequestParam String empNo,
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
			@RequestParam String empNo,
			@RequestParam String password,
			@SessionAttribute(required=false) String next,
			Model model,
			RedirectAttributes redirectAttr) {
		
//		Emp loginEmp = empService.selectOneEmp(empNo);
		
//		UserDetails test = customUserDetailsService.loadUserByUsername(empNo);
//		log.info("loginEmp = {}", loginEmp);
//		log.info("encodedPassword = {}", bCryptPasswordEncoder.encode(password));
//
//		String location = "/";
//		if(loginEmp != null && bCryptPasswordEncoder.matches(password, loginEmp.getPassword())) {
//			// 로그인 성공시
//			model.addAttribute("loginEmp", loginEmp);
//			
////			log.info("next = {}", next);
////			location = next;
////			redirectAttr.addFlashAttribute("msg", "로그인을 성공했습니다.");
//		}
//		else {
//			// 로그인 실패시
//			location = "/empLogin.do";
//			redirectAttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
//		}
		
		return "redirect:";
	}

	@PostMapping("/empLogout.do")
	public String empLogout() {
		return "redirect:";
	}
	
	@GetMapping("/empList.do")
	public void empList() {
		//업무로직: celeb목록조회
	
	List<Emp> list = empService.selectAllMember();
//	
//	Gson gson = new Gson();
//	String jsonStr = null;
//	for(Emp emp : list) {
//		if(emp.getEmpName().contains(input) || String.valueOf(emp.getEmpNo()).contains(input)) {
//			jsonStr = gson.toJson(emp);
//		}
//	}
//	
//	System.out.println("[JsonCelebListServlet] jsonStr = " + jsonStr);
//	
//	//응답메세지에 직접 출력: json형식
//	//null | {} | [] 
//	//{}
//	//	- {}의 속성은 항상 쌍따옴표로 감싸야 하고, {}의 속성값중 문자열은 쌍따옴표로 감싸야 한다.
//	//	- 숫자, boolean, {}, []이 올 수 있다.
//	response.setContentType("application/json; charset=utf-8");
//	response.getWriter().append(jsonStr);
//	
//	//csv형식		
//	System.out.println("/message/empList.do 호출");
//	//사용자입력값 확인
//	String term = request.getParameter("term");
//	//System.out.println("[AutoCompleteServlet] term = " + term);
//	
//	List<Emp> list = messageService.selectAllMember();
//	
//	List<Emp> resultList = new ArrayList<Emp>();
//	for(Emp emp : list) {
//		if(emp.getEmpName().contains(term) || String.valueOf(emp.getEmpNo()).contains(term)) {
//			resultList.add(emp);
//			
//		}
//		
//	}
//	StringBuilder sb = new StringBuilder();
//	for(int i = 0; i < resultList.size(); i++) {
//		sb.append(resultList.get(i).getEmpNo());
//		sb.append("-");
//		sb.append(resultList.get(i).getEmpName());
//		sb.append(",");
//	}
//	response.setContentType("text/csv; charset=utf-8");
//	response.getWriter().append(sb);
	}

	@GetMapping("/empView.do")
	public void empView(Model model,
						Principal principal){
		Emp emp = empService.selectOneEmpInfo(principal.getName());

		model.addAttribute("emp", emp);

	}

	@PostMapping("/empUpdate.do")
	public String empUpdate(RedirectAttributes attributes,
							Principal principal,
							Emp emp){
		log.debug("emp = {}", emp);
		String msg = "";
		try {
			emp.setEmpNo(principal.getName());
			int result = empService.updateEmp(emp);
			if(result > 0){
				msg = "정보를 수정했습니다.";
			}else{
				msg = "정보수정 오류";
			}
		}catch (DataIntegrityViolationException e){
//			log.debug("e= {}", e);
			msg = "중복된 전화번호 또는 이메일 주소 입니다.";
			e.printStackTrace();
		}

		attributes.addFlashAttribute("msg", msg);
		return "redirect:/emp/empView.do";
	}
	
	
	
	
	
	
}
