package com.wx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wx.servlet.BaseMobileServlet;
import com.wx.entity.NewsListItem;


public class FavorServlet extends BaseMobileServlet {

	private static final long serialVersionUID = -1543111033584564605L;

	public String getFavorsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<NewsListItem> list = favorsDao.getCommentsList(userId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	public String addNewFavor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String newsId = request.getParameter("newsId");
		if (favorsDao.isfavored(userId, newsId)) {
			return "You have already favourite it";
		} else {
			if (favorsDao.addNewFavor(userId, newsId)) {
				return "Favourite successfully";
			} else {
				return "Fail";
			}
		}
	}
	
}
