<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html lang="ko" class="no-js">
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title>Home4 - Homebrew Community</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <!-- GLOBAL MANDATORY STYLES -->
	<link href="http://fonts.googleapis.com/css?family=Hind:300,400,500,600,700" rel="stylesheet" type="text/css">
	<link href="../../../resources/vendor/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="../../../resources/vendor/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />

	<!-- font-Glyphicon -->
	<!-- <link rel="stylesheet" href="vendor/fontawesome-free-5.15.4-web/fontawesome-free-5.15.4-web/css/fontawesome.css"> -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

	<!-- PAGE LEVEL PLUGIN STYLES -->
	<link href="../../../resources/css/animate.css" rel="stylesheet">
	<link href="../../../resources/vendor/swiper/css/swiper.min.css" rel="stylesheet" type="text/css" />

	<!-- THEME STYLES -->
	<link href="../../../resources/css/layout.css" rel="stylesheet" type="text/css" />

	<!-- Favicon -->
	<link rel="shortcut icon" href="../../../resources/img/favicon/favicon-32x32.png" />

	<!-- custom -->
	<link rel="stylesheet" href="../../../resources/css/custom.css">

	<!-- datatable  -->
	<link href="../../../resources/vendor/DataTables/datatables.css" >

	</head>
	
    <!-- END HEAD -->

    <!-- BODY -->

    <body class="page-on-scroll">
        <!--========== HEADER ==========-->
        <header class="header navbar-fixed-top">
            <!-- Navbar -->
		<%@ include file="../include/header_control.jsp" %>
            <!-- Navbar -->
        </header>
        <!--========== END HEADER ==========-->

        <!--========== SLIDER ==========-->

        <!--========== SLIDER ==========-->

        <!--========== PAGE LAYOUT ==========-->
        <!-- Service -->
        <div class="bg-color-sky-light " data-auto-height="true">
            <div class="content-lg container" style="margin-top : 50px;" >
                <h1 class="pt-4">
                    <i class="fas fa-user-circle title_subject_icon"></i>
                    	관리자 - 신고된 회원
                </h1>
                <!--reportedmember layout -->
                <div class="content-lg container">
                    <h2>
                        <i class="fas fa-user-alt-slash title_subject_icon"></i>신고된 회원
                    </h2>
                    <div class="table-responsive">
                        <table id="foo-table" class="table table-striped" data-order='[[ 4, "desc" ]]'>
                            <thead>
                                <tr>
                                    <th style="width: 20%;">회원이름</th>
                                    <th style="width: 20%;">휴대폰번호</th>
                                    <th style="width: 20%;">가입일자</th>
                                    <th style="width: 20%;">신고일자</th>
                                    <th style="width: 20%;">계정관리</th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:choose>
                                <c:when test="${reportedmember != null and fn:length(reportedmember) > 0 }">
                                    <c:forEach var="userReportedList" items="${reportedmember}">
                                        <tr>
                                            <td>
                                                <p>
                                                    ${userReportedList.user_name }
                                                </p>
                                            </td>
                                            <td>
                                                <p>
                                                    ${userReportedList.user_phone }
                                                </p>
                                            </td>
                                            <td>
                                                <p>
                                                	<fmt:formatDate value="${userReportedList.user_regdate }" pattern="yyyy-MM-dd" />
                                                </p>
                                            </td>
                                            <td>
                                            	<p>
                                                	<fmt:formatDate value="${userReportedList.report_regdate }" pattern="yyyy-MM-dd" />
                                                </p>
                                            </td>
                                            <td class="text-left">
                                            	<a href="/admin/memberundo?user_index=${userReportedList.user_index }" class="btn btn-warning" id="boardreport" type="submit"">신고해제</a>
                                            	<a href="/admin/memberexpell?user_index=${userReportedList.user_index }" class="btn btn-danger" id="boardreport" type="submit" >회원차단</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" class="text-center">신고된 회원이 없습니다.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--end reportedmember layout -->
            </div>
        </div>
        <!--========== FOOTER ==========-->
        <footer class="footer ">

            <!-- Copyright -->
		<%@ include file="../include/footer_control.jsp" %>
                <!-- End Copyright -->
        </footer>
<!--========== END FOOTER ==========-->

    <!-- Back To Top -->
    <a href="javascript:void(0);" class="js-back-to-top back-to-top">Top</a>

   <!-- JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- CORE PLUGINS -->
	<script src="../../../resources/vendor/jquery.min.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/jquery-migrate.min.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

	<!-- PAGE LEVEL PLUGINS -->
	<script src="../../../resources/vendor/jquery.easing.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/jquery.back-to-top.js" type="text/javascript"></script>
	<!--  <script src="vendor/jquery.smooth-scroll.js" type="text/javascript"></script> -->
	<script src="../../../resources/vendor/jquery.wow.min.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/swiper/js/swiper.jquery.min.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/masonry/jquery.masonry.pkgd.min.js" type="text/javascript"></script>
	<script src="../../../resources/vendor/masonry/imagesloaded.pkgd.min.js" type="text/javascript"></script>

	<!-- PAGE LEVEL SCRIPTS -->
	<script src="../../../resources/js/layout.min.js" type="text/javascript"></script>
	<script src="../../../resources/js/components/wow.min.js" type="text/javascript"></script>
	<script src="../../../resources/js/components/swiper.min.js" type="text/javascript"></script>
	<script src="../../../resources/js/components/masonry.min.js" type="text/javascript"></script>
	<script src="../../../resources/js/action.js"></script>
	<!-- F12 ,right click block-->
	<!-- <script src="js/Prevention.js"></script> -->

	<!-- Load d3.js and c3.js -->
	<script src="../../../resources/vendor/c3-0.7.20/c3.js"></script>
	<script src="../../../resources/vendor/c3-0.7.20/docs/js/d3-5.8.2.min.js" charset="utf-8"></script>
		<!-- datatables -->
	<script type="text/javascript" src="../../../resources/vendor/DataTables/datatables.js"></script>
	<script type="text/javascript" src="../../../resources/vendor/DataTables/DataTables-1.11.5/js/dataTables.bootstrap.js"></script>
 	<script type="text/javascript"> 
 	$(document).ready(function() {
 	    $('#foo-table').DataTable( {
 	    	bInfo : false,
 	       	bSortable : false,
 	       	bPaginate : true,
 	        displayLength : 10
 	    } );
 	} );	
 </script> 

	</body>
            <!-- END BODY -->
</html>