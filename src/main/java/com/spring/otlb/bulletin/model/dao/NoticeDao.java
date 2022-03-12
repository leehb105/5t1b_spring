package com.spring.otlb.bulletin.model.dao;

import java.util.List;

import com.spring.otlb.bulletin.model.vo.Notice;

public interface NoticeDao {

	List<Notice> selectNoticeMain();

}
