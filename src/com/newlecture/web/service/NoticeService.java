package com.newlecture.web.service;

import java.util.List;
import com.newlecture.web.entity.Notice;

public interface NoticeService {
	List<Notice> getNoticeList();
	List<Notice> getNoticeList(int page);
	List<Notice> getNoticeList(String field, String query);
	List<Notice> getNoticeList(int page, String field, String query);
	
	Notice getNotice(int id);
	Notice getNextNotice(int id);
	Notice getPriviewNotice(int id);
	
	
	int addNotice(Notice notice);
	int updateNotice(Notice notice);
	int deleteNotices(int id);
	int deleteNotices(int[] ids);
	int pubNotices(int[] ids);
}
