<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 인증객체의 principal속성을 pageContext 속성으로 저장 -->
<sec:authentication property="principal" var="loginEmp"/>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>

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
                               <td style="padding-left: 30px;">${message.emp.empName}(${message.emp.deptName})</td>
                           </tr>
                         	<tr>
                         		<th>보낸시간</th>
                         		<td style="padding-left: 30px;">
									<fmt:formatDate value="${message.sentDate}" pattern="yy-MM-dd [HH:mm]"/>
								</td> 
                         	</tr>
 					</table>
 					<hr class="sidebar-divider">
 					<div class="container-container" style="color: black;">
						${message.content}
 					</div>	
 					
	 			</div>
                    

                </div>
                <!-- /.container-fluid -->
				<form
		 			id = "delFrm"
					name="messageDelFrm"
					method="POST" 
					action="${pageContext.request.contextPath}/message/sentMessageDelete" >
					<input type="hidden" id="no" name="no" value="${message.no}" />
				</form>
            </div>
            <!-- End of Main Content -->
<script>
function delMessage(){
	$("form[name=messageDelFrm]").submit();	
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>