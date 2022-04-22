package kr.co.bttf.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.OracleReplyVO;
import kr.co.bttf.mapper.OracleReplyMapper;
import lombok.Setter;

@Service
public class OracleReplyServiceImpl implements OracleReplyService {
	
	
	@Inject
	private OracleReplyMapper mapper;

	@Override
	public int register(OracleReplyVO vo) {
		System.out.println("service 들어옴");
		return mapper.insert(vo);
	}

	@Override
	public OracleReplyVO get(Long reply_id) {
		return mapper.read(reply_id);
	}

	@Override
	public void replyupdate(OracleReplyVO replybean){
		mapper.replyupdate(replybean);
	}

	@Override
	public List<OracleReplyVO> getList(Criteria cri, Long post_id) {
		return mapper.getListWithPaging(cri, post_id);
	}
	
	@Override
	public int remove(Long reply_id) {
		return mapper.delete(reply_id);
	}


}
