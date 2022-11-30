package co.micol.prj.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.service.impl.BoardServiceImpl;
import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.board.vo.PageVO;
import co.micol.prj.common.Command;

public class GetBoard implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		
		BoardService dao = new BoardServiceImpl();
		List<BoardVO> boards = new ArrayList<BoardVO>();
		boards = dao.boardSelectList();
		
		//페이지
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		int pg = Integer.parseInt(page);
		PageVO paging = new PageVO(boards.size(), pg); 
		
		//페이지처리 있어야 함
		int pg1 = (pg-1)*10+1;
		int pg2 = (pg*10);
		boards = dao.boardSelectPgList(pg1, pg2);
		System.out.println(boards);
		for(BoardVO vo: boards) {
			System.out.println(vo.toString());
		}
		ObjectMapper mapper = new ObjectMapper();
		
		String json ="";
		
		try {
			json = mapper.writeValueAsString(boards);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+json;
	}

}
