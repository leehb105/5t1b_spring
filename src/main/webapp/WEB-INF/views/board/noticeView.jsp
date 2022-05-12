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
			<button class="btn btn-primary btn-icon-split" onclick="moveBoardList();" style="padding: 5px; margin-top: 20px;">목록</button>
			
			<c:if test="${board.empNo eq loginEmp.empNo}">
				<button class="btn btn-primary btn-icon-split" onclick="updateBoard();" style="padding: 5px; margin-top: 20px;">수정</button>
				<button class="btn btn-danger btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button>
			</c:if>			
			<!-- 관리자 삭제권한 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button class="btn btn-danger btn-icon-split" onclick="deleteBoard();" style="padding: 5px; margin-top: 20px;">삭제</button>
			</sec:authorize>
				
			<hr class="sidebar-divider my-3">
		</div>
		<div class="container-fluid" id="titleContent">
			<p>공지사항</p>
			<h5 style="font-weight: bold; color: black;">${board.title}</h5>
			<span style="color: black;">작성자 ${board.empNo}</span>
			<span style="margin-left: 10px;">조회 ${board.readCount}</span>
			<span style="margin-left: 10px;">
				<fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd [HH:mm]"/>
			</span>
		</div>
		<br />

		<div class="container-fluid" id="Content" style="margin-top: 20px; margin-bottom: 50px;">
			<span>${board.content}</span>
		</div>
		


		<form:form
			action="${pageContext.request.contextPath}/board/noticeDelete.do" 
			name="boardDeleteFrm"
			method="POST">
			<input type="hidden" name="no" value="${board.no}"/>
		</form:form>



<!-- <script src="<%= request.getContextPath() %>/js/empPopup.js"></script>
<script>
	setPopover("<%= request.getContextPath() %>", "게시글보기 링크", "프로필 보기 링크", "대화 링크", "쪽지 보내기 링크");
</script> -->
<script>
//삭제하기 버튼
function deleteBoard() {
	if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
		$(document.boardDeleteFrm).submit();		
	}
}
//수정하기 버튼
function updateBoard() {
	location.href = "${pageContext.request.contextPath}/board/noticeUpdate.do?no=${board.no}";
}
//게시판 리스트로 돌아가는 함수
function moveBoardList() {
	location.href = "${pageContext.request.contextPath}/board/noticeList.do";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>