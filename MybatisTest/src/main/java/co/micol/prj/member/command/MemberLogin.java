package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리
		MemberService dao = new MemberServiceImpl();
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession(); //세션값을 사용하기 위해
		
		String id = request.getParameter("id");
		String password = request.getParameter("passwd");
		
		String message = "아이디 또는 패스워드가 틀립니다.";
		
		member = dao.memberLogin(id, password);
		
		if(member != null) {
			session.setAttribute("id", member.getId());
			session.setAttribute("name", member.getName());
			session.setAttribute("respsibility", member.getResposibility());
			message = member.getName() + "님 환영합니다.";			
		}
		
		request.setAttribute("message", message);
		return "member/memberLogin.tiles";
	}

}
