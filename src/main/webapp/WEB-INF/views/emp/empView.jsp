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

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="p-5">
							<a href="${pageContext.request.contextPath}"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
							<div class="text-center">
								<br /> <br />
								<h1 class="h4 text-gray-900 mb-4">회원정보</h1>
							</div>
							<div class="row">
								<div class="col-lg-6 d-none d-lg-block bg-mypage-image">
									<div class="form-group container row">
										<c:if test="${emp.profileImage eq null}">
											<img class="img-profile rounded-circle"
											width="250px"
											src="${pageContext.request.contextPath}/resources/img/profile/default_profile.png">
										</c:if>
										<c:if test="${emp.profileImage ne null}"> 
											<img class="img-profile rounded-circle"
											width="250px"
											src="${pageContext.request.contextPath}/resources/img/profile/${loginEmp.profileImage}">
										</c:if>
									</div>
									<input type="button" class="btn btn-primary btn-user btn-block"
										onclick="updateProfileImg();" value="사진변경" />
								</div>
								<div class="col-lg-6">
									<form:form 
										id="empUpdateFrm"
										atcion="${pageContext.request.contextPath}/emp/empView"
										method="POST">
										<div class="form-group">
											<p>
												사원명 :
												${emp.empName}
											</p>
										</div>
										<div class="form-group">
											<p>
												사원번호 :
												${emp.empNo}
											</p>
										</div>
										<!-- <div class="form-group">
											<p>
												현재 비밀번호 :<input type="password" name="oldpassword"
													class="form-control form-control-user" required>
											</p>
										</div>
										<div class="form-group">
											<p>
												새로운 비밀번호 :<input type="password" name="newpassword"
													class="form-control form-control-user" required>
											</p>
										</div> -->
										<div class="form-group">
											<p>
												전화번호 :<input type="tel" placeholder="(-없이)01012345678"
													name="phone" id="phone" maxlength="11"
													class="form-control form-control-user"
													value="${emp.phone}" required>
											</p>
										</div>
										<div class="form-group">
											<p>
												이메일 :<input type="email" placeholder="abc@5t1b.com"
													name="email" id="email" class="form-control form-control-user"
													value="${emp.email}" required>
											</p>
										</div>
										<div class="form-group">
											<p>
												생년월일 :
												<fmt:formatDate value="${emp.birthdate}" pattern="yyyy년 MM월 dd일"/>
											</p>
										</div>
										<div class="form-group">
											<input 
												type="radio" name="gender" id="gender0" value="M"
												${emp.gender eq 'M' ? "checked" : ""} readonly> 
											<label for="gender0">남자</label> 
											<input
												type="radio" name="gender" id="gender1" value="F"
												${emp.gender eq 'F' ? "checked" : ""} readonly> 
											<label for="gender1">여자</label>
										</div>
										<div class="form-group">
											<p>
												부서 : ${emp.deptName}
											</p>
										</div>
										<div class="form-group">
											<p>
												직급 : ${emp.jobName}
											</p>
										</div>
										<input type="button" 
											class="btn btn-primary btn-user btn-block"
											onclick="updateEmp();" value="정보수정" />
										<input type="button"
											class="btn btn-primary btn-user btn-block"
											onclick="updatePassword();" value="비밀번호변경" /> 
									</form:form>
								<!-- </div>
							</div> -->
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

<script>
const updateProfileImg = () => location.href = "${pageContext.request.contextPath}/emp/updateProfileImg";
const updatePassword = () => location.href = "${pageContext.request.contextPath}/emp/updatePassword";

const updateEmp = () => {
	$(empUpdateFrm)
		.attr("action", "${pageContext.request.contextPath}/emp/empUpdate.do")
		.submit();
};

/**
 * #empUpdateFrm 유효성검사(전화번호, 이메일)
 */
$(empUpdateFrm).submit((e) => {
	
	//phone
	const $phone = $(phone);
	if(!/^010[0-9]{8}$/.test($phone.val())){
		alert("유효한 전화번호가 아닙니다.");
		return false;
	}

	//email
	const $email = $(email);
	if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test($email.val())){
		alert("올바른 이메일 형식이 아닙니다.");
		return false;
	}
	
	return true;
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>