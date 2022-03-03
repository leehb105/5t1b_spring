<%@page import="com.otlb.semi.foodMenu.model.vo.FoodMenu"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>
 
 <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/caleandar/css/theme3.css" >

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

	<!-- Main Content -->
	<div id="content">

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<div class="container">
			<br>
			  <h1 style=" color: black; font-weight:bolder; text-shadow: 1px 1px skyblue; margin-left:20px; margin-top:30px">만족도 설문조사</h1>
			  <br>

		  <form action="<%= request.getContextPath() %>/foodMenu/survey?year=<%= request.getParameter("year") + "&month=" + request.getParameter("month") %>"  method="post">
			<div>		
				<br>
    	  		<p><strong>1. 이번주의 식단은 어떠셨습니까?</strong></p>
    	  	</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q1radio1">
				<input type="radio" class="form-check-input" id="q1radio1" name="q1" value="1">매우 불만족
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q1radio2">
				<input type="radio" class="form-check-input" id="q1radio2" name="q1" value="2">불만족
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q1radio3">
				<input type="radio" class="form-check-input" id="q1radio3" name="q1" value="3">보통
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q1radio4">
				<input type="radio" class="form-check-input" id="q1radio4" name="q1" value="4">만족
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q1radio5">
				<input type="radio" class="form-check-input" id="q1radio5" name="q1" value="5">매우 만족
			  </label>
			</div>
			
			<div>		
				<br>
    	  		<p><strong>2. 식사가 가장 맛있었던 요일은 언제입니까?</strong></p>
    	  	</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q2radio1">
				<input type="radio" class="form-check-input" id="q2radio1" name="q2" value="1">월요일
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q2radio2">
				<input type="radio" class="form-check-input" id="q2radio2" name="q2" value="2">화요일
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q2radio3">
				<input type="radio" class="form-check-input" id="q2radio3" name="q2" value="3">수요일
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q2radio4">
				<input type="radio" class="form-check-input" id="q2radio4" name="q2" value="4">목요일
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q2radio5">
				<input type="radio" class="form-check-input" id="q2radio5" name="q2" value="5">금요일
			  </label>
			</div>   
	
			<div>		
				<br>
    	  		<p><strong>3. 구내 식당의 위생상태는 어떻습니까?</strong></p>
    	  	</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q3radio1">
				<input type="radio" class="form-check-input" id="q3radio1" name="q3" value="1">매우 더러움
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q3radio2">
				<input type="radio" class="form-check-input" id="q3radio2" name="q3" value="2">더러움
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q3radio3">
				<input type="radio" class="form-check-input" id="q3radio3" name="q3" value="3">보통
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q3radio4">
				<input type="radio" class="form-check-input" id="q3radio4" name="q3" value="4">깨끗함
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q3radio5">
				<input type="radio" class="form-check-input" id="q3radio5" name="q3" value="5">매우 깨끗함
			  </label>
			</div>   
			
			<div>
				<br>
				<p><strong>4. 배식되는 메뉴의 양은 어떠합니까?</strong></p>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q4radio1">
				<input type="radio" class="form-check-input" id="q4radio1" name="q4" value="1">매우 적음
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q4radio2">
				<input type="radio" class="form-check-input" id="q4radio2" name="q4" value="2">적음
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q4radio3">
				<input type="radio" class="form-check-input" id="q4radio3" name="q4" value="3">보통
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q4radio4">
				<input type="radio" class="form-check-input" id="q4radio4" name="q4" value="4">많음
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q4radio5">
				<input type="radio" class="form-check-input" id="q4radio5" name="q4" value="5">매우 많음
			  </label>
			</div>
			

			<div>		
				<br>
    	  		<p><strong>5. 국과 반찬의 간은 어떠합니까?</strong></p>
    	  	</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q5radio1">
				<input type="radio" class="form-check-input" id="q5radio1" name="q5" value="1">매우 싱거움
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q5radio2">
				<input type="radio" class="form-check-input" id="q5radio2" name="q5" value="2">싱거움
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q5radio3">
				<input type="radio" class="form-check-input" id="q5radio3" name="q5" value="3">보통
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q5radio4">
				<input type="radio" class="form-check-input" id="q5radio4" name="q5" value="4">짬
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label" for="q5radio5">
				<input type="radio" class="form-check-input" id="q5radio5" name="q5" value="5">매우 짬
			  </label>
			</div>
    
    <div>
    <br>
    <button type="submit" class="btn btn-primary">제출</button>
    </div>
  </form>
</div>


	</div>
	<!-- End of Main Content -->
 
 
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>