package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDao;
import service.ForwardService;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * サインイン画面遷移処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// サインインページへ
		new ForwardService(request,response,"/view/signin.jsp");
	}

	/**
	 * アカウント登録処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 入力チェック
		if (request.getParameter("name") == null || request.getParameter("name").equals("")) {
			new ForwardService(request,response,"/view/signin.jsp", "ユーザ名を入力してください");
			return;
		} 
		if (request.getParameter("pass") == null || request.getParameter("pass").equals("")) {
			new ForwardService(request,response,"/view/signin.jsp", "パスワードを入力してください");
			return;
		}
		
		if (new UserDao().createUser(request.getParameter("name"), request.getParameter("pass"))) {
			new ForwardService(request,response,"/view/login.jsp","アカウント登録が完了しました");
		} else {
			new ForwardService(request,response,"/view/signin.jsp","アカウントが登録できませんでした");
		}
	}

}
