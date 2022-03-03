<%@page import="com.otlb.semi.foodMenu.model.vo.FoodMenu"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>
<%
	List<FoodMenu> list = (List<FoodMenu>) request.getAttribute("foodMenu"); 
 %>
 
 <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/caleandar/css/theme3.css" >

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

	<!-- Main Content -->
	<div id="content">

		<!-- Begin Page Content -->
		<div class="container-fluid">
		<div id="caleandar"></div>
<%-- 		<%= request.getAttribute("foodMenu") %>
 --%>			<!-- /.container-fluid -->

		</div>

	</div>
	<!-- End of Main Content -->
		<script type="text/javascript" src="<%= request.getContextPath() %>/caleandar/js/caleandar.js"></script>
<%--         <script type="text/javascript" src="<%= request.getContextPath() %>/caleandar/js/demo.js"></script>
 --%>
 <button type="button" class="btn btn-primary" id="survey">설문조사 참여</button>
 

 		<script>
 		
 		const events = [];
 		const settings = {};
 		const element = document.getElementById("caleandar");
 		
 		<% for(FoodMenu fm : list) { 
 			String[] date = fm.getMenuDate().toString().split("-");
 		%>
 			events.push({Date: new Date(<%= date[0] %>, <%= Integer.parseInt(date[1]) - 1 %>, <%= date[2] %>), Title: `<%= fm.listMenu() %>`});
 		<% } %>

  		<%
  		String curYear = request.getParameter("year");
  		String curMonth = request.getParameter("month");
  		if(curYear == null || curMonth == null) { %>
	   		caleandar(element, events, settings);
		<% } else { %>
    		caleandar(element, events, settings, <%= curYear %> + "-" + <%= Integer.parseInt(curMonth) %>);
    		<% } %>

 		
 	</script>

 	<script>
 		const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
 		const cld_rwd = document.getElementsByClassName("cld-nav")[0];	
 		const cld_fwd = document.getElementsByClassName("cld-nav")[1];	
 		const today = document.getElementsByClassName("today")[0].innerText;
 		let curYear = today.split(',')[1].trim();
 		let curMonth = '';
 		months.forEach(function(month, i) {
 			if(month === today.split(',')[0]) curMonth = i + 1; 
 		});
 		
 		cld_rwd.addEventListener("click", () => {
 			if(curMonth == 1) {
 				curMonth = 13;
 				curYear--;
 			}
  			location.href = "./calendar?year=" + curYear + "&month=" + (curMonth - 1);
 		}); 
 		
 		cld_fwd.addEventListener("click", () => {
 			if(curMonth == 12) {
 				curMonth = 0;
 				curYear++;
 			}
  			location.href = "./calendar?year=" + curYear + "&month=" + (curMonth + 1);
 		});
 		
 		survey.addEventListener("click", () => {
 			location.href = "./survey?year=" + curYear + "&month=" + curMonth;
 		});
 		
 		</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>