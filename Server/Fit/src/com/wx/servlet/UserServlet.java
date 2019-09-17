package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wx.servlet.BaseMobileServlet;
import com.wx.entity.User;

public class UserServlet extends BaseMobileServlet {

	private static final long serialVersionUID = 358651300184847071L;
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.login(username, password);
		Gson gson = new Gson();
		String json = "";
		if (user == null) {
			json = ERROR;
		} else {
			json = gson.toJson(user);
		}
		return json;
	}

	public String register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = packageEntity(request);
		Gson gson = new Gson();
		String json = "";
		
		if (userDao.isExist(user.getUsername())) {
			json = "The username is existed";
		} else if (userDao.register(user)) {
			json = gson.toJson(user);
		} else {
			json = "Registration failed, please try again later";
		}
		return json;
	}
	
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = packageEntity(request);
		Gson gson = new Gson();
		String json = "";
		if (userDao.updateUser(user)) {
			json = gson.toJson(user);
		} else {
			json = "Update failed";
		}
		return json;
	}
	
	private User packageEntity(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setHeight(Double.parseDouble(height));
		user.setWeight(Double.parseDouble(weight));
		user.setSex(sex);
		return user;
	}
}
