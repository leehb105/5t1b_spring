package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;

public interface AnonyBoardDao {

	List<Board> selectAnonyBoardMain();

	List<Board> selectTopAnonyBoardMain();

    List<Board> selectAllAnonymousBoard(Map<String, Object> param);

	int selectTotalAnonyBoardCount(Map<String, Object> param);

	int insertAnonymousBoard(Board board);

	int insertAnonyAttachment(Attachment attach);

	Board selectAnonyBoardAttachments(int no);

	List<BoardComment> selectAnonyBoardCommentList(int no);

	int updateReadCount(int no);

	Attachment selectOneAttachment(int no);

	int updateAnonyBoardLikeCount(int no);

	int deleteAnonymousBoard(int no);

	int insertAnonyBoardComment(BoardComment boardComment);


	int updateAnonymousBoard(Board board);

	List<Attachment> selectAttachments(int no);

	int deleteAnonymousAttachment(int no);

	int deleteAnonymousBoardComment(int commentNo);
}
