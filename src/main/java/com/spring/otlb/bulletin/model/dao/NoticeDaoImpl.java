package com.spring.otlb.bulletin.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.bulletin.model.vo.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Notice> selectNoticeMain() {
		return session.selectList("notice.selectNoticeMain");
	}

}
