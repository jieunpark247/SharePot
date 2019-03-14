<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="manager.NoticeDTO"%>
<%@ page import="manager.NoticeDAO"%>

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
<link href="./plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- dataTables -->
<link href="./plugins/datatables/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<link href="./plugins/datatables/responsive.bootstrap.min.css"
	rel="stylesheet" type="text/css">
<!--template custom css file-->
<link href="./css/style.css" rel="stylesheet">

<script src="./js/modernizr.js"></script>
<script src="http://code.jquery.com/jquery-1.12.1.js"></script>
<jsp:include page="managerMenu.jsp"></jsp:include>
<!-- ��ܸ޴��� -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
<body hoe-navigation-type="vertical" hoe-nav-placement="left"
	theme-layout="wide-layout">

	<!--side navigation start-->
	<div id="hoeapp-wrapper" class="hoe-hide-lpanel"
		hoe-device-type="desktop">

		<div id="hoeapp-container" hoe-color-type="lpanel-bg7"
			hoe-lpanel-effect="shrink">





         <!--start main content-->
         <section id="main-content">
            <div class="space-30"></div>
            <div class="container">

               <div class="row">

                  <div class="col-sm-12">
                     <div class="panel">
                        <div class="panel-body">
                           <div class="row mail-header">
                              <div class="col-md-6 mail-open-header">
                                 <p>
                                    <strong style="font-family:'�������'; font-size:18px;">${ndto.title} </strong>
                                 </p>
                                 <div>
                                    From:
                                    <code> ${ndto.manager_id}</code>
                                 </div>
                                 <div>��¥ : ${ndto.date}</div>
                                 <p>��ȸ��: ${ndto.hits}</p>


                              </div>
                              <div class="col-md-6">
                                 <div class="pull-right tooltip-show">
                                    
                              <button type="submit"  title=""  data-placement="top" data-toggle="tooltip"
                                 type="button" data-original-title="�����ϱ�"
                                 class="btn btn-sm btn-default" form="fix" value="Submit" >
                                 <form action="./managerPage/managementNoticeUpdateWrite.do?notice_no=${param.notice_no}" method="post" id="fix">
                                    <input name="notice_no" value="<%=request.getParameter("notice_no")%>" type="hidden" /> <i class="fa fa-plus"></i>
                                 </form>
                                 
                              </button>

                              <!-- ���� -->
                              <button  type="submit" title="" data-placement="top" data-toggle="tooltip"
                                 data-original-title="�����ϱ�" class="btn btn-sm btn-default" form="delete" value="Submit" >
                                 <form action="./MngNoticeDeleteAction.do" method="post" id="delete">
                                    <input name="notice_no" value="<%=request.getParameter("notice_no")%>" type="hidden" /> <i class="ion-trash-b"></i>
                                 </form>
                              </button>
                                 </div>
                              </div>
                           </div>
                           <hr>

                           <div class="mail-content margin-b-20">
                              <p><pre>${ndto.content}</pre></p>
                           </div>

                           <div class="mail-body text-right tooltip-demo">
                           
                              
                              <button type="submit"  title=""  data-placement="top" data-toggle="tooltip"
                                 type="button" data-original-title="�����ϱ�"
                                 class="btn btn-sm btn-default" form="fix" value="Submit" >
                                 <form action="./managerPage/managementNoticeUpdateWrite.do?notice_no=${param.notice_no}" method="post" id="fix">
                                    <input name="notice_no" value="<%=request.getParameter("notice_no")%>" type="hidden" /> <i class="fa fa-plus"></i> ����
                                 </form>
                                 
                              </button>

                              <!-- ���� -->

                              <button  type="submit" title="" data-placement="top" data-toggle="tooltip"
                                 data-original-title="�����ϱ�" class="btn btn-sm btn-default" form="delete" value="Submit" >
                                 <form action="./MngNoticeDeleteAction.do" method="post" id="delete">
                                    <input name="notice_no" value="<%=request.getParameter("notice_no")%>" type="hidden" /> <i class="ion-trash-b"></i> ����
                                 </form>
                              </button>

                           </div>

                        </div>
                     </div>

                  </div>
                  <!--col-md-9 end-->

               </div>
               <!--row end-->
            </div>
            <!--container end-->

            <!--footer start-->
            <div class="footer">
               <div class="row">
                  <div class="col-sm-12">
                     <span>&copy; Copyright 2016. Assan</span>
                  </div>
               </div>
            </div>
            <!--footer end-->
         </section>
         <!--end main content-->
      </div>
   </div>
   <!--end wrapper-->



   <!--Common plugins-->
   <script src="./plugins/jquery/dist/jquery.min.js"></script>
   <script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
   <script src="./plugins/hoe-nav/hoe.js"></script>
   <script src="./plugins/pace/pace.min.js"></script>
   <script src="./plugins/slimscroll/jquery.slimscroll.min.js"></script>
   <script src="./js/app.js"></script>
</body>
</html>