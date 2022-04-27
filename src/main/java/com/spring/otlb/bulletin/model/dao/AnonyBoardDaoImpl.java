package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.BoardComment;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.bulletin.model.vo.Board;

@Repository
public class AnonyBoardDaoImpl implements AnonyBoardDao{

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Board> selectAnonyBoardMain() {
		return session.selectList("anonyBoard.selectAnonyBoardMain");
	}

	@Override
	public List<Board> selectTopAnonyBoardMain() {
		return session.selectList("anonyBoard.selectTopAnonyBoardMain");
	}

	@Override
	public List<Board> selectAllAnonymousBoard(Map<String, Object> param) {
		return session.selectList("anonyBoard.selectAllAnonymousBoard", param);
	}

	@Override
	public int selectTotalAnonyBoardCount(Map<String, Object> param) {
		return session.selectOne("anonyBoard.selectTotalAnonyBoardCount", param);
	}

	@Override
	public int insertAnonymousBoard(Board board) {
		return session.insert("anonyBoard.insertAnonymousBoard", board);
	}

	@Override
	public int insertAnonyAttachment(Attachment attach) {
		return session.insert("anonyBoard.insertAnonyAttachment", attach);
	}

	@Override
	public Board selectAnonyBoardAttachments(int no) {
		return session.selectOne("anonyBoard.selectAnonyBoardAttachments", no);
	}

	@Override
	public List<BoardComment> selectAnonyBoardCommentList(int no) {
		return session.selectList("anonyBoard.selectAnonyBoardCommentList", no);
	}

	@Override
	public int updateReadCount(int no) {
		return session.update("anonyBoard.updateReadCount", no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return session.selectOne("anonyBoard.selectOneAttachment", no);
	}

	@Override
	public int updateAnonyBoardLikeCount(int no) {
		return session.update("anonyBoard.updateAnonyBoardLikeCount", no);
	}

	@Override
	public int deleteAnonymousBoard(int no) {
		return session.update("anonyBoard.deleteAnonymousBoard", no);
	}

	@Override
	public int insertAnonyBoardComment(BoardComment boardComment) {
		return session.insert("anonyBoard.insertAnonyBoardComment", boardComment);
	}

	@Override
	public int updateAnonymousBoard(Board board) {
		return session.update("anonyBoard.updateAnonymousBoard", board);
	}

	@Override
	public List<Attachment> selectAttachments(int no) {
		return session.selectList("anonyBoard.selectAttachments", no);
	}

	@Override
	public int deleteAnonymousAttachment(int no) {
		return session.delete("anonyBoard.deleteAnonymousAttachment", no);
	}


}
