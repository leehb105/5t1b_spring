<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 쿠키처리
	Cookie[] cookies = request.getCookies();
	String saveEmpNo = null;
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			// System.out.println(name + " = " + value);
			if("saveEmpNo".equals(name)){
				saveEmpNo = value;
			}
		}
	}
	// System.out.println("saveEmpNo@empLogin.jsp = " + saveEmpNo);
	
	String modalHeader = (String) session.getAttribute("modalHeader");
	String modalBody = (String) session.getAttribute("modalBody");
	session.removeAttribute("modalHeader");
	session.removeAttribute("modalBody");
	//System.out.println("modalHeader = " + modalHeader);
%>    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>5T1b - Login</title>

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
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                <a href="<%= request.getContextPath() %>"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                                    <div class="text-center">
                                    	<br />
                                        <h1 class="h4 text-gray-900 mb-4">환영합니다!</h1>
                                    	<br />
                                    </div>
                                    <form 
                                    	id="loginFrm"
                                    	class="user"
                                    	action="<%= request.getContextPath() %>/emp/login"
                                    	method="POST">
                                        <div class="form-group">
                                            <input type="text" name="empNo" value="<%= saveEmpNo != null ? saveEmpNo : "" %>" class="form-control form-control-user"
                                                id="empNo" aria-describedby="emailHelp"
                                                placeholder="사원번호" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" class="form-control form-control-user"
                                                id="password" placeholder="Password" tabindex="0" required>
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" name="saveNo" <%= saveEmpNo != null ? "checked" : "" %> class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">아이디 저장</label>
                                            </div>
                                        </div>

                                         <input type="submit" value="로그인" class="btn btn-primary btn-user btn-block" />

                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/emp/findPassword">비밀번호 찾기</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/emp/empEnroll">회원가입</a>
                                        <br />
                                        <br />
                                        <br />
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
$("#loginFrm").submit((e) =>{
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