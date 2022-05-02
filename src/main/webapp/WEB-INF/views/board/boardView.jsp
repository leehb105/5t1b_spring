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

<style>
	tr:hover{
		color: #4e73df;
	}
</style>

	<!-- Content Wrapper -->
	<div id="content-wrapper" class="d-flex flex-column">
		<div class="container-fluid">
			<button class="btn btn-primary btn-icon-split" onclick="moveBoardList();" style="padding: 5px; margin-top: 20px;">목록</button>
		
			<c:if test="${board.empNo eq loginEmp.empNo}">
				<button class="btn btn-primary btn-icon-split" onclick="updateBoard();" style="padding: 5px; margin-top: 20px;">수정</button>
				<button class="btn btn-danger btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button>
			</c:if>
			
			<!-- <c:if test="${board.empNo ne loginEmp.empNo}"> -->
				<!-- <button class="btn btn-primary btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button> -->
			<!-- </c:if> -->

			<hr class="sidebar-divider my-3">
		</div>
		<div class="container-fluid" id="titleContent">
			<p>자유게시판</p>
			<h5 style="font-weight: bold; color: black;">${board.title}</h5>
			<span style="color: black;">작성자 ${board.empNo}</span>
			<span style="margin-left: 30px;">추천수 ${board.likeCount}</span>
			<span style="margin-left: 10px;">조회 ${board.readCount}</span>
			<span style="margin-left: 10px;">
				<fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd [HH:mm]"/>
			</span>
		</div>
		<br />
		
		<div class="container-fluid" id="Content" style="margin-top: 20px; margin-bottom: 50px;">
			<span>${board.content}</span>
		</div>
		<div class="container-fluid">
			<form:form 
				action="${pageContext.request.contextPath}/board/boardLikeCount.do"
				method="post">
				<input type="hidden" name="no" value="${board.no}" />
				<button class="btn btn-primary btn-icon-split" id="recommend-btn" type="submit" style="padding: 5px; margin-top: 20px;">
					<i class="far fa-thumbs-up"> 추천하기</i>
				</button>
			</form:form>	
		</div>
		<div class="container-fluid" id="attachContent" >
			<hr class="sidebar-divider my-3">
			<c:if test="${board.attachments[0].fileName ne null}">
				<p>첨부파일</p>
				<table>
					<c:forEach items="${board.attachments}" var="attach" varStatus="status">
						<c:if test="${attach.fileName ne null}">
							<tr>
								<td>
									<i class="fa fa-paperclip" src="${pageContext.request.contextPath} width=16px alt='첨부파일'" ></i>
									<a href="${pageContext.request.contextPath}/board/boardFileDownload?no=${attach.no}">${attach.fileName}</a>
								</td>
							</tr>
							
						</c:if>
					</c:forEach>	
				</table>
				<hr class="sidebar-divider my-3">
			</c:if>
		</div>
		
			
		<div class="container-fluid" id="commentContent">
			<span>댓글 ${board.commentCount}</span>

			<c:if test="${boardCommentList ne null}">
					
				<table id="commentTbl">

					<c:forEach items="${boardCommentList}" var="comment" varStatus="status">	
						<!-- 부모 댓글 -->
						<c:if test="${comment.commentLevel == 1}">			
							<c:if test="${comment.deleteYn == 'N'}">
								<tr class="level1">
									<td style="padding: 10px;" width="1000px;">
										<sub class="comment-writer" style="font-weight: bold;">${comment.empNo}</sub>
										<sub class="comment-date">
											<fmt:formatDate value="${comment.regDate}" pattern="yy-MM-dd [HH:mm]"/>
										</sub>
										<br />
										<!-- 댓글내용 -->
										${comment.content}
									</td>
									<td>
										<button class="btn btn-primary btn-icon-split" id="btn-reply" value="${comment.no}" onclick="commentReply(this);" style="padding: 5px; margin-top: 20px;">답글</button>
									</td>
									<!-- 삭제버튼 -->
									<c:if test="${comment.empNo eq loginEmp.empNo}">
										<td>
											<button class="btn btn-danger btn-icon-split" id="btn-reply" value="${comment.no}" onclick="commentDelete(this)" style="padding: 5px; margin-top: 20px;">삭제</button>
										</td>
									</c:if>
									
								</tr>
							</c:if>
							<!-- 삭제댓글 -->
							<c:if test="${comment.deleteYn == 'Y'}">
								<tr class="level1">
									<td style="padding: 10px;" width="1000px;">
										<sub class="comment-writer" style="font-weight: bold;">${comment.empNo}</sub>
										<sub class="comment-date">
											<fmt:formatDate value="${comment.regDate}" pattern="yy-MM-dd [HH:mm]"/>
										</sub>
										<br />
										<!-- 댓글내용 -->
										<del>삭제된 댓글입니다.</del>
									</td>
								</tr>
							</c:if>
						</c:if>
						<!-- 대댓글(자식 댓글) -->
						<c:if test="${comment.commentLevel != 1}">
							<c:if test="${comment.deleteYn == 'N'}">
								<tr class="level2">
									<td style="padding-left: 50px; padding-bottom: 15px;">
										<sub class="comment-writer" style="font-weight: bold;">${comment.empNo}</sub>
										<sub class="comment-date">
											<fmt:formatDate value="${comment.regDate}" pattern="yy-MM-dd [HH:mm]"/>
										</sub>
										<br />
										<!-- 대댓글내용 -->
										${comment.content}
									</td>
									<c:if test="${comment.empNo eq loginEmp.empNo}">
										<td>
											<button class="btn btn-danger btn-icon-split" id="btn-reply" value="${comment.no}" onclick="commentDelete(this)" style="padding: 5px; margin-top: 20px;">삭제</button>
										</td>
									</c:if>
								</tr>
							</c:if>
							<!-- 삭제댓글 -->
							<c:if test="${comment.deleteYn == 'Y'}">
								<tr class="level2">
									<td style="padding-left: 50px; padding-bottom: 15px;">
										<sub class="comment-writer" style="font-weight: bold;">${comment.empNo}</sub>
										<sub class="comment-date">
											<fmt:formatDate value="${comment.regDate}" pattern="yy-MM-dd [HH:mm]"/>
										</sub>
										<br />
										<!-- 대댓글내용 -->
										<del>삭제된 댓글입니다.</del>
									</td>
								</tr>
							</c:if>
						</c:if>
					</c:forEach>		

				</table>
			</c:if>


			<hr class="sidebar-divider my-3">
			<!-- 댓글입력칸 -->
			<form:form
				action="${pageContext.request.contextPath}/board/boardCommentEnroll.do" 
				method="post"
				name="boardCommentFrm">
				<input type="hidden" name="no" value="${board.no}" />
				<input type="hidden" name="commentLevel" value="1" />
				<input type="hidden" name="reCommentRef" value="0" />    
				<div id="comment-input">
					<textarea name="content" cols="100" rows="3" style="resize: none;" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
					<br />
					<button type="submit" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-bottom: 10px;">등록</button>
				</div>
			</form:form>
		</div>
		<!-- <form
			name=recommendFrm
			method="POST" 
			action="${pageContext.request.contextPath}/board/anonyLikeCount.do" >
			<input type="hidden" name="no" value="${board.no}" />
			<input type="hidden" name="board" value="anonyBoard" />
		</form>	 -->
		<form:form
			action="${pageContext.request.contextPath}/board/boardDelete.do" 
			name="boardDeleteFrm"
			method="POST">
			<input type="hidden" name="no" value="${board.no}"/>
		</form:form>

		<form:form
			action="${pageContext.request.contextPath}/board/boardCommentDelete.do" 
			name="commentDeleteFrm"
			method="POST">
			<input type="hidden" name="commentNo" value=""/>
			<input type="hidden" name="no" value="${board.no}"/>
		</form:form>


<script>
//삭제하기 버튼
function deleteBoard() {
	if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
		$(document.boardDeleteFrm).submit();		
	}
}
//수정하기 버튼
function updateBoard() {
	location.href = "${pageContext.request.contextPath}/board/boardUpdate.do?no=${board.no}";
}
//추천하기 버튼
// function recommend(){
// 	$("form[name=recommendFrm]").submit();	
// }

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
let commentRefVal; 

//대댓글 기능
function commentReply(e) {
	//대댓글 상위댓글 저장
	commentRefVal = e.value;
	console.log(commentRefVal);
	const tr = `<tr>
		<td colspan="2" style="text-align:left">
			<form:form
				action="${pageContext.request.contextPath}/board/boardCommentEnroll.do" 
				method="post"
				name="reCommentFrm">
			    <input type="hidden" name="no" value="${board.no}" />
			    <input type="hidden" name="commentLevel" value="2" />
			    <input type="hidden" name="reCommentRef" id="reCommentRef" value="" />    
				<textarea name="content" cols="100" rows="3" style="resize: none;" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
			    <br />
				<button type="submit" id="reCommentBtn" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-bottom: 10px;">등록</button>
			</form:form>
		</td>`;

	const baseTr = e.parentNode.parentNode;
	const $baseTr = $(e.target).parent().parent();
	const $tr = $(tr);


	$tr.insertAfter(baseTr)	
	.find("form")
	.submit((e) => {
		const $content = $("[name=content]", e.target);
		$('#reCommentRef').val(commentRefVal);
		if(!/^(.|\n)+$/.test($content.val())){
			alert("댓글을 작성해주세요.");
			e.preventDefault();
		}
	});

	//$(e.target).off("click");

}

function commentDelete(e){
	if(confirm("댓글을 삭제하시겠습니까?")){
		$('input[name=commentNo]').val(e.value);
		$(document.commentDeleteFrm).submit();		
	}
}

// $(reCommentBtn).on('click', function(){

// 	$('#reCommentRef').val(commentRefVal);
// 	console.log($('#reCommentRef').val());

// 	//댓글등록전 검사
// 	$(document.boardCommentFrm).submit((e) => {

// 	const $content = $("[name=content]", e.target);
// 	if(!/^(.|\n)+$/.test($content.val())){
// 		alert("댓글을 작성해주세요.");
// 		e.preventDefault();
// 	}
// 	});


// });
	



$(document.reCommentFrm)
//게시판 리스트로 돌아가는 함수
function moveBoardList() {
	location.href = "${pageContext.request.contextPath}/board/boardList.do";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>