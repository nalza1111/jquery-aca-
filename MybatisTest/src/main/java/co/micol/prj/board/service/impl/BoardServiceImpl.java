package co.micol.prj.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.prj.board.mapper.BoardMapper;
import co.micol.prj.board.service.BoardService;
import co.micol.prj.board.vo.BoardVO;
import co.micol.prj.board.vo.ReplyVO;
import co.micol.prj.common.DataSource;

public class BoardServiceImpl implements BoardService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private BoardMapper map = sqlSession.getMapper(BoardMapper.class);
	
	@Override
	public List<BoardVO> boardSelectList() {
		return map.boardSelectList();
	}

	@Override
	public int boardInsert(BoardVO vo) {
		return map.boardInsert(vo);
	}

	@Override
	public BoardVO boardSelectOne(BoardVO vo) {
		return map.boardSelectOne(vo);
	}

	@Override
	public int boardCntPlus(BoardVO vo) {
		return map.boardCntPlus(vo);
	}

	@Override
	public int replyInsert(ReplyVO vo) {
		return map.replyInsert(vo);
	}

	@Override
	public List<ReplyVO> replySelectList(ReplyVO vo) {
		return map.replySelectList(vo);
	}

	@Override
	public int replyDelete(ReplyVO vo) {
		return  map.replyDelete(vo);//사실상 업데이트

	}

	@Override
	public int boardDelete(BoardVO vo) {
		return map.boardDelete(vo);
	}

	@Override
	public List<BoardVO> boardSelectPgList(int pg1, int pg2) {
		return map.boardSelectPgList(pg1, pg2);
	}

}
