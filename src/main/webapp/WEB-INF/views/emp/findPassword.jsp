<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String modalHeader = (String) session.getAttribute("modalHeader");
	String modalBody = (String) session.getAttribute("modalBody");
	session.removeAttribute("modalHeader");
	session.removeAttribute("modalBody");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>5T1B - 비밀번호 찾기</title>

    <!-- Custom fonts for this template-->
    <link href="<%= request.getContextPath() %>/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%= request.getContextPath() %>/resources/css/sb-admin-2.min.css" rel="stylesheet">
	<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                <a href="<%= request.getContextPath() %>"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">비밀번호 찾기</h1>
                                        <p class="mb-4">입력하신 이메일로 임시비밀번호를 보내드립니다. 발급 받은 임시비밀번호로 로그인 후 변경해주세요.</p>
                                    </div>
                                    <form 
                                    	id="findPasswordFrm"
                                    	class="user"
                                    	action="<%= request.getContextPath() %>/emp/findPassword"
                                    	method="POST">
                                    	<div class="form-group">
	                                    	<input type="text" name="empName" class="form-control form-control-user"
	                                                placeholder="이름" required autocomplete="off">
                                    	</div>
                                    	<div class="form-group">
	                                    	<input type="text" name="empNo" id="empNo" class="form-control form-control-user"
	                                               placeholder="사원번호" required autocomplete="off">
                                    	</div>
                                        <div class="form-group">
                                            <input type="email" name="email" class="form-control form-control-user"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="이메일" required autocomplete="off">
                                        </div>
                                        <input type="submit" class="btn btn-primary btn-user btn-block"
                                            value="임시 비밀번호 받기">
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/emp/empEnroll">회원가입</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/emp/login">로그인</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
<%
	if(modalHeader != null){
%>
<script>
// modal 실행
$(function(){
	$("#staticBackdrop").modal('show');		
});
</script>
<%
	}
%>

<script>
$("#findPasswordFrm").submit((e) =>{
	if(!validateEmpNo({}))
		return false;
	
	return true;
});

const validateEmpNo = ({target = empNo}) => {
	const $empNo = $(empNo);
	 if(!/^\d+$/.test($empNo.val())){
		 const errorTitle = "사원번호 입력 오류";
		 const errorMsg = "숫자만 입력해주세요.";
		 $("#staticBackdropLabel").html(errorTitle);
		 $("#modalBody").html(errorMsg);
		 $("#staticBackdrop").modal('show');
		 return false;
	 }
	 else{
		 return true;
	 }
};


</script>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel"><%= modalHeader != null ? modalHeader : "" %></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="modalBody">
      	<%= modalBody != null ? modalBody : "" %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- Bootstrap core JavaScript-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>