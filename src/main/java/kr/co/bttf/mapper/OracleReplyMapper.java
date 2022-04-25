package kr.co.bttf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;

public interface OracleReplyMapper {
	
	// 추가된 게시글의 수가 반환
	public int  insert_html(ReplyVO vo);
	
	public int  insert_css(ReplyVO vo);
	
	public int  insert_javascript(ReplyVO vo);
	
	public int  insert_jsp(ReplyVO vo);
	
	public int  insert_java(ReplyVO vo);
	
	public int  insert_oracle(ReplyVO vo);
	
	public int  insert_spring(ReplyVO vo);
	
	
	//
	public ReplyVO read (Long reply_id);// 댓글을 가져온다
	
	public int delete (Long reply_id); // 댓글을 삭제한다.
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri, 
			@Param("post_id") Long post_id); // 댓글 목록(페이징)
	
	public void replyupdate(ReplyVO replybean);

}
