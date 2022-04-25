package kr.co.bttf.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.ReplyVO;
import kr.co.bttf.mapper.OracleReplyMapper;
import lombok.Setter;

@Service
public class OracleReplyServiceImpl implements OracleReplyService {
	
	
	@Inject
	private OracleReplyMapper mapper;

	
	
	@Override
	public int register(ReplyVO vo) {
		System.out.println("service 들어옴");
		
		
		int board_category_id = vo.getBoard_category_id();
		int result = 0;
		
		
		switch (board_category_id) {
			case 1:
				result = mapper.insert_html(vo);
				break;
			case 2:
				result = mapper.insert_css(vo);
				break;
			case 3:
				result = mapper.insert_javascript(vo);
				break;
			case 4:
				result = mapper.insert_jsp(vo);
				break;
			case 5:
				result = mapper.insert_java(vo);
				break;
			case 6:
				result = mapper.insert_oracle(vo);
				break;
			case 7:
				result = mapper.insert_spring(vo);
				break;
		}
		
		
		return result;
	}

	@Override
	public ReplyVO get(Long reply_id, int board_category_id) {
		
		int result = 0;
		
		switch (board_category_id) {
			case 1:
				result = mapper.insert_html(vo);
				break;
			case 2:
				result = mapper.insert_css(vo);
				break;
			case 3:
				result = mapper.insert_javascript(vo);
				break;
			case 4:
				result = mapper.insert_jsp(vo);
				break;
			case 5:
				result = mapper.insert_java(vo);
				break;
			case 6:
				result = mapper.insert_oracle(vo);
				break;
			case 7:
				result = mapper.insert_spring(vo);
				break;
		}
		
		
		return result;
		
		return mapper.read(reply_id);
	}

	@Override
	public void replyupdate(ReplyVO replybean){
		mapper.replyupdate(replybean);
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
