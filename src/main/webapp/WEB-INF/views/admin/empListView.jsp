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
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">사원 목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>사번</th>
                                            <th>이름</th>
                                            <th>부서</th>
                                            <th>직급</th>
                                            <th>생년월일</th>
                                            <th>이메일</th>
                                            <th>핸드폰</th>
                                            <th>수정</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${list}" var="emp" varStatus="status">
											<tr>
												<td>${emp.empNo}</td>
												<td>
												<!-- <a href="${pageContext.request.contextPath}/emp/empInfoView?empNo=${empNo}"> -->
													${emp.empName}</td>
												</a>
												<td>
                                                    <select name="deptCode" id="deptCode">
                                                        <option value="FM" ${emp.deptName eq '관리부' ? 'selected' : ''}>관리부</option>
                                                        <option value="SL" ${emp.deptName eq '영업부' ? 'selected' : ''}>영업부</option>
                                                        <option value="DV" ${emp.deptName eq '개발부' ? 'selected' : ''}>개발부</option>
                                                        <option value="HR" ${emp.deptName eq '인사부' ? 'selected' : ''}>인사부</option>
                                                        <option value="GA" ${emp.deptName eq '총무부' ? 'selected' : ''}>총무부</option>
                                                    </select>
                                                </td>
												<td>
                                                    <select name="jobCode" id="jobCode">
                                                        <option value="J1" ${emp.jobName eq '사장' ? 'selected' : ''}>사장</option>
                                                        <option value="J2" ${emp.jobName eq '부사장' ? 'selected' : ''}>부사장</option>
                                                        <option value="J3" ${emp.jobName eq '부장' ? 'selected' : ''}>부장</option>
                                                        <option value="J4" ${emp.jobName eq '차장' ? 'selected' : ''}>차장</option>
                                                        <option value="J5" ${emp.jobName eq '과장' ? 'selected' : ''}>과장</option>
                                                        <option value="J6" ${emp.jobName eq '대리' ? 'selected' : ''}>대리</option>
                                                        <option value="J7" ${emp.jobName eq '사원' ? 'selected' : ''}>사원</option>
                                                        <option value="J8" ${emp.jobName eq '인턴' ? 'selected' : ''}>인턴</option>
                                                    </select>
                                                </td>
												<td><fmt:formatDate value="${emp.birthdate}" pattern="yyyy-MM-dd"/></td>
												<td>${emp.email}</td>
												<td>${emp.phone}</td>
                                                <td style="text-align: center;">
                                                    <button type="button" id="${emp.empNo}" class="btn btn-primary" onclick="updateInform(this);">수정</button>
                                                </td>
											</tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
    <form:form 
        id="updateFrm"
        action="${pageContext.request.contextPath}/admin/empCodeUpdate.do" 
        method="post">
        <input type="hidden" id="empNo" name="empNo" value="">
        <input type="hidden" id="updateDeptCode" name="updateDeptCode" value="">
        <input type="hidden" id="updateJobCode" name="updateJobCode" value="">
    </form:form>


    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/demo/datatables-demo.js"></script>

<script>

// $('.updateBtn').click( function() {
//     console.log('press');


// });
function updateInform(e){
    let $jobCode = $(e).parent().siblings().find('#deptCode'); //부서코드
    let $deptCode = $(e).parent().siblings().find('#jobCode'); //직급코드

    console.log($jobCode.val());
    console.log($deptCode.val());

    let empNo = document.getElementById('empNo');
    let updateDeptCode = document.getElementById('updateDeptCode');
    let updateJobCode = document.getElementById('updateJobCode');
    empNo.value = e.id;
    updateDeptCode.value = $jobCode.val();
    updateJobCode.value = $deptCode.val();

    $('#updateFrm').submit();
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
