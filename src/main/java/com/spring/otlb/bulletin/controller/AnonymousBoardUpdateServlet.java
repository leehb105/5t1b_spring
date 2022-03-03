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
@WebServlet("/board/anonymousBoardUpdate")
public class AnonymousBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();
       
	/**
	 * select * from anonymous_board where no = ?
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Board board = bulletinService.selectOneAnonymousBoard(no);
		System.out.println(board);
		
		request.setAttribute("board", board);
		request
			.getRequestDispatcher("/WEB-INF/views/anonymousBoard/anonymousBoardUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * update board set category = ?, title = ?, content = ? where no = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// A. server computer에 사용자 업로드파일 저장
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			int maxPostSize = 1024 * 1024 * 10; // 10MB
			String encoding = "utf-8";
			FileRenamePolicy policy = new AttachFileRenamePolicy();
			
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 사용자입력값
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			int empNo = Integer.parseInt(multipartRequest.getParameter("empNo"));
			String[] delFiles = multipartRequest.getParameterValues("delFile");
			System.out.println(delFiles);
			
			Board board = new Board();
			board.setNo(no);
			board.setTitle(title);
			board.setContent(content);
			board.setEmpNo(empNo);
		
			// 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
			Enumeration fileNames = multipartRequest.getFileNames();
			List<Attachment> attachments = new ArrayList<>();
			while(fileNames.hasMoreElements()) {
				String fileName = (String) fileNames.nextElement();
				System.out.println("[BoardUpdateServlet] fileName = " + fileName);
				File upFile = multipartRequest.getFile(fileName);
				if(upFile != null) {
					Attachment attach = EmpUtils.makeAttachment(multipartRequest, fileName);
					attach.setBoardNo(no);
					attachments.add(attach);					
				}
			}
			if(!attachments.isEmpty())
				board.setAttachments(attachments);
			
			
			
			// 업무로직
			// a. 기존첨부파일 삭제
			if(delFiles != null) {
				for(String temp : delFiles) {
					int delFileNo = Integer.parseInt(temp);
					Attachment attach = bulletinService.selectOneAnonymousAttachment(delFileNo);
					//가. 첨부파일 삭제 
					String renamedFilename = attach.getRenamedFilename();
					File delFile = new File(saveDirectory, renamedFilename);
					boolean removed = delFile.delete();
					
					//나. DB 첨부파일 레코드 삭제
					int result = bulletinService.deleteAnonymousAttachment(delFileNo);
					
					System.out.println("[BoardUpdateServlet] " + renamedFilename + " 삭제 : " + removed);
					System.out.println("[BoardUpdateServlet] " + renamedFilename + "  레코드 삭제 : " + result);
					
					
				}
			}
			
			
			// b. db 레코드 수정 (update board + insert attachment)
			
			
			
			int result = bulletinService.updateAnonymousBoard(board);
			
			String msg = result > 0 ? "게시물 수정 성공" : "게시물 수정 실패";
			
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/board/anonymousBoardView?no=" + board.getNo();
			response.sendRedirect(location);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
