package co.micol.prj.book.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("bookCode");
		System.out.println(code);
		
		BookVO vo = new BookVO();
		vo.setBookCode(code);
		
		BookService service = new BookServiceImpl();
		vo = service.bookSelect(vo);
		System.out.println(vo.toString());
		String json = null;
		if(vo.getBookPress()!=null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(vo);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			json = "Fail";
		}
		System.out.println(json);
		return "ajax:"+json;
		
	}

}
