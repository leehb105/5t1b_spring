package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.otlb.bulletin.model.dao.BoardDao;
import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.BoardComment;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public int insertBoard(Board board) {
		int result = boardDao.insertBoard(board);
		List<Attachment> list = board.getAttachments();
		if(list != null) {
			for(Attachment attach : list) {
				attach.setBoardNo(board.getNo());
				result = insertAttachment(attach);
			}
		}
		
		return result;
		
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}
	
	@Override
	public int deleteBoard(int no) {
		return 0;
	}

	@Override
	public Board selectOneBoard(int no) {
		return boardDao.selectOneBoard(no);
	}

	@Override
	public int updateBoard(Board board) {
		return 0;
	}

	@Override
	public List<Board> selectAllBoard(Map<String, Object> param) {
		return boardDao.selectAllBoard(param);
	}

	@Override
	public List<Board> selectAllNotice(Map<String, Object> param) {
		return null;
	}



	@Override
	public int selectTotalBoardCount() {
		return boardDao.selectTotalBoardCount();
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return null;
	}

	@Override
	public int deleteAttachment(int delFileNo) {
		return 0;
	}

	@Override
	public List<BoardComment> selectBoardCommentList(int no) {
		return boardDao.selectBoardCommentList(no);
	}

	@Override
	public int updateReadCount(int no) {
		return boardDao.updateReadCount(no);
	}

	@Override
	public int insertBoardComment(BoardComment bc) {
		return 0;
	}

	@Override
	public List<Board> searchBoard(Map<String, Object> param) {
		return null;
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
	public int updateBoardLikeCount(int no) {
		return boardDao.updateBoardLikeCount(no);
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
		return boardDao.selectBoardMain();
	}

	@Override
	public List<Board> selectTopBoardMain() {
		return boardDao.selectTopBoardMain();
	}

	@Override
	public Board selectBoardAttachments(int no) {
		return boardDao.selectBoardAttachments(no);
	}


}
