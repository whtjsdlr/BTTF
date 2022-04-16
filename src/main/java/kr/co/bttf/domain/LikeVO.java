package kr.co.bttf.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LikeVO {
	private int recommend_id;
	private int post_id;
	private String user_index;
	private String board_category_id;
	
}
