package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.mainpage.controller.BoardServlet;

/**
 * Servlet implementation class BoardLikeCountServlet
 */
@WebServlet("/board/boardLikeCount")
public class BoardLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.valueOf(request.getParameter("no"));

		String msg = "";
		// 쿠키 생성 
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String boardLikeCookieVal = "";
		if(cookies != null ) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if("boardLikeCookie".equals(name)) {
					boardLikeCookieVal = value;
					if(value.contains("[" + no + "]")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		// 좋아요 증가 및 쿠키 생성 
		if(!hasRead) {
			int result = bulletinService.updateBoardLikeCount(no);
			
			Cookie cookie = new Cookie("boardLikeCookie",boardLikeCookieVal + "[" + no + "]");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
			//System.out.println("조회수 증가 & 쿠키 생성 ");
			msg = result > 0 ? "추천하셨습니다!" : "추천에 오류가 있습니다...";
		}else {
			msg = "이미 추천하셨습니다.";
		}

		
		request.getSession().setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/board/boardView?no=" + no;
		response.sendRedirect(location);
			
	
		
		
	}
	
}
