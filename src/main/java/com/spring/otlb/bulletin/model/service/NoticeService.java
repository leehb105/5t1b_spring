package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Board;
import com.spring.otlb.bulletin.model.vo.Notice;

public interface NoticeService {

	List<Notice> selectNoticeMain();


	List<Board> selectAllNotice(Map<String, Object> param);

	int selectTotalNoticeCount();

	int updateReadCount(int no);

	Board selectOneNotice(int no);

}
