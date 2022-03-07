package com.otlb.semi.bulletin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.Attachment;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));

		Attachment attach =bulletinService.selectAttachment(no);
		System.out.println("[FileDownloadServlet] attach = " + attach);
		
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		File downFile = new File(saveDirectory, attach.getRenamedFilename());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos= new BufferedOutputStream(sos);
		
		response.setContentType("application/octet-stream"); 
		String originalFilename = new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1");
		System.out.println("[FileDownloadServlet] originalFilename = " + originalFilename);
		response.setHeader("Content-Disposition", "attachment; filename=" + originalFilename);
		
		int data = -1;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
	
		bos.close();
		bis.close();
	}

}
