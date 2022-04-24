package kr.co.bttf.service;

import java.util.List;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.CssReplyVO;

public interface CssReplyService {
	public int register(CssReplyVO vo);

	public CssReplyVO get(Long reply_id);

	public int remove(Long reply_id);

	public List<CssReplyVO> getList(Criteria cri, Long bno);

	public void replyupdate(CssReplyVO replybean);

}
