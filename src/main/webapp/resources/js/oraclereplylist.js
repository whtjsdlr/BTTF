/**
 * oracleReplyList
 */


var oracleReplyService = (function() {

	function add(oracleReply, callback, error) {
		alert("add 들어옴");
		$.ajax({
			url : '/reply/oracleReplyWrite',
			type : 'post',
			data : oracleReply,
			contentType : 'application/json; charset=utf-8',
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	return {
		add : add
	};

})();
