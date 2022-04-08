package kr.co.bttf.service;

import java.util.List;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;

public interface ReplyService {
	public int register (ReplyVO vo);
	public ReplyVO get (Integer reply_id);
	public int modify (ReplyVO vo);
	public int remove (Integer reply_id);
	public List<ReplyVO> getList (Criteria cri, Integer post_id);
	
}
