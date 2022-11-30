package co.micol.prj.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.board.vo.ReplyVO;

public interface BoardMapper {
	List<BoardVO> boardSelectList();
	int boardInsert(BoardVO vo);
	BoardVO boardSelectOne(BoardVO vo);
	int boardCntPlus(BoardVO vo);
	
	int boardDelete(BoardVO vo);
	
	int replyInsert(ReplyVO vo);
	List<ReplyVO> replySelectList(ReplyVO vo);
	int replyDelete(ReplyVO vo);//사실상 업데이트

	List<BoardVO> boardSelectPgList(@Param("pg1") int pg1,@Param("pg2") int pg2);
}
