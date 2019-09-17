package com.wx.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wx.entity.NewsListItem;
import com.wx.utils.JdbcUtils;


public class FavorsDao {

	private String sql = "";
	
	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();
	
	public List<NewsListItem> getCommentsList(String userId) {
		try {
			sql= "SELECT b.newsId, title, username FROM `user` a, news b, favors c WHERE a.userId = b.userId AND a.userId = c.userId AND b.newsId = c.newsId AND a.userId = ?;";
			return queryRunner.query(sql, new BeanListHandler<NewsListItem>(NewsListItem.class), userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isfavored(String userId, String newsId) {
		try {
			sql= "SELECT favorId FROM favors WHERE userId = ? AND newsId = ?;";
			return queryRunner.query(sql, new ScalarHandler<Integer>(), userId, newsId) != null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean addNewFavor(String userId, String newsId) {
		try {
			sql= "INSERT INTO favors (newsId, userId) VALUES (?, ?);";
			return queryRunner.update(sql, newsId, userId) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
