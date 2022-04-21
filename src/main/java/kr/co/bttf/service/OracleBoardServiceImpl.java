package kr.co.bttf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.bttf.dao.OracleBoardDAO;
import kr.co.bttf.domain.OracleBoardVO;
import kr.co.bttf.domain.HeartVO;

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
	public HeartVO findHeart(int post_id_oracle, int user_index) throws Exception {
		// 2개의 parameter를 보내기 위해 Map 선언 및 Map에 데이터 삽입
		Map<String, Integer> post_useridx = new HashMap<String, Integer>();
		post_useridx.put("e_number", post_id_oracle);
		post_useridx.put("m_number", user_index);
		return dao.findHeart(post_useridx);
	}
	
	public int insertHeart(HeartVO heart) throws Exception {
		// 좋아요가 DB에 저장이 되는것이 없으면 0이 그대로 리턴으로 넘어감
		int result = 0;
		// 좋아요가 이미 있는지 확인하는 코드
		HeartVO find = dao.findHeart(heart);
		
		// find가 null이면 좋아요가 없는 상태이므로 정보 저장
		// find가 null이 아니면 좋아요가 있는 상태이므로 정보 삭제
		if(find==null) {
			// insert의 리턴값은 DB에 성공적으로 insert된 갯수를 보내므로 result가 1이 됨
			result = dao.insertHeart(heart);
		} else {
			dao.deleteHeart(heart);
		}
	    	// 0 or 1이 담겨져서 @Controller에 보냄.
		return result;
	}

}