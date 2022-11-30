package co.micol.prj.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class ajaxMemberAuthorSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 유저 권한별로 리스트 가져오기
		MemberService service = new MemberServiceImpl();
		List<MemberVO> members = new ArrayList<MemberVO>();
		ObjectMapper objectMapper = new ObjectMapper();  //list ==> json
		
		String author = request.getParameter("author");
		String list = null;
		members = service.memberSelectList(author);
		try {
			list = objectMapper.writeValueAsString(members);  //json String 으로 변환
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+list;
	}

}
