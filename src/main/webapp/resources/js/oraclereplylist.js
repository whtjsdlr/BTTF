/**
 * oracleReplyList
 */

console.log("댓글 모듈 진행...시작");
var oracleReplyService = (
	
	function(){
		function add(oracleReply, callback){
			alert("댓글 쓰기 처리 진행중...");
			$.ajax({
				url : '/reply/oracleReplyWrite',
				type : 'post',
				data : oracleReply,
				contentType : 'application/json; charset=utf-8',
				success : function(result, status, xhr){
					if(callback){
						callback();			
					}
					alert(result);
					alert(status);					
					
				},
				error : function(xhr, status, er){
					alert(status);
					alert(er);
				}
			
			});			
		}
	} 
	
	
	
	
	
)();

















