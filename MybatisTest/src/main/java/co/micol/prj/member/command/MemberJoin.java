package co.micol.prj.member.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.common.Command;
import co.micol.prj.common.FileUpLoadVO;
import co.micol.prj.common.FileUpload;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.impl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberJoin implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원입력
		MemberService dao = new MemberServiceImpl();
		String saveFolder = "upload";
		FileUpload fileUpload = new FileUpload(request, saveFolder);  //파일업로드 객체 호출
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<FileUpLoadVO> fileList = new ArrayList<FileUpLoadVO>();
		
		MemberVO vo = new MemberVO();
		
		map = fileUpload.setFileUpload(); //파일 업로드 실행
		System.out.println(map.get("id"));   //데이터 확인 vo에 담으면 됨
		System.out.println(map.get("passwd"));
		System.out.println(map.get("name"));
		System.out.println(map.get("email"));
		fileList = (List<FileUpLoadVO>) map.get("attechs");  
		for(FileUpLoadVO item : fileList) {  //여기서 파일명을 DB에 저장
			System.out.println(item.getOfileName());
			System.out.println(item.getPfileName());
		}
		
//		int n = dao.memberInsert(vo);
//		String message = "입력이 실패 했습니다.";
//		if (n > 0) {
//			message = "회원가입이 정상적으로 이루어 졌다.";
//		}
//		request.setAttribute("message", message);

		return "member/memberLogin.tiles";
	}

}
