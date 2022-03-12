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
			<div class="row" style="flex-wrap: nowrap; margin-right: 22.8rem;">
				<!-- 공지사항 & 오늘의 메뉴 부분  -->
				<div class="col-lg-6 mb-4">
					<!-- 공지사항 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/noticeList">
								공지사항 <i class="fas fa-thumbtack"></i>
								</a>
							</h6>
						</div>
						<div class="card-body">
							<!-- 공지사항 내용 띄울 부분 -->
							<p>
								<a
									href="${pageContext.request.contextPath}/board/noticeView?no=" 
									style="color: black;">


								</a>
							</p>
							<hr>
						
						</div>
					</div>
					<!-- 오늘의 메뉴 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/foodMenu/calendar">
								오늘의 메뉴 <i class="fas fa-utensils"></i>
								</a>
							</h6>
						</div>

						<div class="card-body" style="line-height:0.75em">
							<p style="list-style-type: none; text-align: center;">[밥]</p>
							<p style="text-align: center;"></p>
							<p style="list-style-type: none; text-align: center;">[국]</p>
							<p style="text-align: center;"></p>
							<p style="list-style-type: none; text-align: center;">[반찬]</p>
							<p style="text-align: center;"></p>
							<p style="text-align: center;"></p>
							<p style="text-align: center;"></p>
							<p style="list-style-type: none; text-align: center;">[디저트]</p>
							<p style="text-align: center;"></p>
						</div>
					</div>
				</div>

				<!-- 자유게시판 & 익명 게시판 부분  -->
				<div class="col-lg-6 mb-4">
					<!-- 자유게시판 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/boardList">
								자유게시판 <i class="fas fa-users"></i>
								</a>
							</h6>
						</div>
						<c:forEach items="${boardList}" var="board" varStatus="status">
							<div class="card-body">
								<!-- 자유게시판 내용 띄울 부분 -->
								<p>
									<a
										href="${pageContext.request.contextPath}/board/boardView.do?no=${board.no}"
										style="color: black;">[${board.category}]${board.title} [${board.commentCount}] ${board.regDate}</a>
								</p>
								<hr>
							</div>
						</c:forEach>
					</div>
					<!-- 익명게시판부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/anonymousBoardList">
								익명게시판 <i class="fas fa-user-secret"></i>
							</a>
							</h6>
						</div>
						<c:forEach items="${anonymousBoardList}" var="anonyBoard" varStatus="status">
							<div class="card-body">
								<!-- 익명 게시판 내용 띄울 부분 -->
								<p>
									<a
										href="${pageContext.request.contextPath}/board/anonymousBoardView.do?no=${anonyBoard.no}"
										style="color: black;">
										[익명]${anonyBoard.title} [${anonyBoard.commentCount}] ${anonyBoard.regDate}
									</a>
								</p>
								<hr>

							</div>
						</c:forEach>
					</div>
				</div>

				<!-- 자유 & 익명 게시판 인기게시글 부분 -->
				<div class="col-lg-6 mb-4">
					<!-- 인기게시글 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							<a class="nav-link" href="${pageContext.request.contextPath}/board/boardList">
								인기게시글 <i class="far fa-thumbs-up"></i>
								</a>

							</h6>
						</div>
						<div class="card-body">
							<!-- 자유 게시판 인기게시글 내용 띄울 부분 -->
							<p>
								<a
									href="${pageContext.request.contextPath}/board/boardView?no="
									style="color: black;">

								</a>
							</p>
							<hr>
						</div>

						<hr>

				
						<div class="card-body">
							<!-- 익명 게시판 인기게시글 내용 띄울 부분 -->
							
							<p>
								<a
									href="${pageContext.request.contextPath}/board/boardView?no="
									style="color: black;">

								</a>
							</p>
							<hr>

						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
