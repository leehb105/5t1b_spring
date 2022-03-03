<%@page import="java.util.List"%>
<%@page import="com.otlb.semi.bulletin.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<Board> list = (List<Board>) request.getAttribute("list"); 
	List<String> regDate = (List<String>) request.getAttribute("regDate"); 
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<style>
div#search-container {margin:0 0 10px 0; padding:3px; width:100%; text-align:center;}
div#search-title {display: <%= searchType == null || "title".equals(searchType) ? "inline-block" : "none" %>;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->

	<div>
		<h1 style=" color: black; font-weight:bolder; text-shadow: 1px 1px skyblue; margin-left:20px; margin-top:30px">공지사항</h1>
		<a class="btn btn-primary btn-icon-split"
			href="<%=request.getContextPath()%>/board/noticeForm" style="margin-left:90%; width: 100px; height:35px; border-radius:10px; padding-top:5px;;">
		<span>
		<i class="fas fa-envelope fa-fw"></i>글쓰기</span>
	</a>
	</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(int i = 0; i < list.size(); i++){
							Board board = list.get(i);
						
						//for (Board board : list) {
							
					%>
						<tr>
							<td><%= board.getNo()%></td>
							<td><a href="<%= request.getContextPath()%>/board/noticeView?no=<%= board.getNo()%>">
								<%= board.getTitle()%></a>
							</td>
							<td><%= board.getEmp().getEmpName() %></td>
							<td><%= regDate.get(i) %></td>
							<td><%= board.getReadCount() %></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
				<div id="search-container">
		        <select id="searchType">
		            <option value="title" <%= "title".equals(searchType) ? "selected" : "" %>>제목</option>		
		        </select>
		        <div id="search-title" class="search-type">
		            <form action="<%=request.getContextPath()%>/board/noticeFinder">
		                <input type="hidden" name="searchType" value="title"/>
		                <input type="text" name="searchKeyword" value="<%= "title".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 제목을 입력하세요."/>
		                <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 2px">검색
		                <i class="fa fa-search" aria-hidden="true"></i></button>			
		            </form>	
		        </div>
				<div id="pageBar"><%= request.getAttribute("pagebar") %></div>
			</div>
		</div>
		<div>
			<div>


<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>

</body>

</html>
<br /><br /><br /><br /><br />
<%@ include file="/WEB-INF/views/common/footer.jsp"%>