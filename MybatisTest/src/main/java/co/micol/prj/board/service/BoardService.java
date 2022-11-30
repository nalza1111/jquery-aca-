package co.micol.prj.board.service;

import java.util.List;

import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.board.vo.ReplyVO;

public interface BoardService {
	List<BoardVO> boardSelectList();
	int boardInsert(BoardVO vo);
	BoardVO boardSelectOne(BoardVO vo);
	int boardCntPlus(BoardVO vo);
	
	int boardDelete(BoardVO vo);
	
	int replyInsert(ReplyVO vo);
	List<ReplyVO> replySelectList(ReplyVO vo);
	int replyDelete(ReplyVO vo);//사실상 업데이트
	
	//페이지
	List<BoardVO> boardSelectPgList(int pg1, int pg2);
}
