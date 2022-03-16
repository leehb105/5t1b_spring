<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 인증객체의 principal속성을 pageContext 속성으로 저장 -->
<sec:authentication property="principal" var="loginEmp"/>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<body id="page-top">

<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

	<!-- Board board  = (Board) request.getAttribute("board");
	String regDate = (String) request.getAttribute("regDate");
	String content = (String) request.getAttribute("content");	
	
	String writerProfileImagePath = "/img/profile/profile.png";
	Boolean writerProfileImageExists = (boolean) ((request.getAttribute("writerProfileImageExists") == null) ? false : request.getAttribute("writerProfileImageExists"));
	if(writerProfileImageExists) writerProfileImagePath = "/img/profile/" + board.getEmpNo() + ".png";	 -->
	


 		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
	        <div class="container-fluid">
		    	<button class="btn btn-primary btn-icon-split" onclick="moveBoardList();" style="padding: 5px; margin-top: 20px;">목록</button>

				<c:if test="${board.empNo eq loginEmp.empNo}">
					<button class="btn btn-primary btn-icon-split" onclick="updateBoard();" style="padding: 5px; margin-top: 20px;">수정</button>
					<button class="btn btn-primary btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button>
				</c:if>

				<sec:authorize access="isAuthenticated()">
					<button class="btn btn-primary btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button>
				</sec:authorize>

				<hr class="sidebar-divider my-3">
			</div>
			 <div class="container-fluid" id="titleContent">
			 	<p>자유게시판</p>
		 		<h5 style="font-weight: bold; color: black;">[${board.category}] ${board.title}</h5>
		 		
		 		<img class="img-profile rounded-circle" src="<%= request.getContextPath() + writerProfileImagePath %>" height="40px" />
			 	<span 
			 		style="color: black;"
			 		class="empPopover" data-toggle="popover" data-emp-no="${board.empNo}" data-emp-name="${board.emp.empName}">${board.emp.empName}(${board.emp.deptName})</span>
			 	<span style="margin-left: 30px;">추천수${board.likeCount}</span>
			 	<span style="margin-left: 10px;">조회${board.readCount}</span>
			 	<span style="margin-left: 10px;">${board.regDate}</span>
			 </div>
			 <br />
			 	
			 
			 <div class="container-fluid" id="Content" style="margin-top: 20px; margin-bottom: 50px;">
			 	<span>${board.content}</span>
			 </div>
			 <div class="container-fluid">
			 	<button class="btn btn-primary btn-icon-split" id="recommend-btn" onclick="recommend();" style="padding: 5px; margin-top: 20px;"><i class="far fa-thumbs-up"> 추천하기</i></button>
			 </div>
			 <div class="container-fluid" id="attachContent" >
			 <hr class="sidebar-divider my-3">	
			 <p>첨부파일</p>
				<table>
					<tr>
						<td>
						 	<i class="fa fa-paperclip" src="${pageContext.request.contextPath} width=16px alt="첨부파일" ></i>
						 	<a href="${pageContext.request.contextPath}/board/fileDownload.do?no=">${attach.getOriginalFilename}</a>
						</td>
					</tr> 
				</table>	

			<hr class="sidebar-divider my-3">
			</div>
			 	
			  <div class="container-fluid" id="commentContent">
			 	<span>댓글 ${board.commentCount}</span>
			 	


	
	<!-- List<Boolean> commenterImageList = (List<Boolean>) request.getAttribute("commenterImageList"); -->
	
	if(commentList != null && !commentList.isEmpty()){ 
		<c:if test="boardCommentList != null"> 
		<c:if test="${boardCommentList != null}">
				<table>
				<c:forEach items="${boardCommentList}" var="bc" varStatus="status">
					

		
			
			<!-- String commenterProfileImagePath = "/img/profile/profile.png";
			Boolean commenterProfileImageExists = commenterImageList.get(i);
			
			Boolean commenterProfileImageExists = (boolean) ((request.getAttribute("commenterProfileImageExists") == null) ? false : request.getAttribute("commenterProfileImageExists"));
			if(commenterProfileImageExists) commenterProfileImagePath = "/img/profile/" + bc.getEmpNo() + ".png"; -->
			<!-- boolean removable = (
					  loginEmp.getEmpNo() == bc.getEmpNo()
					  || EmpService.ADMIN_ROLE.equals(loginEmp.getEmpRole())); -->

			
			
			<c:if test="${bc.commentLevel == 1}">
				<c:if test="${bc.deleteYn eq 'N'}">
					<c:if test="${board.deleteYn eq 'N'}">
					<tr class="level1">
						<td style="padding: 15px;" width="1000px;">
							<img class="img-profile rounded-circle" src="${pageContext.request.contextPath}" height="30px" />
							<sub class="comment-writer empPopover" data-toggle="popover" style="font-weight: bold;" data-emp-no="${bc.empNo}" data-emp-name="${bc.emp.empName}">${bc.emp.empName}(${bc.emp.deptName})</sub>
							<sub class="comment-date">${board.commentDate}</sub>
							<br />
							<div class="container-fluid" style="margin-top: 10px;">
								${board.commentContent}
							
							</div>
							<!-- 댓글내용 -->
							
						</td>	
						<td>
							<button class="btn btn-primary btn-icon-split" id="btn-reply" value="${bc.no}" onclick="commentReply(this);" style="padding: 5px; margin-top: 20px;">답글</button>
						
					<c:if test="${loginEmp eq bc.empNo}">
							<button class="btn btn-primary btn-icon-split" name="btn-delete" value="${bc.no}" style="padding: 5px; margin-top: 20px;">삭제</button>
					</c:if>
						</td>
					</tr>
					</c:if>
					<c:if test="loginEmp ne bc.empNo">

					<tr class="level1" style="color: #4e73df;">
						<td style="padding: 15px;">
							<!-- 댓글내용 -->
							삭제된 댓글입니다.
						</td>
	
					</tr>
				</c:if>
			</c:if>
		</c:if>
	</c:if>

<c:if test="${bc.deleteYn eq 'N'}">
		<c:if test="bc != null"> 
			<c:if test="bc.deleteYn eq 'N">
					<tr class="level2">
						<td style="padding-left: 50px; padding-bottom: 15px;">
							<img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/commenterProfileImagePath" height="30px" />
							<sub class="comment-writer empPopover" data-toggle="popover" style="font-weight: bold;" data-emp-no="${bc.empNo}">${bc.emp.empName}(${bc.emp.deptName})</sub>
							<sub class="comment-date">${bc.regDate}</sub>
							<br />
							<!-- 대댓글내용 -->
							${bc.content}
						</td>
						<td>
							<button class="btn btn-primary btn-icon-split" id="btn-reply" value="${bc.empNo}" onclick="commentReply(this);" style="padding: 5px; margin-top: 20px;">답글</button>

						</c:if>								
							<button class="btn btn-primary btn-icon-split" name="btn-delete" value="${bc.empNo}" style="padding: 5px; margin-top: 20px;">삭제</button>
					
						</td>	
					</tr>
					</c:if>
						
					<tr class="level2" style="color: #4e73df;">
						<td style="padding-left: 50px; padding-bottom: 15px;">
							<!-- 대댓글내용 -->
							삭제된 댓글입니다.
						</td>
					</tr>

			
				</c:forEach>
				</table>
	</c:if>
</c:if>

				<hr class="sidebar-divider my-3">
				<!-- 댓글입력칸 -->
				<form 
					action="${pageContext.request.contextPath}/board/boardCommentEnroll.do" 
					method="post"
					name="boardCommentFrm">
				    <input type="hidden" name="no" value="${board.no}" />
				    <input type="hidden" name="commentLevel" value="1" />
				    <input type="hidden" name="commentRef" value="0" />    
				    <div id="comment-input">
						<textarea name="content" cols="120" rows="3" style="resize: none;" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
					   	
					   	<br />
					    <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-top: 20px;">등록</button>
					</div>
				</form>
			 </div>
			 

			 <form
				name=recommendFrm
				method="POST" 
				action="${pageContext.request.contextPath}/board/boardLikeCount.do" >
				<input type="hidden" name="no" value="${board.no}" />
			</form>	
			<form 
				action="${pageContext.request.contextPath}/board/boardCommentDelete.do" 
				name="boardCommentDelFrm"
				method="POST">
				<input type="hidden" name="no" />
				<input type="hidden" name="boardNo" value="${board.no}"/>
			</form>
			<form 
				action="${pageContext.request.contextPath}/board/boardDelete.do?no=${board.no}" 
				name="boardDeleteFrm"
				method="POST">
				<input type="hidden" name="boardNo" value="${board.no}"/>
			</form>

<!-- <script src="${pageContext.request.contextPath}/js/empPopup.js"></script> -->
<script>
	const empPopovers = document.getElementsByClassName("empPopover");
	for (let empPopover of empPopovers) {
		console.log(empPopover.dataset.empName);
		setPopover("${pageContext.request.contextPath}", empPopover.dataset.empNo, empPopover, empPopover.dataset.empName, "${loginEmp.empNo}", "${loginEmp.empName}");
 }
</script>
<script>
//삭제하기 버튼
function deleteBoard() {
	if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
		$(document.boardDeleteFrm).submit();		
	}
}
//수정하기 버튼
function updateBoard() {
	location.href = "${pageContext.request.contextPath}/board/boardUpdate.do?no=<%= board.getNo() %>";
}
//추천하기 버튼
function recommend(){
	$("form[name=recommendFrm]").submit();	
}

/* 댓글 쓰기 100자 제한 코드 */
$('textarea[name=content]').on('keyup', function() {
	//console.log($(this).val().length);
	
	$('#count').html($(this).val().length);
	
	if($(this).val().length > 100) {
		alert("100자까지만 입력할 수 있습니다.");
           $(this).val($(this).val().substring(0, 500));
           $('#count').html("100");
       }
});


//댓글등록전 검사
$(document.boardCommentFrm).submit((e) => {

	const $content = $("[name=content]", e.target);
	if(!/^(.|\n)+$/.test($content.val())){
		alert("댓글을 작성해주세요.");
		e.preventDefault();
	}
});

//댓글 삭제 기능
$("button[name=btn-delete]").click(function(){
	console.log(12345);

	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.boardCommentDelFrm);
		var no = $(this).val();
		console.log(no);
		$frm.find("[name=no]").val(no);
		$frm.submit();
	}
});	
//대댓글 기능
function commentReply(e) {
	//대댓글 상위댓글 저장
	const commentRef = e.value;
	const tr = `<tr>
		<td colspan="2" style="text-align:left">
			<form 
				action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
				method="post">
			    <input type="hidden" name="no" value="<%= board.getNo() %>" />
			    <input type="hidden" name="commentLevel" value="2" />
			    <input type="hidden" name="commentRef" value="\${commentRef}" />    
				<textarea name="content" cols="60" rows="3" style="resize: none;" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
			    <br />
				<button type="submit" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-top: 20px;">등록</button>
				</form>
		</td>`;

	const baseTr = e.parentNode.parentNode;
	const $baseTr = $(e.target).parent().parent();
	const $tr = $(tr);

	$tr.insertAfter(baseTr)	
	.find("form")
	.submit((e) => {
		const $content = $("[name=content]", e.target);
		if(!/^(.|\n)+$/.test($content.val())){
			alert("댓글을 작성해주세요.");
			e.preventDefault();
		}
	});

	//$(e.target).off("click");
		
}

//게시판 리스트로 돌아가는 함수
function moveBoardList() {
	location.href = "${pageContext.request.contextPath}/board/boardList.do";

}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>