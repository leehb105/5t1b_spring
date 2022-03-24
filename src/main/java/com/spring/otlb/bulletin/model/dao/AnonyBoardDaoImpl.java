package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
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
	public int selectTotalAnonyBoardCount() {
		return session.selectOne("anonyBoard.selectTotalAnonyBoardCount");
	}

	@Override
	public int insertAnonymousBoard(Board board) {
		return session.insert("anonyBoard.insertAnonymousBoard", board);
	}

	@Override
	public int insertAnonyAttachment(Attachment attach) {
		return session.insert("anonyBoard.insertAnonyAttachment", attach);
	}
}
