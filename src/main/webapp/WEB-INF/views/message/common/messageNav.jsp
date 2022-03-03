<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

			<div class="my-2"></div>
			
            <!-- 쪽지보내기 버튼 -->
            <a class="btn btn-light btn-icon-split" href="<%=request.getContextPath()%>/message/messageForm">            
                <span class="text"><i class="fas fa-envelope fa-fw"></i>쪽지쓰기</span>
               </a>
           	<div class="my-2"></div>
            

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

     
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item" >
                <a class="nav-link" id="hover1" href="<%= request.getContextPath() %>/message/messageList" >
                    <i class="fa fa-envelope-open"></i>
                    <span>받은쪽지함</span>
                    <strong><span id="receivedCount"></span></strong>
                    
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" id="hover2" href="<%= request.getContextPath() %>/message/sentMessageList" >
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
	var receivedCount = document.getElementById("receivedCount");
    $.ajax({
		url: "<%= request.getContextPath() %>/message/messageLoadCount.do",
		method: "GET",
		success(data){

			//span태그에 count데이터 삽입
			receivedCount.innerText = "[" + data + "]";

		},
		error: console.log

	})
//});
$( document ).ready(function(){
	//console.log(document.location.href);
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