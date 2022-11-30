package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;

public class Logout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃 처리
		HttpSession session = request.getSession();
		String message = (String) session.getAttribute("name");
		message = message.concat("님 안녕히 가세요!!!!!!");
		request.setAttribute("message", message);
		session.invalidate();  //세션을 완전히 삭제
		return "member/memberLogin.tiles";
//		return "main.do";  //처음으로 돌리는 것
	}

}
