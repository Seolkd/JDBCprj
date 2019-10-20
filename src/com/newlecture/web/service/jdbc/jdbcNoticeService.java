package com.newlecture.web.service.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

public class jdbcNoticeService implements NoticeService {

	

	@Override
	public List<Notice> getNoticeList() {

		return getNoticeList(1, "title", "");

	}

	@Override
	public List<Notice> getNoticeList(int page) {
		return getNoticeList(page, "title", "");
	}

	@Override
	public List<Notice> getNoticeList(String field, String query) {
		return getNoticeList(1, field, query);
	}

	@Override
	public List<Notice> getNoticeList(int page, String field, String query) {
		List<Notice> list = new ArrayList<Notice>();

		String sql = "SELECT * FROM NOTICE " + 
				"WHERE "+field+" like ? " + 
				"ORDER BY REGDATE DESC " + 
				"OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try {
//			Statement st = jdbcContext.getStatement();
			PreparedStatement st = jdbcContext.getPreparedStatement(sql);
//			st.setString(1, field); // WHERE 'title' LIKE?
			st.setString(1, "%"+query+"%"); // WHERE 'title LIKE
			/*
			 * page 1 : 0, 10
			 * page 2 : 10, 20
			 * */
			st.setInt(2, (page-1)*10); // WHERE 'title LIKE
			st.setInt(3, page*10); // WHERE 'title LIKE
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String content = rs.getString("CONTENT");

				Notice notice = new Notice(id, title, content, writerId, regDate, hit);

				list.add(notice);
			}
			return list;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Notice getNotice(int id) {
		return null;
	}

	@Override
	public Notice getNextNotice(int id) {
		return null;
	}

	@Override
	public Notice getPriviewNotice(int id) {
		return null;
	}

	@Override
	public int addNotice(Notice notice) {
		return 0;
	}

	@Override
	public int updateNotice(Notice notice) {
		return 0;
	}

	@Override
	public int deleteNotices(int id) {
		return 0;
	}

	@Override
	public int deleteNotices(int[] ids) {
		return 0;
	}

	@Override
	public int pubNotices(int[] ids) {
		return 0;
	}

}
