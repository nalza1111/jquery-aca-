package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService {
	List<MemberVO> memberSelectList(String author);
	MemberVO memberSelect(MemberVO vo);  
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	
	MemberVO memberLogin(String id, String password);
	boolean isMemberIdCheck(String id);  //존재하면 false
}
