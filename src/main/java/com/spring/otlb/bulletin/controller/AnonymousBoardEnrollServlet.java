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
 * Servlet implementation class AnonymousBoardEnrollServlet
 */
@WebServlet("/board/anonymousBoardEnroll")
public class AnonymousBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * insert into anonymous_board(no, category, title, content) values(?, ?, ?, ?)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// A. server computer에 사용자 업로드파일 저장
			String saveDirectory = getServletContext().getRealPath("/upload/board"); // 여기서 /는 webroot 디렉토리
			System.out.println("[AnonymousBoardEnrollServlet] saveDirectory = " + saveDirectory);
			
			int maxPostSize = 1024 * 1024 * 10; // 10MB //바이트 단위
			String encoding = "utf-8";
			
			// 파일명 재지정 정책 객체
			// DefaultFileRenamePolicy : 동일한 이름의 파일은 numbering을 통해 overwrite을 방지
//			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy = new AttachFileRenamePolicy();
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			
			// B. 업로드한 파일 정보를 db에 저장 : attachment테이블 하나당 1행 저장
			
			
			
			// 1. 사용자입력값 처리
			// MultipartRequest객체 생성하는 경우, 기존 request가 아닌 MultipartRequest객체에서 값을 가져와야 한다.
			String category = multipartRequest.getParameter("category");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			int empNo = Integer.parseInt(multipartRequest.getParameter("empNo"));
			
			Board board = new Board();
			board.setCategory(category);
			board.setTitle(title);
			board.setContent(content);
			board.setEmpNo(empNo);
			
			// 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
			File upFile1 = multipartRequest.getFile("upFile1");
			File upFile2 = multipartRequest.getFile("upFile2");
			File upFile3 = multipartRequest.getFile("upFile3");
			File upFile4 = multipartRequest.getFile("upFile4");
			File upFile5 = multipartRequest.getFile("upFile5");
			Map<String, File> map = new HashMap<>();
			map.put("upFile1", upFile1);
			map.put("upFile2", upFile2);
			map.put("upFile3", upFile3);
			map.put("upFile4", upFile4);
			map.put("upFile5", upFile5);

			if(upFile1 != null || upFile2 != null || upFile3 != null || upFile4 != null || upFile5 != null) {
				List<Attachment> attachments = new ArrayList<>();
				Set<String> keySet = map.keySet();
				for(String key : keySet) {
					File file = map.get(key);
					if(file != null) {
						Attachment attach = EmpUtils.makeAttachment(multipartRequest, key);
						attachments.add(attach);
					}
				}
				board.setAttachments(attachments);
				System.out.println("[AnonymousBoardEnrollServlet] attachments = " + attachments);
			}

/*			
			if(upFile1 != null || upFile2 != null || upFile3 != null || upFile4 != null || upFile5 != null) {
				List<Attachment> attachments = new ArrayList<>();
				// 현재 fk인 boardNo 필드값은 비어있다.
				if(upFile1 != null) {
					Attachment attach1 = EmpUtils.makeAttachment(multipartRequest, "upFile1");
					attachments.add(attach1);
				}
				if(upFile2 != null) {
					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile2");
					attachments.add(attach2);
				}	
				if(upFile3 != null) {
					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile3");
					attachments.add(attach2);
				}	
				if(upFile4 != null) {
					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile4");
					attachments.add(attach2);
				}	
				if(upFile5 != null) {
					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile5");
					attachments.add(attach2);
				}	
				board.setAttachments(attachments);
				System.out.println("[AnonymousBoardEnrollServlet] attachments = " + attachments);
			}
*/			
			System.out.println("[AnonymousBoardEnrollServlet] board = " + board);
			
			// 2. 업무로직
			int result = bulletinService.insertAnonymousBoard(board);
			String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
			
			// 3. 응답요청
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/board/anonymousBoardView?no=" + board.getNo();
			response.sendRedirect(location);
		} catch (NumberFormatException | IOException e) {
			throw e;
		}
	}

}
