package com.otlb.semi.bulletin.model.service;


import static com.otlb.semi.common.JdbcTemplate.close;
import static com.otlb.semi.common.JdbcTemplate.commit;
import static com.otlb.semi.common.JdbcTemplate.getConnection;
import static com.otlb.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.otlb.semi.bulletin.model.dao.BulletinDao;
import com.otlb.semi.bulletin.model.vo.Attachment;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.bulletin.model.vo.BoardComment;
import com.otlb.semi.bulletin.model.vo.Notice;

import oracle.net.nt.TcpNTAdapter;

public class BulletinService {

	public static final String hasDeleted = "Y";
	public static final String hasNotDeleted = "N";
	public static final String isReported = "Y";
	public static final String isNotReported = "N";
	
	private BulletinDao bulletinDao = new BulletinDao();

	public int insertBoard(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.insertBoard(conn, board);
			System.out.println(" insertBoard전");
			
			// 방금 insert된 boardNo 조회 : select seq_board_no.currval from dual
			int boardNo = bulletinDao.selectLastBoardNo(conn);
			System.out.println("[bulletinService] boardNo = " + boardNo);
			board.setNo(boardNo);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null) {
				// insert into attachment values(seq_attachment_no.nextval, ?, ?, default, 0)
				for(Attachment attach : attachments) {
					attach.setBoardNo(boardNo); // FK컬럼값 설정(중요)
					result = bulletinDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		System.out.println("return 전");
		return result;
	}

	public int deleteBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = bulletinDao.deleteBoard(conn, no);
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Board selectOneBoard(int no) {
		Connection conn = getConnection();
		Board board = bulletinDao.selectOneBoard(conn, no);
		List<Attachment> attachments = bulletinDao.selectAttachmentByBoardNo(conn, no);
		System.out.println(attachments);
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

	public int updateBoard(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.updateBoard(conn, board);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = bulletinDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Board> selectAllBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.selectAllBoard(conn, param);
		close(conn);
		return list;
	}
	
	public List<Board> selectAllNotice(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.selectAllNotice(conn, param);
		close(conn);
		return list;
	}
	
	public List<Board> selectAllAnonymousBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.selectAllAnonymousBoard(conn, param);
		close(conn);
		return list;
	}

	
	public int selectTotalBoardCount() {
		Connection conn = getConnection();
		int totalCount = bulletinDao.selectTotalBoardCount(conn);
		close(conn);
		return totalCount;
	}


	public Attachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = bulletinDao.selectOneAttachment(conn, no);
		close(conn);
		return attach;

	}

	public int deleteAttachment(int delFileNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.deleteAttachment(conn, delFileNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<BoardComment> selectBoardCommentList(int no) {
		Connection conn = getConnection();
		List<BoardComment> boardCommentList = bulletinDao.selectBoardCommentList(conn, no);
		close(conn);
		
		return boardCommentList;
	}
	
	public int updateReadCount(int no) {
		Connection conn = getConnection();
		int result = bulletinDao.updateReadCount(conn,no);
		close(conn);
		return result;
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.insertBoardComment(conn, bc);	
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public List<Board> searchBoard(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.searchBoard(conn, param);
		close(conn);
		return list;
	}

	public int insertAnonymousBoard(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.insertAnonymousBoard(conn, board);
			
			// 방금 insert된 boardNo 조회 : select seq_board_no.currval from dual
			int boardNo = bulletinDao.selectLastAnonymousBoardNo(conn);
			System.out.println("[bulletinService] boardNo = " + boardNo);
			board.setNo(boardNo);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null) {
				// insert into attachment values(seq_attachment_no.nextval, ?, ?, default, 0)
				for(Attachment attach : attachments) {
					attach.setBoardNo(boardNo); // FK컬럼값 설정(중요)
					result = bulletinDao.insertAnonymousAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertNotice(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.insertNotice(conn, board);
			//int boardNo = bulletinDao.selectLastNoticeNo(conn);
			//board.setNo(boardNo);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Board> searchNotice(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.searchNotice(conn, param);
		close(conn);
		return list;
	}

	public List<Board> searchAnonymousBoard(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> list = bulletinDao.searchAnonymousBoard(conn, param);
		close(conn);
		return list;
	}

	public int updateBoardLikeCount(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.updateBoardLikeCount(conn, no);	
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Board selectOneAnonyBoard(int no) {
		Connection conn = getConnection();
		Board board = bulletinDao.selectOneAnonyBoard(conn, no);
		List<Attachment> attachments = bulletinDao.selectAttachmentByBoardNo(conn, no);
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

	public int insertAnonyBoardComment(BoardComment bc) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.insertAnonyBoardComment(conn, bc);	
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<BoardComment> selectAnonyBoardCommentList(int no) {
		Connection conn = getConnection();
		List<BoardComment> boardCommentList = bulletinDao.selectAnonyBoardCommentList(conn, no);
		close(conn);
		
		return boardCommentList;
	}

	public int updateAnonyBoardLikeCount(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.updateAnonyBoardLikeCount(conn, no);	
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateAnonyReadCount(int no) {
		Connection conn = getConnection();
		int result = bulletinDao.updateAnonyReadCount(conn,no);
		close(conn);
		return result;
	}

	public Board selectOneNotice(int no) {
		Connection conn = getConnection();
		Board board = bulletinDao.selectOneNotice(conn, no);
		close(conn);
		return board;
	}

	public int updateNoticeReadCount(int no) {
		Connection conn = getConnection();
		int result = bulletinDao.updateNoticeReadCount(conn,no);
		close(conn);
		return result;
	}

	public int deleteBoardComment(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.deleteBoardComment(conn, no);	
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAnonymousBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = bulletinDao.deleteAnonymousBoard(conn, no);
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteNotice(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = bulletinDao.deleteNotice(conn, no);
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public Attachment selectAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = bulletinDao.selectAttachment(conn, no);
		close(conn);
		return attach;
	}
	public Board selectOneAnonymousBoard(int no) {
		Connection conn = getConnection();
		Board board = bulletinDao.selectOneAnonymousBoard(conn, no);
		List<Attachment> attachments = bulletinDao.selectAttachmentByAnonymousBoardNo(conn, no);
		System.out.println(attachments);
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

	public Attachment selectOneAnonymousAttachment(int delFileNo) {
		Connection conn = getConnection();
		Attachment attach = bulletinDao.selectOneAnonymousAttachment(conn, delFileNo);
		close(conn);
		return attach;
	}

	public int deleteAnonymousAttachment(int delFileNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.deleteAnonymousAttachment(conn, delFileNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateAnonymousBoard(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.updateAnonymousBoard(conn, board);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = bulletinDao.insertAnonymousAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateNotice(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = bulletinDao.updateNotice(conn, board);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;

	}


	
}
