package kr.co.bttf.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jdk.internal.org.jline.utils.Log;
import kr.co.bttf.domain.MemberVO;
import kr.co.bttf.domain.OracleBoardVO;
import kr.co.bttf.domain.ReplyVO;
import kr.co.bttf.service.MemberService;
import kr.co.bttf.service.OracleBoardService;
import kr.co.bttf.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import kr.co.bttf.controller.Criteria;
@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	//@Setter(onMethod_=@Autowired)
	@Inject
	private ReplyService service;
	
	@PostMapping(value = "/new", consumes = "application/json;charset=utf-8", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		System.out.println(reply.getReply_contents());
		
		System.out.println("리플라이컨트롤러에서 댓글 vo: " + reply);
		System.out.println("댓글입력 갯수: " + service.register(reply));
		return service.register(reply) == 1?
				new ResponseEntity<>("success", HttpStatus.OK) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/page/{reply_id}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("page") int page,
			@PathVariable("reply_id") Long reply_id
			){
		System.out.println("리플라이컨트롤러에서 전체 목록 조회...........................");
		Criteria crit = new Criteria(page, 10);
		System.out.println(crit);
		return new ResponseEntity<>(service.getList(crit, reply_id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{reply_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("reply_id") Long reply_id){
		System.out.println("리플라이컨트롤러에서 받은 reply_id = "+ reply_id);
		return new ResponseEntity<>(service.get(reply_id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{reply_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("reply_id") Long reply_id){
		System.out.println("리플라이컨트롤러에서 삭제" + reply_id);
		return service.remove(reply_id) == 1?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PatchMapping(value = "/{reply_id}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply, 
						@PathVariable("reply_id") Long reply_id){
		reply.setReply_id(reply_id);
		System.out.println("리플라이컨트롤러의 수정:" + reply_id);
		return service.modify(reply) == 1?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
