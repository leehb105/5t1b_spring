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
	<!-- Main Content -->
	<div id="content">
		<!-- Begin Page Content -->
		<div class="container-fluid">
			<div class="row">
				<!-- 자유 & 익명 게시판 인기게시글 부분 -->
				<div class="col-lg-12 mb-6 mt-4">
					<!-- 인기게시글 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/boardList.do">
								인기게시글 <i class="far fa-thumbs-up"></i>
								</a>

							</h6>
						</div>
						<c:forEach items="${topBoardList}" var="topBoard" varStatus="status">
							<!-- 자유 게시판 인기게시글 내용 띄울 부분 -->
							<div class="m-2" style="margin-bottom: 0; display: flex;">
								<div style="width: 85%; margin-left: 10px;">
									<a
										href="${pageContext.request.contextPath}/board/boardView.do?no=${topBoard.no}"
										style="color: black;">
										[${topBoard.category}]
										${topBoard.title} [${topBoard.commentCount}] 
									</a>
								</div>
								<div style="text-align: right;">
									<fmt:formatDate value="${topBoard.regDate}" pattern="yy-MM-dd [HH:mm]"/>
								</div>
							</div>
							<hr style="margin: 0;">
						</c:forEach>	

						<c:forEach items="${topAnonyBoardList}" var="topAnonyBoard" varStatus="status">
							<!-- 익명 게시판 인기게시글 내용 띄울 부분 -->
							<div class="m-2" style="margin-bottom: 0; display: flex;">
								<div style="width: 85%; margin-left: 10px;">
									<a
										href="${pageContext.request.contextPath}/board/anonymousBoardView?no=${topAnonyBoard.no}"
										style="color: black;">
										[익명] ${topAnonyBoard.title} [${topAnonyBoard.commentCount}] 
									</a>
								</div>
								<div style="text-align: right;">
									<fmt:formatDate value="${topAnonyBoard.regDate}" pattern="yy-MM-dd [HH:mm]"/>
								</div>
							</div>
							<hr style="margin: 0;">
						</c:forEach>
					</div>
				</div>
				<!-- 공지사항 & 오늘의 메뉴 부분  -->
				<div class="col-lg-12 mb-6">
					
					<!-- 공지사항 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/noticeList.do">
								공지사항 <i class="fas fa-thumbtack"></i>
								</a>
							</h6>
						</div>
						<c:forEach items="${noticeList}" var="notice" varStatus="status">
							<!-- 공지사항 내용 띄울 부분 -->
							<div class="m-2" style="margin-bottom: 0; display: flex;">
								<div style="width: 85%; margin-left: 10px;">
									<a
										href="${pageContext.request.contextPath}/board/noticeView.do?no=${notice.no}" 
										style="color: black;">
										[공지] ${notice.title}
									</a>
								</div>
								<div style="text-align: right;">
									<fmt:formatDate value="${notice.regDate}" pattern="yy-MM-dd [HH:mm]"/>
								</div>
							</div>
							<hr style="margin: 0;">
						</c:forEach>
					</div>
				</div>
				<div class="col-lg-12 mb-6">
					<!-- 자유게시판 & 익명 게시판 부분  -->
					<!-- 자유게시판 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/boardList.do">
								자유게시판 <i class="fas fa-users"></i>
								</a>
							</h6>
						</div>
						<c:forEach items="${boardList}" var="board" varStatus="status">
							<!-- 자유게시판 내용 띄울 부분 -->
							<div class="m-2" style="margin-bottom: 0; display: flex;">
								<div style="width: 85%; margin-left: 10px;">
									<a
										href="${pageContext.request.contextPath}/board/boardView.do?no=${board.no}"
										style="color: black;">
										[${board.category}] ${board.title} [${board.commentCount}] 
									</a>
								</div>
								<div style="text-align: right;">
									<fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd [HH:mm]"/>
								</div>
							</div>
							<hr style="margin: 0;">
						</c:forEach>
					</div>
					<!-- 익명게시판부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/anonymousBoardList.do">
								익명게시판 <i class="fas fa-user-secret"></i>
							</a>
							</h6>
						</div>
						<c:forEach items="${anonymousBoardList}" var="anonyBoard" varStatus="status">
							<!-- 익명 게시판 내용 띄울 부분 -->
							<div class="m-2" style="margin-bottom: 0; display: flex;">
								<div style="width: 85%; margin-left: 10px;">
									<a
										href="${pageContext.request.contextPath}/board/anonymousBoardView.do?no=${anonyBoard.no}"
										style="color: black;">
										[익명] ${anonyBoard.title} [${anonyBoard.commentCount}] 
									</a>
								</div>
								<div style="text-align: right;">
									<fmt:formatDate value="${anonyBoard.regDate}" pattern="yy-MM-dd [HH:mm]"/>
								</div>
							</div>
							<hr style="margin: 0;">
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
