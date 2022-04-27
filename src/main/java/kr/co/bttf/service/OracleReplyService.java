package kr.co.bttf.service;

import java.util.List;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;

public interface OracleReplyService {
	public int register(ReplyVO vo);

	public ReplyVO get(Long reply_id);

	public int remove(Long reply_id);

	public List<ReplyVO> getList(Criteria cri, Long bno);

	public void replyupdate(ReplyVO replybean);

}
