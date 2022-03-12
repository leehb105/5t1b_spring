package com.spring.otlb.bulletin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.otlb.bulletin.model.service.BoardService;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.common.Criteria;
import com.spring.otlb.common.Paging;
import com.spring.otlb.emp.model.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService bulletinService;
	
	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue = "1") int pageNum, 
    		Model model, 
    		HttpServletRequest request ) {
		
		int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
			
		Map<String, Object> param = new HashMap<>();
		param.put("pageNum", pageNum);
		param.put("cri", cri);

		List<Board> list = bulletinService.selectAllBoard(param);
//		List<String> regDate = new ArrayList<>();
		log.debug("list = {}", list);
		
//		for(Board board : list) {
//			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
//		}
		
		int total = bulletinService.selectTotalBoardCount();
		Paging page = new Paging(cri, total);
		
		
//		String url = request.getRequestURI();

        model.addAttribute("list", list);
        model.addAttribute("page", page);
	}
}
