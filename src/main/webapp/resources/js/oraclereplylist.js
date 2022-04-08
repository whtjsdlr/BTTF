/**
 * oracleReplyList
 */
 alert("js 파일 들어옴");
 
var getReplypage = 1;
//
//getReplyList(getReplypage);

var oracleReplyService = (function() {
	
	function add(oracleReply, callback, error) {
		alert("add 들어옴");
		$.ajax({
			url : '/reply/oracleReplyWrite',
			type : 'post',
			data : oracleReply,
			contentType : 'application/json; charset=utf-8',
			success : function(result, status, xhr) {
				$("#reply_contents").val('');
				$("#getReplyList").html("");
				getReplyList(1);
				
				if (callback) {
					callback(result);
					
					console.log(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	function getReplyList(post_id,getReplypage, callback, error){
		$.getJSON ("/reply/oracleReplyList/" + post_id + "/" + getReplypage, function(oracleReply){
			
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
	
	
	return {
		add : add,
		getReplyList : getReplyList,
		
	};

})();
