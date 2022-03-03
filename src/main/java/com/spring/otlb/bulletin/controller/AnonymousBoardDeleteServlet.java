package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.bulletin.model.service.BulletinService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/anonymousBoardDelete")
public class AnonymousBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int result = bulletinService.deleteAnonymousBoard(no);
		
		String msg = result > 0 ? "게시물 삭제 성공" : "게시물 삭제 실패";
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/anonymousBoardList";
		response.sendRedirect(location);
	}

}
