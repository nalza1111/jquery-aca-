package co.micol.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;

public class BoardForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "board/board.tiles";
	}

}
