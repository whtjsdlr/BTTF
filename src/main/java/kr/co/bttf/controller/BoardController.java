package kr.co.bttf.controller;

import java.io.IOException;
import java.lang.System.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bttf.domain.CssBoardVO;
import kr.co.bttf.domain.HtmlBoardVO;
import kr.co.bttf.domain.JavaBoardVO;
import kr.co.bttf.domain.JsBoardVO;
import kr.co.bttf.domain.JspBoardVO;
import kr.co.bttf.domain.MemberVO;
import kr.co.bttf.domain.OracleBoardVO;
import kr.co.bttf.domain.OracleReplyVO;
import kr.co.bttf.domain.SpringBoardVO;
import kr.co.bttf.service.CssBoardService;
import kr.co.bttf.service.HtmlBoardService;
import kr.co.bttf.service.JavaBoardService;
import kr.co.bttf.service.JsBoardService;
import kr.co.bttf.service.JspBoardService;
import kr.co.bttf.service.MemberService;
import kr.co.bttf.service.OracleBoardService;
//import kr.co.bttf.service.OracleReplyService;
import kr.co.bttf.service.SpringBoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	MemberService memberService;
	
	@Inject
	private HtmlBoardService htmlService;
	
	@Inject
	private CssBoardService cssService;
	
	@Inject
	private JsBoardService jsService;
	
	@Inject
	private JspBoardService jspService;
	
	@Inject
	private JavaBoardService javaService;
	
	@Inject
	private OracleBoardService oracleService;
	
//	@Inject
//	private OracleReplyService oracleReplyService;
//	
	@Inject
	private SpringBoardService springService;
	
	
	/* --------------------------------
				01. HTML
	-------------------------------- */
	// 1-1 [GET] 게시물 목록
	@RequestMapping(value = "/htmllist", method = RequestMethod.GET)
	public void htmlList(Model model) throws Exception {

		List<HtmlBoardVO> htmllist = null;
		htmllist = htmlService.htmlList();
		model.addAttribute("htmllist", htmllist);
	}

	// 1-2. write페이지이동
	@RequestMapping(value = "/htmlwrite", method = RequestMethod.GET)
	public void htmlWrite() throws Exception {

	}
	
	// 1-2-1. 게시물 작성
	@RequestMapping(value = "/htmlwrite", method = RequestMethod.POST)
	public String htmlWrite(HtmlBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUser_nickname(member.getUser_nickname());
		htmlService.htmlWrite(vo);
	  return "redirect:/board/htmllist";
	}

	// 1-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/htmlview", method = RequestMethod.GET)
	public void htmlView(@RequestParam("post_id") int post_id, Model model) throws Exception {
		
		// 상세보기 시 조회수 갱신
		int htmlvcnt = 0;
		htmlService.htmlvcnt(post_id);
		model.addAttribute("htmlvcnt", htmlvcnt);
		
		HtmlBoardVO vo = htmlService.htmlView(post_id);
		model.addAttribute("htmlview", vo);
	}
	
	// 1-4. 게시물 수정 페이지 이동
	@RequestMapping(value = "/htmlmodify", method = RequestMethod.GET)
	public void htmlModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

		HtmlBoardVO vo = htmlService.htmlView(post_id);
		model.addAttribute("htmlview", vo);
	}
	

	@RequestMapping(value = "/htmlmodify", method = RequestMethod.POST)
	public String htmlmodify(HtmlBoardVO vo) throws Exception {

		htmlService.htmlModify(vo);
		return "redirect:/board/htmlview?post_id=" + vo.getPost_id();
	}

	// 1-5. vo가 없으니 get방식 삭제
	@RequestMapping(value = "/htmldelete", method = RequestMethod.GET)
	public String htmlDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

		String result = "";
		
		htmlService.htmlDelete(post_id);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		int user_index = member.getUser_index();
		String user_nickname = member.getUser_nickname();
		
		// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
		if( mypage.equals("right")) {
			
			result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
		
		// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
		} else {
			
			result = "redirect:/board/htmllist";
		}
		return result;
	}
	
	// 1-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/htmlreport", method = RequestMethod.GET)
	public void htmlmemberreport(@RequestParam List<Integer> checkbox, 
	
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
		for (Integer c : checkbox) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("report_category_id", c);
			map.put("reportee_index", reportee_index);
			map.put("reporter_index", reporter_index);
			map.put("board_category_id", board_category_id);
			map.put("post_id", post_id);
			
			boolean reportSuccess = memberService.reportSuccess(map);	
			
			if(reportSuccess ) {
				memberService.memberreport(map);						
				htmlService.htmlcategory2(post_id);
				memberService.memcategory2(user_index);
				ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
			}else {
				ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
			}
		}
	}	

	
	//1-7. 게시글 북마크 설정
	@RequestMapping (value = "/htmlbookmark", method = RequestMethod.GET)
	public void bookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = htmlService.htmlbookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/htmlview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				htmlService.htmlbookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/htmlview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
	
	/* --------------------------------
	 			02. CSS
	-------------------------------- */
	// 2-1 [GET] 게시물 목록
	@RequestMapping(value = "/csslist", method = RequestMethod.GET)
	public void cssList(Model model) throws Exception {

		List<CssBoardVO> csslist = null;
		csslist = cssService.cssList();
		model.addAttribute("csslist", csslist);
	}

	// 2-2. write페이지이동
	@RequestMapping(value = "/csswrite", method = RequestMethod.GET)
	public void cssWrite() throws Exception {

	}
	
	// 2-2-1. 게시물 작성
	@RequestMapping(value = "/csswrite", method = RequestMethod.POST)
	public String cssWrite(CssBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUser_nickname(member.getUser_nickname());
		cssService.cssWrite(vo);
	  return "redirect:/board/csslist";
	}
	
	

	// 2-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/cssview", method = RequestMethod.GET)
	public void cssView(@RequestParam("post_id") int post_id, Model model, HttpServletRequest req, HttpSession session) throws Exception {
		
		// 상세보기 시 조회수 갱신
		int cssvcnt = 0;
		cssService.cssvcnt(post_id);
		model.addAttribute("cssvcnt", cssvcnt);
		

		// 좋아요 눌렀는지 조회
		HttpSession sessions = req.getSession();  // 현재 세션 정보를 가져옴
			
			// 현재 로그인해있는 user의 정보 가져오기
			MemberVO member = (MemberVO) sessions.getAttribute("member");
			
			//로그인 되어있는 경우에만
			if(member!=null) {
				
				int user_index = member.getUser_index();
				
				Map<String, Object> post_useridx = new HashMap<>();
				
				post_useridx.put("post_id", post_id);
				post_useridx.put("user_index", user_index);
				
			
				 try {
					 	//사용자가 해당 글에 좋아요 누른적이 있는지 확인
					 	Map<String, Object> recommendcheckmap = cssService.recommendcheck(post_useridx);
						
					 	
						if(recommendcheckmap==null) {
							//한번도 누른적이 없을때
							model.addAttribute("recommend_check", 0);
							
						} else {
							// 추천 누른적이 있을 때 (recommend 테이블에 데이터가 있을 때 )
							model.addAttribute("recommend_check", recommendcheckmap.get("recommend_check"));
						}
				     
						
						
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				
			} else {
				
				model.addAttribute("recommend_check", 0);
			}
			
			CssBoardVO vo = cssService.cssView(post_id);
			model.addAttribute("cssview", vo);
		
	}
	
	
	// 좋아요 눌렀을 때 
	@RequestMapping(value ="/clickRecommend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clickLike(@RequestParam Map<String,Object> post_useridx, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception{
				
		int resultCode = 1;
		int recommend_check = 1;
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> resultMap = new HashMap<>();
		
	
		// 현재 로그인해있는 user의 정보 가져오기
		try {
			
			// 추천 눌렀는지 조회하기
			map = cssService.recommendcheck(post_useridx);
			
		
			if(map == null) {
				
				//처음 추천 누른것
				cssService.insertRecBtn(post_useridx); //recommend 테이블에 데이터 인서트
				cssService.updateRecCntPlus(post_useridx); // 게시글의 추천수 테이블 +1
				resultCode = 1;
				
			} else if (Integer.parseInt(map.get("recommend_check").toString())==0) {
				
				//추천이 처음은 아니고 취소했다가 다시 눌렀을때
				post_useridx.put("recommend_check", recommend_check);
				cssService.updateRecCheck(post_useridx); // 게시글의 추천수 테이블 +1
				cssService.updateRecCntPlus(post_useridx); // 게시글의 추천수 테이블 +1
				resultCode = 1; 
			} else {
				//추천 취소한거 recommend_check=0, 빈하트 되야됨
				
				recommend_check = 0;
				post_useridx.put("recommend_check",recommend_check);
				cssService.updateRecCheck(post_useridx); //  recommend 테이블에 recommend_check=0 으로 업데이트
				cssService.updateRecCntMinus(post_useridx); // 게시글의 추천수 테이블 -1
				resultCode = 0;
				
			}
			
			
			
			
//			if(recCheck == 1) {
//				//추천 취소한거 recommend_check=0, 빈하트 되야됨
//				
//				recommend_check = 0;
//				post_useridx.put("recommend_check",recommend_check);
//				cssService.updateRecCheck(post_useridx); //  recommend 테이블에 recommend_check=0 으로 업데이트
//				cssService.updateRecCntMinus(post_useridx); // 게시글의 추천수 테이블 -1
//				resultCode = 0;
//				
//			} else {
//				// 추천을 누르는 경우 recommend_check=1, 꽉 찬 하트 되야됨
//				
//					if(map == null) {
//						//처음 추천 누른것
//						
//						cssService.insertRecBtn(post_useridx); //recommend 테이블에 데이터 인서트
//						
//					} else if (recCheck == 0) {
//						//추천이 처음은 아니고 취소했다가 다시 눌렀을때
//						post_useridx.put("recommend_check", recommend_check);
//						cssService.updateRecCheck(post_useridx); //recommend 테이블에 recommend_check=1 으로 업데이트
//						
//					}
//					
//					cssService.updateRecCntPlus(post_useridx); // 게시글의 추천수 테이블 +1
//					resultCode = 1;
//				
//			}
			
			
			
			// 해당 게시글 테이블의 RecCnt칼럼 update가 끝난후 RecCnt값 가져옴
			int post_rec = cssService.getRecCnt(post_useridx); 
						
			resultMap.put("post_rec", post_rec);
			resultMap.put("recommend_check", recommend_check);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultCode = -1;
		}
		
		resultMap.put("resultCode", resultCode);
		//resultCode가 1이면 빨간하트 0이면 빈하트
		return resultMap;
	}

	
	
	
	// 2-4. 게시물 수정 페이지 이동
	@RequestMapping(value = "/cssmodify", method = RequestMethod.GET)
	public void cssModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

		CssBoardVO vo = cssService.cssView(post_id);
		model.addAttribute("cssview", vo);
	}
	

	@RequestMapping(value = "/cssedit", method = RequestMethod.POST)
	public String cssedit(CssBoardVO vo) throws Exception {

		cssService.cssEdit(vo);
		return "redirect:/board/cssview?post_id=" + vo.getPost_id();
	}

	// 2-5. vo가 없으니 get방식 삭제
	@RequestMapping(value = "/cssdelete", method = RequestMethod.GET)
	public String cssDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

		String result = "";
		
		cssService.cssDelete(post_id);
		
		HttpSession session = req.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		
		int user_index = member.getUser_index();
		String user_nickname = member.getUser_nickname();
		
		
		// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
		if( mypage.equals("right")) {
			
			result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
		
		// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
		} else {
			
			result = "redirect:/board/csslist";
		}
		return result;
	}
	
	// 2-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/cssreport", method = RequestMethod.GET)
	public void cssmemberreport(@RequestParam List<Integer> checkbox, 
			
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
		for (Integer c : checkbox) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("report_category_id", c);
			map.put("reportee_index", reportee_index);
			map.put("reporter_index", reporter_index);
			map.put("board_category_id", board_category_id);
			map.put("post_id", post_id);
			
			boolean reportSuccess = memberService.reportSuccess(map);	
			
			if(reportSuccess ) {
				memberService.memberreport(map);						
				cssService.csscategory2(post_id);
				memberService.memcategory2(user_index);
				ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
			}else {
				ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
			}
		}
	}
	
	
	//2-7. 게시글 북마크 설정
	@RequestMapping (value = "/cssbookmark", method = RequestMethod.GET)
	public void cssbookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = cssService.cssbookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/cssview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				cssService.cssbookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/cssview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
	

	
	/* --------------------------------
			03. JAVASCRIPT
	-------------------------------- */
	// 3-1 [GET] 게시물 목록
		@RequestMapping(value = "/jslist", method = RequestMethod.GET)
		public void jsList(Model model) throws Exception {

			List<JsBoardVO> jslist = null;
			jslist = jsService.jsList();
			model.addAttribute("jslist", jslist);
		}

		// 3-2. write페이지이동
		@RequestMapping(value = "/jswrite", method = RequestMethod.GET)
		public void jsWrite() throws Exception {

		}
		
		// 3-2-1. 게시물 작성
		@RequestMapping(value = "/jswrite", method = RequestMethod.POST)
		public String jsWrite(JsBoardVO vo, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			vo.setUser_nickname(member.getUser_nickname());
			jsService.jsWrite(vo);
		  return "redirect:/board/jslist";
		}
		

		// 3-3. 게시물 상세보기 페이지 이동
		@RequestMapping(value = "/javascriptview", method = RequestMethod.GET)
		public void jsView(@RequestParam("post_id") int post_id, Model model) throws Exception {
			
			// 상세보기 시 조회수 갱신
			int jsvcnt = 0;
			jsService.jsvcnt(post_id);
			model.addAttribute("jsvcnt", jsvcnt);
			
			JsBoardVO vo = jsService.jsView(post_id);
			model.addAttribute("jsview", vo);
		}
		
		// 3-4. 게시물 수정 페이지 이동
		@RequestMapping(value = "/jsmodify", method = RequestMethod.GET)
		public void jsModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

			JsBoardVO vo = jsService.jsView(post_id);
			model.addAttribute("jsview", vo);
		}
		
		@RequestMapping(value = "/jsmodify", method = RequestMethod.POST)
		public String jsModify(JsBoardVO vo) throws Exception {

			jsService.jsModify(vo);
			return "redirect:/board/javascriptview?post_id=" + vo.getPost_id();
		}

		// 3-5. vo가 없으니 get방식 삭제
		@RequestMapping(value = "/javascriptdelete", method = RequestMethod.GET)
		public String jsDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

			String result = "";
			
			jsService.jsDelete(post_id);
			
			HttpSession session = req.getSession();
			
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			
			int user_index = member.getUser_index();
			String user_nickname = member.getUser_nickname();
			
			
			// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
			if( mypage.equals("right")) {
				
				result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
			
			// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
			} else {
				
				result = "redirect:/board/jslist";
			}
			return result;
		}
		
		// 3-6. 게시글 신고(가용성 카테고리 변경)
		@RequestMapping(value = "/jsreport", method = RequestMethod.GET)
		public void jsmemberreport(@RequestParam List<Integer> checkbox, 
				
		@RequestParam("reportee_index") int reportee_index, 
		@RequestParam("reportee_index") int user_index, 
		@RequestParam("reporter_index") int reporter_index,
		@RequestParam("board_category_id") int board_category_id,
		@RequestParam("post_id") int post_id,
		
		HttpServletResponse response) throws Exception{
			for (Integer c : checkbox) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("report_category_id", c);
				map.put("reportee_index", reportee_index);
				map.put("reporter_index", reporter_index);
				map.put("board_category_id", board_category_id);
				map.put("post_id", post_id);
				
				boolean reportSuccess = memberService.reportSuccess(map);	
				
				if(reportSuccess ) {
					memberService.memberreport(map);						
					jsService.jscategory2(post_id);
					memberService.memcategory2(user_index);
					ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
				}else {
					ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
				}
			}
		}
		
		//3-7. 게시글 북마크 설정
		@RequestMapping (value = "/jsbookmark", method = RequestMethod.GET)
		public void jsbookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
			
			HashMap<String, Integer> postid_useridx = new HashMap<>();
					
			postid_useridx.put("post_id", post_id);
			postid_useridx.put("user_index", user_index);
			
			// 게시글 북마크 유무 확인
			int result = jsService.jsbookmarklist(postid_useridx);
			
			try {
				
				if(result==1) {
					
					ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/javascriptview?post_id=" + post_id);
				
				} else {
					
					//게시글 북마크에추가
					jsService.jsbookmark(postid_useridx);
					ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/javascriptview?post_id=" + post_id);
					
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			 

		}
	
	
	/* --------------------------------
				04. JSP
	-------------------------------- */
	// 4-1 [GET] 게시물 목록
	@RequestMapping(value = "/jsplist", method = RequestMethod.GET)
	public void jspList(Model model) throws Exception {

		List<JspBoardVO> jsplist = null;
		jsplist = jspService.jspList();
		model.addAttribute("jsplist", jsplist);
	}

	// 4-2. write페이지이동
	@RequestMapping(value = "/jspwrite", method = RequestMethod.GET)
	public void jspWrite() throws Exception {

	}
	
	// 4-2-1. 게시물 작성
	@RequestMapping(value = "/jspwrite", method = RequestMethod.POST)
	public String jspWrite(JspBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUser_nickname(member.getUser_nickname());
		jspService.jspWrite(vo);
	  return "redirect:/board/jsplist";
	}
	

	// 4-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/jspview", method = RequestMethod.GET)
	public void jspView(@RequestParam("post_id") int post_id, Model model) throws Exception {
		
		// 상세보기 시 조회수 갱신
		int jspvcnt = 0;
		jspService.jspvcnt(post_id);
		model.addAttribute("jspvcnt", jspvcnt);
		
		JspBoardVO vo = jspService.jspView(post_id);
		model.addAttribute("jspview", vo);
	}
	
	// 4-4. 게시물 수정 페이지 이동
	@RequestMapping(value = "/jspmodify", method = RequestMethod.GET)
	public void jspModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

		JspBoardVO vo = jspService.jspView(post_id);
		model.addAttribute("jspview", vo);
	}
	
	@RequestMapping(value = "/jspmodify", method = RequestMethod.POST)
	public String jspModify(JspBoardVO vo) throws Exception {

		jspService.jspModify(vo);
		return "redirect:/board/jspview?post_id=" + vo.getPost_id();
	}

	// 4-5. vo가 없으니 get방식 삭제
	@RequestMapping(value = "/jspdelete", method = RequestMethod.GET)
	public String jspDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

		String result = "";
		
		jspService.jspDelete(post_id);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		int user_index = member.getUser_index();
		String user_nickname = member.getUser_nickname();
		
		// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
		if( mypage.equals("right")) {
			
			result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
		
		// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
		} else {
			
			result = "redirect:/board/jsplist";
		}
		return result;
	}
	
	// 4-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/jspreport", method = RequestMethod.GET)
	public void jspmemberreport(@RequestParam List<Integer> checkbox, 
			
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
		for (Integer c : checkbox) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("report_category_id", c);
			map.put("reportee_index", reportee_index);
			map.put("reporter_index", reporter_index);
			map.put("board_category_id", board_category_id);
			map.put("post_id", post_id);
			
			boolean reportSuccess = memberService.reportSuccess(map);	
			
			if(reportSuccess ) {
				memberService.memberreport(map);						
				jspService.jspcategory2(post_id);
				memberService.memcategory2(user_index);
				ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
			}else {
				ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
			}
		}
	}
	
	//4-7. 게시글 북마크 설정
	@RequestMapping (value = "/jspbookmark", method = RequestMethod.GET)
	public void jspbookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = jspService.jspbookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/jspview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				jspService.jspbookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/jspview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
	
	/* --------------------------------
				05. JAVA
	-------------------------------- */
	// 5-1 [GET] 게시물 목록
	@RequestMapping(value = "/javalist", method = RequestMethod.GET)
	public void javaList(Model model) throws Exception{
		List<JavaBoardVO> javalist = null;
		javalist = javaService.javaList();
		model.addAttribute("javalist", javalist);
	}
	
	// 5-2. write페이지이동
	@RequestMapping(value = "/javawrite", method = RequestMethod.GET)
	public void javaWrite() throws Exception {

	}
	
	// 5-2-1. 게시물 작성
	@RequestMapping(value = "/javawrite", method = RequestMethod.POST)
	public String javaWrite(JavaBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUser_nickname(member.getUser_nickname());
		javaService.javaWrite(vo);
	  return "redirect:/board/javalist";
	}
	
	// 5-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/javaview", method = RequestMethod.GET)
	public void javaView(@RequestParam("post_id") int post_id, Model model, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session) throws Exception {
		
		// 상세보기 시 조회수 갱신
		int javavcnt = 0;
		javaService.javavcnt(post_id);
		model.addAttribute("javavcnt", javavcnt);
	}	
		
	// 5-4. 게시물 수정 페이지 이동
	@RequestMapping(value = "/javamodify", method = RequestMethod.GET)
	public void javaModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

		JavaBoardVO vo = javaService.javaView(post_id);
		model.addAttribute("javaview", vo);
	}
	

	@RequestMapping(value = "/javamodify", method = RequestMethod.POST)
	public String javaModify(JavaBoardVO vo) throws Exception {

		javaService.javaModify(vo);
		return "redirect:/board/javaview?post_id=" + vo.getPost_id();
	}

	
	// 5-5. vo가 없으니 get방식으로 삭제
	@RequestMapping(value = "/javadelete", method = RequestMethod.GET)
	public String javaDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

		String result = "";
		
		javaService.javaDelete(post_id);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		int user_index = member.getUser_index();
		String user_nickname = member.getUser_nickname();
		
		// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
		if( mypage.equals("right")) {
			
			result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
		
		// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
		} else {
			
			result = "redirect:/board/javalist";
			
		}
		return result;
	}
	
	// 5-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/javareport", method = RequestMethod.GET)
	public void javamemberreport(@RequestParam List<Integer> checkbox, 
			
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
		for (Integer c : checkbox) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("report_category_id", c);
			map.put("reportee_index", reportee_index);
			map.put("reporter_index", reporter_index);
			map.put("board_category_id", board_category_id);
			map.put("post_id", post_id);
			
			boolean reportSuccess = memberService.reportSuccess(map);	
			
			if(reportSuccess ) {
				memberService.memberreport(map);						
				javaService.javacategory2(post_id);
				memberService.memcategory2(user_index);
				ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
			}else {
				ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
			}
		}
	}
	
	//5-7. 게시글 북마크 설정
	@RequestMapping (value = "/javabookmark", method = RequestMethod.GET)
	public void javabookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = javaService.javabookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/javaview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				javaService.javabookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/javaview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}

	
	/* --------------------------------
				06. ORACLE
	-------------------------------- */
	// 6-1 [GET] 게시물 목록
	@RequestMapping(value = "/oraclelist", method = RequestMethod.GET)
	public void oracleList(Model model) throws Exception{
		List<OracleBoardVO> oraclelist = null;
		oraclelist = oracleService.oracleList();
		model.addAttribute("oraclelist", oraclelist);
	}
	
	// 6-2. write페이지이동
	@RequestMapping(value = "/oraclewrite", method = RequestMethod.GET)
	public void oracleWrite() throws Exception {

	}
	
	// 6-2-1. 게시물 작성
	@RequestMapping(value = "/oraclewrite", method = RequestMethod.POST)
	public String oracleWrite(OracleBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUser_nickname(member.getUser_nickname());
		oracleService.oracleWrite(vo);
	  return "redirect:/board/oraclelist";
	}
	
	// 6-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/oracleview", method = RequestMethod.GET)
	public void oracleView(@RequestParam("post_id") int post_id, Model model, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session) throws Exception {
		// 상세보기 시 조회수 갱신
		int oraclevcnt = 0;
		oracleService.oraclevcnt(post_id);
		model.addAttribute("oraclevcnt", oraclevcnt);
		
		OracleBoardVO vo = oracleService.oracleView(post_id);
		model.addAttribute("oracleview", vo);
	}
		
		
	// 6-4. 게시물 수정 페이지 이동
	
	@RequestMapping(value = "/oraclemodify", method = RequestMethod.GET)
	public void oracleModify(@RequestParam("post_id") int post_id, Model model) throws Exception {

		OracleBoardVO vo = oracleService.oracleView(post_id);
		model.addAttribute("oracleview", vo);
	}
	

	@RequestMapping(value = "/oraclemodify", method = RequestMethod.POST)
	public String oracleModify(OracleBoardVO vo) throws Exception {

		oracleService.oracleModify(vo);
		return "redirect:/board/oracleview?post_id=" + vo.getPost_id();
	}

	
	// 6-5. vo가 없으니 get방식으로 삭제
	@RequestMapping(value = "/oracledelete", method = RequestMethod.GET)
	public String oracleDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {

		String result = "";
		
		oracleService.oracleDelete(post_id);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		int user_index = member.getUser_index();
		String user_nickname = member.getUser_nickname();
		
		// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
		if( mypage.equals("right")) {
			
			result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
		
		// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
		} else {
			
			result = "redirect:/board/oraclelist";
			
		}
		return result;
	}
	
	// 6-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/oraclereport", method = RequestMethod.GET)
	public void oraclememberreport(@RequestParam List<Integer> checkbox, 
			
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
		for (Integer c : checkbox) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("report_category_id", c);
			map.put("reportee_index", reportee_index);
			map.put("reporter_index", reporter_index);
			map.put("board_category_id", board_category_id);
			map.put("post_id", post_id);
			
			boolean reportSuccess = memberService.reportSuccess(map);	
			
			if(reportSuccess ) {
				memberService.memberreport(map);						
				oracleService.oraclecategory2(post_id);
				memberService.memcategory2(user_index);
				ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
			}else {
				ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
			}
		}
	}
		
		
	//6-7. 게시글 북마크 설정
	@RequestMapping (value = "/oraclebookmark", method = RequestMethod.GET)
	public void oraclebookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = oracleService.oraclebookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/oracleview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				oracleService.oraclebookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/oracleview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
	
	
	/* --------------------------------
				07. SPRING
	-------------------------------- */
	// 7-1 [GET] 게시물 목록
	@RequestMapping(value = "/springlist", method = RequestMethod.GET)
	public void springList(Model model) throws Exception {
	
	List<SpringBoardVO> springlist = null;
	springlist = springService.springList();
	model.addAttribute("springlist", springlist);
	}
	
	// 7-2. write페이지이동
	@RequestMapping(value = "/springwrite", method = RequestMethod.GET)
	public void springWrite() throws Exception {
	
	}
	
	// 7-2-1. 게시물 작성
	@RequestMapping(value = "/springwrite", method = RequestMethod.POST)
	public String springWrite(SpringBoardVO vo, HttpServletRequest request) throws Exception {
	HttpSession session = request.getSession();
	MemberVO member = (MemberVO) session.getAttribute("member");
	vo.setUser_nickname(member.getUser_nickname());
	springService.springWrite(vo);
	return "redirect:/board/springlist";
	}
	
	// 7-3. 게시물 상세보기 페이지 이동
	@RequestMapping(value = "/springview", method = RequestMethod.GET)
	public void springView(@RequestParam("post_id") int post_id, Model model) throws Exception {
	
		// 상세보기 시 조회수 갱신
		int springvcnt = 0;
		springService.springvcnt(post_id);
		model.addAttribute("springvcnt", springvcnt);
		
		SpringBoardVO vo = springService.springView(post_id);
		model.addAttribute("springview", vo);
	}
	
	// 7-4. 게시물 수정 페이지 이동
	@RequestMapping(value = "/springmodify", method = RequestMethod.GET)
	public void springModify(@RequestParam("post_id") int post_id, Model model) throws Exception {
	
		SpringBoardVO vo = springService.springView(post_id);
	model.addAttribute("springview", vo);
	}
	
	
	@RequestMapping(value = "/springmodify", method = RequestMethod.POST)
	public String springmodify(SpringBoardVO vo) throws Exception {
	
	springService.springModify(vo);
	return "redirect:/board/springview?post_id=" + vo.getPost_id();
	}
	
	// 7-5. vo가 없으니 get방식 삭제
	@RequestMapping(value = "/springdelete", method = RequestMethod.GET)
	public String springDelete(HttpServletRequest req, @RequestParam("post_id") int post_id, @RequestParam("mypage") String mypage) throws Exception {
	
	String result = "";
	
	springService.springDelete(post_id);
	HttpSession session = req.getSession();
	MemberVO member = (MemberVO) session.getAttribute("member");
	
	int user_index = member.getUser_index();
	String user_nickname = member.getUser_nickname();
	
	// mypage에서 글을 삭제하는 경우 > mypage에 남아있음
	if( mypage.equals("right")) {
	
	result = "forward:/member/mypage?user_index=" + user_index + "&user_nickname=" +user_nickname;			
	
	// 게시판에서 글을 삭제하는 경우 > 게시판에 남아있음
	} else {
	
	result = "redirect:/board/springlist";
	}
	return result;
	}
	
	// 7-6. 게시글 신고(가용성 카테고리 변경)
	@RequestMapping(value = "/springreport", method = RequestMethod.GET)
	public void springmemberreport(@RequestParam List<Integer> checkbox, 
	
	@RequestParam("reportee_index") int reportee_index, 
	@RequestParam("reportee_index") int user_index, 
	@RequestParam("reporter_index") int reporter_index,
	@RequestParam("board_category_id") int board_category_id,
	@RequestParam("post_id") int post_id,
	
	HttpServletResponse response) throws Exception{
	for (Integer c : checkbox) {
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	map.put("report_category_id", c);
	map.put("reportee_index", reportee_index);
	map.put("reporter_index", reporter_index);
	map.put("board_category_id", board_category_id);
	map.put("post_id", post_id);
	
	boolean reportSuccess = memberService.reportSuccess(map);	
	
	if(reportSuccess ) {
		memberService.memberreport(map);						
		springService.springcategory2(post_id);
		memberService.memcategory2(user_index);
		ScriptUtils.alertAndMovePage(response, "신고가 접수되었습니다. 메인화면으로 이동합니다.","http://localhost:9090/");
	}else {
		ScriptUtils.alertAndMovePage(response, "이미 신고된 회원입니다.","http://localhost:9090/");
	}
	}
	}	
	

	//7-7. 게시글 북마크 설정
	@RequestMapping (value = "/springbookmark", method = RequestMethod.GET)
	public void springbookmark(@RequestParam("post_id") int post_id, @RequestParam("user_index") int user_index, HttpServletResponse res) throws Exception{
		
		HashMap<String, Integer> postid_useridx = new HashMap<>();
				
		postid_useridx.put("post_id", post_id);
		postid_useridx.put("user_index", user_index);
		
		// 게시글 북마크 유무 확인
		int result = springService.springbookmarklist(postid_useridx);
		
		try {
			
			if(result==1) {
				
				ScriptUtils.alertAndMovePage(res, "이미 북마크에 추가된 게시글입니다. ", "http://localhost:9090/board/springview?post_id=" + post_id);
			
			} else {
				
				//게시글 북마크에추가
				springService.springbookmark(postid_useridx);
				ScriptUtils.alertAndMovePage(res, "게시글이 북마크에 추가되었습니다. ", "http://localhost:9090/board/springview?post_id=" + post_id);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
	

	
	
}