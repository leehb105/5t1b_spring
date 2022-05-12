<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <!-- <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script> -->


</head>

<body class="bg-gradient-primary">
    <c:if test="${errorMsg != null}">
        <script>
            alert("${errorMsg}");
        </script>
    </c:if>
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
                                <a href="${pageContext.request.contextPath}"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                                    <div class="text-center">
                                    	<br />
                                        <h1 class="h4 text-gray-900 mb-4">환영합니다!</h1>
                                    	<br />
                                    </div>
                                    <form:form 
                                    	id="loginFrm"
                                    	class="user"
                                    	action="${pageContext.request.contextPath}/emp/empLogin.do"
                                    	method="POST">
                                        <div class="form-group">
                                            <h6 id="empNoMsg" style="size: 50%; color: red;"></h6>
                                            <input type="text" name="empNo" value="202110" class="form-control form-control-user"
                                                id="empNo" aria-describedby="emailHelp"
                                                placeholder="사원번호" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" class="form-control form-control-user"
                                                id="password" placeholder="Password" tabindex="0" value="test1234" required>
                                                <!-- 전 비번: qwerty1234! -->
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" name="saveNo"  class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">아이디 저장</label>
                                            </div>
                                        </div>

                                         <input type="submit" value="로그인" class="btn btn-primary btn-user btn-block" />

                                    </form:form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/emp/findPassword">비밀번호 찾기</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/emp/empEnroll.do">회원가입</a>
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








<!-- Bootstrap core JavaScript-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

<script>


    $("#empNo").on('keyup', function(){
        let $empNo = $('#empNo');
        let msg = document.getElementById('empNoMsg');
        let text = '';
        if(!/^[0-9]/.test($empNo.val())){
            // const errorMsg = "숫자만 입력해주세요.";
            // msg.innerText = errorMsg;
        }else{

        }
    });
    
    
    </script>

</html>