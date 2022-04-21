package kr.co.bttf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.bttf.domain.OracleBoardVO;
import kr.co.bttf.domain.HeartVO;

@Repository
public class OracleBoardDAOImpl implements OracleBoardDAO {

	@Inject
	private SqlSession sql;
	private static String namespace = "kr.co.bttf.mappers.boardMapper";
	
	@Override
	public List<OracleBoardVO> oracleList() throws Exception {
		return sql.selectList(namespace + ".oraclelist");
	}
	
	@Override
	public void oracleWrite(OracleBoardVO vo) throws Exception {
		sql.insert(namespace + ".oraclewrite", vo);
	}
	
	@Override
	public OracleBoardVO oracleView(int post_id) throws Exception {
		return sql.selectOne(namespace + ".oracleview", post_id);
	}

	@Override
	public int oraclevcnt(int post_id) throws Exception {
		return sql.update(namespace + ".oraclevcnt", post_id);
		
	}
	
	@Override
	public void oracleModify(OracleBoardVO vo) throws Exception {
		sql.update(namespace + ".oraclemodify", vo);
	}
	
	@Override
	public void oracleDelete(int post_id) throws Exception {
		sql.delete(namespace + ".oracledelete", post_id);
	}

	@Override
	public void oraclecategory2(int post_id) throws Exception {
		sql.update(namespace + ".oraclecategory2", post_id);
		
	}
	
	@Override
	public int oraclebookmarklist(HashMap<String, Integer> postid_useridx) throws Exception {

		return sql.selectOne(namespace +".oraclebookmarklist", postid_useridx);
	}

	@Override
	public void oraclebookmark(HashMap<String, Integer> postid_useridx) {
		
		sql.insert(namespace + ".oraclebookmark", postid_useridx);
	}
	
	@Override
	public HeartVO findHeart(Map<String, Integer> post_useridx) throws Exception {
		return sql.selectOne(namespace + ".findHeart", post_useridx);
	}
	
	// 좋아요가 DB에 있는지 확인
	@Override
	public HeartVO findHeart(HeartVO heart) {
		return sql.selectOne(namespace + ".findHeart2",heart);
	}

	// 좋아요 정보(heart_table에 게시글 번호, 회원 번호) 저장
	@Override
	public int insertHeart(HeartVO heart) {
		return sql.insert(namespace + ".insertHeart", heart);
	}

	// 좋아요 삭제
	@Override
	public void deleteHeart(HeartVO heart) {
		sql.delete(namespace + ".deleteHeart",heart);
	}
}
