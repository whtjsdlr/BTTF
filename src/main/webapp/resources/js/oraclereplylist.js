///**
// * oracleReplyList
// */
// alert("js 파일 들어옴");
// 
//var getReplypage = 1;
////
////getReplyList(getReplypage);
//
//var oracleReplyService = (function() {
//	
//	function add(oracleReply, callback, error) {
//		alert("add 들어옴");
//		$.ajax({
//			url : '/reply/oracleReplyWrite',
//			type : 'post',
//			data : oracleReply,
//			contentType : 'application/json; charset=utf-8',
//			success : function(result, status, xhr) {
//				$("#reply_contents").val('');
//				$("#getReplyList").html("");
//				getReplyList(1);
//				
//				if (callback) {
//					callback(result);
//					
//					console.log(result);
//				}
//			},
//			error : function(xhr, status, er) {
//				if (error) {
//					error(er);
//				}
//			}
//		})
//	}
//	
//	function getReplyList(post_id,getReplypage, callback, error){
//		$.getJSON ("/reply/oracleReplyList/" + post_id + "/" + getReplypage, function(oracleReply){
//			
//			$("#getReplyList").html('');
//			var html ='<div class="d-flex mt-4">';
//			$(oracleReply).each(function(){
//				html += '<div class="flex-shrink-0 mr-4 col-sm-offset-1">';
//				html +=		'<div class="ms-3" style="width : 100%;">';
//				html +=		 	'<div class="fw-bold">';
//				html +=				'<h3>${row.user_nickname}</h3>';
//				html +=				'<div style=" clear: both; float: right; position: relative; top: 0; left: 4px;">';
//				html +=					'<a href="/reply/oracleReplyModify" class="btn btn-info btn-sm">수정</a>';
//				html +=					'<a href="/reply/oracleReplyDelete" class="btn btn-danger btn-sm">삭제</a>';
//				html +=				'</div>';
//				html +=			'</div>';
//				html +=			'<p>${row.reply_contents }</p>';
//				html +=		'</div>';
//				
//			});
//			
//			html +=' </div>';
//			$("#getReplyList").append(html);
//			});
//	}
//	
//	
//	return {
//		add : add,
//		getReplyList : getReplyList,
//		
//	};
//
//})();

console.log("reply module.....")
var replyService = (function(){
	function get(reply_id, callback, error){
		$.get("/reply/" + reply_id + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, error){
			if(error){
				error();
			}
		});
	}
	
	function add(reply, callback){
		console.log("reply.............");
		
		
		$.ajax({
			type: 'post',
			url : '/reply/new',
			data : JSON.stringify(reply),
			contentType : 'application/json; charset=utf-8',
			success: function(result, status, xhr){
				if(callback){
					callback(result);
					
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
			
	}
	function getList(param, callback, error){
		var post_id = param.post_id;
		var page = param.page || 1;
		$.getJSON("/reply/page/" + post_id + "/" + page + ".json", 
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
	}
	
	function update(reply, callback, error){
		console.log("reply_id : " + reply.reply_id);
		$.ajax({
			type : 'patch',
			url : '/reply/' + reply.reply_id,
			data : JSON.stringify(reply),
			contentType : "application/json;charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}, error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function remove(reply_id, callback, error) {
			$.ajax({
				type : 'delete',
				url: "/reply/" + reply_id,
				success : function(deleteResult, status, xhr) {
					if(callback){
						callback(deleteResult);
					}
				},
				error : function(xhr, status, er) {
					if(error){
						error(er);
					}
				}
			});
		};
		
		function displayTime(timeValue){
			var today = new Date();
			var gap = today.getTime() - timeValue;
			var dateObj = new Date(timeValue);
			var str = "";
			
			if(gap < (1000*60*60*24)){
				var hh = dateObj.getHours();
				var mi = dateObj.getMinutes();
				var ss = dateObj.getSeconds();
				
				return [(hh > 9 ? '' : '0') + hh, ':', (mi >9 ? '' : '0') + mi,
							':', (ss > 9 ? '' : '0') + ss].join('');
			} else {
				var yy = dateObj.getFullYear();
				var mm = dateObj.getMonth() + 1; //월은 0베이스
				var dd = dateObj.getDate();
				
				return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
			}
		};
	
	return {add : add,
			getList : getList,
			remove : remove,
			update : update,
			get : get,
			displayTime : displayTime
			};
})();  