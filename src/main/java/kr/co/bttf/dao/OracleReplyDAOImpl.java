package kr.co.bttf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.bttf.controller.Criteria;
import kr.co.bttf.domain.OracleReplyVO;

@Repository
public class OracleReplyDAOImpl implements OracleReplyDAO {

	
	@Inject
	private SqlSession sql;
	private static String namespace = "kr.co.bttf.mappers.replyMapper";
	
	// 댓글 목록
	@Override
	public List<OracleReplyVO> oracleReplyList(@Param ("cri")Criteria cri, @Param ("post_id") int post_id) {

		return sql.selectList(namespace + ".oracleReplyList");
	}
	
	// 댓글 개수
	@Override
	public int oracleCount(int post_id) throws Exception {
		return sql.selectOne(namespace + ".oracleCount", post_id);
	}

	// 댓글 작성
	@Override
	public int oracleReplyWrite(OracleReplyVO vo) throws Exception {
		return sql.insert(namespace + ".oracleReplyWrite", vo);
	}

	// 댓글 수정
	@Override
	public int oracleReplyModify(OracleReplyVO vo) throws Exception {
		return sql.update(namespace + ".oracleReplyModify", vo);

	}

	// 댓글 삭제
	@Override
	public int oracleReplyDelete(OracleReplyVO vo) throws Exception {
		return sql.update(namespace + ".oracleReplyDelete", vo);
	}

//	@Override
//	public int oracleCount(int post_id) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}



}
