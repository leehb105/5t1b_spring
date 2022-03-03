<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<%= request.getContextPath() %>/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

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
                                        </tr>
                                    </thead>
                                    <!--  
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                    </tfoot>
                                    -->
                                    <tbody>
                                    <%
                                    List<Emp> list = (List<Emp>) request.getAttribute("list");
                                    for(Emp emp : list) {
                                    %>
											<tr>
												<td><%= emp.getEmpNo() %></td>
												<td>
												<a href="<%= request.getContextPath() %>/emp/empInfoView?empNo=<%= emp.getEmpNo() %>">
													<%= emp.getEmpName() %></td>
												</a>
												<td><%= emp.getDeptCode() %></td>
												<td><%= emp.getJobCode() %></td>
												<td><%= emp.getBirthdate() %></td>
												<td><%= emp.getEmail() %></td>
												<td><%= emp.getPhone() %></td>
											</tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<%= request.getContextPath() %>/resources/vendor/jquery/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%= request.getContextPath() %>/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%= request.getContextPath() %>/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="<%= request.getContextPath() %>/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="<%= request.getContextPath() %>/resources/js/demo/datatables-demo.js"></script>

</body>

</html>
