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

<!-- Bootstrap core JavaScript-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<div class="container">
	<!-- Outer Row -->
	<div class="row justify-content-center">
		<div class="container-fluid">

			<div class="text-center">
				<!-- <div class="error mx-auto" data-text="403"></div> -->
				<p class="lead text-gray-800 mb-5">해당 페이지의 권한이 없습니다.</p>
				<a href="${pageContext.request.contextPath}/">&larr; 홈으로</a>
			</div>

		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>




