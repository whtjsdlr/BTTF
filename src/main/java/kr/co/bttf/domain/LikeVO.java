package kr.co.bttf.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LikeVO {
	
	private int like_id;
	private int user_index;
	private int post_id;
	private int board_category_id;
}
