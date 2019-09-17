package com.wx.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.wx.utils.JdbcUtils;

public class TrainDao {
	private String sql = "";

	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();
	
	public boolean addNewTrainRecord(String userId, String duration) {
		try {
			sql= "INSERT INTO training (userId, duration) VALUES (?, ?);";
			return queryRunner.update(sql, userId, duration) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
