package com.spring.otlb.message.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
	
	@PostMapping("/messageEnroll.do")
	public String messageEnroll(@RequestParam String[] empNo,
			Principal principal,
			Message message,
			RedirectAttributes redirectAttr) {

		String msg = "";
		message.setSenderEmpNo(principal.getName()); //전송인 설정
		for(String receiverEmpNo: empNo){
			message.setReceiverEmpNo(receiverEmpNo);
			int result = messageService.insertMessage(message);


			if(result < 0){
				msg = receiverEmpNo + " 쪽지 발송에 실패했습니다.";
				redirectAttr.addFlashAttribute("msg", msg);
				return "redirect:/message/sentMessageList.do";
			}
		}
		msg = "총 " + empNo.length + "명에게 쪽지를 발송했습니다.";
		redirectAttr.addFlashAttribute("msg", msg);

		return "redirect:/message/sentMessageList.do";
	}

	@GetMapping("/receivedMessageCount.do")
	@ResponseBody
	public String receivedMessageCount(Principal principal,
		 Model model){
//		log.debug("empNo = {}", principal.getName());
		int messageCount = messageService.selectReceivedMessageCount(principal.getName());
		return String.valueOf(messageCount);

	}

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

	@PostMapping("/receivedMessageDelete.do")
	public String receivedMessageDelete(@RequestParam int[] no,
		RedirectAttributes attributes){
//		받은 메세지 삭제
		int result = 0;
		String msg = "";
		for(int i = 0; i < no.length; i++){
			log.debug("no = {}", no[i]);
			result = messageService.deleteReceivedMessage(no[i]);
			if(result < 0){
				msg = "쪽지 삭제 오류";
				attributes.addFlashAttribute("msg", msg);
				return "redirect:/message/receivedMessageList.do";
			}
		}
		msg = "쪽지를 삭제했습니다.";
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/message/receivedMessageList.do";
	}

	@PostMapping("/sentMessageDelete.do")
	public String sentMessageDelete(@RequestParam int[] no,
		RedirectAttributes attributes) {
//		보낸 메세지 삭제
		int result = 0;
		String msg = "";
		for(int i = 0; i < no.length; i++){
			log.debug("no = {}", no[i]);
			result = messageService.deleteSentMessage(no[i]);
			if(result < 0){
				msg = "쪽지 삭제 오류";
				attributes.addFlashAttribute("msg", msg);
				return "redirect:/message/sentMessageList.do";
			}
		}
		msg = "쪽지를 삭제했습니다.";
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/message/sentMessageList.do";
	}

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
	public void messageForm(@RequestParam(required = false) String receiverNo,
		Model model) {
		
		if(receiverNo != null) {
			Emp emp = empService.selectOneEmp(receiverNo);
			log.debug("empNo = {}", emp.getEmpNo());
			model.addAttribute("emp", emp);
			//해야할것: 답장버튼 눌렀을때 해당 아이디가 입력칸에 들어가야함
		}



		
	}

	//한글???깨짐 현상으로 produces지정해줌
	@GetMapping(value = "/empList.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String empList(Model model,
		@RequestParam(required = false) String searchKeyword){
		log.debug("searchKeyword = {}", searchKeyword);
		List<Emp> empList = empService.selectAllMember();
		List<Emp> resultList = new ArrayList<>();

		//검색 키워드 결과값찾기
		for(Emp emp : empList){
			if(emp.getEmpNo().contains(searchKeyword) || emp.getEmpName().contains(searchKeyword)){
				resultList.add(emp);
			}
		}
		//걸러진 결과값 202101-홍길동 의 형태로 StringBuilder생성
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < resultList.size(); i++) {
			sb.append(resultList.get(i).getEmpNo());
			sb.append("-");
			sb.append(resultList.get(i).getEmpName());
			sb.append(" ");
		}

		log.debug(sb.toString());
		return sb.toString();
	}

//	
	

	
}
