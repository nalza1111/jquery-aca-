package co.micol.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.service.impl.BoardServiceImpl;
import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.common.Command;

public class DetailBoard implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "board/reply.tiles";
	}

}
