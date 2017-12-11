package com.reglog.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.reglog.domain.User;
import com.reglog.utils.JDBCUtils;

public class UserDao {
	public List<User> selectAll() {
		return null;
	}

//	 插入
	 public void insert(User user) {
		 QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		 String sql = "insert into userinfo (username,password,nickname,email) values(?,?,?,?)";
		 Object[] params={user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail()};
		 
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	 }

	@SuppressWarnings("deprecation")
	public User selectOne(User user) {
		
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from userinfo where username=? and password=?";
		Object[] params = { user.getUsername(), user.getPassword() };
		
		try {

			User loginUser = runner.query(sql, params, new BeanHandler<User>(
					User.class));
			return loginUser;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public User selectOneByUsername(User user) {
		
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from userinfo where username=?";
		Object param = user.getUsername();

		try {

			User query = runner.query(sql, param, new BeanHandler<User>(User.class));
			return query;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings("deprecation")
	public User selectOneByEmail(User user) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from userinfo where email=?";
		Object param = user.getEmail();

		try {

			User query = runner.query(sql, param, new BeanHandler<User>(
					User.class));
			return query;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
