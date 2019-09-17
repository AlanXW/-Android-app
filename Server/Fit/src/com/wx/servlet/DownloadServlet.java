package com.wx.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -1120930992495743033L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("getNewsImage".equals(method)) {
			getNewsImage(request, response);
		}
	}

	public void getNewsImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("imageName");
		
		String basePath = "C:\\DJZHAO\\WORK\\Workspaces\\FileUpload\\fitness";
		
		File file = new File(basePath, fileName);

		if (!file.exists()) {
			response.getWriter().write("error");
			return;
		}

		InputStream in = new FileInputStream(file);

		fileName = URLEncoder.encode(fileName.substring(fileName.lastIndexOf('#') + 1), "UTF-8");

		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		OutputStream out = response.getOutputStream();
		byte[] buff = new byte[1024];
		int len = -1;
		while ((len = in.read(buff)) != -1) {
			out.write(buff, 0, len);
		}

		out.close();
		in.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
