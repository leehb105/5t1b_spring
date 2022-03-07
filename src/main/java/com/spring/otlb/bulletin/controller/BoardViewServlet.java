package com.otlb.semi.bulletin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.Attachment;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.bulletin.model.vo.BoardComment;
import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.common.LineFormatUtils;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.valueOf(request.getParameter("no"));
			
			
			// 쿠키 생성 
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String boardCookieVal = "";
			if(cookies != null ) {
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("boardCookie".equals(name)) {
						boardCookieVal = value;
						if(value.contains("[" + no + "]")) {
							hasRead = true;
							break;
						}
					}
				}
			}
			// 조회수 증가 및 쿠키 생성 
			if(!hasRead) {
				int result = bulletinService.updateReadCount(no);
				
				Cookie cookie = new Cookie("boardCookie",boardCookieVal + "[" + no + "]");
				cookie.setPath(request.getContextPath() + "/board/boardView");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				response.addCookie(cookie);
				System.out.println("조회수 증가 & 쿠키 생성 ");
			}
			
			//게시판 데이터 가져오기
			Board board = bulletinService.selectOneBoard(no);

			String filepath = BoardViewServlet.class.getResource("/../../img/profile").getPath();
			File writerProfileImage = new File(filepath + board.getEmpNo() + ".png");
			if(writerProfileImage.exists()) request.setAttribute("writerProfileImageExists", true);
			else request.setAttribute("writerProfileImageExists", false);
			

			//System.out.println(board);
			String regDate = DateFormatUtils.formatDate(board.getRegDate());
			String content = LineFormatUtils.formatLine(board.getContent());
			
			//게시판 댓글 가져오기
			List<BoardComment> boardCommentList = bulletinService.selectBoardCommentList(no);
			List<String> commentListContent = new ArrayList<>();
			List<String> commentListDate = new ArrayList<>();
			
			List<Boolean> commenterImageList = new ArrayList<>();
			for(BoardComment bc : boardCommentList) {
				File commenterProfileImage = new File(filepath + bc.getEmpNo() + ".png");
				if(commenterProfileImage.exists()) commenterImageList.add(true);
				else commenterImageList.add(false);

				commentListContent.add(LineFormatUtils.formatLine(bc.getContent()));
				commentListDate.add(DateFormatUtils.formatDateBoard(bc.getRegDate()));
			}
			
			
			request.setAttribute("commenterImageList", commenterImageList);
			request.setAttribute("board", board);
			request.setAttribute("regDate", regDate);
			request.setAttribute("content", content);
			request.setAttribute("boardCommentList", boardCommentList);
			request.setAttribute("commentListContent", commentListContent);
			request.setAttribute("commentListDate", commentListDate);
			
			
			request
				.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
