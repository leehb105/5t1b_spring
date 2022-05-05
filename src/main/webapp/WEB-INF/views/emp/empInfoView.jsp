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

	<!-- <div class="form-group">
		<p>
			부서 : 
			<select name="deptCode" id="deptCode">
				<option value="FM" ${emp.deptCode eq 'FM' ? 'selected' : ''}>관리부</option>
				<option value="SL" ${emp.deptCode eq 'SL' ? 'selected' : ''}>영업부</option>
				<option value="DV" ${emp.deptCode eq 'DV' ? 'selected' : ''}>개발부</option>
				<option value="HR" ${emp.deptCode eq 'HR' ? 'selected' : ''}>인사부</option>
				<option value="GA" ${emp.deptCode eq 'GA' ? 'selected' : ''}>총무부</option>
			</select>
		</p>
	</div>
	<div class="form-group">
		<p>
			직급 : 
			${loginEmp.}
			<select name="jobCode" id="jobCode">
				<option value="J1" ${emp.jobCode eq 'J1' ? 'selected' : ''}>사장</option>
				<option value="J2" ${emp.jobCode eq 'J2' ? 'selected' : ''}>부사장</option>
				<option value="J3" ${emp.jobCode eq 'J3' ? 'selected' : ''}>부장</option>
				<option value="J4" ${emp.jobCode eq 'J4' ? 'selected' : ''}>차장</option>
				<option value="J5" ${emp.jobCode eq 'J5' ? 'selected' : ''}>과장</option>
				<option value="J6" ${emp.jobCode eq 'J6' ? 'selected' : ''}>대리</option>
				<option value="J7" ${emp.jobCode eq 'J7' ? 'selected' : ''}>사원</option>
				<option value="J8" ${emp.jobCode eq 'J8' ? 'selected' : ''}>인턴</option>
			</select>
		</p>
	</div> -->