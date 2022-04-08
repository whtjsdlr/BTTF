package kr.co.bttf.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.bttf.dao.JsBoardDAO;
import kr.co.bttf.domain.JsBoardVO;

@Service
public class JsBoardServiceImpl implements JsBoardService {

	@Inject
	private JsBoardDAO dao;

	// 게시글 목록
	@Override
	public List<JsBoardVO> jsList() throws Exception {
		return dao.jsList();
	}

	// 게시글 작성
	@Override
	public void jsWrite(JsBoardVO vo) throws Exception {
		dao.jsWrite(vo);
	}

	// 게시글 상세
	@Override
	public JsBoardVO jsView(int post_id) throws Exception {
		return dao.jsView(post_id);
	}

	// 게시글 조회수
	@Override
	public int jsvcnt(int post_id) throws Exception {
		return dao.jsvcnt(post_id);
	}

	// 게시글 수정
	@Override
	public void jsModify(JsBoardVO vo) throws Exception {
		dao.jsModify(vo);
	}

	// 게시글 삭제
	@Override
	public void jsDelete(int post_id) throws Exception {
		dao.jsDelete(post_id);
	}
	
	// 게시글 신고(가용성 카테고리 변경)
	@Override
	public void jscategory2(int post_id) throws Exception {
		dao.jscategory2(post_id);
		
	}

	// 게시글 북마크 유무 확인
	@Override
	public int jsbookmarklist(HashMap<String, Integer> postid_useridx) throws Exception {
		
		return dao.jsbookmarklist(postid_useridx);
		
	}

	// 게시글 북마크 설정
	@Override
	public void jsbookmark(HashMap<String, Integer> postid_useridx) throws Exception {

		dao.jsbookmark(postid_useridx);
	}

}