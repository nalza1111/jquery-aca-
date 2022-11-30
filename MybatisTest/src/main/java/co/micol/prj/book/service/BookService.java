package co.micol.prj.book.service;

import java.util.List;

import co.micol.prj.book.vo.BookFileVO;
import co.micol.prj.book.vo.BookVO;

public interface BookService {
	List<BookVO> bookSelectList();  //book 전체리스트 가져오기 getBookList(R)
	BookVO bookSelect(BookVO vo);   //한권의 책 상세내역 가져오기(R) getBook
	int bookInsert(BookVO vo);      //도서입력(C)setBook
	int bookDelete(BookVO vo);      //도서삭제(D)deletBook
	int bookUpdate(BookVO vo);      //도서변경(U)updateBook
	
	int bookInsertImage(BookFileVO vo); //도서 이미지 저장
	int bookCode(); //시퀀스값 찾기
}
