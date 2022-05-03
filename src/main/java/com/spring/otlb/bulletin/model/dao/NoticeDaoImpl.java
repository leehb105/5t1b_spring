package com.spring.otlb.bulletin.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.otlb.bulletin.model.vo.Board;
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

    @Override
    public List<Board> selectAllNotice(Map<String, Object> param) {
        return session.selectList("notice.selectAllNotice", param);
    }

	@Override
	public int selectTotalNoticeCount() {
		return session.selectOne("notice.selectTotalNoticeCount");
	}

	@Override
	public int updateReadCount(int no) {
		return session.update("notice.updateReadCount", no);
	}

	@Override
	public Board selectOneNotice(int no) {
		return session.selectOne("notice.selectOneNotice", no);
	}

}
