package com.otlb.semi.emp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.otlb.semi.common.ProfileRenamePolicy;
import com.otlb.semi.emp.model.vo.Emp;
//import com.otlb.semi.message.model.dao.MessageDao;

/**
 * Servlet implementation class UpdateProfileImgServlet
 */
@WebServlet("/emp/updateProfileImg")
public class UpdateProfileImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 프사변경페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("loginEmp");
		String empNo = String.valueOf(emp.getEmpNo());

		String filepath = UpdateProfileImgServlet.class.getResource("/../../img/profile").getPath();
		File ownProfileImage = new File(filepath + empNo + ".png");
		if(ownProfileImage.exists()) session.setAttribute("ownProfileImageExists", true);
		else session.setAttribute("ownProfileImageExists", false);
		request.getRequestDispatcher("/WEB-INF/views/emp/updateProfileImg.jsp").forward(request, response);
	}

	/**
	 * 프사변경
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession(); Emp emp = (Emp)
		session.getAttribute("loginEmp");
		String empNo = String.valueOf(emp.getEmpNo());
		
		String filepath = UpdateProfileImgServlet.class.getResource("/../../img/profile").getPath();
		File ownProfileImage = new File(filepath + empNo + ".png");
		if(ownProfileImage.exists()) session.setAttribute("ownProfileImageExists", true);
		else session.setAttribute("ownProfileImageExists", false);

		// 파일이 저장되는 경로
		String path = getServletContext().getRealPath("/img/profile");
		System.out.println("경로:" + path);
		int size = 1024 * 1024 * 10; // 저장가능한 파일 크기
		String file = ""; // 업로드 한 파일의 이름(이름이 변경될수 있다)
		String originalFile = ""; // 이름이 변경되기 전 실제 파일 이름

		FileRenamePolicy policy = new ProfileRenamePolicy(empNo);

		// 실제로 파일 업로드하는 과정
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", policy);

			Enumeration files = multi.getFileNames();
			String str = (String) files.nextElement(); // 파일 이름을 받아와 string으로 저장

			file = multi.getFilesystemName(str); // 업로드 된 파일 이름 가져옴
			originalFile = multi.getOriginalFileName(str); // 원래의 파일이름 가져옴

			String msg = "프로필사진 변경 성공";
			session.setAttribute("msg", msg);

			String location = request.getContextPath() + "/emp/empView";
			response.sendRedirect(location);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
