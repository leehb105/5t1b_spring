package com.spring.otlb;

import java.text.DateFormat;
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
import com.spring.otlb.bulletin.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AnonyBoardService anonyBoardService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
//		List<Notice> noticeList = selectService.selectNoticeContent();
//		System.out.println("notice Servlet" + noticeList);

		List<Board> boardList = boardService.selectBoardMain();
		log.debug("boardList = {}", boardList);

		List<Board> anonymousBoardList = anonyBoardService.selectAnonyBoardMain();
		log.debug("anonymousBoardList = {}", anonymousBoardList);
//
//		List<BoardEntity> likeContentBoardSelect = selectService.selectBoardLikeContent();
//		System.out.println("likeContentBoardSelect Servlet" + likeContentBoardSelect);
//		
//		List<BoardEntity> likeContentAnonymous_boardSelect = selectService.selectAnonymous_boardLikeContent();
//		System.out.println("likeContentAnonymous_boardSelect Servlet" +likeContentAnonymous_boardSelect);
//
		FoodMenu foodMenu = selectService.selectFoodMenu();
		System.out.println("foodMenu Servlet" + foodMenu);
//		
//		request.getSession().setAttribute("noticeList", noticeList);
//		request.getSession().setAttribute("boardList", boardList);
//		request.getSession().setAttribute("anonymousBoardList", anonymousBoardList);
//		request.getSession().setAttribute("likeContentBoardSelect", likeContentBoardSelect);
//		request.getSession().setAttribute("likeContentAnonymous_boardSelect", likeContentAnonymous_boardSelect);
//		request.getSession().setAttribute("foodMenu", foodMenu);
		model.addAttribute("boardList", boardList);
		model.addAttribute("anonymousBoardList", anonymousBoardList);
		
		return "index";
	}
	
}
