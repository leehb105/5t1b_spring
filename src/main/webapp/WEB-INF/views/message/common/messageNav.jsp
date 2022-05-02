<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 인증객체의 principal속성을 pageContext 속성으로 저장 -->
<sec:authentication property="principal" var="loginEmp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

			<div class="my-2"></div>
			
            <!-- 쪽지보내기 버튼 -->
            <a class="btn btn-light btn-icon-split" href="${pageContext.request.contextPath}/message/messageForm.do">            
                <span class="text"><i class="fas fa-envelope fa-fw"></i>쪽지쓰기</span>
               </a>
           	<div class="my-2"></div>
            

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

     
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item" >
                <a class="nav-link" id="hover1" href="${pageContext.request.contextPath}/message/receivedMessageList.do" >
                    <i class="fa fa-envelope-open"></i>
                    <span>받은쪽지함</span>
                    <strong><span id="receivedCount">[]</span></strong>
                    
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" id="hover2" href="${pageContext.request.contextPath}/message/sentMessageList.do" >
                    <i class="fa fa-paper-plane"></i>
                    <span>보낸쪽지함</span>
                </a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

        </ul>
<script>
//$( document ).ready(function() {
	//console.log("test");
	// var receivedCount = document.getElementById("receivedCount");
    // $.ajax({
	// 	url: "${pageContext.request.contextPath}/message/messageLoadCount.do",
	// 	method: "GET",
	// 	success(data){

	// 		//span태그에 count데이터 삽입
	// 		receivedCount.innerText = "[" + data + "]";

	// 	},
	// 	error: console.log

	// })
//});
$( document ).ready(function(){
	//console.log(document.location.href);
	let receivedCount = document.getElementById('receivedCount');
	// receivedCount.innerHTML = '[' + countMessage + ']';
	// console.log(countMessage);

	//현재 페이지 url
	let url = document.location.href;
	if(url.includes("messageList")){
		//$('span').css('font-size', '30px'); 
		$(hover1).css('background-color', '#ABC9E3');
	}
	if(url.includes("sentMessageList")){
		//$('span').css('font-size', '30px'); 
		$(hover2).css('background-color', '#ABC9E3');
	}

	$.ajax({
			url: "${pageContext.request.contextPath}/message/receivedMessageCount.do",
			method: "GET",
			success(data){
				// console.log(data);
				//span태그에 count데이터 삽입
				receivedCount.innerText = '[' + data + ']';
			},
			error:function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}

		})
});



//마우스 오버 이벤트
$(hover1).hover(
	function () {
		const span = `<span style=" position: absolute; transform: translate(380%, 50%); " id="hoverSpan">
						<i class="fa fa-arrow-right" style="float: right; "></i>
			       	</span>`;
		$(hover1).append(span);
	},
	function () {
		$(hoverSpan).remove();
	}
);
$(hover2).hover(
		function () {
			const span = `<span style=" position: absolute; transform: translate(560%, 50%); " id="hoverSpan">
							<i class="fa fa-arrow-right" style="float: right; "></i>
				       	</span>`;
			$(hover2).append(span);
		},
		function () {
			$(hoverSpan).remove();
		}
	);



</script>