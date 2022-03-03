<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

<%
	Emp emp = (Emp) request.getAttribute("emp");
	/* Boolean ownProfileImageExists = (boolean) request.getAttribute("ownProfileImageExists");
	String profileImagePath = "";
	if(ownProfileImageExists) {
		profileImagePath = "/img/profile/" + emp.getEmpNo() + ".png";		
	} else {
		profileImagePath = "/img/profile/profile.png";
	} */

%>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

	<!-- Main Content -->
	<div id="content">

		<!-- Begin Page Content -->
		<div class="container-fluid">

			<div class="row">

				<!-- 사원 정보 -->
				<div class="col-lg mb-4">

					<!-- 헤더 부분 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">사원 정보 <i class="fas fa-users"></i></h6>
						</div>
						<img class="card-img-top" src="<%= request.getContextPath() + profileImagePath %>" style="width:50%">
						<div class="card-body">
							<h4 class="card-title"><%= emp.getEmpName() %></h4>
							<p class="card-text"><%= emp.getEmpNo() %></p>
							<p class="card-text"><%= emp.getBirthdate() %></p>
							<p class="card-text"><%= "F".equals(emp.getGender()) ? '여' : '남' %></p>
							<p class="card-text"><%= emp.getDeptName() %></p>
							<p class="card-text"><%= emp.getJobName() %></p>
							<p class="card-text"><%= emp.getEmail() %></p>
							<p class="card-text"><%= emp.getPhone() %></p>
						</div>
					</div>

				</div>
			</div>
			<!-- /.container-fluid -->

		</div>

	</div>
	<!-- End of Main Content -->

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>