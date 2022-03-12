package com.spring.otlb.bulletin.model.service;

import java.util.List;

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
}
