package com.otlb.semi.bulletin.model.dao;


import static com.otlb.semi.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import com.otlb.semi.bulletin.model.exception.BulletinException;
import com.otlb.semi.bulletin.model.vo.Attachment;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.bulletin.model.vo.BoardComment;
import com.otlb.semi.bulletin.model.vo.Notice;
import com.otlb.semi.emp.model.vo.Emp;

public class BulletinDao {

	private Properties prop = new Properties();
	
	public BulletinDao() {
		String filepath = BulletinDao.class.getResource("/bulletin-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시글 등록 실패", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int selectLastBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastBoardNo");
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				boardNo = rset.getInt(1);
		} catch (SQLException e) {
			throw new BulletinException("최근 게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2, attach.getRenamedFilename());
			pstmt.setInt(3, attach.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Board> selectAllBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoard");
		ResultSet rset = null;
		List<Board> list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setNo(rset.getInt("no"));
				board.setCategory(rset.getString("category"));
				board.setTitle(rset.getString("title"));
				
				Emp emp = new Emp();
				emp.setEmpNo(rset.getInt("emp_no"));
				emp.setEmpName(rset.getString("emp_name"));
				board.setEmp(emp);				
				board.setEmpNo(rset.getInt("emp_no"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getTimestamp("reg_date"));
				board.setLikeCount(rset.getInt("like_count"));
				board.setReadCount(rset.getInt("read_count"));
				
				board.setCommentCount(rset.getInt("comment_count"));
				board.setAttachCount(rset.getInt("attach_count"));
				
				list.add(board);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Board> selectAllNotice(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllNotice");
		ResultSet rset = null;
		List<Board> list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setNo(rset.getInt("no"));
//				board.setCategory(rset.getString("category"));
				board.setTitle(rset.getString("title"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("emp_name"));
				board.setEmp(emp);				
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getTimestamp("reg_date"));
//				board.setLikeCount(rset.getInt("like_count"));
				board.setReadCount(rset.getInt("read_count"));
				
//				board.setCommentCount(rset.getInt("comment_count"));
//				board.setAttachCount(rset.getInt("attach_count"));
				
				list.add(board);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Board> selectAllAnonymousBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllAnonymousBoard");
		ResultSet rset = null;
		List<Board> list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
//				Emp emp = new Emp();
//				emp.setEmpName(rset.getString("emp_name"));
//				board.setEmp(emp);
				board.setCategory(rset.getString("category"));
				board.setRegDate(rset.getTimestamp("reg_date"));
				board.setLikeCount(rset.getInt("like_count"));
				board.setReadCount(rset.getInt("read_count"));
				
				board.setCommentCount(rset.getInt("comment_count"));
				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Attachment> selectAttachmentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachmentByBoardNo");
		List<Attachment> attachments = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attach.setBoardNo(rset.getInt("board_no"));
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attachments;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시판 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectTotalBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalBoardCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;

	}

	public int deleteAttachment(Connection conn, int delFileNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delFileNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Attachment selectOneAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneAttachment");
		ResultSet rset = null;
		Attachment attach = new Attachment();

		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt Dao = " + pstmt );
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			System.out.println("rset = "+ rset);
			
			if(rset.next()){
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));

			}
			System.out.println("if문끝 =" + attach);
		}catch(Exception e){
			throw new BulletinException("첨부파일 조회 오류!", e);
		}finally{
			close(rset);
			close(pstmt);
			
		}
		return attach;
	}

	
	public Board selectOneBoard(Connection conn, int no) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectOneBoard");
        Board board = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
			pstmt.setInt(2, no);

            rset = pstmt.executeQuery();
            while(rset.next()) {
                board = new Board();
                board.setNo(rset.getInt("no"));
                board.setTitle(rset.getString("title"));
                board.setContent(rset.getString("content"));
                board.setRegDate(rset.getTimestamp("reg_date"));
                board.setReadCount(rset.getInt("read_count"));
                board.setLikeCount(rset.getInt("like_count"));
                board.setReportYn(rset.getString("report_yn"));
                board.setEmpNo(rset.getInt("emp_no"));
                board.setCategory(rset.getString("category"));
                board.setDeleteYn(rset.getString("delete_yn"));
                board.setCommentCount(rset.getInt("count"));
                
                Emp emp = new Emp();
                emp.setEmpName(rset.getString("emp_name"));
                emp.setDeptName(rset.getString("dept_name"));
                board.setEmp(emp);
            }
        } catch (SQLException e) {
            throw new BulletinException("게시판 조회 오류", e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return board;
    }

	public List<BoardComment> selectBoardCommentList(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoardCommentList");
		ResultSet rset = null;
		List<BoardComment> boardCommentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				BoardComment bc = new BoardComment();
				bc.setNo(rset.getInt("no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setContent(rset.getString("content"));
				bc.setReportYn(rset.getString("report_yn"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setRegDate(rset.getTimestamp("reg_date"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setEmpNo(rset.getInt("emp_no"));
				bc.setDeleteYn(rset.getString("delete_yn"));
				
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("emp_name"));
				emp.setDeptName(rset.getString("dept_name"));
				
				bc.setEmp(emp);
				
				boardCommentList.add(bc);
			}
		} catch (SQLException e) {
			throw new BulletinException("게시판 댓글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardCommentList;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getCommentLevel());
			pstmt.setString(2, bc.getContent());
			//정수형 null 처리
			pstmt.setObject(3, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			pstmt.setInt(4, bc.getBoardNo());
			pstmt.setInt(5, bc.getEmpNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BulletinException("댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Board> searchBoard(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchBoard");
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		
		String searchType = (String) param.get("searchType");
		String searchKeyword = (String) param.get("searchKeyword");
		switch(searchType) {
		case "title": sql += " title like '%" + searchKeyword + "%'"; break;
		case "empName": sql += " emp_name like '%" + searchKeyword + "%'"; break;
		case "category": sql += " category like '%" + searchKeyword + "%'"; break;
		}
		System.out.println("sql@dao = " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = new Board();
				board.setNo(rset.getInt("no"));
				board.setCategory(rset.getString("category"));
				board.setTitle(rset.getString("title"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("emp_name"));
				board.setEmp(emp);
//				board.setEmpName(rset.getString("emp_name"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getTimestamp("reg_date"));
				board.setLikeCount(rset.getInt("like_count"));
				board.setReadCount(rset.getInt("read_count"));
				
				board.setCommentCount(rset.getInt("comment_count"));
				board.setAttachCount(rset.getInt("attach_count"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertAnonymousBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAnonymousBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시글 등록 실패", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastAnonymousBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastAnonymousBoardNo");
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				boardNo = rset.getInt(1);
		} catch (SQLException e) {
			throw new BulletinException("최근 게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int insertAnonymousAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAnonymousAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2, attach.getRenamedFilename());
			pstmt.setInt(3, attach.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시글 등록 실패", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Board> searchNotice(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchNotice");
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		
		String searchType = (String) param.get("searchType");
		String searchKeyword = (String) param.get("searchKeyword");
		switch(searchType) {
		case "title": sql += " title like '%" + searchKeyword + "%'"; break;
		}
		System.out.println("sql@dao = " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = new Board();
				
				board.setNo(rset.getInt("no"));
//				board.setCategory(rset.getString("category"));
				board.setTitle(rset.getString("title"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("emp_name"));
				board.setEmp(emp);				
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getTimestamp("reg_date"));
//				board.setLikeCount(rset.getInt("like_count"));
				board.setReadCount(rset.getInt("read_count"));
				
//				board.setCommentCount(rset.getInt("comment_count"));
//				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}
	
	public List<Board> searchAnonymousBoard(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchAnonymousBoard");
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		
		String searchType = (String) param.get("searchType");
		String searchKeyword = (String) param.get("searchKeyword");
		switch(searchType) {
		case "title": sql += " title like '%" + searchKeyword + "%'"; break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
			Board board = new Board();
			
			board.setNo(rset.getInt("no"));
			board.setTitle(rset.getString("title"));
			board.setContent(rset.getString("content"));
//			Emp emp = new Emp();
//			emp.setEmpName(rset.getString("emp_name"));
//			board.setEmp(emp);
			board.setCategory(rset.getString("category"));
			board.setRegDate(rset.getTimestamp("reg_date"));
			board.setLikeCount(rset.getInt("like_count"));
			board.setReadCount(rset.getInt("read_count"));
			
			board.setCommentCount(rset.getInt("comment_count"));
			board.setAttachCount(rset.getInt("attach_count"));
			list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
	}

	public int updateBoardLikeCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoardLikeCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BulletinException("좋아요 +1 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Board selectOneAnonyBoard(Connection conn, int no) {
		 PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        String sql = prop.getProperty("selectOneAnonyBoard");
	        Board board = null;

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, no);
				pstmt.setInt(2, no);

	            rset = pstmt.executeQuery();
	            while(rset.next()) {
	                board = new Board();
	                board.setNo(rset.getInt("no"));
	                board.setTitle(rset.getString("title"));
	                board.setContent(rset.getString("content"));
	                board.setRegDate(rset.getTimestamp("reg_date"));
	                board.setReadCount(rset.getInt("read_count"));
	                board.setLikeCount(rset.getInt("like_count"));
	                board.setReportYn(rset.getString("report_yn"));
	                board.setEmpNo(rset.getInt("emp_no"));
	                board.setCategory(rset.getString("category"));
	                board.setDeleteYn(rset.getString("delete_yn"));
	                board.setCommentCount(rset.getInt("count"));
	                
	                Emp emp = new Emp();
	                emp.setEmpName(rset.getString("emp_name"));
	                emp.setDeptName(rset.getString("dept_name"));
	                board.setEmp(emp);
	            }
	        } catch (SQLException e) {
	            throw new BulletinException("게시판 조회 오류", e);
	        } finally {
	            close(rset);
	            close(pstmt);
	        }
	        return board;
	}

	public int insertAnonyBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAnonyBoardComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getCommentLevel());
			pstmt.setString(2, bc.getContent());
			//정수형 null 처리
			pstmt.setObject(3, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			pstmt.setInt(4, bc.getBoardNo());
			pstmt.setInt(5, bc.getEmpNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BulletinException("댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardComment> selectAnonyBoardCommentList(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAnonyBoardCommentList");
		ResultSet rset = null;
		List<BoardComment> boardCommentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				BoardComment bc = new BoardComment();
				bc.setNo(rset.getInt("no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setContent(rset.getString("content"));
				bc.setReportYn(rset.getString("report_yn"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setRegDate(rset.getTimestamp("reg_date"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setEmpNo(rset.getInt("emp_no"));
				bc.setDeleteYn(rset.getString("delete_yn"));
				
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("emp_name"));
				emp.setDeptName(rset.getString("dept_name"));
				
				bc.setEmp(emp);
				
				boardCommentList.add(bc);
			}
		} catch (SQLException e) {
			throw new BulletinException("게시판 댓글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardCommentList;
	}

	public int updateAnonyBoardLikeCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAnonyBoardLikeCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BulletinException("좋아요 +1 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAnonyReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAnonyReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Board selectOneNotice(Connection conn, int no) {
		 PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        String sql = prop.getProperty("selectOneNotice");
	        Board board = null;

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, no);

	            rset = pstmt.executeQuery();
	            while(rset.next()) {
	                board = new Board();
	                board.setNo(rset.getInt("no"));
	                board.setTitle(rset.getString("title"));
	                board.setContent(rset.getString("content"));
	                board.setRegDate(rset.getTimestamp("reg_date"));
	                board.setReadCount(rset.getInt("read_count"));
	                board.setEmpNo(rset.getInt("emp_no"));
	                board.setDeleteYn(rset.getString("delete_yn"));
	                
	            }
	        } catch (SQLException e) {
	            throw new BulletinException("게시판 조회 오류", e);
	        } finally {
	            close(rset);
	            close(pstmt);
	        }
	        return board;
	}

	public int updateNoticeReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNoticeReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("조회수 증가 오류", e); 
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoardComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("댓글 삭제 오류", e); 
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAnonymousBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAnonymousBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteNotice(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectLastNoticeNo(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastNoticeNo");
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				boardNo = rset.getInt(1);
		} catch (SQLException e) {
			throw new BulletinException("최근 게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}


	public Attachment selectAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneAttachment");
		ResultSet rset = null;
		Attachment attach = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			throw new BulletinException("첨부파일 조회 오류!", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public Board selectOneAnonymousBoard(Connection conn, int no) {
		 PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        String sql = prop.getProperty("selectOneAnonymousBoard");
	        Board board = null;

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, no);
				pstmt.setInt(2, no);

	            rset = pstmt.executeQuery();
	            while(rset.next()) {
	                board = new Board();
	                board.setNo(rset.getInt("no"));
	                board.setTitle(rset.getString("title"));
	                board.setContent(rset.getString("content"));
	                board.setRegDate(rset.getTimestamp("reg_date"));
	                board.setReadCount(rset.getInt("read_count"));
	                board.setLikeCount(rset.getInt("like_count"));
	                board.setReportYn(rset.getString("report_yn"));
	                board.setEmpNo(rset.getInt("emp_no"));
	                board.setCategory(rset.getString("category"));
	                board.setDeleteYn(rset.getString("delete_yn"));
	                board.setCommentCount(rset.getInt("count"));
	                
	                Emp emp = new Emp();
	                emp.setEmpName(rset.getString("emp_name"));
	                emp.setDeptName(rset.getString("dept_name"));
	                board.setEmp(emp);
	            }
	        } catch (SQLException e) {
	            throw new BulletinException("게시판 조회 오류", e);
	        } finally {
	            close(rset);
	            close(pstmt);
	        }
	        return board;
	}

	public List<Attachment> selectAttachmentByAnonymousBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachmentByAnonymousBoardNo");
		List<Attachment> attachments = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attach.setBoardNo(rset.getInt("board_no"));
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attachments;
	}

	public Attachment selectOneAnonymousAttachment(Connection conn, int delFileNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneAttachment");
		ResultSet rset = null;
		Attachment attach = null;
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delFileNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			throw new BulletinException("첨부파일 조회 오류!", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int deleteAnonymousAttachment(Connection conn, int delFileNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAnonymousAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delFileNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAnonymousBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateAnonymousBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시판 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BulletinException("게시판 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;

	}

	
}

















