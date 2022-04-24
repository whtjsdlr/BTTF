package kr.co.bttf.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.CssReplyVO;
import kr.co.bttf.mapper.CssReplyMapper;

@Service
public class CssReplyServiceImpl implements CssReplyService {
	
	
	@Inject
	private CssReplyMapper mapper;

	@Override
	public int register(CssReplyVO vo) {
		System.out.println("service 들어옴");
		return mapper.insert(vo);
	}

	@Override
	public CssReplyVO get(Long reply_id) {
		return mapper.read(reply_id);
	}

	@Override
	public void replyupdate(CssReplyVO replybean){
		mapper.replyupdate(replybean);
	}

	@Override
	public List<CssReplyVO> getList(Criteria cri, Long post_id) {
		return mapper.getListWithPaging(cri, post_id);
	}
	
	@Override
	public int remove(Long reply_id) {
		return mapper.delete(reply_id);
	}


}
