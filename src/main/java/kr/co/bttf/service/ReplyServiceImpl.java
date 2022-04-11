package kr.co.bttf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;
import kr.co.bttf.mapper.BoardMapper;
import kr.co.bttf.mapper.ReplyMapper;
import lombok.Setter;
	
@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_= {@Autowired})
	private ReplyMapper mapper;
	

	@Setter(onMethod_= {@Autowired})
	private BoardMapper boardMapper;
	
	
	@Override
	public int register(ReplyVO vo) {
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Integer reply_id) {
		
		return mapper.read(reply_id);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int remove(Integer reply_id) {
		
		return mapper.delete(reply_id);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Integer post_id) {
		
		return mapper.getListWithPaging(cri, post_id);
	}

}
