package co.micol.prj.board.result;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.service.impl.BoardServiceImpl;
import co.micol.prj.board.vo.ReplyVO;
import co.micol.prj.common.Command;

public class ReplyInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String boardNo = request.getParameter("no");
		ReplyVO vo = new ReplyVO();
		vo.setReplyWriter(id);
		vo.setBoardNo(boardNo);
		
		BoardService service = new BoardServiceImpl();
		//첫 게시판상세면 쭉빠져서 리스트만 받아옴
		//인서트상황이면 아래로 빠져서 인서트..
		if( id!=null && content!=null ) {
			
			
			vo.setReplyContent(content);
			
			
			int j = 0;
			j = service.replyInsert(vo);
			
			System.out.println("댓글입력성공"+j);
		}
		//리스트받아오기
		List<ReplyVO> repList = new ArrayList<>();
		
		repList = service.replySelectList(vo);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "";
		try {
			json = mapper.writeValueAsString(repList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+json;
	}

}
