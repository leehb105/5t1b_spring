package com.spring.otlb.bulletin.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.otlb.bulletin.model.service.BoardService;
import com.spring.otlb.bulletin.model.vo.Attachment;
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
	private ServletContext application;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue = "1", required = false) int pageNum,
			@RequestParam(required = false) String searchKeyword,
    		Model model) {
		
		int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
		cri.setKeyword(searchKeyword);
			
		Map<String, Object> param = new HashMap<>();
		param.put("pageNum", pageNum);
		param.put("cri", cri);

		List<Board> list = boardService.selectAllBoard(param);
//		List<String> regDate = new ArrayList<>();

		
		int total = boardService.selectTotalBoardCount(param);
		Paging page = new Paging(cri, total);
		
		
//		String url = request.getRequestURI();

        model.addAttribute("list", list);
        model.addAttribute("page", page);
	}
	
	@GetMapping("/boardView.do")
	public String boardView(
			@RequestParam int no,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// ?????? ??????
			Cookie oldCookie = null;
			Cookie[] cookies = request.getCookies();
			log.debug("cookies = {}", cookies);
			//???????????? ?????????
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					//boardView ?????? ?????? ??????
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
			
			
			//????????? ????????? ????????????
			Board board = boardService.selectBoardAttachments(no);
			log.debug("board and attach= {}", board);
			
			
//			String filepath = BoardViewServlet.class.getResource("/../../img/profile").getPath();
//			File writerProfileImage = new File(filepath + board.getEmpNo() + ".png");
//			if(writerProfileImage.exists()) request.setAttribute("writerProfileImageExists", true);
//			else request.setAttribute("writerProfileImageExists", false);
			
			//????????? ?????? ????????????
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
		return "/board/boardView";
	}

	@GetMapping(
			value = "/boardFileDownload",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public Resource fileDownload(@RequestParam int no, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Attachment attach = boardService.selectOneAttachment(no);
		log.debug("attach = {}", attach);

		//?????????????????? ?????? ??????
		String saveDirectory = application.getRealPath("/resources/upload");
		File downFile = new File(saveDirectory, attach.getFileName());
		String location = "file:" + downFile; //file????????? toString??? ??????????????? ???????????????????????????.
		log.debug("location = {}", location);
		Resource resource = resourceLoader.getResource(location);

		//????????????
		String filename = new String(attach.getFileName().getBytes("utf-8"), "iso-8859-1");
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

		return resource;
	}

	
	
	@GetMapping("/boardUpdate.do")
	public void boardUpdate(
			Model model,
			@RequestParam int no
	) {
		Board board = boardService.selectBoardAttachments(no);
		log.debug("board = {}", board);

		model.addAttribute("board", board);
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(
			RedirectAttributes attributes,
			Board board,
			@RequestParam(required = false, value = "upFile") MultipartFile[] upFiles,
			@RequestParam(required = false, value = "oldFileNo") int[] oldFileNo){

		log.debug("post board = {}", board);

		//?????? ????????? ?????? ??????
		//?????? ????????? ?????? ??????
		//?????? ????????? ?????? ??????
		//?????? ????????? ?????? ??????

//        log.debug("upFiles[0] = {}", upFiles[0]);
//        log.debug("board.getAttachments() = {}", board.getAttachments());
		//????????? ????????? ??????
		List<Attachment> oldAttach = boardService.selectAttachments(board.getNo());

		log.debug("oldAttach = {}", oldAttach.size());
		try{
			String saveDirectory = application.getRealPath("/resources/upload");

			for(int i = 0; i < oldFileNo.length; i++){
				log.debug("oldFiles size = {}", oldFileNo.length);
				//?????? ??????
				log.debug("oldFileNo = {}", oldFileNo[i]);
				for(int j = 0; j < oldAttach.size(); j++){
					log.debug("oldAttachNo = {}", oldAttach.get(j).getNo());
					if(oldFileNo[i] == oldAttach.get(j).getNo()){ //????????? ?????? no??? ?????? ?????? ?????? no??? ????????????
						File oldFile = new File(saveDirectory + "/" + oldAttach.get(j).getFileName());

						if(oldFile.exists()){ //?????? ?????????
							if(oldFile.delete()) { //??????
								int result = boardService.deleteAttachment(oldFileNo[i]);
							}
						}
					}
				}
			}

			List<Attachment> attachments = new ArrayList<>();
			log.debug("upFiles.length = {}", upFiles.length);
			//???????????? ??????????????? ??????
			for(int i = 0; i < upFiles.length; i++) {
				String fileName = null;
				log.debug("upFiles[i] = {}", upFiles[i].getOriginalFilename());
				MultipartFile uploadFile = upFiles[i];
				log.debug("uploadFile = {}", uploadFile.getOriginalFilename());
				if(!uploadFile.isEmpty()) {
					String originalFileName = uploadFile.getOriginalFilename();
					String ext = FilenameUtils.getExtension(originalFileName);	//????????? ?????????
					UUID uuid = UUID.randomUUID();	//UUID ?????????
					fileName = uuid + "." + ext;

					File dest = new File(saveDirectory, fileName);

					uploadFile.transferTo(dest);

					Attachment attach = new Attachment();
					attach.setFileName(fileName);
					attachments.add(attach);
				}
			}
//            if(!attachments.isEmpty()) {
//                board.setAttachments(attachments);
//            }
			for(Attachment attach : attachments) {
				attach.setBoardNo(board.getNo());
				log.debug("boardNo = {}", attach.getBoardNo());
				int result = boardService.insertAttachment(attach);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		int result = boardService.updateBoard(board);

		String msg = "";
		if(result > 0){
			msg = "???????????? ?????????????????????.";
		}else{
			msg = "????????? ?????? ??????";
		}
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/board/boardView.do?no=" + board.getNo();

	}

	@PostMapping("/boardLikeCount.do")
	public String boardLikeCount(
			RedirectAttributes attributes,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam int no){

		log.debug("no = {}", no);
		// ?????? ??????
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		log.debug("cookies = {}", cookies);
		String msg = "";
		int result = -1;

		//???????????? ?????????
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				//boardView ?????? ?????? ??????
				if(cookie.getName().equals("boardLikeCount")) {
					oldCookie = cookie;
				}
			}
		}

		if(oldCookie != null) {
			//?????? ???????????? ????????? ????????? ?????? ?????????
			if (!oldCookie.getValue().contains("[" + no + "]")) {
				result = boardService.updateBoardLikeCount(no);
				oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(oldCookie);
			}else{
				msg = "?????? ?????????????????????.";
				attributes.addFlashAttribute("msg", msg);
			}
		} else {
			//oldCookie??? ????????? ?????? ???????????????
			result = boardService.updateBoardLikeCount(no);
			Cookie newCookie = new Cookie("boardLikeCount","[" + no + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(newCookie);
		}

		if(result == 0){
			msg = "?????? ??????";
			attributes.addFlashAttribute("msg", msg);
		}

		return "redirect:/board/boardView.do?no=" + no;
	}





	@PostMapping("/boardDelete.do")
	public String boardDelete(
			RedirectAttributes attributes,
			@RequestParam int no
	){
		log.debug("no = {}", no);
		int result = boardService.deleteBoard(no);
		String msg = "";
		if(result > 0){
			msg = "???????????? ??????????????????.";
		}else{
			msg = "????????? ?????? ??????";
		}

		attributes.addFlashAttribute("msg", msg);
		return "redirect:/board/boardList.do";
	}

	@PostMapping("/boardCommentEnroll.do")
	public String boardCommentEnroll(
			RedirectAttributes attributes,
			Principal principal,
			@RequestParam int no,
			BoardComment boardComment,
			@RequestParam(required = false) int reCommentRef
	){
//            log.debug("no = {}", no);
//            log.debug("id = {}", principal.getName());
		log.debug("boardComment = {}", boardComment);
		log.debug("reCommentRef = {}", reCommentRef);

		//???????????? ?????? ?????? ?????? no ??????
		if(boardComment.getCommentLevel() != 1){
			boardComment.setCommentRef(reCommentRef);
		}
		boardComment.setEmpNo(principal.getName());
		int result = boardService.insertBoardComment(boardComment);

		String msg = "";
		if(result > 0){
			msg = "????????? ?????????????????????!";
		}else{
			msg = "?????? ?????? ??????";
		}
		attributes.addFlashAttribute("msg", msg);

		return "redirect:/board/boardView.do?no=" + no;

	}

	
	@GetMapping("/boardEnroll.do")
	public void boardEnroll() { }
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(
			@RequestParam(required = false, value = "upFile") MultipartFile[] upFiles,
			Board board,
			RedirectAttributes attributes) throws Exception {
		try {
			log.debug("board = {}", board);
			log.debug("upFiles.length = {}", upFiles.length);
			
			String saveDirectory = application.getRealPath("/resources/upload");
			
			List<Attachment> attachments = new ArrayList<>();
			
			//???????????? ??????????????? ??????
			for(int i = 0; i < upFiles.length; i++) {
				String fileName = null;
				MultipartFile uploadFile = upFiles[i];
				if(!uploadFile.isEmpty()) {
					String originalFileName = uploadFile.getOriginalFilename();
					String ext = FilenameUtils.getExtension(originalFileName);	//????????? ?????????
					UUID uuid = UUID.randomUUID();	//UUID ?????????
					fileName = uuid + "." + ext;
					
					File dest = new File(saveDirectory, fileName);
					
					uploadFile.transferTo(dest);
					
					Attachment attach = new Attachment();
					attach.setFileName(fileName);
					attachments.add(attach);
				}
			}
			if(!attachments.isEmpty()) {
				board.setAttachments(attachments);
			}
			
			log.debug("board2 = {}", board);
			
			int result = boardService.insertBoard(board);
			String msg = "";
			if(result > 0) {
				msg = "???????????? ??????????????????!";
				attributes.addFlashAttribute("msg", msg);
				return "redirect:/board/boardList.do";
			}else {
				msg = "???????????? ?????????????????????.";
				attributes.addFlashAttribute("msg", msg);
				return "redirect:/board/boardEnroll.do";
			}
			
		} catch (NumberFormatException | IOException e) {
			throw e;
		}
		
	}
	
	@PostMapping("/boardCommentDelete.do")
	public String boardCommentDelete(RedirectAttributes attributes,
		 @RequestParam int commentNo,
		 @RequestParam int no) {

		int result = boardService.deleteBoardComment(commentNo);
		String msg = "";
		if (result > 0) {
			msg = "????????? ?????????????????????.";
		} else {
			msg = "?????? ?????? ??????";
		}
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/board/boardView.do?no=" + no;
	}
	
	
}
