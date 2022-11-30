package co.micol.prj.member.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;


public class AjaxIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// ajax 아이디 중복체크
		MemberService dao = new MemberServiceImpl();
		String id = request.getParameter("id");
		System.out.println(id + "==========");
		boolean b = dao.isMemberIdCheck(id);  //존재하면 false
		String result = "0";
		if(b) {
			result = "1";
		}
		return "ajax:" + result;
	}

}
