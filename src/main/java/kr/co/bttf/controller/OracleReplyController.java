//package kr.co.bttf.controller;
//
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import jdk.internal.org.jline.utils.Log;
//import kr.co.bttf.domain.MemberVO;
//import kr.co.bttf.domain.OracleBoardVO;
////import kr.co.bttf.domain.OracleReplyVO;
//import kr.co.bttf.domain.ReplyVO;
//import kr.co.bttf.service.MemberService;
//import kr.co.bttf.service.OracleBoardService;
//import kr.co.bttf.service.OracleReplyService;
//import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;
//
//import kr.co.bttf.controller.Criteria;
//@RestController
//@Controller
//@RequestMapping("/reply/*")
//public class OracleReplyController {
//
//	@Inject
//	private MemberService memberService;
//	
////	@Inject
////	private HtmlReplyService htmlService;
//	
////	@Inject
////	private CssReplyService cssService;
//	
////	@Inject
////	private JsReplyService jsService;
//	
////	@Inject
////	private JspReplyService jspService;
//	
////	@Inject
////	private JavaReplyService javaService;
//	
//	@Inject
//	private OracleReplyService OracleReplyService;
//	
//	@Inject
//	private OracleBoardService OracleBoardService;
//	
////	@Inject
////	private SpringReplyService springService;
//	
//	
//	/* --------------------------------
//				06. ORACLE
//	-------------------------------- */
//	
//	// 6-1. 댓글 작성
//	@PostMapping(value = "/oracleReplyWrite", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
//	public ResponseEntity<String> oracleReplyWrite (@RequestBody ReplyVO vo) throws Exception {
//		
//		int insertCount = OracleReplyService.oracleReplyWrite(vo);
//		
//		System.out.println(vo.getPost_id());
//		System.out.println(vo.getUser_nickname());
//		System.out.println(vo.getReply_contents());
//		
//		return insertCount==1
//	                ? new ResponseEntity<String>("success.writer", HttpStatus.OK)
//	                : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	
//	@PostMapping(value = "/oracleReplyList/{post_id}/{page}",
//				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
//	public ResponseEntity<List<ReplyVO>> oracleReplyList (@PathVariable("page") int page, @PathVariable("post_id")int post_id, Criteria cri) throws Exception {
//		
//		
//		return new ResponseEntity<>(OracleReplyService.oracleReplyList(cri,post_id), HttpStatus.OK );
//		
//	}
//	
//	// 6.2 댓글 목록 
////	@RequestMapping(value = "/oracleReplyList", method = RequestMethod.GET)
////	public ModelAndView oracleReplyList(@RequestParam("post_id") int post_id, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session) throws Exception {
////		
////		//페이징 처리
////		int count = OracleReplyService.oracleCount(post_id);
////		ReplyPager replyPager = new ReplyPager(count, curPage);
////		int start = replyPager.getPageBegin();
////		int end = replyPager.getPageEnd();
////		List<OracleReplyVO> list = OracleReplyService.oracleReplyList(post_id, start, end, session);
////		mav.setViewName("/board/oracleview");
////		mav.addObject("list", list);
////		mav.addObject("replyPager", replyPager);
////		return mav;
////		
////	}
////	
////	
//	
//	
//	
//	
//	// 6-3. 댓글 수정
//	@RequestMapping(value = "/oracleReplyModify", method = RequestMethod.POST)
//	public String oracleReplyModify(ReplyVO vo) throws Exception {
//		System.out.println("post reply modify controller");
//		System.out.println(vo.getReply_contents());
//		
//		OracleReplyService.oracleReplyModify(vo);
//		
//		return "redirect:/board/oracleview?post_id=" + vo.getPost_id();	
//		
//	}
//	
//	
//	
//	// 6-3. 댓글 수정(GET)
////	@RequestMapping(value = "/oracle_reply_modify", method = RequestMethod.GET)
////	public String oracleReplyModify(OracleReplyVO vo, HttpServletRequest request, Model model) throws Exception {
////		
////		model.addAttribute("oracleReplyModify", oracleService.oracleReplyList(vo.getReply_id()));
////		
////		return null;
////	}
//	
//	// 6-3-1. 댓글 수정(POST)
////	@RequestMapping(value = "/oracle_reply_modify", method = RequestMethod.POST)
////	public String oracleReplyModifyOK(OracleReplyVO vo, HttpServletRequest request) throws Exception {
////		return null;
////	}
//	
//	// 6-4. 댓글 삭제 // 댓글삭제 경로 수정 확인
//	@RequestMapping(value = "/oracleReplyDelete", method = RequestMethod.GET)
//	public String oracleReplyDelete(@RequestParam("post_id") int post_id, ReplyVO vo, Model model) throws Exception {
//		OracleReplyService.oracleReplyDelete(vo);
//		
//		//List<OracleReplyVO> oraclereplylist = OracleReplyService.oracleReplyList(post_id);
//		//model.addAttribute("oraclereplylist", oraclereplylist);
//		
//	  return "redirect:/board/oraclelist";
//	}
//	
//}
