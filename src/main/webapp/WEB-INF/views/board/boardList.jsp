<%@page import="java.util.List"%>
<%@page import="com.otlb.semi.bulletin.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<Board> list = (List<Board>) request.getAttribute("list"); 
	List<String> regDate = (List<String>) request.getAttribute("regDate"); 
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	Emp currentLoginEmp = (Emp) session.getAttribute("loginEmp");
%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>


<!-- Custom styles for this template -->
<link
	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<style>
div#search-container {margin:0 0 10px 0; padding:3px; width:100%; text-align:center;}
div#search-title {display: <%= searchType == null || "title".equals(searchType) ? "inline-block" : "none" %>;}
div#search-empName{display: <%= "empName".equals(searchType) ? "inline-block" : "none" %>;}
div#search-category{display: <%= "category".equals(searchType) ? "inline-block" : "none" %>;}
</style>
<style>
.hidden {
  display: none;
}
</style>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 style=" color: black; font-weight:bolder; text-shadow: 1px 1px skyblue; margin-left:20px; margin-top:30px">자유 게시판</h1>
		<a class="btn btn-primary btn-icon-split"
			href="<%=request.getContextPath()%>/board/boardForm" style="margin-left:90%; width: 100px; height:35px; border-radius:10px; padding-top:5px;;">
			<span>
			<i class="fas fa-envelope fa-fw"></i>글쓰기</span>
		</a>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>추천수</th>
							<th>작성일</th>
							<th>조회</th>
						</tr>
					</thead>
					<tbody class="list">
					<%
					for(int i = 0; i < list.size(); i++){
						Board board = list.get(i);
					// for (Board board : list) {
					%>
						<tr id="table-row">
							<td id="no"><span></span><%= board.getNo()%></td>
							<td id="main" colspan="1">
								<a id="link" href="<%= request.getContextPath()%>/board/boardView?no=<%=board.getNo()%>">
									<span id="category">[<%=board.getCategory()%>]</span><span id="title"><%=board.getTitle()%></span>
								</a> 
								<% if (board.getAttachCount() > 0) { %> 
								 <span id="comment"><i class="fa fa-paperclip"></i></span> 
								<% } %>
								<%= board.getCommentCount() > 0 ? "(" + board.getCommentCount() + ")" : "" %>
							 </td>
							<td id="writer" class="empPopover" data-toggle="popover" 
								data-snd-emp-no="<%= currentLoginEmp.getEmpNo() %>"
		                        data-snd-emp-nm="<%= currentLoginEmp.getEmpName() %>"
		                        data-rcv-emp-no="<%= board.getEmp().getEmpNo() %>"
		                        data-rcv-emp-nm="<%= board.getEmp().getEmpName() %>" ><%= board.getEmp().getEmpName() %></td>
							<td id="like"><%= board.getLikeCount()%></td>
							<td id="date"><%= regDate.get(i) %></td>
							<td id="read"><%= board.getReadCount()%></td>
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
		            <option value="empName" <%= "empName".equals(searchType) ? "selected" : "" %>>작성자</option>
		            <option value="category" <%= "category".equals(searchType) ? "selected" : "" %>>말머리</option>
		        </select>
		        <div id="search-title" class="search-type">
		            <form action="<%=request.getContextPath()%>/board/boardFinder">
		                <input type="hidden" name="searchType" value="title"/>
		                <input type="text" name="searchKeyword" value="<%= "title".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 제목을 입력하세요."/>
		                <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 2px">검색
		                <i class="fa fa-search" aria-hidden="true"></i></button>			
		            </form>	
		        </div>
		        <div id="search-empName" class="search-type">
		            <form action="<%=request.getContextPath()%>/board/boardFinder">
		                <input type="hidden" name="searchType" value="empName"/>
		                <input type="text" name="searchKeyword" value="<%= "empName".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 이름을 입력하세요."/>
		                <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 2px">검색
		                <i class="fa fa-search" aria-hidden="true"></i></button>			
		            </form>
		        </div>
		        <div id="search-category" class="search-type">
		            <form action="<%=request.getContextPath()%>/board/boardFinder">
		                <input type="hidden" name="searchType" value="category"/>
		                <input type="text" name="searchKeyword" value="<%= "category".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 말머리를 입력하세요."/>
		                <button type="submit" class="btn btn-primary btn-icon-split" style="padding: 2px">검색
		                <i class="fa fa-search" aria-hidden="true"></i></button>
		            </form>
		        </div>
				<div id="pageBar"><%= request.getAttribute("pagebar") %></div>
		    </div>
		</div>
	<!-- </div>
</div> -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>


<script>
const category = document.getElementById('category').textContent;
const main = document.getElementById('main');

const displayNone = function (id) {
	document.getElementById(id).style.display = 'none';
}
if(category == '[공지]'){
	displayNone('no');
	displayNone('like');
	displayNone('read');
	displayNone('date');
	displayNone('writer');
	document.getElementById('table-row').style.backgroundColor = '#b5d1ff';
	main.colSpan = "6";
	main.setAttribute('style', 'color: blue; font-weight:bold');
	document.getElementById('category').setAttribute('style', 'color: red; margin-left:75px');
}


</script>
<script src="<%= request.getContextPath() %>/js/empPopup.js"></script>

<script>
	const empPopovers = document.getElementsByClassName("empPopover");
	for (let empPopover of empPopovers) {
	      setPopoversBoard("<%= request.getContextPath() %>"
		            , empPopover.dataset.sndEmpNo
		            , empPopover.dataset.sndEmpNm
		            , empPopover.dataset.rcvEmpNo
		            , empPopover.dataset.rcvEmpNm
		            , empPopover);

	}

$(searchType).change((e) => {
	$(".search-type").hide();
	
	const v = $(e.target).val();
	$("#search-" + v).css("display", "inline-block");
});

</script>

</body>

</html>
<br /><br /><br /><br /><br />
<%@ include file="/WEB-INF/views/common/footer.jsp"%>