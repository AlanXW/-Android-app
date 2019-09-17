package com.wx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wx.servlet.BaseMobileServlet;
import com.wx.entity.NewsListItem;


public class CommentServet extends BaseMobileServlet {

	private static final long serialVersionUID = -8280552315331051134L;

	public String getCommentsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<NewsListItem> list = commentsDao.getCommentsList(userId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	public String addNewComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String newsId = request.getParameter("newsId");
		String comment = request.getParameter("comment");
		String replyUser = request.getParameter("replyUser");
		if (commentsDao.addNewComment(userId, newsId, comment, replyUser)) {
			return "Comment Successfully!";
		} else {
			return "error";
		}
	}

}
