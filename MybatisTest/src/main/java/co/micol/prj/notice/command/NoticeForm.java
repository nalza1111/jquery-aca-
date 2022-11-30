package co.micol.prj.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;

public class NoticeForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글쓰기 폼
		return "notice/noticeForm.tiles";
	}

}
