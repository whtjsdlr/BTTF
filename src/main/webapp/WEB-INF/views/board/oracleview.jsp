<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko" class="no-js" style="height : 100vh;">
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
    <link rel="shortcut icon" href="/resources/img/favicon/favicon-32x32.png" />

    <!-- custom -->
    <link rel="stylesheet" href="../../../resources/css/custom.css">

    <!-- c3 chart -->
    <link href="../../../resources/vendor/c3-0.7.20/c3.css" rel="stylesheet">
    
   
    <style type="text/css">
       .remove{
          display : none;
       }
       
       
       .bg_white_pd{
       		background-color : white;
       		padding-bottom : 200px;
       }
    </style>
  
</head>
<!-- END HEAD -->
<!-- BODY -->
<body class="page-on-scroll ">
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
    <div class="bg-color-sky-light" data-auto-height="true">
        <div class="content-lg container" style="margin-top : 50px;">
            <div class="row row-space-1 margin-b-2">
                <div class="col-sm-12 sm-margin-b-2 bg_white_pd wow fadeInLeft" style="margin-bottom: 20px;">
                
                    <div class="wow fadeInLeft" data-wow-duration=".3" data-wow-delay=".3s">
                        <div class="my_box" data-height="height">
                            <form method="post" id="likeForm">
                                <div class="col-md-6">
									<input type="hidden" name="post_id" value="${oracleview.post_id }">
                                    <p style="color: black; font-size: 2rem; font-weight:bold;">글 제목 : ${oracleview.post_subject }</p>
                                </div>
                                <div class="col-md-2">
                                	<p class="margin-b-50 text-center" >조회수 ${oracleview.post_vcount }</p>
                                </div>
                                <div class="col-md-2">
                                	<p class="margin-b-50 text-center" > 작성자 ${oracleview.user_nickname }</p>
                                	
                                </div>
                                <div class="col-md-2">
                                	<p class="margin-b-50 text-center" > <fmt:formatDate value="${oracleview.post_regdate}" pattern="yyyy-MM-dd HH:mm" /></p>
                                </div>
                                <div>
                                    <pre class="form-control" placeholder="내용을 입력해 주세요." style="height : 650px; resize: none; background-color: #fff;" disabled>${oracleview.post_contents }</pre>
                                </div> 
	                        	<div class="mb-5">
									<c:if test="${member.user_nickname eq oracleview.user_nickname}">
		 		                    	<a href="/board/oracleedit?post_id=${oracleview.post_id }" class="btn btn-primary mt-4" id="list" type="submit">글수정</a>                          
				                		<a href="/board/oracledelete?post_id=${oracleview.post_id }&mypage=" class="btn btn-danger mt-4" id="list" type="submit">글삭제</a>
									</c:if> 
	                        		<a href="/board/oraclelist" class="btn btn-default mt-4" id="edit" type="submit">글 목록</a>
									<c:if test="${member.user_nickname != oracleview.user_nickname && member != null && oracleview.user_nickname != 'admin'}">
										<a href="/board/oraclebookmark?post_id=${oracleview.post_id }&user_index=${member.user_index }" class="btn btn-default mt-4">북마크</a>
										<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#memberreport" data-whatever="@getbootstrap" style="float: right;" >작성자 신고</button>
									</c:if>
                              	 </div>
								 <div>
									<c:choose>
										<%-- 로그인 상태일때 - 하트 클릭 되게 --%>
										<c:when test="${member != null }">
											<c:choose>
												<c:when test="${empty oracleview.recommend_id}">
													<%-- 빈 하트일때 --%>
													<span> 
														<input type="hidden" name="user_index" value="${member.user_index }">
														<input idx="${oracleview.post_id }" type="button" href="javascript:" class="heart-click heart_icon${oracleview.post_id }" name = "emptyheart">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
	                                  							<path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" />
	                                						</svg>
                                						</a>
													</span>
														</c:when>
														<c:otherwise>
													<%-- 꽉찬 하트일때 --%>
													<span> 
														<input idx="${oracleview.post_id }" type="button" href="javascript:" class="heart-click heart_icon${oracleview.post_id }" name="fullheart">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
                                  								<path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z" />
                                							</svg>
                                						</a>
													</span>
												</c:otherwise>
											</c:choose>
										</c:when>
										<%-- 로그인 상태가 아닐때  - 하트클릭 안되게 --%>
										<c:otherwise>
											<span> <a href="javascript:" class="heart-notlogin">
													<svg class="heart3" xmlns="http://www.w3.org/2000/svg"
														width="16" height="16" fill="currentColor"
														class="bi bi-suit-heart" viewBox="0 0 16 16">
                          					<path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" />
                        					</svg>
											</a>
											</span>
										</c:otherwise>
									</c:choose>
									<span id="heart${oracleview.post_id }">${oracleview.post_rec }</span>
								</div>
							</form>
           					<!-- 댓글 작성 -->
							<div class="card" id="result ">
	                            <div class="card-body">
	                                <!-- Comment form-->
	                                <form id="replyForm" name="replyForm" method="post" class="mb-4 d-flex">
	                                	<input type="hidden" id="user_nickname" name="user_nickname" value="${member.user_nickname}">
	                                	<input type="hidden" id="post_id" name="post_id" value="${oracleview.post_id}">
	                                	<textarea id="reply_contents" name="reply_contents" class="form-control mr-5" rows="2" placeholder="댓글을 작성하세요"></textarea>
	                                </form>
									<a id="btnReply" class="btn btn-primary" style="height:44px; line-height:32px;">작성하기</a>
	                               	<!-- Comment with nested comments-->
	                                <div id="getReplyList">
	                                		
	                                </div>
	                                	
<%-- 	                                <c:forEach var="row" items="${oraclereplylist}"> --%>
<!-- 	                                <div class="d-flex mb-4 mt-10"> -->
<!-- 	                                    Parent comment -->
<!-- 	                                    <div class="flex-shrink-0 mr-4"> -->
<!-- <!-- 	                                    	<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /> -->
<!-- 	                                    </div> -->
<!-- 		                                    <div class="ms-3" style="width : 100%;"> -->
<!-- 		                                        <div class="fw-bold"> -->
<!-- 		                                        	<div class="d-flex"> -->
<%-- 					                                    <h3>${row.user_nickname}</h3> --%>
<!-- 					                                    <p style="transform : translate(16% 20%);"> -->
<%-- 					                                    	<fmt:formatDate value="${row.post_regdate}" pattern="yyyy-MM-dd HH:mm" /> --%>
<!-- 					                                    </p>		                                        	 -->
<!-- 		                                        	</div> -->
<!-- 		                                        	<div style=" clear: both; float: right; position: relative; top: 0; left: 4px;"> -->
<!-- 			                                        	<a href="/reply/oracleReplyModify" id="btnUpdate" class="btn btn-info btn-sm">수정</a> -->
<!-- 			                                        	<a href="/reply/oracleReplyDelete" class="btn btn-danger btn-sm">삭제</a> -->
<!-- 		                                        	</div> -->
<!-- 		                                        </div> -->
<%-- 		                                        <p>${row.reply_contents }</p> --%>
		                                       
<!-- 		                                        Child comment 1 -->
<%-- 				                                <c:otherwise> --%>
<!-- 			                                        <div class="d-flex mt-4"> -->
<!-- 			                                            <div class="flex-shrink-0 mr-4 col-sm-offset-1"> -->
<!-- 														<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /> -->
<!-- 			                                            </div> -->
<!-- 			                                            <div class="ms-3" style="width : 100%;"> -->
<!-- 			                                                <div class="fw-bold"> -->
<%-- 					                                        	<h3>${row.user_nickname}</h3> --%>
<!-- 			                                                	<div style=" clear: both; float: right; position: relative; top: 0; left: 4px;"> -->
<!-- 						                                        	<a href="/reply/oracleReplyModify" class="btn btn-info btn-sm">수정</a> -->
<!-- 						                                        	<a href="/reply/oracleReplyDelete" class="btn btn-danger btn-sm">삭제</a> -->
<!-- 					                                        	</div> -->
<!-- 			                                                </div> -->
<%-- 			                                                <p>${row.reply_contents }</p> --%>
<!-- 			                                            </div> -->
<!-- 			                                        </div> -->
<%-- 				                                </c:otherwise> --%>
<!-- 		                                    </div> -->
<!-- 	                                	</div> -->
<%-- 	                                </c:forEach>	                                --%>
	                            </div>
	                        </div>                             
                        </div>
                    </div>
                </div>
                <!--// end row -->
            </div>
            <!--// end row -->
        </div>
    </div>		
    <!-- End join Form -->
    <!--========== END PAGE LAYOUT ==========-->

    <!--========== FOOTER ==========-->
    <footer class="footer fixed_footer">

        <!-- Copyright -->
		<%@ include file="../include/footer_control.jsp" %>
        <!-- End Copyright -->
    </footer>
    <!--========== END FOOTER ==========-->

    <!-- Back To Top -->
    <a href="javascript:void(0);" class="js-back-to-top back-to-top">Top</a>

    <!-- JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
    <!-- CORE PLUGINS -->
<!--     <script src="../../../resources/vendor/jquery.min.js" type="text/javascript"></script> -->
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
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
	
	<!-- Drop Down Menu -->
    <script src="../../../resources/js/action.js"></script>
    
	<!--CKEDITOR -->
    <script src="../../../resources/vendor/ckeditor5-build-classic/translations/ko.js"></script>
	<script src="../../../resources/vendor/ckeditor5-build-classic/ckeditor.js"></script>
	
	
	<!-- REPLY AJAX -->
	<script type="text/javascript" src="../../../resources/js/oraclereplylist.js"></script>
	
	<!-- ALERT SECTION -->
    <script src="../../../resources/js/confirm.js"></script>
	<script>
   		
    </script>
    <script>
        ClassicEditor
            .create( document.querySelector( '#classic' ))
            .catch( error => {
                console.error( error );
    });
    </script>
	<script type="text/javascript">
// 로그인 한 상태에서 하트를 클릭했을 때 (로그인한 상태인 하트의 <a></a> class명: heart-click)
   $(".heart-click").click(function() {
		var likeForm = $("#likeForm");
		var empty =  likeForm.find("input [name='emptyheart']");
		var user_index = likeForm.find("input [name="user_index"]")
		
       // 게시물 번호(no)를 idx로 전달받아 저장합니다.
       let post_id = $(this).attr('idx');
       console.log("heart-click");

       // 빈하트를 눌렀을때
       if($(this).children('svg').attr('class') == "bi bi-suit-heart"){
           console.log("빈하트 클릭" + post_id);

           $.ajax({
               url : '/board/saveHeart',
               type : 'get',
               data : {
            	   post_id : post_id,
               },
               success : function(bvo) {
                   //페이지 새로고침
                   //document.location.reload(true);

                   let heart = bvo.post_rec;

                   // 페이지, 모달창에 하트수 갱신
                   $('#heart'+post_id).text(heart);

                   console.log("하트추가 성공");
               },
               error : function() {
                   alert('서버 에러');
               }
           });
           console.log("꽉찬하트로 바껴라!");

           // 꽉찬하트로 바꾸기
           $(this).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");
           $('.heart_icon'+post_id).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");

       // 꽉찬 하트를 눌렀을 때
       }else if($(this).children('svg').attr('class') == "bi bi-suit-heart-fill"){
           console.log("꽉찬하트 클릭" + post_id);

           $.ajax({
               url : '/board/removeHeart',
               type : 'get',
               data : {
            	   post_id : post_id,
               },
               success : function(bvo) {
                   //페이지 새로고침
                   //document.location.reload(true);

                   let heart = bvo.post_rec;
                   // 페이지, 모달창에 하트수 갱신
                   $('#heart'+post_id).text(heart);

                   console.log("하트삭제 성공");
               },
               error : function() {
                   alert('서버 에러');
               }
           });
           console.log("빈하트로 바껴라!");

           // 빈하트로 바꾸기
           $(this).html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16"><path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" /></svg>');

           $('.heart_icon'+post_id).html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16"><path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" /></svg>');
       }



   });


   // 로그인 안한 상태에서 하트를 클릭하면 로그인해야한다는 알림창이 뜹니다.
   // (로그인한 상태인 하트의 <a></a> class명: heart-notlogin)
   $(".heart-notlogin").unbind('click');
   $(".heart-notlogin ").click(function() {
       alert('로그인 하셔야 하트를 누를수 있습니다!');
   });
   
 //페이지가 뒤로가기 하면 하트버튼과 하트수 갱신이 안된다. 이때 하트를 누르면 디비에 중복으로 값이 들어가진다.
 //방지하기 위해 페이지가 뒤로가기 할때마다 css로 클릭을 막고 새로고침을 통해 갱신된 하트버튼과 하트수가 나오도록 한다.
 $(window).bind("pageshow", function (event) {
    //파이어폭스와 사파리에서는 persisted를 통해서 뒤로가기 감지가 가능하지만 익스와 크롬에서는 불가  ||뒤의 코드를 추가한다. 
    if (event.originalEvent.persisted || (window.performance && window.performance.navigation.type == 2)) {
       console.log('BFCahe로부터 복원됨');
       $(".heart-click").css("pointer-events","none");
       location.reload();//새로고침 
    }
    else {
       console.log('새로 열린 페이지');
    }
 });
   </script>
   
</body>
<!-- END BODY -->
</html>
