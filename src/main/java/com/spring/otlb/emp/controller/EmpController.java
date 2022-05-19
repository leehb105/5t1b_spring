package com.spring.otlb.emp.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import oracle.jdbc.proxy.annotation.Post;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.otlb.emp.model.service.EmpService;
import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.security.model.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController {
	@Autowired
	private EmpService empService;

	@Autowired
	private ServletContext application;

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
			@RequestParam(required = false, defaultValue = "false") boolean error,
			@RequestParam(required = false, value = "errorMsg") String errorMsg,
			@SessionAttribute(required=false) String next,
			RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("errorMsg", errorMsg);
		return "redirect:/emp/empLogin.do";
	}

	@PostMapping("/empLogout.do")
	public String empLogout() {
		return "redirect:";
	}
	
	@GetMapping("/empListView.do")
	public void empListView(Model model) {
		//업무로직: celeb목록조회
	
		List<Emp> list = empService.selectAllMember();
		model.addAttribute("list", list);

	}

	@PostMapping("/empCodeUpdate.do")
	public String empCodeUpdate(RedirectAttributes attributes,
							  @RequestParam String empNo,
							  @RequestParam String updateDeptCode,
							  @RequestParam String updateJobCode){

		log.debug("empNo = {}", empNo);
		log.debug("updateDeptCode = {}", updateDeptCode);
		log.debug("updateJobCode = {}", updateJobCode);

		String msg = "";
		Emp emp = empService.selectOneEmp(empNo);
		if(emp.getDeptCode().equals(updateDeptCode) && emp.getJobCode().equals(updateJobCode)){
			msg = "변경사항이 없습니다.";
		}else{
			emp.setDeptCode(updateDeptCode);
			emp.setJobCode(updateJobCode);
			int result = empService.updateEmpCode(emp);
			if(result > 0){
				msg = "정보를 변경하였습니다.";
			} else{
				msg = "변경 요류";
			}

		}
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/emp/empListView.do";
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
							@RequestParam(required = false) MultipartFile imageFile,
							Emp emp){
		log.debug("emp = {}", emp);
		String saveDirectory = application.getRealPath("/resources/img/profile");
		String msg = "";
		try {
			String fileName = null;
			MultipartFile uploadImage = null;
			if(imageFile != null){
				uploadImage = imageFile;
				if(!uploadImage.isEmpty()){
					String originalFileName = principal.getName(); //파일이름을 사번으로
					String ext = FilenameUtils.getExtension(uploadImage.getOriginalFilename());	//확장자 구하기
					fileName = originalFileName + "." + ext;

					File file = new File(saveDirectory, fileName);
					//파일 경로에 저장
					uploadImage.transferTo(file);
				}
			}

			emp.setEmpNo(principal.getName());
			emp.setProfileImage(fileName);
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		attributes.addFlashAttribute("msg", msg);
		return "redirect:/emp/empView.do";
	}

	@GetMapping("/updatePassword.do")
	public void updatePassword(){
	}

	@PostMapping("/updatePassword.do")
	public String updatePassword(RedirectAttributes attributes,
								 String oldPassword,
								 String newPassword,
								 Principal principal){
		log.debug("oldPassword = {}", oldPassword);
		log.debug("newPassword = {}", newPassword);

//		Emp emp = new Emp();
//		emp.setEmpNo(principal.getName());
//		emp.setPassword(bCryptPasswordEncoder.encode(newPassword));

		String msg = "";
		Emp emp = empService.selectOneEmp(principal.getName());
		log.debug("emp = {}", emp);
		if(!bCryptPasswordEncoder.matches(oldPassword, emp.getPassword())){
			msg = "비밀번호가 일치하지 않습니다.";
			attributes.addFlashAttribute("msg", msg);
			return "redirect:/emp/updatePassword.do";
		}

		emp.setPassword(bCryptPasswordEncoder.encode(newPassword));
		log.debug("bcryptNewPassword = {}", emp.getPassword());

		int result = empService.updatePassword(emp);
		if(result > 0){
			msg = "비밀번호를 수정했습니다.";
		}else{
			msg = "비밀번호 변경 오류";
		}
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/emp/empView.do";
	}
	
	
	
	
	
	
}
