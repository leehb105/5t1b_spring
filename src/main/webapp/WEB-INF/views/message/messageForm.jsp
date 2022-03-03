<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

         <!-- 쪽지합 nav -->
		<%@ include file="/WEB-INF/views/message/common/messageNav.jsp"%>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
	        <form 
	        	action="${pageContext.request.contextPath}/message/messageEnroll.do"
	        	method="POST">
	        	<div class="container">
					<input type="submit" value="보내기" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-top: 20px;"/>
				</div>
			 	<hr class="sidebar-divider my-3">
	            <!-- Main Content -->
	            <div id="content">
		 		
	                <!-- Begin Page Content -->
	                <div class="container">
						<span class="text">받는사람</span>
						<input 
							type="text" class="form-control form-control-sm" 
							name="receiver" 
							id="receiver"
							placeholder="받는 사람을 검색하세요(사번/이름)" />
							
							<!-- 실제 받는사람 전송 입력부 -->
						<input 
							type="text" class="form-control form-control-sm"
							name="receiverList"
							id="receiverList"
							readonly="readonly" 
							value="<%= receiver != null ? receiver  : "" %>"
							style="margin-top: 10px"/>
	                    <textarea 
	                    	name="content" id="textContent" cols="30" rows="10"
	                    	class="form-control"
                    		style="resize: none; margin-top: 10px"></textarea>
                    		<div class="counter" style="float: right;">
								<span id="count">0</span><span>/500</span>
                    		</div>
	                </div>
	                <!-- /.container-fluid -->
					
	            </div>
	            
			</form>
            <!-- End of Main Content -->
<script>
/* $(receiver).change(function() {
	console.log("함수작동");
}); */

/* 받는사람 검색기능 */
$(receiver).autocomplete({
	source: function(request, response) {
		//console.log(request);
		//console.log(response);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/message/empList.do",
			data: request,
			method: "GET",
			success(data){
				//console.log(data);
				if(data == '') return;

				const emp = data.split(",");
				//console.log(emp);
				const arr = $.map(emp, (elem, i) =>{
					const arr2 = elem.split("-");
					//console.log(arr2);
					
					return{
						lable: elem,
						value: elem
					};
					
				});
				//console.log(arr);
				
				response(arr);
			},
			error: console.log
		})
	},
    focus: function(event, selected) {
        const selected2 = document.getElementsByClassName("ui-state-active")[0];
        receiver.value =  selected2.innerText;
        return false;
    } 
});
$("#ui-id-1").click(() => {
     if(receiverList.value)
        receiverList.value += ', ' + receiver.value;
     else receiverList.value = receiver.value;
    receiver.value = '';    
});
// <%-- //답장기능 적용
// window.onload = function(){
// 	console.log(123);
// 	const $receiver = $(receiver);
// 	$receiver.val(<%= senderNo %>);
// }; --%>

/* 쪽지 쓰기 500자 제한 코드 */
$(document).ready(function() {
	$('#textContent').on('keyup', function() {
		console.log($(this).val().length);
		
		$('#count').html($(this).val().length);
		
		if($(this).val().length > 500) {
			alert("500자까지만 입력할 수 있습니다.");
            $(this).val($(this).val().substring(0, 500));
            $('#count').html("500");
        }
	});
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>