package com.wx.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wx.utils.JdbcUtils;


public class DailyCheckDao {

	private String sql = "";

	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();

	public boolean check(String userId) {
		sql = "INSERT INTO dailycheck (userId, checkDate, checkTime) VALUES (?, CURDATE(), CURRENT_TIME());";
		try {
			return queryRunner.update(sql, userId) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isChecked(String userId) {
		sql = "SELECT id FROM dailycheck WHERE userId = ? AND checkDate = CURDATE();";
		try {
			return queryRunner.query(sql, new ScalarHandler<Integer>(), userId) != null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getTotalCheckedDays(String userId) {
		sql = "SELECT COUNT(*) FROM dailycheck WHERE userId = ?;";
		try {
			return queryRunner.query(sql, new ScalarHandler<Long>(), userId).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getTotalTrainingDays(String userId) {
		sql = "SELECT SUM(duration) FROM training WHERE userId = ?;";
		try {
			return queryRunner.query(sql, new ScalarHandler<BigDecimal>(), userId).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public String getCheckedList(String userId) {
		sql = "SELECT checkDate FROM dailycheck WHERE userId = ?;";
		try {
			return queryRunner.query(sql, new ResultSetHandler<String>() {

				@Override
				public String handle(ResultSet rs) throws SQLException {
					StringBuilder sb = new StringBuilder();
					while (rs.next()) {
						sb.append(rs.getString(1)).append(",");
					}
					if (sb.length() > 0){
						return sb.substring(0, sb.length() - 1);
					}
					else {
						return "error";
					}
				}
			}, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
