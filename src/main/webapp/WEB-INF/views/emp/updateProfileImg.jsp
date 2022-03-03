<%@page import="com.otlb.semi.emp.model.vo.Department"%>
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
								<h1 class="h4 text-gray-900 mb-4">프로필사진 변경</h1>
 								<!-- <div class="row"> -->
<!--								<div class="col-lg-6 d-none d-lg-block bg-mypage-image"> -->
									<form name="updateProfileFrm"
										atcion="<%=request.getContextPath()%>/emp/updateProfileImg"
										method="post" enctype="multipart/form-data">
										<div class="form-group">
											<img id="img__wrap" 
												src="<%= request.getContextPath() + profileImagePath %>" 
												width="320px" height="300px" />
										</div>
										<div class="form-group">
											<input type="file" name="Profile" id="img__preview"/>
										</div>
										<div class="form-group">
											<input type="submit" value="저장하기"
												class="btn btn-primary btn-user btn-block" />
										</div>
										
										<input type="hidden" name="id" value="${sessionScope.principal.id }"/>
									</form>
									</div>
								<!-- </div> -->
							<!-- </div> -->
						<!-- </div>
					</div> -->
				</div>

			</div>

		</div>

	</div>
<script>
$("#img__preview").on("change", function(e){
	var f=e.target.files[0];

	if(!f.type.match("image*")){
		alert("이미지만 첨부할 수 있습니다..");
		$("#img__preview").val('');
		return;
	}

	// f.size = 1024*1024*10

	if(f.size>1024*1024*10){
		alert("10mb까지의 사진만 업데이트 할 수 있습니다.");
		$("#img__preview").val('');
		return;
	}


});
</script>

</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>