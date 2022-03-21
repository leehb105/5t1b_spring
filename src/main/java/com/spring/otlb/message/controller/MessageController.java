package com.spring.otlb.message.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.otlb.emp.model.service.EmpService;
import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.service.MessageService;
import com.spring.otlb.message.model.vo.Message;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private EmpService empService;
	
//	@PostMapping("/messageEnroll.do")
//	public String messageEnroll(Model model,
//			@AuthenticationPrincipal Emp loginEmp,
//			RedirectAttributes redirectAttr) {
//		//보내는사람
////		HttpSession session = request.getSession();
////		Emp emp = (Emp) session.getAttribute("loginEmp");
////		int sender = emp.getEmpNo();
//		
//		//받는사람
//		String _receiverList = request.getParameter("receiverList");
//		System.out.println("_receiverList" + _receiverList);
//		String[] receiverList = _receiverList.split(", ");
//		System.out.println("receiverList" + receiverList);
//		
//		String content = request.getParameter("content");
//		
//		List<Integer> receiverNo = new ArrayList<>();
//		List<String> receiverName = new ArrayList<>();
//		for(String str : receiverList) {
//			String[] temp = str.split("-");
//			//앞부분 사번만 가져옴
//			receiverNo.add(Integer.valueOf(temp[0]));
//			receiverName.add(temp[1]);
//		}
//		
//		int count = 0;
//		String msg = null;
//		//보낸사람 만큼 요청 보냄
//		for(int i = 0; i < receiverNo.size(); i++) {
//			Message message = new Message(0, content, sender, receiverNo.get(i), null, null, null, null);
//			int result = messageService.insertMessage(message);
//			if(result > 0) {
//				count++;
//				msg = count + "명에게 쪽지를 성공적으로 발송했습니다!";
//			}else {
//				msg = receiverName.get(i) + "의 쪽지 발송에 실패했습니다...";
//				break;
//			}
//		}
//		
//		session.setAttribute("msg", msg);
//		String location = request.getContextPath() + "/message/messageForm";
//		response.sendRedirect(location);
//		
//		return null;
//	}
//	
	@GetMapping("/sentMessageView.do")
	public void sentMessageView(@RequestParam int no,
			Model model) {
		//보낸쪽지함
		//글번호
		log.debug("no = {}", no);
		Message message = messageService.selectOneSentMessage(no);
		
		
		model.addAttribute("message", message);
		
	}
	
	
	@GetMapping("/sentMessageList.do")
	public void sentMessageList(Model model,
			Principal principal) {
		
		List<Message> list = messageService.selectAllSentMessage(principal.getName());
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getContent().length() > 50) {
				//n자가 넘는 쪽지의 경우 n자만출력해줌
				list.get(i).setContent(list.get(i).getContent().substring(0, 40) + "...더보기");
			}

		}
		model.addAttribute("list", list);
	}
	
//	@PostMapping("/sentMessageDelete.do")
//	public String sentMessageDelete() {
//		//넘어온 글 번호
//		String no = request.getParameter("no");
//		
//		List<Integer> list = new ArrayList<>();
//		
//		//,가 넘어온 경우만 split처리
//		if(no.contains(",")) {
//			//,기준으로 문자열 split
//			String[] noArr = no.split(",");
//			
//			//문자열 숫자를 정수형으로 형변환
//			for(String str : noArr) {
//				list.add(Integer.parseInt(str));
//			}
//			
//		}else {
//			//no가 단일값일경우
//			list.add(Integer.parseInt(no));
//		}
//		String msg = "";
//		for(int i = 0; i < list.size(); i++) {
//			int result = messageService.updateSenderDelYn(list.get(i));
//			if(result > 0) {
//				msg = (i+1) + "개의 쪽지 삭제에 성공하였습니다.";
//			}else {
//				msg = "일부 쪽지 삭제에 실패 하였습니다.";
//				break;
//			}
//			
//		}
//		
//		request.getSession().setAttribute("msg", msg);
//		response.sendRedirect(request.getContextPath() + "/message/sentMessageList");
//		return null;
//	}
//	
	@GetMapping("/receivedMessageView.do")
	public void messageView(@RequestParam int no,
			Model model) {
		//받은쪽지함
		log.debug("no = {}", no);
		Message message = messageService.selectOneReceivedMessage(no);
		
		int result = messageService.updateReadDate(no);
		String msg = result > 0 ? null : "쪽지 읽음처리 오류";
		
		model.addAttribute("message", message);
		model.addAttribute("msg", msg);

	}
//	
	@GetMapping("/receivedMessageList.do")
	public void messageList(Model model,
			Principal principal) {
		//받은 메세지함
//		log.debug("loginEmp = {}", loginEmp);
		log.debug("principal = {}", principal);
		List<Message> list = messageService.selectAllReceivedMessage(principal.getName());

		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getContent().length() > 50) {
				//n자가 넘는 쪽지의 경우 n자만출력해줌
				list.get(i).setContent(list.get(i).getContent().substring(0, 40) + "...더보기");
			}

		}
		int messageCount = messageService.selectReceivedMessageCount(principal.getName());
		
		model.addAttribute("list", list);
		model.addAttribute("messageCount", messageCount);

	}
//	
	@GetMapping("/messageForm.do")
	public void messageForm(@RequestParam(required = false) String receiverNo) {
		
		if(receiverNo != null) {
			Emp emp = empService.selectOneEmp(receiverNo);
		}
		
	}
	

//	
	

	
}
