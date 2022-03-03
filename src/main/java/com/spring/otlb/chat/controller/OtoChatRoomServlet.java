package com.otlb.semi.chat.controller;

import static com.otlb.semi.common.JdbcTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.emp.model.dao.EmpDao;
import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class ChatroomServlet
 */
@WebServlet("/otochat/otochatroom")
public class OtoChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();
	private EmpDao empDao = new EmpDao();
	
	public OtoChatRoomServlet() {
		System.out.println("////ChatroomServlet////...create");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.
		String otoSenderId = request.getParameter("otoSenderId");
		String otoReceiverId = request.getParameter("otoReceiverId");
		String otoSRTp = request.getParameter("otoSRTp");

		System.out.println("[OtoChatRoomServlet]" + otoSenderId + ":" + otoReceiverId + ":" + otoSRTp);

		// initialize
		if (otoSenderId == null) {
			otoSenderId = "";
		}
		if (otoReceiverId == null) {
			otoReceiverId = "";
		}
		if (otoSenderId.trim().equals("") || otoReceiverId.trim().equals("")) {
			request.setAttribute("errorMsg", "파라미터 확인이 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/chat/otoChatRoomError.jsp").forward(request, response);
			return;
		}
		Connection conn = getConnection();
		//Emp emp = empDao.selectOneEmp(conn, no);
		
//		Emp sndEmp = empService.selectOneEmp(Integer.parseInt(otoSenderId));
//		Emp rcvEmp = empService.selectOneEmp(Integer.parseInt(otoReceiverId));

		Emp sndEmp = empDao.selectOneEmp(conn, Integer.parseInt(otoSenderId));
		Emp rcvEmp = empDao.selectOneEmp(conn, Integer.parseInt(otoReceiverId));
		
		
		// 2.
		// websocket session
		request.getSession().setAttribute("otoSenderId", otoSenderId);
		request.getSession().setAttribute("otoReceiverId", otoReceiverId);

		request.setAttribute("otoSenderId", otoSenderId);
		request.setAttribute("otoSenderNm", sndEmp.getEmpName());
		request.setAttribute("otoSenderDeptNm", sndEmp.getDeptName());
		request.setAttribute("otoReceiverId", otoReceiverId);
		request.setAttribute("otoReceiverNm", rcvEmp.getEmpName());
		request.setAttribute("otoReceiverDeptNm", rcvEmp.getDeptName());
		request.setAttribute("otoSRTp", otoSRTp);

		// 3. view
		request.getRequestDispatcher("/WEB-INF/views/chat/otoChatRoom.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[OtoChatRoomServlet][doPost][called]");
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[OtoChatRoomServlet][doProcess][called]");
	}
}
