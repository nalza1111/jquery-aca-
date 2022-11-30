package co.micol.prj.board.command;

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

public class ReplyDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String replyNo = request.getParameter("repNo");
		String boardNo = request.getParameter("bno");
		
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(replyNo);
		vo.setBoardNo(boardNo);
		BoardService service = new BoardServiceImpl();
		service.replyDelete(vo);
		
		//댓글돌려보내기
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
