package co.micol.prj.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private String replyNo;
	private String boardNo;
	private String replyContent;
	private String replyWriter;
	private String replyDate;
	
	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", boardNo=" + boardNo + ", replyContent=" + replyContent
				+ ", replyWriter=" + replyWriter + ", replyDate=" + replyDate + "]";
	}
	
}
