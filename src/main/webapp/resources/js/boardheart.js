
// 사용자가 입력한 이름 변수에 담아주기
var user_index = document.getElementById("user_index").value;
var post_id = document.getElementById("post_id").value;

var btn_like = document.getElementById("btn_like");
 btn_like.onclick = function(){ changeHeart(); }
 


 // 좋아요 버튼 눌렀을 때
  function changeHeart(){ 
  
  	
  	if(user_index == null || user_index.length==0){
  		
  		var msg = confirm('로그인이 필요합니다. 로그인 하시겠습니까?');
  		
  		if(msg) {
			location.href= "/member/signin";
  		} else{
  		
  		return;
  		
  		}
  	}
  	
     $.ajax({
            type : "POST",  
            url : "/board/clickRecommend",       
            dataType : "json",   
            data : "post_id="+post_id+"&user_index="+user_index,
            error : function(){
                Rnd.alert("통신 에러","error","확인",function(){});
            },
            success : function(data) {
                if(data.resultCode == -1 || data.resultCode == null){
                    Rnd.alert("좋아요 오류","error","확인",function(){});
                }
                else{
                    if(data.recommend_check == 1){
                        $("#btn_like").attr("src","../../../resources/img/heart-fill.png");
                        $("#post_rec").empty();
                        $("#post_rec").append(data.post_rec);
                    }
                    else if (data.recommend_check == 0){
                        $("#btn_like").attr("src","../../../resources/img/heart.png");
                        $("#post_rec").empty();
                        $("#post_rec").append(data.post_rec);
                        
                    }
                }
            }
        });
 }