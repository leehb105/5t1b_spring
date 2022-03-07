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

import com.spring.otlb.bulletin.model.service.BulletinService;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.common.DateFormatUtils;
import com.spring.otlb.emp.model.vo.Emp;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BulletinService bulletinService;
	
	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue = "1") int pageNum, 
    		@AuthenticationPrincipal Emp loginEmp,
    		Model model, 
    		HttpServletRequest request ) {
//		final int numPerPage = 10;
//		int cPage = 1;
//		try {
//			cPage = Integer.parseInt(request.getParameter("cPage"));
//		} catch (NumberFormatException e) {}
//		int start = (cPage - 1) * numPerPage + 1; 
//		int end = cPage * numPerPage;
		Map<String, Integer> param = new HashMap<>();
//		param.put("start", start);
//		param.put("end", end);
		
		List<Board> list = bulletinService.selectAllBoard(param);
		List<String> regDate = new ArrayList<>();
		System.out.println("list@servlet = " + list);
		
		for(Board board : list) {
			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
		}
		
		int totalContent = bulletinService.selectTotalBoardCount();
		String url = request.getRequestURI();
//		String pagebar = EmpUtils.getPagebar(cPage, numPerPage, totalContent, url);
//		System.out.println("pagebar@servlet = " + pagebar);

		
		request.setAttribute("list", list);
		request.setAttribute("regDate", regDate);
//		request.setAttribute("pagebar", pagebar);
		

	}
}
