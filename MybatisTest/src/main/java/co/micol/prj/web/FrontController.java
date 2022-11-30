package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.board.command.BoardDelete;
import co.micol.prj.board.command.BoardForm;
import co.micol.prj.board.command.BoardInsert;
import co.micol.prj.board.command.DetailBoard;
import co.micol.prj.board.command.DetailBoard2;
import co.micol.prj.board.command.GetBoard;
import co.micol.prj.board.command.ReplyDelete;
import co.micol.prj.board.result.ReplyInsert;
import co.micol.prj.book.AjaxBookAdd;
import co.micol.prj.book.AjaxBookModify;
import co.micol.prj.book.command.AjaxBookDelete;
import co.micol.prj.book.command.AjaxBookList;
import co.micol.prj.book.command.AjaxBookSearch;
import co.micol.prj.book.command.AjaxMainForm;
import co.micol.prj.book.command.BookList;
import co.micol.prj.book.command.bookInsert;
import co.micol.prj.book.command.bookInsertForm;
import co.micol.prj.cart.command.ajaxCartList;
import co.micol.prj.common.Command;
import co.micol.prj.emp.command.EmpDelete;
import co.micol.prj.emp.command.EmpInsert;
import co.micol.prj.emp.command.EmpList;
import co.micol.prj.emp.command.EmpSelect;
import co.micol.prj.emp.command.EmpUpdate;
import co.micol.prj.emp.command.JobSelect;
import co.micol.prj.main.MainCommand;
import co.micol.prj.member.command.AjaxIdCheck;
import co.micol.prj.member.command.Logout;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MyInfo;
import co.micol.prj.member.command.ajaxMemberAuthorSelect;
import co.micol.prj.notice.command.NoticeForm;

/**
 * 모든요청을 받아들이는 컨트롤러
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
 
	public FrontController() {
        super();
    }

    //요청한 것을 실행하는 명령을 모아 두는 곳
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand());  //처음 보여줄 페이지 명령
		map.put("/bookList.do", new BookList());  //책목록보기
		map.put("/memberLoginForm.do", new MemberLoginForm()); //로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); //멤버로그인처리
		map.put("/logout.do", new Logout());  //로그아웃
		map.put("/memberJoinForm.do", new MemberJoinForm()); //회원가입 폼 호출
		map.put("/ajaxIdCheck.do", new AjaxIdCheck());//ajax를 이용한 아이디 중복체크
		map.put("/memberJoin.do", new MemberJoin()); //회원가입
		map.put("/noticeForm.do", new NoticeForm()); //글쓰기 폼
		map.put("/myInfo.do", new MyInfo()); //내 정보보기
		map.put("/memberList.do", new MemberList()); //멤버목록보기
		map.put("/ajaxMemberAuthorSelect.do", new ajaxMemberAuthorSelect()); //유저권한별 리스트
		map.put("/bookInsertForm.do", new bookInsertForm());  //도서입력 화면
		map.put("/bookInsert.do", new bookInsert());  //도서 등록
		
		// jquery ajax 용 컨트롤.
		map.put("/ajaxJquery.do", new AjaxMainForm());
		map.put("/ajaxBookList.do", new AjaxBookList());
		map.put("/ajaxBookAdd.do", new AjaxBookAdd());
		map.put("/ajaxBookModify.do", new AjaxBookModify());
		map.put("/ajaxBookDelete.do", new AjaxBookDelete());
		map.put("/ajaxBookSearch.do", new AjaxBookSearch());
		
		//게시판, 댓글 등록.
		map.put("/boardForm.do", new BoardForm());//게시판넘어가기
		map.put("/getBoard.do", new GetBoard());//게시판가져오기
		map.put("/boardInsert.do", new BoardInsert());//게시판입력하기
		map.put("/detailBoard.do", new DetailBoard());//상세글로 넘어가기
		map.put("/detailBoard2.do", new DetailBoard2());//상세글 불러오기
		map.put("/replyInsert.do", new ReplyInsert());//댓글첫리스트뜸+댓글추가...귀찮
		map.put("/replyDelete.do", new ReplyDelete());//댓글삭제
		map.put("/boardDelete.do", new BoardDelete());//글삭제
		
		//카트
		map.put("/ajaxCartList.do", new ajaxCartList());//카트리스트가져오기
		
		//emp
		map.put("/empList.do", new EmpList());// emp 리스트 가져오기(0)
		map.put("/jobSelect.do", new JobSelect());// 잡셀렉트가져오기(0)
		map.put("/empSelect.do", new EmpSelect());// emp 개별(0)
		map.put("/empInsert.do", new EmpInsert());// emp insert(0)
		map.put("/empDelete.do", new EmpDelete());// emp delete(0)
		map.put("/empUpdate.do", new EmpUpdate());// emp update
	}

	//요청을 분석하고 실행, 결과를 돌려주는 곳
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //한글깨짐방지
		String uri = request.getRequestURI();  //요청한 uri를 구함
		String contextPath = request.getContextPath();  //루트를 구함,context path
		String page = uri.substring(contextPath.length());  //실제 수행할 요청을 구함
		
		Command command = map.get(page);  //init 메소드에서 수행할 명령을 가져온다.
		String viewPage = command.exec(request, response); //명령을 수행하고 결과를 돌려받음
		
		//viewResolve 파트
		if(!viewPage.endsWith(".do") && viewPage != null) {
			//ajax 처리
			if(viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			//타일즈 돌아가는곳
			if(!viewPage.endsWith(".tiles")) {
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; //타일즈를 안태움
			}			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(viewPage);  //*.do 로 들어올때 돌아가는 곳
		}		
	}
}
