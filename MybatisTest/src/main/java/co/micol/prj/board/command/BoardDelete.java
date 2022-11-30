package co.micol.prj.board.command;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.service.impl.BoardServiceImpl;
import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.common.Command;

public class BoardDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String boardNo = request.getParameter("bno");
		BoardVO vo = new BoardVO();
		vo.setBoardNo(boardNo);
		
		BoardService service = new BoardServiceImpl();
		String json = "";
		
		if(service.boardDelete(vo)==1) {
			json = "Success";
		} else {
			json = "Fail";
		}
		System.out.println("ajax:"+json);
		return "ajax:"+json;
	}

}
