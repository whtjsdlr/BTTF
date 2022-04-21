package kr.co.bttf.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.bttf.dao.OracleBoardDAO;
import kr.co.bttf.domain.LikeVO;
import kr.co.bttf.domain.OracleBoardVO;

@Service
public class OracleBoardServiceImpl implements OracleBoardService {

	@Inject
	private OracleBoardDAO dao;

	@Override
	public List<OracleBoardVO> oracleList() throws Exception {
		return dao.oracleList();
	}

	@Override
	public void oracleWrite(OracleBoardVO vo) throws Exception {
		dao.oracleWrite(vo);
	}

	@Override
	public OracleBoardVO oracleView(int post_id) throws Exception {
		return dao.oracleView(post_id);
	}

	@Override
	public int oraclevcnt(int post_id) throws Exception {
		return dao.oraclevcnt(post_id);
	}
	
	@Override
	public void oracleModify(OracleBoardVO vo) throws Exception {
		dao.oracleModify(vo);
	}

	@Override
	public void oracleDelete(int post_id) throws Exception {
		dao.oracleDelete(post_id);
	}
	
	// 게시글 신고(가용성 카테고리 변경)
	@Override
	public void oraclecategory2(int post_id) throws Exception {
		dao.oraclecategory2(post_id);
		
	}
	
	// 게시글 북마크 유무 확인
	@Override
	public int oraclebookmarklist(HashMap<String, Integer> postid_useridx) throws Exception {
		
		return dao.oraclebookmarklist(postid_useridx);
		
	}

	// 게시글 북마크 설정
	@Override
	public void oraclebookmark(HashMap<String, Integer> postid_useridx) throws Exception {

		dao.oraclebookmark(postid_useridx);
	}
	
	@Override
    public void insertBoardLike(LikeVO lvo) throws Exception {
        dao.insertBoardLike(lvo);
        dao.updateBoardLike(lvo.getPost_id());
    }

    @Override
    public void deleteBoardLike(LikeVO lvo) throws Exception {
        dao.deleteBoardLike(lvo);
        dao.updateBoardLike(lvo.getPost_id());
    }
    
    @Override
    public  int getBoardLike(LikeVO lvo) throws Exception {
            return dao.getBoardLike(lvo);
    }
	
}