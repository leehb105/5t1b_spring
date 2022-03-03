package com.otlb.semi.bulletin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/noticeEnroll")
public class NoticeEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * insert into notice(no, title, content) values(?, ?, ?)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// 1. 사용자입력값 처리

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			
			Board board = new Board();
			//board.setCategory(category);
			board.setTitle(title);
			board.setContent(content);
			board.setEmpNo(empNo);
			
			System.out.println("[BoardEnrollServlet] board = " + board);
			
			// 2. 업무로직
			int result = bulletinService.insertNotice(board);
			String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
			
			// 3. 응답요청
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/board/noticeList";
			response.sendRedirect(location);
		} catch (NumberFormatException | IOException e) {
			throw e;
		}
	}

}
