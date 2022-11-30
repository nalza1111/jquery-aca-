package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MyInfo implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내 정보 보기
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession();
		
		vo.setId((String) session.getAttribute("id"));
		vo = dao.memberSelect(vo);
		String viewPage = "member/myInfo.tiles";
		if(vo != null) {
			request.setAttribute("my", vo);
		}else {
			request.setAttribute("message", "시스템 오류가 발생했습니다. \n 관리자에게 문의하세요.");
			viewPage = "member/memberLogin.tiles";
		}
		return viewPage;
	}

}
