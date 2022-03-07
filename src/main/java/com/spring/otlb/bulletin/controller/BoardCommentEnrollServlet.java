package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.BoardComment;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/board/boardCommentEnroll")
public class BoardCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("loginEmp");

		int no = Integer.valueOf(request.getParameter("no"));
		int empNo = emp.getEmpNo();
		int commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
		int commentRef = Integer.valueOf(request.getParameter("commentRef"));
		String content = request.getParameter("content");
		BoardComment bc = new BoardComment(0, commentLevel, content, null, commentRef, null, no, empNo, null);
		//System.out.println(content);
		
		int result = bulletinService.insertBoardComment(bc);
		String msg = result > 0 ? "댓글을 등록했습니다!" : "댓글 등록에 실패했습니다...";
		session.setAttribute("msg", msg);
		
		//댓글작성한 해당 글로 리다이렉트
		String location = request.getContextPath() + "/board/boardView?no=" + bc.getBoardNo();
		response.sendRedirect(location);

	}

}
