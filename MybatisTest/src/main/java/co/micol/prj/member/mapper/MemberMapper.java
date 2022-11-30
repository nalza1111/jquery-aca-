package co.micol.prj.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.member.vo.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList(String author);
	MemberVO memberSelect(MemberVO vo);  
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	
	MemberVO memberLogin(@Param("id") String id, @Param("password") String password);
	boolean isMemberIdCheck(String id);  //존재하면 false
}
