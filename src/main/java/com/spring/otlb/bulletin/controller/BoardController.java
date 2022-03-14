package com.spring.otlb.bulletin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.otlb.bulletin.model.service.BoardService;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;
import com.spring.otlb.common.Criteria;
import com.spring.otlb.common.Paging;
import com.spring.otlb.emp.model.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue = "1") int pageNum, 
    		Model model, 
    		HttpServletRequest request ) {
		
		int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
			
		Map<String, Object> param = new HashMap<>();
		param.put("pageNum", pageNum);
		param.put("cri", cri);

		List<Board> list = boardService.selectAllBoard(param);
//		List<String> regDate = new ArrayList<>();
		log.debug("list = {}", list);
		
//		for(Board board : list) {
//			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
//		}
		
		int total = boardService.selectTotalBoardCount();
		Paging page = new Paging(cri, total);
		
		
//		String url = request.getRequestURI();

        model.addAttribute("list", list);
        model.addAttribute("page", page);
	}
	
	@GetMapping("/boardView.do")
	public void boardView(@RequestParam int no,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			log.debug("no = {}", no);
			// 쿠키 생성 
			Cookie oldCookie = null;
			Cookie[] cookies = request.getCookies();
			log.debug("cookies = {}", cookies);
			//쿠키값이 있다면
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					//boardView 쿠키 여부 확인
					if(cookie.getName().equals("boardView")) {
						oldCookie = cookie;
					}
				}
			}
			
			if(oldCookie != null) {
				 if (!oldCookie.getValue().contains("[" + no + "]")) {
					 	boardService.updateReadCount(no);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    } else {
			    	boardService.updateReadCount(no);
			        Cookie newCookie = new Cookie("boardView","[" + no + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
		    }
			
			
			//게시판 데이터 가져오기
			Board board = boardService.selectOneBoard(no);
			log.debug("board = {}", board);
//			String filepath = BoardViewServlet.class.getResource("/../../img/profile").getPath();
//			File writerProfileImage = new File(filepath + board.getEmpNo() + ".png");
//			if(writerProfileImage.exists()) request.setAttribute("writerProfileImageExists", true);
//			else request.setAttribute("writerProfileImageExists", false);
			
			//게시판 댓글 가져오기
			List<BoardComment> boardCommentList = boardService.selectBoardCommentList(no);
			log.debug("boardCommentList = {}", boardCommentList);
//			List<Boolean> commenterImageList = new ArrayList<>();
//			for(BoardComment bc : boardCommentList) {
//				File commenterProfileImage = new File(filepath + bc.getEmpNo() + ".png");
//				if(commenterProfileImage.exists()) commenterImageList.add(true);
//				else commenterImageList.add(false);
//
//			}
			
			model.addAttribute("board", board);
			model.addAttribute("boardCommentList", boardCommentList);
//			model.addAttribute("commenterImageList", commenterImageList);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
//	
//	@GetMapping("/boardUpdate.do")
//	public void boardUpdate() {
//		int no = Integer.parseInt(request.getParameter("no"));
//		
//		Board board = bulletinService.selectOneBoard(no);
//		System.out.println(board);
//		
//		request.setAttribute("board", board);
//		request
//			.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp")
//			.forward(request, response);
//	}
//	
//	@PostMapping("/boardUpdate.do")
//	public String boardUpdate() {
//		try {
//			// A. server computer에 사용자 업로드파일 저장
//			String saveDirectory = getServletContext().getRealPath("/upload/board");
//			int maxPostSize = 1024 * 1024 * 10; // 10MB
//			String encoding = "utf-8";
//			FileRenamePolicy policy = new AttachFileRenamePolicy();
//			
//			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
//			
//			// 사용자입력값
//			int no = Integer.parseInt(multipartRequest.getParameter("no"));
//			String category = multipartRequest.getParameter("category");
//			String title = multipartRequest.getParameter("title");
//			String content = multipartRequest.getParameter("content");
//			int empNo = Integer.parseInt(multipartRequest.getParameter("empNo"));
//			String[] delFiles = multipartRequest.getParameterValues("delFile");
//			System.out.println(delFiles);
//			
//			Board board = new Board();
//			board.setNo(no);
//			board.setCategory(category);
//			board.setTitle(title);
//			board.setContent(content);
//			board.setEmpNo(empNo);
//		
//			// 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
//			Enumeration fileNames = multipartRequest.getFileNames();
//			List<Attachment> attachments = new ArrayList<>();
//			while(fileNames.hasMoreElements()) {
//				String fileName = (String) fileNames.nextElement();
//				System.out.println("[BoardUpdateServlet] fileName = " + fileName);
//				File upFile = multipartRequest.getFile(fileName);
//				if(upFile != null) {
//					Attachment attach = EmpUtils.makeAttachment(multipartRequest, fileName);
//					attach.setBoardNo(no);
//					attachments.add(attach);					
//				}
//			}
//			if(!attachments.isEmpty())
//				board.setAttachments(attachments);
//			
//			
//			
//			// 업무로직
//			// a. 기존첨부파일 삭제
//			if(delFiles != null) {
//				for(String temp : delFiles) {
//					int delFileNo = Integer.parseInt(temp);
//					Attachment attach = bulletinService.selectOneAttachment(delFileNo);
//					//가. 첨부파일 삭제 
//					String renamedFilename = attach.getRenamedFilename();
//					File delFile = new File(saveDirectory, renamedFilename);
//					boolean removed = delFile.delete();
//					
//					//나. DB 첨부파일 레코드 삭제
//					int result = bulletinService.deleteAttachment(delFileNo);
//					
//					System.out.println("[BoardUpdateServlet] " + renamedFilename + " 삭제 : " + removed);
//					System.out.println("[BoardUpdateServlet] " + renamedFilename + "  레코드 삭제 : " + result);
//					
//					
//				}
//			}
//			
//			
//			// b. db 레코드 수정 (update board + insert attachment)
//			
//			
//			
//			int result = bulletinService.updateBoard(board);
//			
//			String msg = result > 0 ? "게시물 수정 성공" : "게시물 수정 실패";
//			
//			request.getSession().setAttribute("msg", msg);
//			String location = request.getContextPath() + "/board/noticeView?no=" + board.getNo();
//			response.sendRedirect(location);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return null;
//	}
//	
//	@PostMapping("/boardLikeCount.do")
//	public String boardLikeCount() {
//		int no = Integer.valueOf(request.getParameter("no"));
//
//		String msg = "";
//		// 쿠키 생성 
//		Cookie[] cookies = request.getCookies();
//		boolean hasRead = false;
//		String boardLikeCookieVal = "";
//		if(cookies != null ) {
//			for(Cookie cookie : cookies) {
//				String name = cookie.getName();
//				String value = cookie.getValue();
//				if("boardLikeCookie".equals(name)) {
//					boardLikeCookieVal = value;
//					if(value.contains("[" + no + "]")) {
//						hasRead = true;
//						break;
//					}
//				}
//			}
//		}
//		// 좋아요 증가 및 쿠키 생성 
//		if(!hasRead) {
//			int result = bulletinService.updateBoardLikeCount(no);
//			
//			Cookie cookie = new Cookie("boardLikeCookie",boardLikeCookieVal + "[" + no + "]");
//			cookie.setPath(request.getContextPath());
//			cookie.setMaxAge(365 * 24 * 60 * 60);
//			response.addCookie(cookie);
//			//System.out.println("조회수 증가 & 쿠키 생성 ");
//			msg = result > 0 ? "추천하셨습니다!" : "추천에 오류가 있습니다...";
//		}else {
//			msg = "이미 추천하셨습니다.";
//		}
//
//		
//		request.getSession().setAttribute("msg", msg);
//		
//		String location = request.getContextPath() + "/board/boardView?no=" + no;
//		response.sendRedirect(location);
//		return null;
//	}
//	
//	@GetMapping("/boardForm.do")
//	public void boardForm() {
//		
//	}
//	
//	@GetMapping("/boardFinder.do")
//	public void boardFinder() {
//		String searchType = request.getParameter("searchType");
//		String searchKeyword = request.getParameter("searchKeyword");
//		Map<String, Object> param = new HashMap<>();
//		param.put("searchType", searchType);
//		param.put("searchKeyword", searchKeyword);
//		System.out.println("param@servlet = " + param);
//		
//		List<Board> list = bulletinService.searchBoard(param);
//		List<String> regDate = new ArrayList<>();
//		System.out.println("list : " + list);
//		
//		for(Board board : list) {
//			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
//		}
//		
//		request.setAttribute("list", list);
//		request.setAttribute("regDate", regDate);
//		request
//			.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
//			.forward(request, response);
//	}
//	
//	@PostMapping("/boardDelete.do")
//	public String boardDelete() {
//		int no = Integer.parseInt( request.getParameter("no"));
//		int result = bulletinService.deleteBoard(no);
//		
//		String msg = result > 0 ? "게시물 삭제 성공" : "게시물 삭제 실패";
//		
//		request.getSession().setAttribute("msg", msg);
//		String location = request.getContextPath() + "/board/boardList";
//		response.sendRedirect(location);
//		return null;
//	}
//	
//	@PostMapping("/boardCommentEnroll.do")
//	public String boardCommentEnroll() {
//		HttpSession session = request.getSession();
//		Emp emp = (Emp) session.getAttribute("loginEmp");
//
//		int no = Integer.valueOf(request.getParameter("no"));
//		int empNo = emp.getEmpNo();
//		int commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
//		int commentRef = Integer.valueOf(request.getParameter("commentRef"));
//		String content = request.getParameter("content");
//		BoardComment bc = new BoardComment(0, commentLevel, content, null, commentRef, null, no, empNo, null);
//		//System.out.println(content);
//		
//		int result = bulletinService.insertBoardComment(bc);
//		String msg = result > 0 ? "댓글을 등록했습니다!" : "댓글 등록에 실패했습니다...";
//		session.setAttribute("msg", msg);
//		
//		//댓글작성한 해당 글로 리다이렉트
//		String location = request.getContextPath() + "/board/boardView?no=" + bc.getBoardNo();
//		response.sendRedirect(location);
//		return null;
//	}
//	
//	@PostMapping("/boardEnroll.do")
//	public String boardEnroll() {
//		try {
//			System.out.println("이게 찍혀야됨");
//			// A. server computer에 사용자 업로드파일 저장
//			String saveDirectory = getServletContext().getRealPath("/upload/board"); // 여기서 /는 webroot 디렉토리
//			System.out.println("[BoardEnrollServlet] saveDirectory = " + saveDirectory);
//			
//			int maxPostSize = 1024 * 1024 * 10; // 10MB //바이트 단위
//			String encoding = "utf-8";
//			
//			// 파일명 재지정 정책 객체
//			// DefaultFileRenamePolicy : 동일한 이름의 파일은 numbering을 통해 overwrite을 방지
////			FileRenamePolicy policy = new DefaultFileRenamePolicy();
//			FileRenamePolicy policy = new AttachFileRenamePolicy();
//			
//			MultipartRequest multipartRequest = 
//					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
//			
//			
//			// B. 업로드한 파일 정보를 db에 저장 : attachment테이블 하나당 1행 저장
//			
//			
//			
//			// 1. 사용자입력값 처리
//			// MultipartRequest객체 생성하는 경우, 기존 request가 아닌 MultipartRequest객체에서 값을 가져와야 한다.
//			String category = multipartRequest.getParameter("category");
//			String title = multipartRequest.getParameter("title");
//			String content = multipartRequest.getParameter("content");
//			int empNo = Integer.parseInt(multipartRequest.getParameter("empNo"));
//			
//			Board board = new Board();
//			board.setCategory(category);
//			board.setTitle(title);
//			board.setContent(content);
//			board.setEmpNo(empNo);
//			
//			// 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
//			File upFile1 = multipartRequest.getFile("upFile1");
//			File upFile2 = multipartRequest.getFile("upFile2");
//			File upFile3 = multipartRequest.getFile("upFile3");
//			File upFile4 = multipartRequest.getFile("upFile4");
//			File upFile5 = multipartRequest.getFile("upFile5");
//			Map<String, File> map = new HashMap<>();
//			map.put("upFile1", upFile1);
//			map.put("upFile2", upFile2);
//			map.put("upFile3", upFile3);
//			map.put("upFile4", upFile4);
//			map.put("upFile5", upFile5);
//
//			if(upFile1 != null || upFile2 != null || upFile3 != null || upFile4 != null || upFile5 != null) {
//				List<Attachment> attachments = new ArrayList<>();
//				Set<String> keySet = map.keySet();
//				for(String key : keySet) {
//					File file = map.get(key);
//					if(file != null) {
//						Attachment attach = EmpUtils.makeAttachment(multipartRequest, key);
//						attachments.add(attach);
//					}
//				}
//				board.setAttachments(attachments);
//				System.out.println("[BoardEnrollServlet] attachments = " + attachments);
//			}
//
///*			
//			if(upFile1 != null || upFile2 != null || upFile3 != null || upFile4 != null || upFile5 != null) {
//				List<Attachment> attachments = new ArrayList<>();
//				// 현재 fk인 boardNo 필드값은 비어있다.
//				if(upFile1 != null) {
//					Attachment attach1 = EmpUtils.makeAttachment(multipartRequest, "upFile1");
//					attachments.add(attach1);
//				}
//				if(upFile2 != null) {
//					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile2");
//					attachments.add(attach2);
//				}	
//				if(upFile3 != null) {
//					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile3");
//					attachments.add(attach2);
//				}	
//				if(upFile4 != null) {
//					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile4");
//					attachments.add(attach2);
//				}	
//				if(upFile5 != null) {
//					Attachment attach2 = EmpUtils.makeAttachment(multipartRequest, "upFile5");
//					attachments.add(attach2);
//				}	
//				board.setAttachments(attachments);
//				System.out.println("[BoardEnrollServlet] attachments = " + attachments);
//			}
//*/			
//			System.out.println("[BoardEnrollServlet] board = " + board);
//			
//			// 2. 업무로직
//			int result = bulletinService.insertBoard(board);
//			String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
//			
//			// 3. 응답요청
//			request.getSession().setAttribute("msg", msg);
//			String location = request.getContextPath() + "/board/boardView?no=" + board.getNo();
//			System.out.println(location);
//			response.sendRedirect(location);
//		} catch (NumberFormatException | IOException e) {
//			throw e;
//		}
//		return null;
//		
//	}
//	
//	@PostMapping("/boardCommentDelete.do")
//	public String boardCommentDelete() {
//		int boardNo = Integer.valueOf(request.getParameter("boardNo"));
//		int no = Integer.parseInt(request.getParameter("no"));
//		
//		int result = bulletinService.deleteBoardComment(no);
//		
//		String msg = (result > 0) ? "댓글을 삭제했습니다." : "댓글이 안지워져요...";	
//		
//		request.getSession().setAttribute("msg", msg);
//		String location = request.getContextPath() + "/board/boardView?no=" + boardNo;
//		response.sendRedirect(location);
//		return null;
//	}
	
	
	
}
