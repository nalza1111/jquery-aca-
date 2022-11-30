package co.micol.prj.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookModify implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("bookCode");
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("bookAuthor");
		String press = request.getParameter("bookPress");
		String price = request.getParameter("bookPrice");
		
		BookVO vo = new BookVO();
		vo.setBookCode(code);
		vo.setBookTitle(title);
		vo.setBookAuthor(author);
		vo.setBookPress(press);
		vo.setBookPrice(Integer.parseInt(price));
		
		BookService service = new BookServiceImpl();
		int result = service.bookUpdate(vo);
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
