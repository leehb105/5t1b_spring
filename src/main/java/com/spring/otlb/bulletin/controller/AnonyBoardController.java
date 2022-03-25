package com.spring.otlb.bulletin.controller;

import com.spring.otlb.bulletin.model.service.AnonyBoardService;
import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;
import com.spring.otlb.common.Criteria;
import com.spring.otlb.common.Paging;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/board")
public class AnonyBoardController {

    @Autowired
    private ServletContext application;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AnonyBoardService anonyBoardService;

    @PostMapping("/anonyLikeCount.do")
    public String anonyLikeCount(
             RedirectAttributes attributes,
             HttpServletRequest request,
             HttpServletResponse response,
             @RequestParam int no){

        log.debug("no = {}", no);
        // 쿠키 생성
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        log.debug("cookies = {}", cookies);
        String msg = "";
        int result = -1;

        //쿠키값이 있다면
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                //boardView 쿠키 여부 확인
                if(cookie.getName().equals("anonyBoardLikeCount")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) {
            //현재 게시글의 쿠키를 가지고 있지 않으면
            if (!oldCookie.getValue().contains("[" + no + "]")) {
                result = anonyBoardService.updateAnonyBoardLikeCount(no);
                oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }else{
                msg = "이미 추천하셨습니다.";
                attributes.addFlashAttribute("msg", msg);
            }
        } else {
            //oldCookie가 없으면 새로 생성해준다
            result = anonyBoardService.updateAnonyBoardLikeCount(no);
            Cookie newCookie = new Cookie("anonyBoardLikeCount","[" + no + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        if(result == 0){
            msg = "추천 오류";
            attributes.addFlashAttribute("msg", msg);
        }

        return "redirect:/board/anonymousBoardView.do?no=" + no;
    }

//    @PostMapping("/anonymousBoardCommentEnroll.do")
//    public String anonymousBoardCommentEnroll(){
//        HttpSession session = request.getSession();
//        Emp emp = (Emp) session.getAttribute("loginEmp");
//
//        int no = Integer.valueOf(request.getParameter("no"));
//        int empNo = emp.getEmpNo();
//        int commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
//        int commentRef = Integer.valueOf(request.getParameter("commentRef"));
//        String content = request.getParameter("content");
//        BoardComment bc = new BoardComment(0, commentLevel, content, null, commentRef, null, no, empNo, null);
//        //System.out.println(content);
//
//        int result = bulletinService.insertAnonyBoardComment(bc);
//        String msg = result > 0 ? "댓글을 등록했습니다!" : "댓글 등록에 실패했습니다...";
//        session.setAttribute("msg", msg);
//
//        //댓글작성한 해당 글로 리다이렉트
//        String location = request.getContextPath() + "/board/anonymousBoardView?no=" + bc.getBoardNo();
//        response.sendRedirect(location);
//        return null;
//    }

    @PostMapping("/anonymousBoardDelete.do")
    public String anonymousBoardDelete(
            RedirectAttributes attributes,
            @RequestParam int no
    ){
          log.debug("no = {}", no);
          int result = anonyBoardService.deleteAnonymousBoard(no);
          String msg = "";
          if(result > 0){
              msg = "게시글을 삭제했습니다.";
          }else{
              msg = "게시글 삭제 오류";
          }

          attributes.addFlashAttribute("msg", msg);
          return "redirect:/board/anonymousBoardList.do";
    }

    @GetMapping("/anonymousBoardEnroll.do")
    public void anonymousBoardEnroll(){}

    @PostMapping("/anonymousBoardEnroll.do")
    public String anonymousBoardEnroll(
            Model model,
            @RequestParam(required = false, value = "upFile") MultipartFile[] upFiles,
            Board board,
            RedirectAttributes attributes) throws IOException {

        try {
            log.debug("board = {}", board);
            log.debug("upFiles.length = {}", upFiles.length);

            String saveDirectory = application.getRealPath("/resources/upload");

            List<Attachment> attachments = new ArrayList<>();

            //업로드한 파일갯수별 처리
            for(int i = 0; i < upFiles.length; i++) {
                String fileName = null;
                MultipartFile uploadFile = upFiles[i];
                if(!uploadFile.isEmpty()) {
                    String originalFileName = uploadFile.getOriginalFilename();
                    String ext = FilenameUtils.getExtension(originalFileName);	//확장자 구하기
                    UUID uuid = UUID.randomUUID();	//UUID 구하기
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

            int result = anonyBoardService.insertAnonymousBoard(board);
            String msg = "";
            if(result > 0) {
                msg = "게시글을 등록했습니다!";
                attributes.addFlashAttribute("msg", msg);
                return "redirect:/board/anonymousBoardList.do";
            }else {
                msg = "게시글을 등록오류입니다.";
                attributes.addFlashAttribute("msg", msg);
                return "redirect:/board/anonymousBoardEnroll.do";
            }

        } catch (NumberFormatException | IOException e) {
            throw e;
        }

    }
    @GetMapping(
            value = "/anonyFileDownload",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public Resource fileDownload(@RequestParam int no, HttpServletResponse response)
            throws UnsupportedEncodingException {
        Attachment attach = anonyBoardService.selectOneAttachment(no);
        log.debug("attach = {}", attach);

        //다운로드받을 파일 경로
        String saveDirectory = application.getRealPath("/resources/upload");
        File downFile = new File(saveDirectory, attach.getFileName());
        String location = "file:" + downFile; //file객체의 toString은 절대경로로 오버라이드되어있다.
        log.debug("location = {}", location);
        Resource resource = resourceLoader.getResource(location);

        //헤더설정
        String filename = new String(attach.getFileName().getBytes("utf-8"), "iso-8859-1");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

        return resource;
    }



//
//    @GetMapping("/anonymousBoardFinder.do")
//    public void anonymousBoardFinder(){
//        String searchType = request.getParameter("searchType");
//        String searchKeyword = request.getParameter("searchKeyword");
//        Map<String, Object> param = new HashMap<>();
//        param.put("searchType", searchType);
//        param.put("searchKeyword", searchKeyword);
//        System.out.println("param@servlet = " + param);
//
//        List<Board> list = bulletinService.searchAnonymousBoard(param);
//        List<String> regDate = new ArrayList<>();
//        System.out.println("list : " + list);
//
//        for(Board board : list) {
//            regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
//        }
//
//        request.setAttribute("list", list);
//        request.setAttribute("regDate", regDate);
//        request
//                .getRequestDispatcher("/WEB-INF/views/anonymousBoard/anonymousBoardList.jsp")
//                .forward(request, response);
//    }

    @GetMapping("/anonymousBoardUpdate.do")
    public void anonymousBoardUpdate(
            Model model,
            @RequestParam int no
    ){
            log.debug("no = {}", no);
            Board board = anonyBoardService.selectAnonyBoardAttachments(no);
            log.debug("board = {}", board);

            model.addAttribute("board", board);
    }

//    @PostMapping("/anonymousBoardUpdate.do")
//    public String anonymousBoardUpdate(){
//        try {
//            // A. server computer에 사용자 업로드파일 저장
//            String saveDirectory = getServletContext().getRealPath("/upload/board");
//            int maxPostSize = 1024 * 1024 * 10; // 10MB
//            String encoding = "utf-8";
//            FileRenamePolicy policy = new AttachFileRenamePolicy();
//
//            MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
//
//            // 사용자입력값
//            int no = Integer.parseInt(multipartRequest.getParameter("no"));
//            String title = multipartRequest.getParameter("title");
//            String content = multipartRequest.getParameter("content");
//            int empNo = Integer.parseInt(multipartRequest.getParameter("empNo"));
//            String[] delFiles = multipartRequest.getParameterValues("delFile");
//            System.out.println(delFiles);
//
//            Board board = new Board();
//            board.setNo(no);
//            board.setTitle(title);
//            board.setContent(content);
//            board.setEmpNo(empNo);
//
//            // 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
//            Enumeration fileNames = multipartRequest.getFileNames();
//            List<Attachment> attachments = new ArrayList<>();
//            while(fileNames.hasMoreElements()) {
//                String fileName = (String) fileNames.nextElement();
//                System.out.println("[BoardUpdateServlet] fileName = " + fileName);
//                File upFile = multipartRequest.getFile(fileName);
//                if(upFile != null) {
//                    Attachment attach = EmpUtils.makeAttachment(multipartRequest, fileName);
//                    attach.setBoardNo(no);
//                    attachments.add(attach);
//                }
//            }
//            if(!attachments.isEmpty())
//                board.setAttachments(attachments);
//
//
//
//            // 업무로직
//            // a. 기존첨부파일 삭제
//            if(delFiles != null) {
//                for(String temp : delFiles) {
//                    int delFileNo = Integer.parseInt(temp);
//                    Attachment attach = bulletinService.selectOneAnonymousAttachment(delFileNo);
//                    //가. 첨부파일 삭제
//                    String renamedFilename = attach.getRenamedFilename();
//                    File delFile = new File(saveDirectory, renamedFilename);
//                    boolean removed = delFile.delete();
//
//                    //나. DB 첨부파일 레코드 삭제
//                    int result = bulletinService.deleteAnonymousAttachment(delFileNo);
//
//                    System.out.println("[BoardUpdateServlet] " + renamedFilename + " 삭제 : " + removed);
//                    System.out.println("[BoardUpdateServlet] " + renamedFilename + "  레코드 삭제 : " + result);
//
//
//                }
//            }
//
//
//            // b. db 레코드 수정 (update board + insert attachment)
//
//
//
//            int result = bulletinService.updateAnonymousBoard(board);
//
//            String msg = result > 0 ? "게시물 수정 성공" : "게시물 수정 실패";
//
//            request.getSession().setAttribute("msg", msg);
//            String location = request.getContextPath() + "/board/anonymousBoardView?no=" + board.getNo();
//            response.sendRedirect(location);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return null;
//    }

    @GetMapping("/anonymousBoardList.do")
    public void anonymousBoardList(
            @RequestParam(defaultValue = "1", required = false) int pageNum,
            Model model){
        int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);

        Map<String, Object> param = new HashMap<>();
        param.put("pageNum", pageNum);
        param.put("cri", cri);

        List<Board> list = anonyBoardService.selectAllAnonymousBoard(param);


        int total = anonyBoardService.selectTotalAnonyBoardCount();
        Paging page = new Paging(cri, total);


        model.addAttribute("list", list);
        model.addAttribute("page", page);


    }

    @GetMapping("/anonymousBoardView.do")
    public String anonymousBoardView(
            Model model,
            @RequestParam int no,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        log.debug("no = {}", no);
        // 쿠키 생성
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        log.debug("cookies = {}", cookies);
        //쿠키값이 있다면
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                //boardView 쿠키 여부 확인
                if(cookie.getName().equals("anonyBoardView")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + no + "]")) {
                anonyBoardService.updateReadCount(no);
                oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            anonyBoardService.updateReadCount(no);
            Cookie newCookie = new Cookie("anonyBoardView","[" + no + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        Board board = anonyBoardService.selectAnonyBoardAttachments(no);
        log.debug("board = {}", board);

        List<BoardComment> boardCommentList = anonyBoardService.selectAnonyBoardCommentList(no);
        log.debug("boardCommentList = {}", boardCommentList);

        model.addAttribute("board", board);
        model.addAttribute("boardCommentList", boardCommentList);

        return "/board/anonymousBoardView";
//        //게시판 댓글 가져오기
//        List<BoardComment> boardCommentList = bulletinService.selectAnonyBoardCommentList(no);
//        Map<Integer, String> anonyName = new HashMap<>();
//        int count = 1;
//        for(int i = 0; i < boardCommentList.size(); i++) {
//            //System.out.println(boardCommentList.get(i));
//            //사번 저장
//            int empNo = boardCommentList.get(i).getEmpNo();
//            //System.out.println(empNo);
//            //댓글작성자가 map에 없으면
//            if(!anonyName.containsKey(empNo)) {
//                //System.out.println(12345678);
//                String temp = "익명" + count++;
//                anonyName.put(empNo, temp);
//                //System.out.println(anonyName.get(empNo));
//            }
//        }

    }


}
