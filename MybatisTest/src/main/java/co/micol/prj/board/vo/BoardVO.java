package co.micol.prj.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private String boardNo;
	private String title;
	private String content;
	private String writer;
	private String clickCnt;
	private String image;
	private String writeDate;
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", clickCnt=" + clickCnt + ", image=" + image + ", writeDate=" + writeDate + "]";
	}
}
