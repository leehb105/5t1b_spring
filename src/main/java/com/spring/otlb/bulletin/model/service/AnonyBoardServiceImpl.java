package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Attachment;
import com.spring.otlb.bulletin.model.vo.BoardComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.bulletin.model.dao.AnonyBoardDao;
import com.spring.otlb.bulletin.model.vo.Board;

@Service
@Slf4j
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
	public int selectTotalAnonyBoardCount(Map<String, Object> param) {
		return anonyBoardDao.selectTotalAnonyBoardCount(param);
	}

	@Override
	public int insertAnonymousBoard(Board board) {
		int result = anonyBoardDao.insertAnonymousBoard(board);
		List<Attachment> list = board.getAttachments();
		if(list != null) {
			for(Attachment attach : list) {
				attach.setBoardNo(board.getNo());
				log.debug("boardNo = {}", attach.getBoardNo());
				result = insertAnonyAttachment(attach);
			}
		}

		return result;
	}

	@Override
	public int insertAnonyAttachment(Attachment attach) {
		return anonyBoardDao.insertAnonyAttachment(attach);
	}

	@Override
	public Board selectAnonyBoardAttachments(int no) {
		return anonyBoardDao.selectAnonyBoardAttachments(no);
	}

	@Override
	public List<BoardComment> selectAnonyBoardCommentList(int no) {
		return anonyBoardDao.selectAnonyBoardCommentList(no);
	}

	@Override
	public int updateReadCount(int no) {
		return anonyBoardDao.updateReadCount(no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return anonyBoardDao.selectOneAttachment(no);
	}

	@Override
	public int updateAnonyBoardLikeCount(int no) {
		return anonyBoardDao.updateAnonyBoardLikeCount(no);
	}

	@Override
	public int deleteAnonymousBoard(int no) {
		return anonyBoardDao.deleteAnonymousBoard(no);
	}

	@Override
	public int insertAnonyBoardComment(BoardComment boardComment) {
		return anonyBoardDao.insertAnonyBoardComment(boardComment);
	}

	@Override
	public int updateAnonymousBoard(Board board) {
		return anonyBoardDao.updateAnonymousBoard(board);
	}

	@Override
	public List<Attachment> selectAttachments(int no) {
		return anonyBoardDao.selectAttachments(no);
	}

	@Override
	public int deleteAnonymousAttachment(int no) {
		return anonyBoardDao.deleteAnonymousAttachment(no);
	}

	@Override
	public int deleteAnonymousBoardComment(int commentNo) {
		return anonyBoardDao.deleteAnonymousBoardComment(commentNo);
	}


}
