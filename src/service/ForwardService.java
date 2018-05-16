package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardService {
	
	// フォワード処理
	public ForwardService(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	// フォワード処理（メッセージあり）
	public ForwardService(HttpServletRequest request, HttpServletResponse response, String path, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
