package kr.co.bttf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bttf.domain.CssReplyVO;
import kr.co.bttf.domain.OracleReplyVO;
import kr.co.bttf.service.CssReplyService;
import kr.co.bttf.service.OracleReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	private CssReplyService cssservice;
	
	@Inject
	private OracleReplyService oracleservice;
	
	/* --------------------------------
				02. CSS
	-------------------------------- */
	@PostMapping(value = "/cssnew", consumes = "application/json;charset=utf-8", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> cssReplyCreate(@RequestBody CssReplyVO reply){
		if (cssservice.register(reply) == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
			
		}else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	@GetMapping(value = "/csspage/{post_id}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<CssReplyVO>> cssReplyGetList(
			@PathVariable("page") int page,
			@PathVariable("post_id") Long post_id){
		Criteria crit = new Criteria(page, 10);
		return new ResponseEntity<>(cssservice.getList(crit, post_id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/css/{reply_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CssReplyVO> cssReplyGet(@PathVariable("reply_id") Long reply_id){
		return new ResponseEntity<>(cssservice.get(reply_id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/css/{reply_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> cssReplyRemove(@PathVariable("reply_id") Long reply_id){
		return cssservice.remove(reply_id) == 1?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@PostMapping(value = "/css/{reply_id}/{reply_contents}")
	public Map<String, Object> cssReplyupdate(@PathVariable int reply_id, @PathVariable String reply_contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CssReplyVO replybean = new CssReplyVO();
			replybean.setReply_id(reply_id);
			replybean.setReply_contents(reply_contents);
			cssservice.replyupdate(replybean);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
		}
		return map;
	}
	
	
	/* --------------------------------
				06. ORACLE
	-------------------------------- */
	@PostMapping(value = "/oraclenew", consumes = "application/json;charset=utf-8", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> oracleReplyCreate(@RequestBody OracleReplyVO reply){
		if (oracleservice.register(reply) == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
			
		}else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	@GetMapping(value = "/oraclepage/{post_id}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<OracleReplyVO>> oracleReplyGetList(
			@PathVariable("page") int page,
			@PathVariable("post_id") Long post_id){
		Criteria crit = new Criteria(page, 10);
		return new ResponseEntity<>(oracleservice.getList(crit, post_id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/oracle/{reply_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<OracleReplyVO> oracleReplyGet(@PathVariable("reply_id") Long reply_id){
		return new ResponseEntity<>(oracleservice.get(reply_id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/oracle/{reply_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> oracleReplyRemove(@PathVariable("reply_id") Long reply_id){
		return oracleservice.remove(reply_id) == 1?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@PostMapping(value = "/oracle/{reply_id}/{reply_contents}")
	public Map<String, Object> oracleReplyUpdate(@PathVariable int reply_id, @PathVariable String reply_contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			OracleReplyVO replybean = new OracleReplyVO();
			replybean.setReply_id(reply_id);
			replybean.setReply_contents(reply_contents);
			oracleservice.replyupdate(replybean);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
		}
		return map;
	}
	
}
