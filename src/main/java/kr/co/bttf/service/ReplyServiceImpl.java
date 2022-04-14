package kr.co.bttf.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;
import kr.co.bttf.mapper.ReplyMapper;
import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	
	@Inject
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		System.out.println("service 들어옴");
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long reply_id) {
		return mapper.read(reply_id);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long post_id) {
		return mapper.getListWithPaging(cri, post_id);
	}
	
	@Override
	public int remove(Long reply_id) {
		return mapper.delete(reply_id);
	}


}
