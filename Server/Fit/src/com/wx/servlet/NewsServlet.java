package com.wx.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.wx.servlet.BaseMobileServlet;
import com.wx.entity.Comments;
import com.wx.entity.News;
import com.wx.entity.NewsDetail;
import com.wx.entity.NewsListForFound;


public class NewsServlet extends BaseMobileServlet {

	private static final long serialVersionUID = -6810618231374558214L;

	public String releaseNewsWithImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(5 * 1024 * 1024);

		upload.setSizeMax(20 * 1024 * 1024);

		upload.setHeaderEncoding("utf-8");

		News news = new News();

		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					if (name.equals("userId")) {
						news.setUserId(Integer.parseInt(value));
					} else if (name.equals("title")) {
						news.setTitle(value);
					} else if (name.equals("content")) {
						news.setContent(value);
					}
					System.out.println(name + " : " + value);
				} else {
					String fileName = item.getName();
					fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
					news.setImage(fileName);

					String basePath = "C:\\DJZHAO\\WORK\\Workspaces\\FileUpload\\fitness";

					File file = new File(basePath, fileName);

					item.write(file);

					item.delete();
				}
			}

			if (newsDao.releaseNews(news)) {
				return "success";
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	public String releaseNewsWithoutImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String userId = request.getParameter("userId");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			News news = new News();
			news.setContent(content);
			news.setTitle(title);
			news.setUserId(Integer.parseInt(userId));
			if (newsDao.releaseNews(news)) {
				return "success";
			}
		return "error";
	}
	
	public String getNewsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NewsListForFound> newsList = newsDao.getNewsList(10);
		Gson gson = new Gson();
		String json = gson.toJson(newsList);
		return json;
	}
	
	public String getNewsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String newsId = request.getParameter("newsId");
		NewsDetail newsDetail = newsDao.getNewsDetail(newsId);
		if (newsDetail != null) {
			List<Comments> comments = commentsDao.getCommentsByNewsId(newsId);
			newsDetail.setComments(comments);
			Gson gson = new Gson();
			String json = gson.toJson(newsDetail);
			return json;
		} else {
			return ERROR;
		}
	}
}
