<%@page import="com.otlb.semi.emp.model.vo.Emp"%>
<%@page import="com.otlb.semi.emp.controller.EmpEnrollServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//값의 입력 여부를 servlet에서 확인하기 때문에
	//하나라도 값을 입력하지 않고 가입하기를 누르면
	//기존에 입력했던 값이 사라지는 문제가 발생하여
	//jsp에서 기존에 입력했던 값을 받아와서 해결
	String empName = request.getParameter("empName");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String gender = request.getParameter("gender");

	String messageType = null;
	String messageContent = null;
	if(session.getAttribute("messageType") != null 
			&& session.getAttribute("messageContent") != null){
		messageType = (String) session.getAttribute("messageType");
		messageContent = (String) session.getAttribute("messageContent");
	}
%>	
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>5T1B - 회원가입</title>

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
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

</head>

<body class="bg-gradient-primary">
	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<a href="<%= request.getContextPath() %>"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form 
								name="empEnrollFrm"
								class="user" 
								action="<%= request.getContextPath() %>/emp/empEnroll" 
								method="POST">
								<div class="form-group">	
									<input type="text" name="empNo" class="form-control form-control-user"
										id="empNo" placeholder="사원번호" autocomplete="off">
										<div id="validateMessage"></div>									
								</div>
								<div class="form-group">	
									<input  type="text" name="empName" class="form-control form-control-user" id="empName"
										 placeholder="이름" autocomplete="off" value="<%= empName != null ? empName : "" %>">	
									<div style="color: red; font-size: 0.8em;" id="nameCheckMessage"></div>							
								</div>
								<div class="form-group">
									<input type="email" name="email" class="form-control form-control-user"
										id="email" placeholder="이메일" autocomplete="off" value="<%= email != null ? email : "" %>">
									<div style="color: red; font-size: 0.8em;" id="emailCheckMessage"></div>		
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input  type="password" name="password" class="form-control form-control-user"
											id="password" placeholder="비밀번호" autocomplete="off">
											<div style="color: red; font-size: 0.8em;" id="passwordCheckMessage"></div>
									</div>
									<div class="col-sm-6">
										<input type="password" name="passwordCheck" class="form-control form-control-user"
											id="passwordCheck" placeholder="비밀번호 확인" autocomplete="off">
										<div style="color: red; font-size: 0.8em;" id="passwordCheckMessage1"></div>
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="phone" class="form-control form-control-user" id="phone"
										 placeholder="전화번호 (-) 제외하고 입력" autocomplete="off" value="<%= phone != null ? phone : "" %>">
									<div style="color: red; font-size: 0.8em;" id="phoneCheckMessage"></div>
								</div>
								<div class="form-group">
									<select name="gender" id="gender" class="form-control rounded-pill" style="height:49px; font-size: .8rem;" required>
										<option value="" <%= gender == null ? "selected" : ""  %> disabled hidden>성별</option>
										<option  value="F" <%= "F".equals(gender) ? "selected" : "" %>>여자</option>
										<option  value="M" <%= "M".equals(gender) ? "selected" : "" %>>남자</option>
									</select>
								</div>
								
								<label for="year">생일</label>
								<div class="form-group">
									<div class="row">
										<div class="col">
											<select name="birthdayYear" id="year" class="form-control rounded-pill" style="height:49px; font-size: .8rem;" required>
											<option value="" selected disabled hidden>년</option>
											<option value="2021">2021</option>
											<option value="2020">2020</option>
											<option value="2019">2019</option>
											<option value="2018">2018</option>
											<option value="2017">2017</option>
											<option value="2016">2016</option>
											<option value="2015">2015</option>
											<option value="2014">2014</option>
											<option value="2013">2013</option>
											<option value="2012">2012</option>
											<option value="2011">2011</option>
											<option value="2010">2010</option>
											<option value="2009">2009</option>
											<option value="2008">2008</option>
											<option value="2007">2007</option>
											<option value="2006">2006</option>
											<option value="2005">2005</option>
											<option value="2004">2004</option>
											<option value="2003">2003</option>
											<option value="2002">2002</option>
											<option value="2001">2001</option>
											<option value="2000">2000</option>
											<option value="1999">1999</option>
											<option value="1998">1998</option>
											<option value="1997">1997</option>
											<option value="1996">1996</option>
											<option value="1995">1995</option>
											<option value="1994">1994</option>
											<option value="1993">1993</option>
											<option value="1992">1992</option>
											<option value="1991">1991</option>
											<option value="1990">1990</option>
											<option value="1989">1989</option>
											<option value="1988">1988</option>
											<option value="1987">1987</option>
											<option value="1986">1986</option>
											<option value="1985">1985</option>
											<option value="1984">1984</option>
											<option value="1983">1983</option>
											<option value="1982">1982</option>
											<option value="1981">1981</option>
											<option value="1980">1980</option>
											<option value="1979">1979</option>
											<option value="1978">1978</option>
											<option value="1977">1977</option>
											<option value="1976">1976</option>
											<option value="1975">1975</option>
											<option value="1974">1974</option>
											<option value="1973">1973</option>
											<option value="1972">1972</option>
											<option value="1971">1971</option>
											<option value="1970">1970</option>
											<option value="1969">1969</option>
											<option value="1968">1968</option>
											<option value="1967">1967</option>
											<option value="1966">1966</option>
											<option value="1965">1965</option>
											<option value="1964">1964</option>
											<option value="1963">1963</option>
											<option value="1962">1962</option>
											<option value="1961">1961</option>
											<option value="1960">1960</option>
											<option value="1959">1959</option>
											<option value="1958">1958</option>
											<option value="1957">1957</option>
											<option value="1956">1956</option>
											<option value="1955">1955</option>
											<option value="1954">1954</option>
											<option value="1953">1953</option>
											<option value="1952">1952</option>
											<option value="1951">1951</option>
											<option value="1950">1950</option>
											<option value="1949">1949</option>
											<option value="1948">1948</option>
											<option value="1947">1947</option>
											<option value="1946">1946</option>
											<option value="1945">1945</option>
											<option value="1944">1944</option>
											<option value="1943">1943</option>
											<option value="1942">1942</option>
											<option value="1941">1941</option>
											<option value="1940">1940</option>
											<option value="1939">1939</option>
											<option value="1938">1938</option>
											<option value="1937">1937</option>
											<option value="1936">1936</option>
											<option value="1935">1935</option>
											<option value="1934">1934</option>
											<option value="1933">1933</option>
											<option value="1932">1932</option>
											<option value="1931">1931</option>
											<option value="1930">1930</option>
											<option value="1929">1929</option>
											<option value="1928">1928</option>
											<option value="1927">1927</option>
											<option value="1926">1926</option>
											<option value="1925">1925</option>
											<option value="1924">1924</option>
											<option value="1923">1923</option>
											<option value="1922">1922</option>
											<option value="1921">1921</option>
											<option value="1920">1920</option>
											<option value="1919">1919</option>
											<option value="1918">1918</option>
											<option value="1917">1917</option>
											<option value="1916">1916</option>
											<option value="1915">1915</option>
											<option value="1914">1914</option>
											<option value="1913">1913</option>
											<option value="1912">1912</option>
											<option value="1911">1911</option>
											<option value="1910">1910</option>
											<option value="1909">1909</option>
											<option value="1908">1908</option>
											<option value="1907">1907</option>
											<option value="1906">1906</option>
											<option value="1905">1905</option>
										</select> 
										</div>
										<div class="col">
											<select name="birthdayMonth" id="month" class="form-control rounded-pill" style="height:49px; font-size: .8rem;" required>
											<option value="" selected disabled hidden>월</option>
											<option value="1">1월</option>
											<option value="2">2월</option>
											<option value="3">3월</option>
											<option value="4">4월</option>
											<option value="5">5월</option>
											<option value="6">6월</option>
											<option value="7">7월</option>
											<option value="8">8월</option>
											<option value="9">9월</option>
											<option value="10">10월</option>
											<option value="11">11월</option>
											<option value="12">12월</option>
										</select> 
										</div>
										<div class="col">
											<select name="birthdayDay" id="day" class="form-control rounded-pill" style="height:49px; font-size: .8rem;" required>
											<option value="" selected disabled hidden>일</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
											<option value="13">13</option>
											<option value="14">14</option>
											<option value="15">15</option>
											<option value="16">16</option>
											<option value="17">17</option>
											<option value="18">18</option>
											<option value="19">19</option>
											<option value="20">20</option>
											<option value="21">21</option>
											<option value="22">22</option>
											<option value="23">23</option>
											<option value="24">24</option>
											<option value="25">25</option>
											<option value="26">26</option>
											<option value="27">27</option>
											<option value="28">28</option>
											<option value="29">29</option>
											<option value="30">30</option>
											<option value="31">31</option>
										</select>
										</div>
									</div>
								</div>
								
								<!-- 회원가입 버튼 -->
								<button type="submit" class="btn btn-primary btn-user btn-block">가입하기</button>
								
							</form>
							<hr>
                             
                             <!-- 로그인 화면으로 이동 -->
							<div class="text-center">
								<a class="small" href="<%= request.getContextPath() %>/emp/login">로그인</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<script>
document.empEnrollFrm.onsubmit = (e) => {
	// 이름 유효성 검사
	if(!validateEmpName({}))
		return false;
	// 이메일 유효성 검사
	if(!validateEmail({}))
		return false;
	// 비밀번호 유효성 검사
	if(!validatePassword({}))
		return false;
	// 비밀번호 확인 유효성 검사
	if(!validatePasswordCheck({}))
		return false;
	// 전화번호 유효성 검사
	if(!validatePhone({}))
		return false;
	
	return true;
};



// 사원번호 유효성 검사
const $validateEmpNo = $("#empNo").blur(({target = empNo}) => {
	const empNo = $("#empNo").val();
	
	$.ajax({
		url: "<%= request.getContextPath() %>/emp/empNoCheck?empNo=" + empNo,
		success(data){
			// data == 1 ? 중복o : 중복x
			//console.log("data = " + data);
			
			if(data == 1) {
				// data == 1 -> 사원번호 중복
				$("#validateMessage").text("유효한 사원번호 입니다.");
				$("#validateMessage").removeClass("text-danger");
				$("#validateMessage").addClass("text-success");
			}
			else{
				// data == 0 -> 사원번호 길이 & 문자열 검사
				const reg_empNo = /^\d{6}$/; // 6자리 숫자
				if(reg_empNo.test(empNo)){
					$("#validateMessage").text("발급받은 사원번호를 입력하세요.");
					$("#validateMessage").addClass("text-danger");
				}
				else{
					$("#validateMessage").text("6자리의 사원번호를 입력하세요.");
					$("#validateMessage").addClass("text-danger");
				}
				
			}
		},
		error: console.log
	});
});

// 이름 유효성 검사
const validateEmpName = ({target = empName}) => {
	let empName = $("#empName").val();
    if(!/^[가-힣]{2,}$/.test(empName)){
    	$("#nameCheckMessage").html("특수문자, 영어, 숫자는 사용할 수 없습니다. 한글만 입력해주세요.");
    	return false;
    }
    else{
    	$("#nameCheckMessage").html("");
    	return true;
    }
};

// 이메일 유효성 검사
const validateEmail = ({target = email}) =>{
	let email = $("#email").val();
	if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email)){
		$("#emailCheckMessage").html("유효한 이메일 형식이 아닙니다.");
		return false;
	}
	else{
		$("#emailCheckMessage").html("");
		return true;
	}
};

// 비밀번호 유효성 검사
const validatePassword = ({target = password}) => {
	let checkLen = /^.{8,15}$/;
	let checkNum = /\d/;
	let checkEng = /[a-zA-Z]/;
	let checkSpc = /[~!@#$%^&*()_+|<>?:{}]/;
	let password = $("#password").val();
	
	if(!checkLen.test(password) || !checkNum.test(password) || !checkEng.test(password) || !checkSpc.test(password)){
		$("#passwordCheckMessage").html("비밀번호는 8~15자리 숫자/문자/특수문자를 포함해야합니다.");
    	return false;
	} else {
		$("#passwordCheckMessage").html("");
    	return true;
	}
	
};

// 비밀번호 확인 유효성 검사
const validatePasswordCheck = ({target = passwordCheck}) => {
	let passwordCheck = $("#passwordCheck").val();
	let password = $("#password").val();
	if(password != passwordCheck){
		$("#passwordCheckMessage1").html("비밀번호가 일치하지 않습니다.");
		return false;
	} else {
		$("#passwordCheckMessage1").html("");
		return true;
	}
};

// 전화번호 유효성 검사
const validatePhone = ({target = phone}) => {
	let phone = $("#phone").val();
	if(!/^01[016789][^0][0-9]{6,7}$/.test(phone)){
		$("#phoneCheckMessage").html("유효하지 않은 전화번호입니다.")
		return false;
	} else {
		$("#phoneCheckMessage").html("");
		return true;
	}
};

empName.onkeyup = validateEmpName;
email.onkeyup = validateEmail;
password.onkeyup = validatePassword;
passwordCheck.onkeyup = validatePasswordCheck;
phone.onkeyup = validatePhone;
</script>


<%
	if(messageType != null) {
%>
<script>

	// modal 실행
	$(function(){
		$("#staticBackdrop").modal('show');		
	});
</script>

<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel"><%= messageType %></h5>
	          <% if(messageType.equals(EmpEnrollServlet.ERROR_MESSAGE)){ %>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
	          <% } %>
	      </div>
	      <div class="modal-body">
	      	  <%= messageContent %>
	      </div>
	      <div class="modal-footer">
		      <% if(messageType.equals(EmpEnrollServlet.SUCCESS_MESSAGE)){ %>
				<button type="button" class="btn btn-secondary" id="success_button" data-dismiss="modal" onclick="location.href='<%= request.getContextPath() %>/emp/login'">확인</button>	      		
		      <% } else { %>	
		        <button type="button" class="btn btn-secondary" id="error_button" data-dismiss="modal">닫기</button>
			  <% } %>	      
	      </div>
	    </div>
	  </div>
	</div>
	
<script>
	if(<%= messageType.equals(EmpEnrollServlet.SUCCESS_MESSAGE) %>)
		$(".modal-header").attr("style", "background-color:#d9edf7");
	else
		$(".modal-header").attr("style", "background-color:#f2dede");
</script>

<%
	session.removeAttribute("messageContent");
	session.removeAttribute("messageType");
	}
%>




	<!-- Bootstrap core JavaScript-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>