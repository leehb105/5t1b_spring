package com.otlb.semi.mainpage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.mainpage.model.service.SelectService;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.bulletin.model.vo.Notice;
import com.otlb.semi.foodMenu.model.vo.FoodMenu;
import com.otlb.semi.bulletin.model.vo.*;
import com.otlb.semi.mainpage.model.vo.AnonymousBoard;

/**
 * Servlet implementation class Notice
 */

@WebServlet("")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SelectService selectService = new SelectService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) 공지사항 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-------------servlet test ---------------");

		List<Notice> noticeList = selectService.selectNoticeContent();
		System.out.println("notice Servlet" + noticeList);

		List<Board> boardList = selectService.selectBoardContent();
		System.out.println("board  Servlet" + boardList);

		List<AnonymousBoard> anonymousBoardList = selectService.selectAnonymousBoardContent();
		System.out.println("anonymousBoard Servlet" + anonymousBoardList);

		List<BoardEntity> likeContentBoardSelect = selectService.selectBoardLikeContent();
		System.out.println("likeContentBoardSelect Servlet" + likeContentBoardSelect);
		
		List<BoardEntity> likeContentAnonymous_boardSelect = selectService.selectAnonymous_boardLikeContent();
		System.out.println("likeContentAnonymous_boardSelect Servlet" +likeContentAnonymous_boardSelect);

		FoodMenu foodMenu = selectService.selectFoodMenu();
		System.out.println("foodMenu Servlet" + foodMenu);
		
		request.getSession().setAttribute("noticeList", noticeList);
		request.getSession().setAttribute("boardList", boardList);
		request.getSession().setAttribute("anonymousBoardList", anonymousBoardList);
		request.getSession().setAttribute("likeContentBoardSelect", likeContentBoardSelect);
		request.getSession().setAttribute("likeContentAnonymous_boardSelect", likeContentAnonymous_boardSelect);
		request.getSession().setAttribute("foodMenu", foodMenu);
		String location = request.getContextPath() + "/index.jsp";
		response.sendRedirect(location);

	}

}
