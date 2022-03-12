<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 인증객체의 principal속성을 pageContext 속성으로 저장 -->
<sec:authentication property="principal" var="loginEmp"/>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- 웹폰트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>



<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>5T1B 커뮤니티에 오신것을 환영합니다!</title>
<script src="${pageContext.request.contextPath}/js/otochatroom.js"></script>
<!-- 제이쿼리 링크 -->
<!-- <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script> -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/otochatroom.js"></script>

<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>

<!-- Custom fonts for this template-->
<!-- <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:wght@400;700"
	rel="stylesheet">


<!-- Custom styles for this template-->
<!-- <link href="css/sb-admin-2.min.css" rel="stylesheet"> -->

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css">
	


</head>


<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- 5t1b 홈버튼-->
	<a href="${pageContext.request.contextPath}">
	<img alt="logo" src="${pageContext.request.contextPath}/resources/img/logo.png">
	</a>

	
	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
		<!-- Nav Item - Search Dropdown (Visible Only XS) -->
		<li class="nav-item dropdown no-arrow d-sm-none"><a
			class="nav-link dropdown-toggle" href="#" id="searchDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
		</a> <!-- Dropdown - Messages -->
			<div
				class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
				aria-labelledby="searchDropdown">
				<form class="form-inline mr-auto w-100 navbar-search">
					<div class="input-group">
						<input type="text" class="form-control bg-light border-0 small"
							placeholder="Search for..." aria-label="Search"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div></li>

	
		<!-- Dropdown - Alerts -->
			<div
				class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="alertsDropdown">
				<h6 class="dropdown-header">Alerts Center</h6>
				<a class="dropdown-item d-flex align-items-center" href="#">
					<div class="mr-3">
						<div class="icon-circle bg-primary">
							<i class="fas fa-file-alt text-white"></i>
						</div>
					</div>
					<div>
						<div class="small text-gray-500">December 12, 2019</div>
						<span class="font-weight-bold">A new monthly report is
							ready to download!</span>
					</div>
				</a> <a class="dropdown-item d-flex align-items-center" href="#">
					<div class="mr-3">
						<div class="icon-circle bg-success">
							<i class="fas fa-donate text-white"></i>
						</div>
					</div>
					<div>
						<div class="small text-gray-500">December 7, 2019</div>
						$290.29 has been deposited into your account!
					</div>
				</a> <a class="dropdown-item d-flex align-items-center" href="#">
					<div class="mr-3">
						<div class="icon-circle bg-warning">
							<i class="fas fa-exclamation-triangle text-white"></i>
						</div>
					</div>
					<div>
						<div class="small text-gray-500">December 2, 2019</div>
						Spending Alert: We've noticed unusually high spending for your
						account.
					</div>
				</a> <a class="dropdown-item text-center small text-gray-500" href="#">Show
					All Alerts</a>
			</div></li>

		<!-- Nav Item - Messages -->
		<c:if test="${loginEmp ne null}">
			<li class="nav-item dropdown no-arrow mx-1">
				<a
					class="nav-link dropdown-toggle"
					href="${pageContext.request.contextPath}/message/messageList"
					id="messagesDropdown" role="button"
					aria-haspopup="true" aria-expanded="false"> 
					<i class="fas fa-envelope fa-fw"></i> 
					<!-- Counter - Messages --> 
					<!-- 안읽은 받은쪽지 카운터 -->
					<span class="badge badge-danger badge-counter" id="counter"></span>
					<input type="hidden" id="hiddenCnt" value=""/>
				</a> 
			</li>

		</c:if>

		

		<!-- Login -->
		<sec:authorize access="isAnonymous()">
			<div class="book-now-btn ml-3 ml-lg-5">
				<button onclick="location.href='${pageContext.request.contextPath}/emp/empEnoll.do'" class="btn btn-sm btn-primary shadow-sm" style="margin:3px; height:2rem;">회원가입 <i class="fas fa-unlock-alt"></i></button>
				<button onclick="location.href='${pageContext.request.contextPath}/emp/empLogin.do'" class="btn btn-sm btn-primary shadow-sm" style= "margin :3px; height:2rem;">로그인 <i class="fas fa-user-plus"></i></button>
			</div>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
		<div style="padding-right:20px">
			<c:choose>
				<c:when test="${loginEmp eq 'admin'}">
					<!-- <a href="${pageContext.request.contextPath}/admin/admin.do">
					<sec:authentication property="principal.username"/>
					</a>님 &nbsp
					<a href="${pageContext.request.contextPath}/booking/myBooking.do?pageNum=1&amout=5"><i class="fa fa-list fa-lg"></i></a> &nbsp
					<a href="${pageContext.request.contextPath}/member/memberLogout.do" class="btn roberto-btn mb-15 w-10">로그아웃</a> -->
				</c:when>
				
				<c:otherwise>
					<!-- <a href="${pageContext.request.contextPath}/emp/mypageMain.do">
					<sec:authentication property="principal.username"/>
					</a>님 &nbsp -->

					<li class="nav-item dropdown no-arrow"><a
						class="nav-link dropdown-toggle" href="#" id="userDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> 
						<span
							class="mr-2 d-none d-lg-inline text-gray-600 small">${loginEmp.empName}</span>
						<img class="img-profile rounded-circle"
						src="${pageContext.request.contextPath}/img/profile/${loginEmp.profileImage}">
					</a> <!-- Dropdown - User Information -->
						<div
							class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
							aria-labelledby="userDropdown">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/emp/empView.do?"> <i
								class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 마이 페이지
							 </a> 
			
							<div class="dropdown-divider"></div>
			
							 <a class="dropdown-item" href="${pageContext.request.contextPath}/emp/empLogout.do"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
								로그아웃
							</a>
						</div>
					</li>









				</c:otherwise>
			</c:choose>
			</div>
		</sec:authorize>



	</ul>

</nav>
<!-- End of Topbar -->
 <!-- if (loginEmp != null) {  -->
<script>
$( document ).ready(function() {

	//console.log("test");
	var counter = document.getElementById("counter");
    $.ajax({
		url: "${pageContext.request.contextPath}/message/messageLoadCount.do",
		method: "GET",
		success(data){

			//span태그에 count데이터 삽입
			counter.innerText = data;

		},
		error: console.log

	})
		

});
</script>



