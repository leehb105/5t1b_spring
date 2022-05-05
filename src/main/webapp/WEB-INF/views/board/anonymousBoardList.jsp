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

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

<style>
div#search-container {margin:0 0 10px 0; padding:3px; width:100%; text-align:center;}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 style=" color: black; font-weight:bolder; text-shadow: 1px 1px skyblue; margin-left:20px; margin-top:30px">익명 게시판</h1>
	<a class="btn btn-primary btn-icon-split"
		href="${pageContext.request.contextPath}/board/anonymousBoardEnroll.do" style="margin-left:90%; width: 100px; height:35px; border-radius:10px; padding-top:5px;;">
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
					<tbody>
					<c:forEach items="${list}" var="board" varStatus="status">
						<tr>
							<td id="no">
								${page.total - ((page.cri.pageNum - 1) * page.cri.amount) - status.index}
							</td>

							<td id="main" colspan="1">
								<a id="link" href="${pageContext.request.contextPath}/board/anonymousBoardView.do?no=${board.no}">
									<span id="category">[${board.category}]</span><span id="title">${board.title}</span>
								</a> 
								<c:if test="${board.attachCount > 0}">
									<span id="comment"><i class="fa fa-paperclip"></i></span> 
									(${board.commentCount})
								</c:if>
							 </td>
							 
							<td>익명</td>
							<td id="like">${board.likeCount}</td>
							<td id="date">
								<fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd [HH:mm]"/>
							</td>
							<td id="read">${board.readCount}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="search-container">
		        <!-- <select id="searchType">
		            <option value="title">제목</option>		
					<option value="empName" >작성자</option>	
		        </select> -->
		        <div id="search-title" class="search-type">
		            <form action="${pageContext.request.contextPath}/board/anonymousBoardList.do">
		                <!-- <input type="hidden" name="searchType" value="title"/> -->
		                <input type="text" name="searchKeyword" value="" size="25" placeholder="검색어를 입력하세요." autocomplete="off"/>
		                <button type="submit" id="searchBtn" class="btn btn-primary btn-icon-split" style="padding: 2px">검색
		                <i class="fa fa-search" aria-hidden="true"></i></button>			
		            </form>
		        </div>
		    	<!-- Pagination -->
                <nav class="roberto-pagination mb-100">
                    <ul class="pagination">
                        <c:if test="${page.prev}"> 
                            <li class="page-item"><a class="page-link" href="${page.startPage - 1}"> 이전 <i class="fa fa-angle-left"></i></a></li>
                        </c:if>
                        <c:forEach var="num" begin="${page.startPage }" end="${page.endPage }">
                            <li class="page-item ${page.cri.pageNum == num ? 'active' : ''}"><a class="page-link" href="${num}">${num}</a></li>
                        </c:forEach>
                        <c:if test="${page.next}">
                            <li class="page-item"><a class="page-link" href="${page.endPage + 1}"> 다음 <i class="fa fa-angle-right"></i></a></li>
                        </c:if>
                    </ul>
                </nav>
				<form id='actionForm' action="${pageContext.request.contextPath}/board/anonymousBoardList.do" method="get"> 
                    <input type="hidden" name="pageNum" value="${page.cri.pageNum}"> 
                    <input type="hidden" name="searchKeyword" value="${page.cri.keyword}"> 
                    <!-- <input type="hidden" name="amount" value="${page.cri.amount}">  -->
                </form>
		    </div>
		</div>
		<div>
			<div>


<!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>


<script>
var actionForm = $('#actionForm'); 
$('.page-item a').on('click', function(e) { e.preventDefault(); 
	//걸어둔 링크로 이동하는 것을 일단 막음 
	actionForm.find('input[name="pageNum"]').val($(this).attr('href')); 
	actionForm.submit(); 
});

// $(searchType).change((e) => {
// 	$(".search-type").hide();
	
// 	const v = $(e.target).val();
// 	$("#search-" + v).css("display", "inline-block");
// });
//검색어 미입력시 제출 금지
window.onload = function(){
	var searchBtn = document.getElementById('searchBtn');
	//입력값이 없는 경우 버튼 비활성화
	searchBtn.disabled = true;
};
$("input[name=searchKeyword]").on('keyup', function(){
	if($('input[name=searchKeyword]').val().length > 0){
		//입력값이 있을경우 버튼 활성화
		searchBtn.disabled = false;
	}else{
		//입력값이 없는 경우 버튼 비활성화
		searchBtn.disabled = true;
	}
});

</script>

<br /><br /><br /><br /><br />
<%@ include file="/WEB-INF/views/common/footer.jsp"%>