package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.bulletin.model.vo.BoardComment;
import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.common.LineFormatUtils;

/**
 * Servlet implementation class NoticeViewsServlet
 */
@WebServlet("/board/noticeView")
public class NoticeViewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.valueOf(request.getParameter("no"));
		
		// 쿠키 생성 
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String noticeCookieVal = "";
		if(cookies != null ) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if("noticeCookie".equals(name)) {
					noticeCookieVal = value;
					if(value.contains("[" + no + "]")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		// 조회수 증가 및 쿠키 생성 
		if(!hasRead) {
			int result = bulletinService.updateNoticeReadCount(no);
			
			Cookie cookie = new Cookie("noticeCookie",noticeCookieVal + "[" + no + "]");
			cookie.setPath(request.getContextPath() + "/board/noticeList");
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
			//System.out.println("조회수 증가 & 쿠키 생성 ");
		}
		//게시판 데이터 가져오기
		Board board = bulletinService.selectOneNotice(no);
		System.out.println(board);
		//System.out.println(board);
		String regDate = DateFormatUtils.formatDate(board.getRegDate());
		String content = LineFormatUtils.formatLine(board.getContent());
		
		
		request.setAttribute("board", board);
		request.setAttribute("regDate", regDate);
		request.setAttribute("content", content);
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/notice/noticeView.jsp")
			.forward(request, response);
	}

}
