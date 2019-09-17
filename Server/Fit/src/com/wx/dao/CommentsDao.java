package com.wx.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wx.entity.Comments;
import com.wx.entity.NewsListItem;
import com.wx.utils.JdbcUtils;


public class CommentsDao {

	private String sql = "";
	
	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();
	
	public List<NewsListItem> getCommentsList(String userId) {
		try {
			sql= "SELECT DISTINCT c.newsId, title, username FROM `user` a, news b, comments c WHERE a.userId = b.userId AND b.newsId = c.newsId AND a.userId = ?;";
			return queryRunner.query(sql, new BeanListHandler<NewsListItem>(NewsListItem.class), userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Comments> getCommentsByNewsId(String newsId) {
		try {
			sql= "SELECT commentId, username, replyUser, `comment`, commentTime FROM `user` a, comments b WHERE a.userId = b.userId AND newsId = ? ORDER BY commentTime DESC;";
			return queryRunner.query(sql, new BeanListHandler<Comments>(Comments.class), newsId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean addNewComment(String userId, String newsId, String comment, String replyUser) {
		try {
			sql= "INSERT INTO comments (newsId, userId, comment, replyUser) VALUES (?, ?, ?, ?);";
			return queryRunner.update(sql, newsId, userId, comment, replyUser) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
