package kr.co.bttf.service;

import java.util.List;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.OracleReplyVO;

public interface OracleReplyService {
	public int register(OracleReplyVO vo);

	public OracleReplyVO get(Long reply_id);

	public int remove(Long reply_id);

	public List<OracleReplyVO> getList(Criteria cri, Long bno);

	public void replyupdate(OracleReplyVO replybean);

}
