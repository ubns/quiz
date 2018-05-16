package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.UserBean;
import model.dao.UserDao;
import service.ForwardService;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getリクエストの場合はセッションを切りログインページへ
		HttpSession session = request.getSession(false);
		session.invalidate();
		request.setAttribute("message", "ログアウトしました。");
		new ForwardService(request,response,"/view/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Postリクエストの場合は認証処理へ
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		// 入力チェック
		if (name != null ) {  // ユーザ名欄のチェック
			if (pass != null) {  // パスワード欄のチェック
				// ログインチェック
				UserDao dao = new UserDao();
				List<UserBean> list = dao.findByUser(name, pass);
				if (list == null || list.size() != 1) {
					request.setAttribute("message", "ユーザ名またはパスワードが違います");
					new ForwardService(request,response,"/view/login.jsp");
				}
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", "true");
				session.setAttribute("name", list.get(0).getName());
				
				// 管理者と一般ユーザの振り分け
				if (list.get(0).getRole().equals("管理者")) {
					new ForwardService(request,response,"/auth/dashboard.jsp");
				} else if (list.get(0).getRole().equals("一般")) {
					new ForwardService(request,response,"/user/mypage.jsp");
				} else {
					new ForwardService(request,response,"/view/login.jsp");
				}

			} else {
				request.setAttribute("message", "パスワードを入力してください");
				new ForwardService(request,response,"/view/login.jsp");
			}
		} else {
			request.setAttribute("message", "ユーザ名を入力してください");
			new ForwardService(request,response,"/view/login.jsp");
		}
	}

}
