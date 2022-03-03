package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class AnonymousBoardViewServlet
 */
@WebServlet("/board/anonymousBoardView")
public class AnonymousBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.valueOf(request.getParameter("no"));
		System.out.println(no);
		// 쿠키 생성 
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String boardCookieVal = "";
		if(cookies != null ) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if("anonyBoardCookie".equals(name)) {
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
			int result = bulletinService.updateAnonyReadCount(no);
			
			Cookie cookie = new Cookie("anonyBoardCookie",boardCookieVal + "[" + no + "]");
			cookie.setPath(request.getContextPath() + "/board/anonymousBoardView");
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
			System.out.println("조회수 증가 & 쿠키 생성 ");
		}
		
		//게시판 데이터 가져오기
		Board board = bulletinService.selectOneAnonyBoard(no);

		//System.out.println(board);
		String regDate = DateFormatUtils.formatDate(board.getRegDate());
		String content = LineFormatUtils.formatLine(board.getContent());
		
		//게시판 댓글 가져오기
		List<BoardComment> boardCommentList = bulletinService.selectAnonyBoardCommentList(no);
		Map<Integer, String> anonyName = new HashMap<>();
		int count = 1;
		for(int i = 0; i < boardCommentList.size(); i++) {
			//System.out.println(boardCommentList.get(i));
			//사번 저장
			int empNo = boardCommentList.get(i).getEmpNo();
			//System.out.println(empNo);
			//댓글작성자가 map에 없으면
			if(!anonyName.containsKey(empNo)) {
				//System.out.println(12345678);
				String temp = "익명" + count++;
				anonyName.put(empNo, temp);
				//System.out.println(anonyName.get(empNo));
			}
		}
		
		
		
		List<String> commentListContent = new ArrayList<>();
		List<String> commentListDate = new ArrayList<>();
		
		for(BoardComment bc : boardCommentList) {
			commentListContent.add(LineFormatUtils.formatLine(bc.getContent()));
			commentListDate.add(DateFormatUtils.formatDateBoard(bc.getRegDate()));
		}
		request.setAttribute("board", board);
		request.setAttribute("regDate", regDate);
		request.setAttribute("content", content);
		request.setAttribute("boardCommentList", boardCommentList);
		request.setAttribute("commentListContent", commentListContent);
		request.setAttribute("commentListDate", commentListDate);
		request.setAttribute("anonyName", anonyName);
		
		request
			.getRequestDispatcher("/WEB-INF/views/anonymousBoard/anonymousBoardView.jsp")
			.forward(request, response);
	}

}
