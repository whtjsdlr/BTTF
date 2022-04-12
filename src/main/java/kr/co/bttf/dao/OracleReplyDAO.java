//package kr.co.bttf.dao;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.ibatis.annotations.Param;
//
//import kr.co.bttf.controller.Criteria;
////import kr.co.bttf.domain.OracleReplyVO;
//import kr.co.bttf.domain.ReplyVO;
//
//public interface OracleReplyDAO {
//
//
//	// 댓글 작성
//	public int oracleReplyWrite(ReplyVO vo) throws Exception;
//
//	// 댓글 수정
//	public int oracleReplyModify(ReplyVO vo) throws Exception;
//
//	// 댓글 삭제
//	public int oracleReplyDelete(ReplyVO vo) throws Exception;
//
//	// 댓글 목록
//	public  List<ReplyVO> oracleReplyList(@Param("cri") Criteria cri,@Param("post_id") int post_id) throws Exception;
//
//	// 댓글 갯수
//	public int oracleCount(int post_id) throws Exception;
//
////	public void oracleReplyDelete(int reply_id, int post_id);
//	
//}
