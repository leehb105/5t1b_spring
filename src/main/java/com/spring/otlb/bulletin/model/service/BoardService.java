package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;


public interface BoardService {

	public int insertBoard(Board board);

	public int deleteBoard(int no);
	
	public Board selectOneBoard(int no);

	public int updateBoard(Board board);

	public List<Board> selectAllBoard(Map<String, Object> param);
	
	public List<Board> selectAllNotice(Map<String, Object> param);
	
	public List<Board> selectAllAnonymousBoard(Map<String, Object> param);

	public int selectTotalBoardCount();

	public Attachment selectOneAttachment(int no);

	public int deleteAttachment(int delFileNo);

	public List<BoardComment> selectBoardCommentList(int no);
	
	public int updateReadCount(int no);

	public int insertBoardComment(BoardComment bc);
	
	public List<Board> searchBoard(Map<String, Object> param);

	public int insertAnonymousBoard(Board board);

	public int insertNotice(Board board);

	public List<Board> searchNotice(Map<String, Object> param);

	public List<Board> searchAnonymousBoard(Map<String, Object> param);

	public int updateBoardLikeCount(int no);

	public Board selectOneAnonyBoard(int no);

	public int insertAnonyBoardComment(BoardComment bc);

	public List<BoardComment> selectAnonyBoardCommentList(int no);

	public int updateAnonyBoardLikeCount(int no);

	public int updateAnonyReadCount(int no);

	public Board selectOneNotice(int no);

	public int updateNoticeReadCount(int no);

	public int deleteBoardComment(int no);

	public int deleteAnonymousBoard(int no);

	public int deleteNotice(int no);

	public Attachment selectAttachment(int no);
	
	public Board selectOneAnonymousBoard(int no);

	public Attachment selectOneAnonymousAttachment(int delFileNo);

	public int deleteAnonymousAttachment(int delFileNo);

	public int updateAnonymousBoard(Board board);

	public int updateNotice(Board board);

	public List<Board> selectBoardMain();

	public List<Board> selectTopBoardMain();


	
}
