package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.Notice;

public interface NoticeDao {

	List<Notice> selectNoticeMain();

    List<Board> selectAllNotice(Map<String, Object> param);

    int selectTotalNoticeCount();

    int updateReadCount(int no);

    Board selectOneNotice(int no);

    int deleteNotice(int no);

    int updateNotice(Board board);

    int insertNotice(Board board);
}
