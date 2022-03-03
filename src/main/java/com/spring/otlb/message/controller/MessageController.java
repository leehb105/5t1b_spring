package com.spring.otlb.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/message")
@Slf4j
public class MessageController {

	@Autowired 
	private MessageService messageService;
	
//	@GetMapping("/empList.do")
//	public void empList() {
//		
//		//System.out.println("[AutoCompleteServlet] term = " + term);
//		
//		List<Emp> list = messageService.selectAllMember();
//		
//		List<Emp> resultList = new ArrayList<Emp>();
//		for(Emp emp : list) {
//			if(emp.getEmpName().contains(term) || String.valueOf(emp.getEmpNo()).contains(term)) {
//				resultList.add(emp);
//				
//			}
//			
//		}
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < resultList.size(); i++) {
//			sb.append(resultList.get(i).getEmpNo());
//			sb.append("-");
//			sb.append(resultList.get(i).getEmpName());
//			sb.append(",");
//		}
//		response.setContentType("text/csv; charset=utf-8");
//		response.getWriter().append(sb);
//	}
	
	//쪽지 전송
	@PostMapping("/messageEnroll.do")
	public String messageEnroll(Emp loginEmp) {
		
		//보내는사람
		int sender = loginEmp.getEmpNo();
		
		//받는사람
		String _receiverList = request.getParameter("receiverList");
		System.out.println("_receiverList" + _receiverList);
		String[] receiverList = _receiverList.split(", ");
		System.out.println("receiverList" + receiverList);
		
		String content = request.getParameter("content");
		
		List<Integer> receiverNo = new ArrayList<>();
		List<String> receiverName = new ArrayList<>();
		for(String str : receiverList) {
			String[] temp = str.split("-");
			//앞부분 사번만 가져옴
			receiverNo.add(Integer.valueOf(temp[0]));
			receiverName.add(temp[1]);
		}
		
		int count = 0;
		String msg = null;
		//보낸사람 만큼 요청 보냄
		for(int i = 0; i < receiverNo.size(); i++) {
			Message message = new Message(0, content, sender, receiverNo.get(i), null, null, null, null);
			int result = messageService.insertMessage(message);
			if(result > 0) {
				count++;
				msg = count + "명에게 쪽지를 성공적으로 발송했습니다!";
			}else {
				msg = receiverName.get(i) + "의 쪽지 발송에 실패했습니다...";
				break;
			}
		}
		
		session.setAttribute("msg", msg);
		String location = request.getContextPath() + "/message/messageForm";
		response.sendRedirect(location);
	}
	
	//메세지 폼 
	@GetMapping("/messageForm.do")
	public void messageForm(Model model) {
		
	}
	
	
	
	//답장 메세지 폼 
	@GetMapping("/messageForm.do")
	public void messageForm(@RequestParam int receiverNo, Model model) {
		
		Emp emp = messageService.selectOneMember(receiverNo);
		String receiver = emp.getEmpNo() + "-" + emp.getEmpName();
		
		model.addAttribute("receiver", receiver);
		
		
	}
	
	@GetMapping("/messageList")
	public void messageList() {
		//받은 메세지함
		HttpSession session = request.getSession();
		
		Emp emp = (Emp) session.getAttribute("loginEmp");
		
		int empNo = emp.getEmpNo();
		
		
		
		List<Message> list = messageService.selectAllReceivedMessage(empNo);
		List<String> titleList = new ArrayList<>();
		List<String> sentDateList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getContent().length() > 50) {
				//n자가 넘는 쪽지의 경우 n자만출력해줌
				titleList.add(list.get(i).getContent().substring(0, 40) + "...더보기");
			}else {
				titleList.add(list.get(i).getContent());
			}
			//쪽지 날짜 변형부
			sentDateList.add(DateFormatUtils.formatDate(list.get(i).getSentDate()));
		}
		int totalContent = messageService.selectTotalSentMessageount(empNo);
		String url = request.getRequestURI();
		
		request.setAttribute("list", list);
		request.setAttribute("titleList", titleList);
		request.setAttribute("sentDateList", sentDateList);
		
		request
			.getRequestDispatcher("/WEB-INF/views/message/messageList.jsp")
			.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
