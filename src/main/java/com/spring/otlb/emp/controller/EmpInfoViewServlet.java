package com.otlb.semi.emp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class EmpListViewServlet
 */
@WebServlet("/emp/empInfoView")
public class EmpInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			System.out.println(empNo);
			Emp emp = empService.selectOneEmp(empNo);
			
			request.setAttribute("emp", emp);

			// 진짜 이렇게 짜면 안 되는데
			String filepath = EmpInfoViewServlet.class.getResource("../../../../../../../img/profile").getPath();
			File ownProfileImage = new File(filepath + emp.getEmpNo() + ".png");
			if(ownProfileImage.exists()) {
				request.setAttribute("ownProfileImageExists", true);
//				System.out.println("존재해");
			} else {
				request.setAttribute("ownProfileImageExists", false);
//				System.out.println("존재안해");
			}
			
			request.getRequestDispatcher("/WEB-INF/views/emp/empInfoView.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		
		}
	}

}
