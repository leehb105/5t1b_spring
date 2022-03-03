<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>5T1b - Mypage</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>

</head>

<body class="mypage" id="mypage">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="p-5">
							<a href="<%= request.getContextPath() %>/emp/empView"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
							<div class="text-center">
								<br /> <br />
								<h1 class="h4 text-gray-900 mb-4">비밀번호 변경</h1>
								<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<p style="height: 44px;">현재 비밀번호 :</p>
									</div>
									<div class="form-group">
										<p style="height: 44px;">새로운 비밀번호 :</p>
									</div>
									<div class="form-group">
										<p>새로운 비밀번호 확인:</p>
									</div>
								</div>
								<div class="col-lg-6">
									<form name="updatePwdFrm"
										atcion="<%=request.getContextPath()%>/emp/updatePassword"
										method="post">
										<div class="form-group">
											<input type="password" name="oldPassword" id="oldPassword"
													class="form-control form-control-user" required>
										</div>
										<div class="form-group">
											<input type="password" name="newPassword" id="newPassword"
													class="form-control form-control-user" required>
										</div>
										<div class="form-group">
											<input type="password" id="newPasswordCheck"
													class="form-control form-control-user" width="100px" required>
											<br />
										</div>
										<input type="submit" value = "변경하기"
											class="btn btn-primary btn-user btn-block" />
									</form>
								<!-- </div>
							</div> -->
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
	
	if(!passwordValidate()){
		return false;
	}
	
	if($oldPassword.val() == $newPassword.val()){
		alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
		$oldPassword.select();
		return false;
	}
	
	return true;	

});

$("#newPasswordCheck").blur(passwordValidate);

/**
 * 신규비밀번호 일치 검사 
 */ 
function passwordValidate(){
	var $newPassword = $("#newPassword");
	var $newPasswordCheck = $("#newPasswordCheck");
	if($newPassword.val() != $newPasswordCheck.val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$newPassword.select();
		return false;
	}
	return true;	
}

</script>

</body>
</html>
<br /><br /><br /><br /><br />
<%@ include file="/WEB-INF/views/common/footer.jsp"%>