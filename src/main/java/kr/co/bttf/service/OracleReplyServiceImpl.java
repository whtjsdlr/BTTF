package kr.co.bttf.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.dao.OracleReplyDAO;
import kr.co.bttf.domain.MemberVO;
import kr.co.bttf.domain.OracleReplyVO;

@Service
public class OracleReplyServiceImpl implements OracleReplyService {
	
	@Inject
	private OracleReplyDAO dao;

	// 댓글 목록
	@Override
	public List<OracleReplyVO> oracleReplyList(Criteria cri, int post_id) throws Exception {
//		List<OracleReplyVO> oracleReplyList =
		return  dao.oracleReplyList(cri, post_id);
	}

	// 댓글 작성
	@Override
	public int oracleReplyWrite(OracleReplyVO vo) throws Exception {
		
		return dao.oracleReplyWrite(vo);
	}

	// 댓글 수정
	@Override
	public int oracleReplyModify(OracleReplyVO vo) throws Exception {
		return dao.oracleReplyModify(vo);
	}

	// 댓글 삭제
	@Override
	public int oracleReplyDelete(OracleReplyVO vo) throws Exception {
		return dao.oracleReplyDelete(vo);
		
	}


	@Override
	public int oracleCount(int post_id) throws Exception {
		return dao.oracleCount(post_id);
	}

}
