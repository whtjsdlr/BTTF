package kr.co.bttf.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.bttf.domain.LikeVO;
import kr.co.bttf.domain.OracleBoardVO;

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
    public int getBoardLike(LikeVO lvo) throws Exception {
        return sql.selectOne(namespace +".getBoardLike",lvo);
    }

    @Override
    public void insertBoardLike(LikeVO lvo) throws Exception {
    	sql.insert(namespace +".createBoardLike",lvo);
    }

    @Override
    public void deleteBoardLike(LikeVO lvo) throws Exception {
    	sql.delete(namespace +".deleteBoardLike",lvo);
    }

    @Override
    public void updateBoardLike(int post_id) throws Exception {
    	sql.update(namespace +".updateBoardLike",post_id);
    }
}
