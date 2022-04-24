package kr.co.bttf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.CssReplyVO;

public interface CssReplyMapper {
	public int  insert(CssReplyVO vo);// 추가된 게시글의 수가 반환
	
	public CssReplyVO read (Long reply_id);// 댓글을 가져온다
	
	public int delete (Long reply_id); // 댓글을 삭제한다.
	
	public List<CssReplyVO> getListWithPaging(
			@Param("cri") Criteria cri, 
			@Param("post_id") Long post_id); // 댓글 목록(페이징)
	
	public void replyupdate(CssReplyVO replybean);

}
