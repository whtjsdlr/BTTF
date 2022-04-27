package kr.co.bttf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.bttf.domain.HtmlBoardVO;

public interface HtmlBoardService {
	
	// 게시글 목록
	public List<HtmlBoardVO> htmlList() throws Exception;
	
	// 게시글 작성
	public void htmlWrite(HtmlBoardVO vo) throws Exception;
	
	// 게시글 상세
	public HtmlBoardVO htmlView(int post_id) throws Exception;

	// 게시글 조회수
	public int htmlvcnt(int post_id) throws Exception;
	
	// 게시글 추천확인
	public Map<String, Object> recommendcheck(Map<String, Object> post_useridx);
			
	// 게시글 수정
	public void htmlModify(HtmlBoardVO vo) throws Exception;
	
	// 게시글 삭제
	public void htmlDelete(int post_id) throws Exception;

	// 게시글 신고(가용성 카테고리 변경)
	public void htmlcategory2(int post_id) throws Exception;

	// 게시글 북마크 유무 확인
	public int htmlbookmarklist(HashMap<String, Integer> postid_useridx) throws Exception;
	
	// 게시글 북마크 설정
	public void htmlbookmark(HashMap<String, Integer> postid_useridx) throws Exception;

	// 추천 테이블 인서트
	public void insertRecBtn(Map<String, Object> post_useridx) throws Exception;

	// 게시글 추천수 +1 업데이트
	public void updateRecCntPlus(Map<String, Object> post_useridx) throws Exception;

	// 추천 테이블의 recommend_check 업데이트
	public void updateRecCheck(Map<String, Object> post_useridx) throws Exception;
	
	// 게시글 추천수 -1 업데이트
	public void updateRecCntMinus(Map<String, Object> post_useridx) throws Exception ;

	// 게시글 추천수 조회
	public int getRecCnt(Map<String, Object> post_useridx) throws Exception;
}
