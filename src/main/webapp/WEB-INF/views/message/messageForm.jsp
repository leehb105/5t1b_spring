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
	        <form:form
	        	action="${pageContext.request.contextPath}/message/messageEnroll.do"
	        	method="POST"
				id="messageEnrollFrm">
	        	<div class="container">
					<input type="submit" value="보내기" id="enrollBtn" class="btn btn-primary btn-icon-split" style="padding: 5px; margin-top: 20px;"/>
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
							value="${emp ne null ? emp.empNo += '-' += emp.empName : ''}"
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
	            <!-- 답장버튼으로 사번을 넘겨받은 경우 input사용, 아닐 시 disabled -->
				<input 
					type="hidden" 
					name="${emp ne null ? 'empNo' : ''}"
					value="${emp ne null ? emp.empNo : ''}">
			</form:form>
            <!-- End of Main Content -->
<script>

        
let selected2;
/* 받는사람 검색기능 */
$(receiver).autocomplete({ 
	source: function(request, response) {
		// console.log(request.term);
		// console.log(response);
		$.ajax({
			url: "${pageContext.request.contextPath}/message/emp/" + request.term,
			// data: {
			// 	searchKeyword : request.term
			// },
			method: "GET",
			dataType : 'json', //서버에서 반환되는 데이터 형식을 지정
			contentType:'application/json;charset=utf-8', //서버에 데이터를 보낼 때 사용 content - type 헤더의 값
			success: function(result){
				if(result == '') return;
				// console.log(result);
				
				// const emp = data.split(" ");
				//console.log(emp);
				// const arr = $.map(data, (elem, i) =>{
				// 	// const arr2 = elem.split("-");
				// 	//console.log(arr2);
					
				// 	return{
				// 		lable: elem,
				// 		value: elem
				// 	};
					
				// });
				// console.log(arr);
					
				// response(arr);

				let arr = $.map( result, function( item ) {
					return {
						//label : 화면에 보여지는 텍스트
						//value : 실제 text태그에 들어갈 값
						label: item.empNo + '-' + item.empName,
						value: item.empNo + '-' + item.empName
					}
				});
				// console.log('arr' + arr);
				response(arr);
			},
			error: console.log
		})
	},
    focus: function(event, selected) {
        selected2 = document.getElementsByClassName("ui-state-active")[0];
        receiver.value =  selected2.innerText;

        return false;
    } 
});

let enrollBtn = $('#enrollBtn');
let receiverBox = $('#receiverList');

//넘어온 사번-이름 결과값 선택(클릭)시 하단 input란에 추가해줌
$("#ui-id-1").click(() => {
     if(receiverList.value)
        receiverList.value += ' ' + receiver.value;
     else receiverList.value = receiver.value;
    receiver.value = '';    
	// console.log(receiverList.value);

	let messageEnrollFrm = document.getElementById('messageEnrollFrm');

	let input = document.createElement('input');
	input.setAttribute("type", "hidden"); 
	input.setAttribute("name", "empNo"); 
	// console.log(selected2);

	input.setAttribute("value", selected2.innerText.split('-')[0]); 

	messageEnrollFrm.appendChild(input);

	//수신자 입력 시 보내기 버튼 활성화 검사
	if(receiverBox.val() != '' && $('#textContent').val().length > 0){
		enrollBtn.prop("disabled", false);
	}

});


/* 쪽지 쓰기 500자 제한 코드 */
$(document).ready(function() {
	// let textContent = document.getElementById('textContent');
	
	enrollBtn.prop("disabled", true);	//초기화

	$('#textContent').on('keyup', function() {
		// console.log($(this).val().length);
		
		$('#count').html($(this).val().length);
		
		if($(this).val().length > 500) {
			alert("500자까지만 입력할 수 있습니다.");
            $(this).val($(this).val().substring(0, 500));
            $('#count').html("500");
        }
		//메시지 내용이 없으면 보내기 버튼 비활성화
		if($(this).val().length == 0 || receiverBox.val() == ''){
			enrollBtn.prop("disabled", true);	
		}else{
			enrollBtn.prop("disabled", false);
		}

	});

	

	

});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>