package co.micol.prj.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버목록 보기
		MemberService service = new MemberServiceImpl();
		List<MemberVO> members = new ArrayList<>();
		members = service.memberSelectList("all");
		request.setAttribute("members", members);
		return "member/memberList.tiles";
	}

}
