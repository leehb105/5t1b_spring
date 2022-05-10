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
<style>
	.form-group{
		text-align: left;
	}
	.form-control{
		width: 500px;
	}
</style>
	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="p-5">
							<a href="${pageContext.request.contextPath}/emp/empView.do"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
							<div class="text-center">
								<br /> <br />
								<h1 class="h4 text-gray-900 mb-4">비밀번호 변경</h1>
								<div class="row" style="display: inline-block;text-align: center;">
									<div class="col-lg-12">
										<form:form name="updatePwdFrm"
											id = "updatePwdFrm"
											atcion="${pageContext.request.contextPath}/emp/updatePassword.do"
											method="post">
											<div class="form-group">
												<label for="oldPassword">현재 비밀번호</label>
												<input type="password" name="oldPassword" id="oldPassword"
														class="form-control form-control-user" required>
											</div>
											<div class="form-group">
												<label for="newPassword">새로운 비밀번호</label>
												<input type="password" name="newPassword" id="newPassword"
														class="form-control form-control-user" required>
											</div>
											<div class="form-group">
												<label for="newPasswordCheck" id="checkLabel">새로운 비밀번호 확인</label>
												<input type="password" name="newPasswordCheck" id="newPasswordCheck"
														class="form-control form-control-user" required>
												<br />
											</div>
											<input type="submit" value = "변경하기"
												id="updateBtn"
												class="btn btn-primary btn-user btn-block mb-4" />
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

<script>

/**
 * 비번 변경 유효성검사
 */ 
$("[name=updatePwdFrm]").submit(function(){
	var $oldPassword = $("#oldPassword");
	var $newPassword = $("#newPassword");
	
	// if(!passwordValidate()){
	// 	return false;
	// }
	
	if($oldPassword.val() == $newPassword.val()){
		alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
		$oldPassword.select();
		return false;
	}
	
	return true;	

});

// $("#newPasswordCheck").blur(passwordValidate);
let checkPassword = false;
let checkLabel = document.getElementById('checkLabel');
/**
 * 신규비밀번호 일치 검사 
 */ 
// function passwordValidate(){
// 	var $newPassword = $("#newPassword");
// 	var $newPasswordCheck = $("#newPasswordCheck");
// 	if($newPassword.val() != $newPasswordCheck.val()){
// 		checkLabel.style.color = 'red';
// 		checkPassword = false;
// 		// $newPassword.select();
// 		return false;
// 	}else{
// 		checkPassword = true;
// 	}
// 	return true;	
// }

window.onload = function(){
	let updateBtn = document.getElementById('updateBtn');
	updateBtn.disabled = true;
	checkLabel.style.color = 'red';

	let $oldPassword = $('#oldPassword');
	let $newPassword = $('#newPassword');
	let $newPasswordCheck = $('#newPasswordCheck');


	//input전체 입력 시 변경버튼 활성화
	$( '#oldPassword, #newPassword, #newPasswordCheck' ).keyup( function() {
		if($oldPassword.val().length > 0 && $newPassword.val().length > 0 && $newPasswordCheck.val().length > 0){
			updateBtn.disabled = false;
			if($newPassword.val() == $newPasswordCheck.val()){
				checkPassword = true;
			
			}else{
				checkPassword = false;
				updateBtn.disabled = true;
			}
			
		}else{
			updateBtn.disabled = true;

		}

		//새 비번 둘이 일치하지 않으면 붉은 글씨
		if(!checkPassword){
			checkLabel.style.color = 'red';
		}else{
			checkLabel.style.color = '#6e707e';
		}


	});
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>