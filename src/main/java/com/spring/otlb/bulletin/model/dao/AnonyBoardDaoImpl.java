package com.spring.otlb.bulletin.model.dao;

import java.util.List;

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
}
