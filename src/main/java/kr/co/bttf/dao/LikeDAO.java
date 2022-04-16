package kr.co.bttf.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.bttf.domain.LikeVO;
import kr.co.bttf.domain.OracleBoardVO;

@Repository
public class LikeDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "kr.co.bttf.mappers.boardMapper";

	public OracleBoardVO pictureSaveHeart(LikeVO vo) throws Exception {
		// p_board 테이블에 해당 게시물의 heart수를 +1 하기위한 to세팅
		OracleBoardVO bvo = new OracleBoardVO();
		bvo.setPost_id(vo.getPost_id());

		// 해당 게시물의 heart를 +1 한다.
		sql.update(namespace + ".picture_heart_up", bvo);

		// p_heart 테이블에 추가
		int result = sql.insert(namespace + ".picture_heart_save", vo);

		if (result == 1) { // p_heart 테이블에 새로운 좋아요 추가가 성공한다면..
			// 갱신된 하트 갯수를 가져옴
			bvo = sql.selectOne(namespace + ".picture_heart_count", bvo);
		}
		return bvo;
	}

	public OracleBoardVO pictureRemoveHeart(LikeVO vo) throws Exception {
		// p_board 테이블에 해당 게시물의 heart수를 -1 하기위한 to세팅
		OracleBoardVO bvo = new OracleBoardVO();
		bvo.setPost_id(vo.getPost_id());

		// 해당 게시물의 heart를 -1 한다.
		sql.update(namespace + ".picture_heart_down", bvo);

		// p_heart 테이블에서 삭제
		int result = sql.delete(namespace + ".picture_heart_remove", vo);

		if (result == 1) { // p_heart 테이블에 좋아요 삭제가 성공한다면..
			// 갱신된 하트 갯수를 가져옴
			bvo = sql.selectOne(namespace + ".picture_heart_count", bvo);
		}
		return bvo;
	}
}
