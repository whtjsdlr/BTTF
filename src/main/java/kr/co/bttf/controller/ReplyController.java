package kr.co.bttf.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
//import kr.co.bttf.domain.OracleReplyVO;
import kr.co.bttf.domain.ReplyVO;
import kr.co.bttf.service.MemberService;
import kr.co.bttf.service.OracleBoardService;
import kr.co.bttf.service.OracleReplyService;
import kr.co.bttf.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import kr.co.bttf.controller.Criteria;
//@RestController
@Controller
//@AllArgsConstructor
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	@PostMapping(value = "new",
				consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<? extends Object> create(@RequestBody ReplyVO vo){
		
		int inserCount = service.register(vo);
		
		
		return inserCount == 1? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
