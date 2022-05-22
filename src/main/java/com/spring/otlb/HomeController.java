package com.spring.otlb;

import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.otlb.bulletin.controller.BoardController;
import com.spring.otlb.bulletin.model.service.AnonyBoardService;
import com.spring.otlb.bulletin.model.service.BoardService;
import com.spring.otlb.bulletin.model.service.NoticeService;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.Notice;
import com.spring.otlb.foodMenu.controller.FoodMenuController;
import com.spring.otlb.foodMenu.model.service.FoodMenuService;
import com.spring.otlb.foodMenu.model.vo.FoodMenu;
import com.spring.otlb.message.model.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AnonyBoardService anonyBoardService;
	
	@Autowired
	private FoodMenuService foodMenuService;
	
	@Autowired 
	private NoticeService noticeService;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model,
			Principal principal) {
		List<Notice> noticeList = noticeService.selectNoticeMain();
//		log.debug("noticeList = {}", noticeList);

		List<Board> boardList = boardService.selectBoardMain();
//		log.debug("boardList = {}", boardList);

		List<Board> anonymousBoardList = anonyBoardService.selectAnonyBoardMain();
//		log.debug("anonymousBoardList = {}", anonymousBoardList);

		List<Board> topBoardList = boardService.selectTopBoardMain();
//		log.debug("topBoardList = {}", topBoardList);
		
		List<Board> topAnonyBoardList = anonyBoardService.selectTopAnonyBoardMain();
//		log.debug("topAnonyBoardList = {}", topAnonyBoardList);

		
		FoodMenu foodMenu = foodMenuService.selectFoodMenu();
//		log.debug("foodMenu = {}", foodMenu);
		if(principal != null) {
			int sentCount = messageService.selectReceivedMessageCount(principal.getName());
			model.addAttribute("sentCount", sentCount);			
		}
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("anonymousBoardList", anonymousBoardList);
		model.addAttribute("foodMenu", foodMenu);
		model.addAttribute("topBoardList", topBoardList);
		model.addAttribute("topAnonyBoardList", topAnonyBoardList);
		
		return "index";
	}

	@RequestMapping(value = "/error/accessError.do", method = RequestMethod.GET)
	public void accessError(){

	}
	
}
