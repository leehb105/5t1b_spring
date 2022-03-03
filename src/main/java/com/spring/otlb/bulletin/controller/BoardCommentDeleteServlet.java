package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.bulletin.model.service.BulletinService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService =  new BulletinService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.valueOf(request.getParameter("boardNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = bulletinService.deleteBoardComment(no);
		
		String msg = (result > 0) ? "댓글을 삭제했습니다." : "댓글이 안지워져요...";	
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/boardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
