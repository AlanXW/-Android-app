package com.wx.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.dao.CommentsDao;
import com.wx.dao.DailyCheckDao;
import com.wx.dao.FavorsDao;
import com.wx.dao.NewsDao;
import com.wx.dao.TrainDao;
import com.wx.dao.UserDao;

public class BaseMobileServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6970143137677892748L;
	String ERROR = "{'info':'error'}";

	UserDao userDao = new UserDao();
	DailyCheckDao dailyCheckDao = new DailyCheckDao();
	CommentsDao commentsDao = new CommentsDao();
	FavorsDao favorsDao = new FavorsDao();
	NewsDao newsDao = new NewsDao();
	TrainDao trainDao = new TrainDao();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			
			String methodName = request.getParameter("method");
			
			Class clazz = this.getClass();
			
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

			Object returnValue = method.invoke(this, request, response);

			response.getWriter().write(returnValue.toString());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(ERROR);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
