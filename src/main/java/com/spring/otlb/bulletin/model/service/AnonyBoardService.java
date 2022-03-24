package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;

public interface AnonyBoardService {

	List<Board> selectAnonyBoardMain();

	List<Board> selectTopAnonyBoardMain();

	public List<Board> searchAnonymousBoard(Map<String, Object> param);

	public List<Board> selectAllAnonymousBoard(Map<String, Object> param);

	public int selectTotalAnonyBoardCount();

	public int insertAnonymousBoard(Board board);

	public int insertAnonyAttachment(Attachment attach);
}
