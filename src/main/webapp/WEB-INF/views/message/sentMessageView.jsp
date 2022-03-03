<%@page import="com.otlb.semi.message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Message message = (Message) request.getAttribute("message");
	String sentDate = (String) request.getAttribute("sentDate");
%>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

         <!-- 쪽지합 nav -->
		<%@ include file="/WEB-INF/views/message/common/messageNav.jsp"%>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
			<div class="container">
        		<button class="btn btn-primary btn-icon-split" onclick="delMessage();" style="padding: 5px; margin-top: 20px;">삭제</button>
			</div>
            <!-- Main Content -->
            <div id="content">
	 		<div class="row">
	 			
	 		
	 		</div>
                <!-- Begin Page Content -->
                <div class="container-fluid">
					<hr class="sidebar-divider">
	 				<div class="col-sm-12">
	 				<table style="color: black;">
                           <tr>
                               <th>받는사람</th>
                               <td style="padding-left: 30px;"><%= message.getEmp().getEmpName() %>(<%= message.getEmp().getDeptName() %>)</td>
                           </tr>
                         	<tr>
                         		<th>보낸시간</th>
                         		<td style="padding-left: 30px;"><%= sentDate %></td> 
                         	</tr>
 					</table>
 					<hr class="sidebar-divider">
 					<div class="container-container" style="color: black;">
						<%= message.getContent() %>
 					</div>	
 					
	 			</div>
                    

                </div>
                <!-- /.container-fluid -->
				<form
		 			id = "delFrm"
					name="messageDelFrm"
					method="POST" 
					action="<%= request.getContextPath() %>/message/sentMessageDelete" >
					<input type="hidden" id="no" name="no" value="<%= message.getNo() %>" />
				</form>
            </div>
            <!-- End of Main Content -->
<script>
function delMessage(){
	$("form[name=messageDelFrm]").submit();	
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>