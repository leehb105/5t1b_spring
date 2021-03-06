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
import org.springframework.web.bind.annotation.*;
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
			// 2. ????????????
			int result = empService.insertEmp(emp);
			
			// 3. ????????????
			if(result > 0) {
				// ???????????? ????????? modal??? ????????? ????????? ??????
//				modalMessage(request, response, SUCCESS_MESSAGE, "??????????????? ?????????????????????.");
//				return;
			}
			else {
				
				// ???????????? ????????? modal??? ????????? ????????? ??????
//				modalMessage(request, response, ERROR_MESSAGE, "?????? ???????????? ???????????????.");
//				return;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // tomcat??? error.jsp??? ????????????.
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
					String originalFileName = principal.getName(); //??????????????? ????????????
					String ext = FilenameUtils.getExtension(uploadImage.getOriginalFilename());	//????????? ?????????
					fileName = originalFileName + "." + ext;

					File file = new File(saveDirectory, fileName);
					//?????? ????????? ??????
					uploadImage.transferTo(file);
				}
			}

			emp.setEmpNo(principal.getName());
			emp.setProfileImage(fileName);
			int result = empService.updateEmp(emp);
			if(result > 0){
				msg = "????????? ??????????????????.";
			}else{
				msg = "???????????? ??????";
			}
		}catch (DataIntegrityViolationException e){
//			log.debug("e= {}", e);
			msg = "????????? ???????????? ?????? ????????? ?????? ?????????.";
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
			msg = "??????????????? ???????????? ????????????.";
			attributes.addFlashAttribute("msg", msg);
			return "redirect:/emp/updatePassword.do";
		}

		emp.setPassword(bCryptPasswordEncoder.encode(newPassword));
		log.debug("bcryptNewPassword = {}", emp.getPassword());

		int result = empService.updatePassword(emp);
		if(result > 0){
			msg = "??????????????? ??????????????????.";
		}else{
			msg = "???????????? ?????? ??????";
		}
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/emp/empView.do";
	}
	
	
	
	
	
	
}
