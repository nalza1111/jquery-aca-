package co.micol.prj.book.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("bookCode");
		System.out.println(code);
		
		BookVO vo = new BookVO();
		vo.setBookCode(code);
		
		BookService service = new BookServiceImpl();
		int result = service.bookDelete(vo);
		String json = null;
		if(result>0) {
			json = "Success";
		} else {
			json = "Fail";
		}
		System.out.println(json);
		return "ajax:"+json;
		
	}

}
