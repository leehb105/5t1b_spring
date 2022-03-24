package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.bulletin.model.dao.AnonyBoardDao;
import com.spring.otlb.bulletin.model.vo.Board;

@Service
public class AnonyBoardServiceImpl implements AnonyBoardService{

	@Autowired
	private AnonyBoardDao anonyBoardDao;
	
	@Override
	public List<Board> selectAnonyBoardMain() {
		return anonyBoardDao.selectAnonyBoardMain();
	}

	@Override
	public List<Board> selectTopAnonyBoardMain() {
		return anonyBoardDao.selectTopAnonyBoardMain();
	}

	@Override
	public List<Board> searchAnonymousBoard(Map<String, Object> param) {
		return null;
	}

	@Override
	public List<Board> selectAllAnonymousBoard(Map<String, Object> param) {
		return anonyBoardDao.selectAllAnonymousBoard(param);
	}

	@Override
	public int selectTotalAnonyBoardCount() {
		return anonyBoardDao.selectTotalAnonyBoardCount();
	}

}
