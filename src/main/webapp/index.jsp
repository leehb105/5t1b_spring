<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
							<a class="nav-link" href="${pageContext.request.contextPath}/board/noticeList.do">
								공지사항 <i class="fas fa-thumbtack"></i>
								</a>
							</h6>
						</div>
						<%
						List<Notice> noticeList = (List<Notice>) session.getAttribute("noticeList");
						%>
						<div class="card-body">
							<!-- 공지사항 내용 띄울 부분 -->
							<%
							for (Notice notice : noticeList) {
							%>
							<p>
								<a
									href="${pageContext.request.contextPath}/board/noticeView?no=<%=notice.getNo()%>" 
									style="color: black;">
										<%=notice.getTitle()%>

								</a>
							</p>
							<hr>
							<%
							}
							%>
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
						<%
						FoodMenu foodMenu = (FoodMenu) session.getAttribute("foodMenu");
						%>
						<div class="card-body" style="line-height:0.75em">
							<p style="list-style-type: none; text-align: center;">[밥]</p>
							<p style="text-align: center;"><%=foodMenu.getMain()%></p>
							<p style="list-style-type: none; text-align: center;">[국]</p>
							<p style="text-align: center;"><%=foodMenu.getSoup()%></p>
							<p style="list-style-type: none; text-align: center;">[반찬]</p>
							<p style="text-align: center;"><%=foodMenu.getSide1()%></p>
							<p style="text-align: center;"><%=foodMenu.getSide2()%></p>
							<p style="text-align: center;"><%=foodMenu.getSide3()%></p>
							<p style="list-style-type: none; text-align: center;">[디저트]</p>
							<p style="text-align: center;"><%=foodMenu.getDessert()%></p>
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
						<%
						List<Board> boardList = (List<Board>) session.getAttribute("boardList");
						%>
						<div class="card-body">
							<!-- 자유게시판 내용 띄울 부분 -->
							<%
							for (Board board : boardList) {
							%>
							<p>
								<a
									href="${pageContext.request.contextPath}/board/boardView?no=<%=board.getNo()%>"
									style="color: black;"> <%=board.getTitle()%></a>
							</p>
							<hr>
							<%
							}
							%>
						</div>
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
						<%
						List<AnonymousBoard> anonymousBoardList = (List<AnonymousBoard>) session.getAttribute("anonymousBoardList");
						%>
						<div class="card-body">
							<!-- 익명 게시판 내용 띄울 부분 -->
							<%
							for (AnonymousBoard anonymousBoard : anonymousBoardList) {
							%>
							<p>
								<a
									href="${pageContext.request.contextPath}/board/anonymousBoardView?no=<%=anonymousBoard.getNo()%>"
									style="color: black;"><%=anonymousBoard.getTitle()%>

								</a>
							</p>
							<hr>
							<%
							}
							%>
						</div>
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
						<%
						List<BoardEntity> likeContentBoardSelect = (List<BoardEntity>) session.getAttribute("likeContentBoardSelect");
						%>
						<div class="card-body">
							<!-- 자유 게시판 인기게시글 내용 띄울 부분 -->
							<%
							for (BoardEntity likeContent : likeContentBoardSelect) {
							%>
							<p>
								<a
									href="${pageContext.request.contextPath}/board/boardView?no=<%=likeContent.getNo()%>"
									style="color: black;">
									<%=likeContent.getTitle()%>
								</a>
							</p>
							<hr>
							<%
							}
							%>
						</div>

						<hr>

						<%
						List<BoardEntity> likeContentAnonymous_boardSelect = (List<BoardEntity>) session
								.getAttribute("likeContentAnonymous_boardSelect");
						%>
						<div class="card-body">
							<!-- 익명 게시판 인기게시글 내용 띄울 부분 -->
							<%
							for (BoardEntity likeContent : likeContentAnonymous_boardSelect) {
							%>
							<p>
								<a
									href="${pageContext.request.contextPath}/board/boardView?no=<%=likeContent.getNo()%>"
									style="color: black;">
									<%=likeContent.getTitle()%>
								</a>
							</p>
							<hr>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>

	<!--Endof Main Content -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>