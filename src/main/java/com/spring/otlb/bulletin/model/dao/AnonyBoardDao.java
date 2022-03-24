package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;

public interface AnonyBoardDao {

	List<Board> selectAnonyBoardMain();

	List<Board> selectTopAnonyBoardMain();

    List<Board> selectAllAnonymousBoard(Map<String, Object> param);

	int selectTotalAnonyBoardCount();

	int insertAnonymousBoard(Board board);

	int insertAnonyAttachment(Attachment attach);

	Board selectAnonyBoardAttachments(int no);
}
