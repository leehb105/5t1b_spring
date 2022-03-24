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
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>


 		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
	        <div class="container-fluid">
		    	<button class="btn btn-primary btn-icon-split" onclick="moveAnonymousList();" style="padding: 5px; margin-top: 20px;">목록</button>
			
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
			 	<p>익명게시판</p>
		 		<h5 style="font-weight: bold; color: black;">${board.title}</h5>
			 	<span style="color: black;">익명</span>
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
		
				<c:if test="${board.attachments ne null}">
					<c:forEach items="${board.attachments}" var="attach" varStatus="status">
						<p>첨부파일</p>
						<table>
							<tr>
								<td>
									<i class="fa fa-paperclip" src="${pageContext.request.contextPath} width=16px alt='첨부파일'" ></i>
									<a href="${pageContext.request.contextPath}/board/fileDownload?no=">${attach}</a>
								</td>
							</tr>
						</table>

						<hr class="sidebar-divider my-3">
					</c:forEach>	
				</c:if>
			</div>
 	
			 
			<div class="container-fluid" id="commentContent">
				<span>댓글 ${board.commentCount}</span>

	<c:if test="${boardCommentList ne null}">
			
				<table>

		<c:forEach items="${boardCommentList}" var="comment" varStatus="status">	
			<c:if test="${comment.commentLevel == 1}">			
					<tr class="level1">
						<td style="padding: 10px;" width="1000px;">
							<sub class="comment-writer" style="font-weight: bold;">익명</sub>
							<sub class="comment-date">${comment.regDate}</sub>
							<br />
							<!-- 댓글내용 -->
							${comment.content}
						</td>
						<td>
							<button class="btn btn-primary btn-icon-split" id="btn-reply" value="${comment.no}" onclick="commentReply(this);" style="padding: 5px; margin-top: 20px;">답글</button>
						</td>
					</tr>
			</c:if>
			<c:if test="${comment.commentLevel != 1}">

					<tr class="level2">
						<td style="padding-left: 50px; padding-bottom: 15px;">
							<sub class="comment-writer" style="font-weight: bold;">익명</sub>
							<sub class="comment-date">${comment.regDate}</sub>
							<br />
							<!-- 대댓글내용 -->
							${comment.content}
						</td>
						<td>
							<button class="btn btn-primary btn-icon-split" id="btn-reply" value="${comment.no}" onclick="commentReply(this);" style="padding: 5px; margin-top: 20px;">답글</button>
						</td>
					</tr>


			</c:if>
		</c:forEach>		

				</table>
	</c:if>


				<hr class="sidebar-divider my-3">
				<!-- 댓글입력칸 -->
				<form 
					action="${pageContext.request.contextPath}/board/anonymousBoardCommentEnroll.do" 
					method="post"
					name="boardCommentFrm">
				    <input type="hidden" name="no" value="${board.no}" />
				    <input type="hidden" name="commentLevel" value="1" />
				    <input type="hidden" name="commentRef" value="0" />    
				    <div id="comment-input">
						<textarea name="content" cols="100" rows="3" style="resize: none;" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
					   	<br />
					    <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-top: 20px;">등록</button>
					</div>
				</form>
			 </div>
			 <form
				name=recommendFrm
				method="POST" 
				action="${pageContext.request.contextPath}/board/anonyLikeCount.do" >
				<input type="hidden" name="no" value="${board.no}" />
				<input type="hidden" name="board" value="anonyBoard" />
			</form>	
			<form 
				action="${pageContext.request.contextPath}/board/anonymousBoardDelete.do" 
				name="boardDeleteFrm"
				method="POST">
				<input type="hidden" name="no" value="${board.no}"/>
			</form>

<script>
//삭제하기 버튼
function deleteBoard() {
	if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
		$(document.boardDeleteFrm).submit();		
	}
}
//수정하기 버튼
function updateBoard() {
	location.href = "${pageContext.request.contextPath}/board/anonymousBoardUpdate?no=${board.no}";
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

//대댓글 기능
function commentReply(e) {
	//대댓글 상위댓글 저장
	const commentRef = e.value;
	const tr = `<tr>
		<td colspan="2" style="text-align:left">
			<form 
				action="${pageContext.request.contextPath}/board/anonymousBoardCommentEnroll.do" 
				method="post">
			    <input type="hidden" name="no" value="${board.no}" />
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
function moveAnonymousList() {
	location.href = "${pageContext.request.contextPath}/board/anonymousBoardList.do";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>