package com.spring.otlb.bulletin.model.service;

import java.util.List;

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

}
