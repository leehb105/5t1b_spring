<%@page import="com.otlb.semi.message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!-- 받은 쪽지함 jsp -->
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
		 	<hr class="sidebar-divider my-3">
            <!-- Main Content -->
            <div id="content">
	 		<div class="row">
	 			<div class="col-sm-12">
	 				<table class="table table-bordered dataTable">
	 					<thead>
                           <tr style="background-color: #CCCCCC; color: black;">
                               <th style="text-align: center;"><input type="checkbox" class="checkAll"/></th>
                               <th>보낸사람</th>
                               <th>내용</th>
                               <th>날짜</th>
                           </tr>
                         </thead>
                         <tbody>
<%
/* 
	로그인 회원이 받은 쪽지데이터 출력
*/
List<Message> list = (List<Message>) request.getAttribute("list");
List<String> titleList = (List<String>) request.getAttribute("titleList");
List<String> sentDateList = (List<String>) request.getAttribute("sentDateList");
	//for(Message message : list){
	for(int i = 0; i < list.size(); i++){	
		Message message = list.get(i);
		String title = titleList.get(i);
%>
                         	<tr>
                         		<td width="50px;" style="text-align: center;"><input type="checkbox" name="check" value="<%= message.getNo()%>"/></td>
                         		<!-- 안읽었다면 파란글씨 -->
                         		<td width="180px" style="font-weight: bold;">
                         			<a class="empPopover" data-toggle="popover" 
		                         		data-emp-no="<%= message.getSenderEmpNo()%>"
		                         		data-emp-name="<%= message.getEmp().getEmpName() %>" 
		                         		<%= message.getReadDate() != null ? "style=\"color: #858796;\"" : "" %>>
                         				<%= message.getEmp().getEmpName() %>(<%= message.getEmp().getDeptName() %>)
                         			</a>
        
     						
                         		<%-- <span ><%= message.getEmp().getEmpName() %>(<%= message.getEmp().getDeptName() %>)</span> --%>
                         		</td>
                         		<td>
                         			<!-- 읽었다면 링크 회색글씨 처리-->
                         			<a 
                       				href="<%= request.getContextPath() %>/message/messageView?no=<%= message.getNo()%>" 
									<%= message.getReadDate() != null ? "style=\"color: #858796;\"" : "" %>>
                       				<%= title %>
                       				</a>
                   				</td>
                         		<%-- <td><%= message.getSentDate() %></td> --%>
                         		<td width="200px"><%= sentDateList.get(i) %></td>
                         	</tr>
<% 
	}
 %>
                         </tbody>
 					</table>
	 				<%-- <div id="pageBar"><%= request.getAttribute("pagebar") %></div> --%>
	 			</div>
	 		<form
	 		
	 			id = "delFrm"
				name="messageDelFrm"
				method="POST" 
				action="<%= request.getContextPath() %>/message/receivedMessageDelete" >
				<input type="hidden" id="no" name="no" value="" />
			</form>	
	 		</div>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            
<script src="<%= request.getContextPath() %>/js/empPopup.js"></script>
<script>
    const empPopovers = document.getElementsByClassName("empPopover");
    for (let empPopover of empPopovers) {
        console.log(empPopover.dataset.empName);
        setPopover("<%= request.getContextPath() %>", empPopover.dataset.empNo, empPopover, empPopover.dataset.empName, "<%= loginEmp.getEmpNo() %>", "<%= loginEmp.getEmpName() %>");
 }
</script>           
<script>
//메세지 삭제 제어
function delMessage(){
	// 선택된 갯수 
	var count = $("input:checkbox[name=check]:checked").length;  

	//선택한 쪽지가 1개 이상일때
	if(count > 0){
		if(confirm("삭제하시겠습니까?")){
			
			//check박스 요소들 변수 저장
			var check = document.getElementsByName("check");
			//글번호 저장
			var no = "";
			//check박스 전체순회
			for(let i = 0; i < check.length; i++){
				//해당순번의 체크박스가 체크되어 있으면
				if(check[i].checked){
					//,를 구분자로 값을 연결
					no += check[i].value + ",";
				}
			}
			var inputNo = document.getElementById("no");
			//input value에 글번호 대입
			inputNo.value = no;
			console.log("input value: " + inputNo.value);
			$("form[name=messageDelFrm]").submit();	
			//$(document.messageDelFrm).submit();
			
		}
	//선택한 쪽지가 0개일때
	}else{
		alert("선택한 쪽지가 없습니다.");
	}
}

// 체크박스 제어
$(".checkAll").click(function() {
	if($(".checkAll").is(":checked")) $("input[name=check]").prop("checked", true);
	else $("input[name=check]").prop("checked", false);
});

$("input[name=check]").click(function() {
	var total = $("input[name=check]").length;
	var checked = $("input[name=check]:checked").length;
	
	if(total != checked) $(".checkAll").prop("checked", false);
	else $(".checkAll").prop("checked", true); 
});
</script>
<%-- <%@ include file="/WEB-INF/views/common/footer.jsp"%> --%>