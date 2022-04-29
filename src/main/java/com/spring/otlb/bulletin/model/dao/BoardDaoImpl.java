package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertBoard(Board board) {
		return session.insert("board.insertBoard", board);
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return session.insert("board.insertAttachment", attach);
	}

	@Override
	public int deleteBoard(int no) {
		return session.update("board.deleteBoard", no);
	}

	@Override
	public Board selectOneBoard(int no) {
		return session.selectOne("board.selectOneBoard", no);
	}

	@Override
	public int updateBoard(Board board) {
		return session.update("board.updateBoard", board);
	}

	@Override
	public List<Board> selectAllBoard(Map<String, Object> param) {
		return session.selectList("board.selectAllBoard", param);
	}

	@Override
	public List<Board> selectAllNotice(Map<String, Object> param) {
		return null;
	}

	@Override
	public List<Attachment> selectAttachments(int no) {
		return session.selectList("board.selectAttachments", no);
	}

	@Override
	public List<Board> selectAllAnonymousBoard(Map<String, Object> param) {
		return null;
	}

	@Override
	public int selectTotalBoardCount() {
		return session.selectOne("board.selectTotalBoardCount");
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return session.selectOne("board.selectOneAttachment", no);
	}

	@Override
	public int deleteAttachment(int no) {
		return session.delete("board.deleteAttachment", no);
	}

	@Override
	public List<BoardComment> selectBoardCommentList(int no) {
		return session.selectList("board.selectBoardCommentList", no);
	}

	@Override
	public int updateReadCount(int no) {
		return session.update("board.updateReadCount", no);
	}

	@Override
	public int insertBoardComment(BoardComment boardComment) {
		return session.insert("board.insertBoardComment", boardComment);
	}

	@Override
	public List<Board> searchBoard(Map<String, Object> param) {
		return null;
	}

	@Override
	public int insertAnonymousBoard(Board board) {
		return 0;
	}

	@Override
	public int insertNotice(Board board) {
		return 0;
	}

	@Override
	public List<Board> searchNotice(Map<String, Object> param) {
		return null;
	}

	@Override
	public List<Board> searchAnonymousBoard(Map<String, Object> param) {
		return null;
	}

	@Override
	public int updateBoardLikeCount(int no) {
		return session.update("board.updateBoardLikeCount", no);
	}

	@Override
	public Board selectOneAnonyBoard(int no) {
		return null;
	}

	@Override
	public int insertAnonyBoardComment(BoardComment bc) {
		return 0;
	}

	@Override
	public List<BoardComment> selectAnonyBoardCommentList(int no) {
		return null;
	}

	@Override
	public int updateAnonyBoardLikeCount(int no) {
		return 0;
	}

	@Override
	public int updateAnonyReadCount(int no) {
		return 0;
	}

	@Override
	public Board selectOneNotice(int no) {
		return null;
	}

	@Override
	public int updateNoticeReadCount(int no) {
		return 0;
	}

	@Override
	public int deleteBoardComment(int no) {
		return 0;
	}

	@Override
	public int deleteAnonymousBoard(int no) {
		return 0;
	}

	@Override
	public int deleteNotice(int no) {
		return 0;
	}

	@Override
	public Attachment selectAttachment(int no) {
		return null;
	}

	@Override
	public Board selectOneAnonymousBoard(int no) {
		return null;
	}

	@Override
	public Attachment selectOneAnonymousAttachment(int delFileNo) {
		return null;
	}

	@Override
	public int deleteAnonymousAttachment(int delFileNo) {
		return 0;
	}

	@Override
	public int updateAnonymousBoard(Board board) {
		return 0;
	}

	@Override
	public int updateNotice(Board board) {
		return 0;
	}

	@Override
	public List<Board> selectBoardMain() {
		return session.selectList("board.selectBoardMain");
	}

	@Override
	public List<Board> selectTopBoardMain() {
		return session.selectList("board.selectTopBoardMain");
	}

	@Override
	public Board selectBoardAttachments(int no) {
		return session.selectOne("board.selectBoardAttachments", no);
	}

}
