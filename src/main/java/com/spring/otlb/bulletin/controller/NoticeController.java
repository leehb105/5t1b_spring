package com.spring.otlb.bulletin.controller;

import com.spring.otlb.bulletin.model.service.NoticeService;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;
import com.spring.otlb.common.Criteria;
import com.spring.otlb.common.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/noticeEnroll.do")
    public void noticeEnroll(){

    }

    @GetMapping("/noticeForm.do")
    public void noticeForm(){}

    @GetMapping("/noticeList.do")
    public void noticeList(Model model,
       @RequestParam(defaultValue = "1", required = false) int pageNum,
       @RequestParam(required = false) String searchKeyword){

        int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
        cri.setKeyword(searchKeyword);

        Map<String, Object> param = new HashMap<>();
        param.put("pageNum", pageNum);
        param.put("cri", cri);

        List<Board> list = noticeService.selectAllNotice(param);


        int total = noticeService.selectTotalNoticeCount();
        Paging page = new Paging(cri, total);

        model.addAttribute("list", list);
        model.addAttribute("page", page);

    }

    @GetMapping("/noticeView.do")
    public String noticeView(
            @RequestParam int no,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 쿠키 생성
            Cookie oldCookie = null;
            Cookie[] cookies = request.getCookies();
            log.debug("cookies = {}", cookies);
            //쿠키값이 있다면
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    //boardView 쿠키 여부 확인
                    if(cookie.getName().equals("noticeView")) {
                        oldCookie = cookie;
                    }
                }
            }

            if(oldCookie != null) {
                if (!oldCookie.getValue().contains("[" + no + "]")) {
                    noticeService.updateReadCount(no);
                    oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
                    oldCookie.setPath("/");
                    oldCookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(oldCookie);
                }
            } else {
                noticeService.updateReadCount(no);
                Cookie newCookie = new Cookie("noticeView","[" + no + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(newCookie);
            }


            //게시판 데이터 가져오기
            Board board = noticeService.selectOneNotice(no);
            log.debug("board and attach= {}", board);


//			String filepath = BoardViewServlet.class.getResource("/../../img/profile").getPath();
//			File writerProfileImage = new File(filepath + board.getEmpNo() + ".png");
//			if(writerProfileImage.exists()) request.setAttribute("writerProfileImageExists", true);
//			else request.setAttribute("writerProfileImageExists", false);



            model.addAttribute("board", board);
//			model.addAttribute("commenterImageList", commenterImageList);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return "/board/noticeView";
    }

    @GetMapping("/noticeUpdate.do")
    public void noticeUpdate(Model model,
        @RequestParam int no){
        Board board = noticeService.selectOneNotice(no);

        model.addAttribute("board", board);

    }

    @PostMapping("/noticeUpdate.do")
    public String noticeUpdate(RedirectAttributes attributes,
       Board board){

        int result = noticeService.updateNotice(board);

        String msg = "";
        if(result > 0){
            msg = "게시글을 수정하였습니다.";
        }else{
            msg = "게시글 수정 오류";
        }
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/board/noticeView.do?no=" + board.getNo();
    }

    @PostMapping("/noticeDelete.do")
     public String noticeDelete(RedirectAttributes attributes,
        @RequestParam int no){

        int result = noticeService.deleteNotice(no);
        String msg = "";
        if(result > 0){
            msg = "게시글을 삭제했습니다.";
        }else{
            msg = "게시글 삭제 오류";
        }

        attributes.addFlashAttribute("msg", msg);
        return "redirect:/board/noticeList.do";
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//
//            // 1. 사용자입력값 처리
//
//            String title = request.getParameter("title");
//            String content = request.getParameter("content");
//            int empNo = Integer.parseInt(request.getParameter("empNo"));
//
//            Board board = new Board();
//            //board.setCategory(category);
//            board.setTitle(title);
//            board.setContent(content);
//            board.setEmpNo(empNo);
//
//            System.out.println("[BoardEnrollServlet] board = " + board);
//
//            // 2. 업무로직
//            int result = bulletinService.insertNotice(board);
//            String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
//
//            // 3. 응답요청
//            request.getSession().setAttribute("msg", msg);
//            String location = request.getContextPath() + "/board/noticeList";
//            response.sendRedirect(location);
//        } catch (NumberFormatException | IOException e) {
//            throw e;
//        }
//    }
//


//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int no = Integer.valueOf(request.getParameter("no"));
//
//        // 쿠키 생성
//        Cookie[] cookies = request.getCookies();
//        boolean hasRead = false;
//        String noticeCookieVal = "";
//        if(cookies != null ) {
//            for(Cookie cookie : cookies) {
//                String name = cookie.getName();
//                String value = cookie.getValue();
//                if("noticeCookie".equals(name)) {
//                    noticeCookieVal = value;
//                    if(value.contains("[" + no + "]")) {
//                        hasRead = true;
//                        break;
//                    }
//                }
//            }
//        }
//        // 조회수 증가 및 쿠키 생성
//        if(!hasRead) {
//            int result = bulletinService.updateNoticeReadCount(no);
//
//            Cookie cookie = new Cookie("noticeCookie",noticeCookieVal + "[" + no + "]");
//            cookie.setPath(request.getContextPath() + "/board/noticeList");
//            cookie.setMaxAge(365 * 24 * 60 * 60);
//            response.addCookie(cookie);
//            //System.out.println("조회수 증가 & 쿠키 생성 ");
//        }
//        //게시판 데이터 가져오기
//        Board board = bulletinService.selectOneNotice(no);
//        System.out.println(board);
//        //System.out.println(board);
//        String regDate = DateFormatUtils.formatDate(board.getRegDate());
//        String content = LineFormatUtils.formatLine(board.getContent());
//
//
//        request.setAttribute("board", board);
//        request.setAttribute("regDate", regDate);
//        request.setAttribute("content", content);
//
//
//        request
//                .getRequestDispatcher("/WEB-INF/views/notice/noticeView.jsp")
//                .forward(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        int no = Integer.parseInt(request.getParameter("no"));
//
//        Board board = bulletinService.selectOneNotice(no);
//        System.out.println(board);
//
//        request.setAttribute("board", board);
//        request
//                .getRequestDispatcher("/WEB-INF/views/notice/noticeUpdate.jsp")
//                .forward(request, response);
//    }
//
//    /**
//     * update board set category = ?, title = ?, content = ? where no = ?
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//
//            // 사용자입력값
//            int no = Integer.parseInt(request.getParameter("no"));
//            String title = request.getParameter("title");
//            String content = request.getParameter("content");
//            int empNo = Integer.parseInt(request.getParameter("empNo"));
//
//            Board board = new Board();
//            board.setNo(no);
//            board.setTitle(title);
//            board.setContent(content);
//            board.setEmpNo(empNo);
//
//            int result = bulletinService.updateNotice(board);
//
//            String msg = result > 0 ? "게시물 수정 성공" : "게시물 수정 실패";
//
//            request.getSession().setAttribute("msg", msg);
//            String location = request.getContextPath() + "/board/noticeView?no=" + board.getNo();
//            response.sendRedirect(location);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int no = Integer.parseInt(request.getParameter("no"));
//        int result = bulletinService.deleteNotice(no);
//
//        String msg = result > 0 ? "게시물 삭제 성공" : "게시물 삭제 실패";
//
//        request.getSession().setAttribute("msg", msg);
//        String location = request.getContextPath() + "/board/noticeList";
//        response.sendRedirect(location);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String searchType = request.getParameter("searchType");
//        String searchKeyword = request.getParameter("searchKeyword");
//        Map<String, Object> param = new HashMap<>();
//        param.put("searchType", searchType);
//        param.put("searchKeyword", searchKeyword);
//        System.out.println("param@servlet = " + param);
//
//        List<Board> list = bulletinService.searchNotice(param);
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
//                .getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp")
//                .forward(request, response);
//    }

}
