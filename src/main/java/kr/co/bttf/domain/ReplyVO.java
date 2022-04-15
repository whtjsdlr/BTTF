package kr.co.bttf.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private Long reply_id;
	private Long post_id;
	//private int reply_depth;
	//private int bundle_id;
	private String reply_contents;
	private String user_nickname;
	private Date reply_regdate;
	private Date reply_regdate_modified;
	private int avaliability_category_id;
}