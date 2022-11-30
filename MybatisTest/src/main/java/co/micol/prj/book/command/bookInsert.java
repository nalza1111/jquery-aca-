package co.micol.prj.book.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookFileVO;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class bookInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response){
		// 도서 등록
		BookService service = new BookServiceImpl();
		BookVO bookVO = new BookVO();
		BookFileVO bookFileVO = new BookFileVO();
		String savePath = request.getServletContext().getRealPath("/upload/book/");
		try {
			MultipartRequest multi = 
					new MultipartRequest(request, savePath, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy());
			
			bookVO.setBookCode(multi.getParameter("bookCode"));
			bookVO.setBookTitle(multi.getParameter("bookTitle"));
			bookVO.setBookAuthor(multi.getParameter("bookAuthor"));
			bookVO.setBookPress(multi.getParameter("bookPress"));
			bookVO.setBookPrice(Integer.valueOf(multi.getParameter("bookPrice")));
			//저장하고
			//시퀀스 값 찾아오는 쿼리 실행		
			int n = service.bookCode();	
			
			Enumeration<?> fileNames = multi.getFileNames();
	        while(fileNames.hasMoreElements()) {
	            String file = (String) fileNames.nextElement();          
	            if(multi.getOriginalFileName(file) == null) {  //file객체에 파일이 존재하지 않으면 다음객체 읽음
	            	continue;
	            }

	            bookFileVO.setBookCode(bookVO.getBookCode());
	            bookFileVO.setBookImage(multi.getFilesystemName(file));
	            bookFileVO.setBookPath("upload/book/" + multi.getOriginalFileName(file));
	            service.bookInsertImage(bookFileVO); //이미지 테이블 파일을 업로드
	        }	
			
			service.bookInsert(bookVO);  //내용테이블 내용을 업로드

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "bookList.do";
	}

}
