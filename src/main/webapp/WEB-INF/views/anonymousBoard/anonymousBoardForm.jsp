<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

<!-- Bootstrap core JavaScript-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<div class="container">
	<!-- Outer Row -->
	<div class="row justify-content-center">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<div class="p-5">
						<br />
						<div class="form-group">
							<div class="row ">
								<div class="col-7">익명게시판 글쓰기</div>

								<div class="col-2">
									<input type="button" value="임시 저장"
										class="btn btn-primary btn-user btn-block"
										style="font-size: .8rem;" />
								</div>
								<div class="col-3">
									<button class="btn btn-primary btn-user btn-block"
										style="font-size: .8rem;">임시 저장 글 블러오기</button>
								</div>
							</div>
							<br />
							<!-- boardEnrollForm -->
							<form 
								id="boardEnrollForm" 
								class="user" 
								action="<%= request.getContextPath() %>/board/anonymousBoardEnroll" 
								method="POST"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col form-group">
										<input type="text" class="form-control" name="title" id="title" placeholder="제목">									
									</div>
								</div>
								<div class="row">
									<div class="form-group col-12">
										<label for="textContent">내용</label>
										<textarea name="content" id="textContent" cols="30" rows="12"
											placeholder="내용을 입력해주세요." class="form-control"
											style="resize: none;"></textarea>
										<div class="counter" style="float: right;">
											<span id="count">0</span><span>/1000</span>
										</div>
									</div>
								</div>
								<!-- 사원번호 -->
								<input type="hidden" name="empNo" value="<%= loginEmp.getEmpNo() %>"/>

								<!-- 첨부파일 -->
								<span id="createInputFileByButton">
									<div class="form-group">
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<button class="btn btn-primary" type="button" onclick="createInputFile()"
													style="width: 50px;" id="button-addon1">+</button>
											</div>
											<div class="custom-file">
												<input type="file" name="upFile1" class="w-70 custom-file-input" id="inputGroupFile01"
													aria-describedby="button-addon1" style="cursor:pointer;"/>
											    <label class="custom-file-label" for="inputGroupFile01" >클릭해서 파일 추가하기</label>
											</div>
										</div>
									</div>
								</span>

								<br /> <br />
								<div class="form-group">
									<div class="row justify-content-around">
										<div class="col-4">
											<input type="button" value="작성 취소" id="cancelWriting"
												class="btn btn-primary btn-block" onclick="cancel();" />
										</div>
										<div class="col-4">
											<input type="submit" value="작성 완료" id="submitButton"
												class="btn btn-primary btn-block" />
										</div>
									</div>
								</div>

							</form>
							<br /> <br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

// 페이지 로딩시 제목으로 포커스됨
window.onload = () => {
	title.focus();
};

/**
 * boardEnrollForm 유효성 검사
 */
function boardValidate(){
	const $category = $("[name=category]");
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	
	// 제목을 작성하지 않은 경우 폼을 제출할 수 없음.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력해주세요.");
		$title.focus();
		return false;
	}
	
	// 내용을 작성하지 않은 경우 폼을 제출할 수 없음.
	// 아무 문자 또는 개행문자
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력해주세요.");
		$content.focus();
		return false;
	}
	
	return true;
}; 
$("#boardEnrollForm").submit(boardValidate);

// 제목은 33글자 이상 입력 못함
$("#title").keyup(({target}) => {
	const $target = $(target);
	const len = $target.val().length;
	if(len > 33){
		alert("제목은 33글자 이상 작성할 수 없습니다.");
		$("#title").focus();
		$("#submitButton")
		.attr("class", "btn btn-danger btn-block")
		.prop("disabled", true);	
	} else {
		$("#submitButton")
		.attr("class", "btn btn-primary btn-block")
		.prop("disabled", false);	
	}
})

// 1000글자 이상 타이핑시 #textContent & 작성완료 버튼 빨간색으로 변경
$("#textContent").keyup(({target}) => {
	//console.log(target);
	//console.log(target.value);
	const $target = $(target);
	const len = $target.val().length;
	$("#count")
		.html(len)
		.css("color", len > 1000 ? "red" : "gray");
	if(len > 1000) {
		$("#submitButton")
				.attr("class", "btn btn-danger btn-block")
				.prop("disabled", true);		
	} else {
		$("#submitButton")
				.attr("class", "btn btn-primary btn-block")
				.prop("disabled", false);	
	}
});

// 작성 취소 클릭시 실행됨
function cancel(){
	if(confirm(`사이트에서 나가시겠습니다? 
변경사항이 저장되지 않을 수 있습니다.`)){
		location.href="<%= request.getContextPath() %>/board/boardList";
	}
};

// 파일 등록했을 때 input:file에 파일명이 바뀌지 않는 문제 해결
$('input:file').change(function(e){
	console.log(e.target.files[0].name);
	const fileName = e.target.files[0].name;
	$(e.target).next().html(fileName);	
	console.log($(e.target).next());
	
});

// 동적으로 input:file 태그 생성
let count = 2;
function createInputFile(){
	
	if(count <= 5){
		//console.log(count);
		const idValue = "inputGroupFile0" + count;
		const attrValue = "upFile" + count;
		const buttonAddon = "button-addon" + count;
		const inputFile = `
		<div class="form-group" id="createdTag">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<button class="btn btn-danger" type="button" onclick="document.getElementById('createdTag').remove();"
						style="width: 50px;" id=\${buttonAddon}>-</button>
				</div>
				<div class="custom-file">
					<input type="file" class="w-70 custom-file-input" id=\${idValue} name=\${attrValue}
						aria-describedby=\${buttonAddon} style="cursor:pointer;"/>
						 <label class="custom-file-label" for=\${idValue} >클릭해서 파일 추가하기</label>
				</div>
			</div>
		</div>
		`;
		$("#createInputFileByButton").append(inputFile);		
		count++;
		
		// 동적으로 생성된 input:file에는 기존의 이벤트가 적용되지 않아서 한번 더 적용
		$('input:file').change(function(e){
			console.log(e.target.files[0].name);
			const fileName = e.target.files[0].name;
			$(e.target).next().html(fileName);	
			console.log($(e.target).next());
		});
		
		if(count > 5) {
			$("#button-addon1")
				.prop("disabled", true);	
		}
		
		
	}	
};



</script>





