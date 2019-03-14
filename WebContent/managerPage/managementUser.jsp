<%@page import="java.util.regex.Pattern"%>
<%@ page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
     <title>SharePot</title>

        <!-- Bootstrap -->
        <link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--side menu plugin-->
        <link href="./plugins/hoe-nav/hoe.css" rel="stylesheet">
        <!-- icons-->
        <link href="./plugins/ionicons/css/ionicons.min.css" rel="stylesheet">
        <link href="./plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- dataTables -->
        <link href="./plugins/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
          <link href="./plugins/datatables/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
        <!--template custom css file-->
        <link href="./css/style.css" rel="stylesheet">

        <script src="./js/modernizr.js"></script>
<script src="http://code.jquery.com/jquery-1.12.1.js"></script> 
        <jsp:include page="managerMenu.jsp"></jsp:include> <!-- 상단메뉴바 -->
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        
        
    </head>
    <body hoe-navigation-type="vertical" hoe-nav-placement="left" theme-layout="wide-layout">

        <!--side navigation start-->
        <div id="hoeapp-wrapper" class="hoe-hide-lpanel" hoe-device-type="desktop">
           
            <div id="hoeapp-container" hoe-color-type="lpanel-bg7" hoe-lpanel-effect="shrink">
                  
        
     
                <!--start main content-->
                <section id="main-content">
                    <div class="space-30"></div>
                    <div class="container">

                        <div class="row">

                            <div class="col-md-12">
                                <div class="panel">
                                    <header class="panel-heading">
                                        <h2 class="panel-title">사용자 관리</h2>
                                    </header>
                                    <div class="panel-body">
                                        <table id="datatable" class="table table-striped dt-responsive nowrap">
                                            <thead>
                                           <tr>
												<th>ID</th>
												<th>이름</th>
												<th>전화번호</th>
												<th>이메일</th>
												<th>생일</th>
												<th>가입일</th>
												<th>이메일체크</th>
											</tr>
                                            </thead>
											
                                     	
										<tbody>
											<c:forEach items="${userlist}" var="userlist">
												<tr>
													<td>${userlist.member_id }</td>
													<td><a href="managementUser.do?member_id=${userlist.member_id }">${userlist.name }</a></td>
													<td>${userlist.tel }</td>
													<td>${userlist.email }</td>
													<td>${userlist.birth_date }</td>
													<td>${userlist.date }</td>
													<td>${userlist.email_check }</td>
												</tr>
											</c:forEach>
											<%-- 		<tr>

												<td><a
													href="managementUserDetail.jsp?member_id=<%=member_id%>"><%=member_id%></a></td>
												<td><%=name%></td>
												<td><%=tel%></td>
												<td><%=email%></td>
												<td><%=birth_date%></td>
												<td><%=date_%></td>
												<td><%=email_check%></td>
											</tr> --%>
										</tbody>
										
                                        </table>
                                    </div><!--panel body end-->
                                </div><!--end panel-->
                            </div><!--col end-->
                        </div><!--row end-->
                    </div><!--container end-->

                   
                
                    <!--footer start-->
                    <div class="footer">
                        <div class="row">
                            <div class="col-sm-12">
                                <span>&copy; Copyright 2016. Assan</span>
                            </div>
                        </div>
                    </div>
                    <!--footer end-->
                </section><!--end main content-->
            </div>
        </div><!--end wrapper-->



        <!--Common plugins-->
        <script src="./plugins/jquery/dist/jquery.min.js"></script>
        <script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="./plugins/hoe-nav/hoe.js"></script>
        <script src="./plugins/pace/pace.min.js"></script>
        <script src="./plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="./js/app.js"></script>

        <!-- Datatables-->
        <script src="./plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="./plugins/datatables/dataTables.responsive.min.js"></script>
        <script>
            $(document).ready(function() {
        $('#datatable').dataTable();
        });
            $(document).ready(function() {
                $('#datatable2').dataTable();
                });
        </script>
    </body>
</html>