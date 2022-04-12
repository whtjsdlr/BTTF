/**
 * 
 */


	function getReplyList(getReplypage){
		$.getJSON ("/reply/oracleReplyList/" + post_id + "/" + page, function(oracleReply){
			
			$("#getReplyList").html('');
			var html ='<div class="d-flex mt-4">';
			$(oracleReply).each(function(){
				html += '<div class="flex-shrink-0 mr-4 col-sm-offset-1">';
				html +=		'<div class="ms-3" style="width : 100%;">';
				html +=		 	'<div class="fw-bold">';
				html +=				'<h3>${row.user_nickname}</h3>';
				html +=				'<div style=" clear: both; float: right; position: relative; top: 0; left: 4px;">';
				html +=					'<a href="/reply/oracleReplyModify" class="btn btn-info btn-sm">수정</a>';
				html +=					'<a href="/reply/oracleReplyDelete" class="btn btn-danger btn-sm">삭제</a>';
				html +=				'</div>';
				html +=			'</div>';
				html +=			'<p>${row.reply_contents }</p>';
				html +=		'</div>';
				
			});
			
			html +=' </div>';
			$("#getReplyList").append(html);
			});
	}
	
//	
//	<div class="d-flex mt-4">
//        <div class="flex-shrink-0 mr-4 col-sm-offset-1">
//		<!--<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /> -->
//        </div>
//        <div class="ms-3" style="width : 100%;">
//            <div class="fw-bold">
//            	<h3>${row.user_nickname}</h3>
//            	<div style=" clear: both; float: right; position: relative; top: 0; left: 4px;">
//                	<a href="/reply/oracleReplyModify" class="btn btn-info btn-sm">수정</a>
//                	<a href="/reply/oracleReplyDelete" class="btn btn-danger btn-sm">삭제</a>
//            	</div>
//            </div>
//            <p>${row.reply_contents }</p>
//        </div>
//    </div>