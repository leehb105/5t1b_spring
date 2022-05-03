package com.spring.otlb.bulletin.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.bulletin.model.dao.NoticeDao;
import com.spring.otlb.bulletin.model.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public List<Notice> selectNoticeMain() {
		return noticeDao.selectNoticeMain();
	}

	@Override
	public List<Board> selectAllNotice(Map<String, Object> param) {
		return noticeDao.selectAllNotice(param);
	}

	@Override
	public int selectTotalNoticeCount() {
		return noticeDao.selectTotalNoticeCount();
	}

	@Override
	public int updateReadCount(int no) {
		return noticeDao.updateReadCount(no);
	}

	@Override
	public Board selectOneNotice(int no) {
		return noticeDao.selectOneNotice(no);
	}
}
