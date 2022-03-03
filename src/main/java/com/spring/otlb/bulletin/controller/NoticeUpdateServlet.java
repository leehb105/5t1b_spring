package com.otlb.semi.bulletin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.Attachment;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.common.AttachFileRenamePolicy;
import com.otlb.semi.common.EmpUtils;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/noticeUpdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();
       
	/**
	 * select * from board where no = ?
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Board board = bulletinService.selectOneNotice(no);
		System.out.println(board);
		
		request.setAttribute("board", board);
		request
			.getRequestDispatcher("/WEB-INF/views/notice/noticeUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * update board set category = ?, title = ?, content = ? where no = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 사용자입력값
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			
			Board board = new Board();
			board.setNo(no);
			board.setTitle(title);
			board.setContent(content);
			board.setEmpNo(empNo);

			int result = bulletinService.updateNotice(board);
			
			String msg = result > 0 ? "게시물 수정 성공" : "게시물 수정 실패";
			
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/board/noticeView?no=" + board.getNo();
			response.sendRedirect(location);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
