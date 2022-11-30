package co.micol.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.service.impl.BoardServiceImpl;
import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.common.Command;

public class DetailBoard2 implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String boardNo = request.getParameter("no");
		
		System.out.println(boardNo);
		
		BoardVO vo = new BoardVO();
		vo.setBoardNo(boardNo);
		
		BoardService service = new BoardServiceImpl();
		
		int confirm =0;
//		조회수 1올리기
		confirm = service.boardCntPlus(vo);
		System.out.println("조회수확인용" +confirm);
//		상세서치
		vo = service.boardSelectOne(vo);
		System.out.println("확인용2" +vo.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "ajax:"+json;
	}

}
