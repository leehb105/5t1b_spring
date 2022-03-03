<%@page import="com.otlb.semi.message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Message message = (Message) request.getAttribute("message");
	String date = (String) request.getAttribute("date");
%>
</style>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

         <!-- 쪽지합 nav -->
		<%@ include file="/WEB-INF/views/message/common/messageNav.jsp"%>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
			<div class="container">
        		<button class="btn btn-primary btn-icon-split" onclick="delMessage();" style="padding: 5px; margin-top: 20px;">삭제</button>
        		<button class="btn btn-primary btn-icon-split" onclick="replyMessage();" style="padding: 5px; margin-top: 20px;">답장</button>
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
                               <th>보낸사람</th>
                               <td style="padding-left: 30px;"><%= message.getEmp().getEmpName() %>(<%= message.getEmp().getDeptName() %>)</td>
                           </tr>
                         	<tr>
                         		<th>받은시간</th>
                         		<td style="padding-left: 30px;"><%= date %></td>
                         	</tr>
	 					</table>
 					
	 				</div>
 					<hr class="sidebar-divider">
 					<div class="container-container" style="color: black;">
						<%= message.getContent() %>
 					</div>	

                </div>
                <!-- /.container-fluid -->
				<form
		 			id = "delFrm"
					name="messageDelFrm"
					method="POST" 
					action="<%= request.getContextPath() %>/message/receivedMessageDelete" >
					<input type="hidden" id="no" name="no" value="<%= message.getNo() %>" />
				</form>	
				<form
		 			id = "replyFrm"
					name="messageReplyFrm"
					method="GET" 
					action="${pageContext.request.contextPath}/message/messageForm.do" >
					<input type="hidden" id="receiverNo" name="receiverNo" value="<%= message.getSenderEmpNo() %>" />
				</form>	
            </div>
            <!-- End of Main Content -->
<script>
//삭제버튼
function delMessage(){
	$("form[name=messageDelFrm]").submit();	
}

//답장버튼
function replyMessage() {
	$("form[name=messageReplyFrm]").submit();	
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>