<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">


			<!-- Divider -->
			<hr class="sidebar-divider my-0">


			<!-- Divider -->
			<hr class="sidebar-divider">


			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/noticeList.do">

					<span>공지사항</span>
			</a></li>
			
			
			<!-- Divider -->
			<hr class="sidebar-divider">


			<!-- Nav Item - Charts -->

			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/boardList.do">

					<span>자유게시판</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">


			<!-- Nav Item - Charts -->

			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/anonymousBoardList.do">

					<span>익명게시판</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/foodMenu/calendar.do">
					<span>사내식당메뉴</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/emp/empListView.do">
					<span>사원목록</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->