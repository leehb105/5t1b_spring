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
							<c:forEach items="${list}" var="message" varStatus="status">

								<!-- <tr onclick="location.href='${pageContext.request.contextPath}/message/receivedMessageView.do?no=${message.no}'" style="cursor:pointer;"> -->
								<tr>
									<td width="50px;" style="text-align: center;"><input type="checkbox" name="check" value="${message.no}"/></td>
									<!-- 안읽었다면 파란글씨 -->
									<c:if test="${message.readDate ne null}">
										<td width="180px" style="font-weight: bold;">
											<a class="empPopover" 
											style="color: #858796;">
											${message.emp.empName}(${message.emp.deptName})
											</a>
										</td>
										<td>
											<a 
											href="${pageContext.request.contextPath}/message/receivedMessageView.do?no=${message.no}" 
											style="color: #858796;">
											${message.content}
											</a>
										</td>
										<td width="200px">
											<a 
											href="${pageContext.request.contextPath}/message/receivedMessageView.do?no=${message.no}" 
											style="color: #858796;">
												<fmt:formatDate value="${message.sentDate}" pattern="yy-MM-dd [HH:mm]"  />
											</a>
										</td>
										
									</c:if>
									<c:if test="${message.readDate eq null}">
										<td width="180px" style="font-weight: bold;">
											<a class="empPopover" >
											${message.emp.empName}(${message.emp.deptName})
											</a>
										</td>
										<td>
											<a 
											href="${pageContext.request.contextPath}/message/receivedMessageView.do?no=${message.no}">
											${message.content}
											</a>
										</td>
										<td width="200px">
											<a 
											href="${pageContext.request.contextPath}/message/receivedMessageView.do?no=${message.no}">
												<fmt:formatDate value="${message.sentDate}" pattern="yy-MM-dd [HH:mm]"/>
											</a>
										</td>
									</c:if>
								</tr>
							</c:forEach>
                        </tbody>
 					</table>
	 				<!-- <div id="pageBar">< request.getAttribute("pagebar") ></div> -->
	 			</div>

				<!-- Pagination -->
				<div class="container">
					<div style="display: inline-block;">
						<nav class="roberto-pagination">
							<ul class="pagination">
								<c:if test="${page.prev}"> 
									<li class="page-item"><a class="page-link" href="${page.startPage - 1}"> 이전 <i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }">
									<li class="page-item ${page.cri.pageNum == num ? 'active' : ''}"><a class="page-link" href="${num}">${num}</a></li>
								</c:forEach>
								<c:if test="${page.next}">
									<li class="page-item"><a class="page-link" href="${page.endPage + 1}"> 다음 <i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</nav>
						<form id='actionForm' action="${pageContext.request.contextPath}/message/receivedMessageList.do" method="get"> 
							<input type="hidden" name="pageNum" value="${page.cri.pageNum}"> 
							<!-- <input type="hidden" name="amount" value="${page.cri.amount}">  -->
						</form>
					</div>
				</div>




	 		<form:form
	 			id = "delFrm"
				name="messageDelFrm"
				method="POST" 
				action="${pageContext.request.contextPath}/message/receivedMessageDelete.do" >
			</form:form>	
	 		</div>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            
<!-- <script src="${pageContext.request.contextPath}/js/empPopup.js"></script> -->
<!-- <script>
    const empPopovers = document.getElementsByClassName("empPopover");
    for (let empPopover of empPopovers) {
        console.log(empPopover.dataset.empName);
        setPopover("${pageContext.request.contextPath}", empPopover.dataset.empNo, empPopover, empPopover.dataset.empName, "${loginEmp.empNo}", "${loginEmp.empName}");
 }
</script>            -->
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
			//form
			var checkBox = document.getElementById("delFrm");
			
			//글번호 저장
			var no = "";
			//check박스 전체순회
			for(let i = 0; i < check.length; i++){
				//해당순번의 체크박스가 체크되어 있으면
				if(check[i].checked){
					no = check[i].value;
					// create element (input) 
					var input = document.createElement('input'); 
					// set attribute (input) 
					input.setAttribute("type", "hidden"); 
					input.setAttribute("name", "no"); 
					input.setAttribute("value", no); 
					// append input (to form) 
					checkBox.appendChild(input); 
				}
			}
			// var inputNo = document.getElementById("no");
			//input value에 글번호 대입
			// inputNo.value = no;
			// console.log("input value: " + inputNo.value);
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

let actionForm = $('#actionForm'); 
$('.page-item a').on('click', function(e) { e.preventDefault(); 
	//걸어둔 링크로 이동하는 것을 일단 막음 
	actionForm.find('input[name="pageNum"]').val($(this).attr('href')); 
	actionForm.submit(); 
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>