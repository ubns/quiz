package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.beans.UserBean;

public class UserDao {
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	List<UserBean> list;
	private final String AUTH = "SELECT * FROM user WHERE name = ? AND pass = ?";
	private final String CREATEUSER = "INSERT INTO user(name,pass,role) VALUES (?,?,'一般')";
	
	public UserDao() {
		getConnection();
	}
	
	// ユーザ名とパスワードがDBのデータと一致するかチェック
	public List<UserBean> findByUser(String name,String pass) {
		if(name == null || pass == null) return null; // nullチェック
		if (con == null) {
			getConnection();
		}
		
		try {
			st = con.prepareStatement(AUTH);
			st.setString(1, name);
			st.setString(2, pass);
			rs = st.executeQuery();
			list = new ArrayList<UserBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dname = rs.getString("name");
				String dpass = rs.getString("pass");
				String role = rs.getString("role");
				UserBean bean = new UserBean(id,dname,dpass,role);
				list.add(bean);
			}
			
			if (list.size() != 1) return null;  // ログイン失敗
			
		} catch (SQLException e) {
			System.out.println("データの取得に失敗しました");
		} finally {
			// リソース解放
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				System.out.println("リソース解放に失敗しました");
			}
		}
		return list;
	}
	
	public boolean createUser(String name, String pass) {
		if(name == null || pass == null) return false; // nullチェック
		if (con == null) {
			getConnection();
		}
		
		try {
			st = con.prepareStatement(CREATEUSER);
			st.setString(1, name);
			st.setString(2, pass);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("データの登録に失敗しました。" + e);
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) st.close();
			} catch (SQLException e) {
					System.out.println("リソースの解放に失敗しました。" + e);
			}
		}
		return true;
	}
	
	public void getConnection() {
		try {
			ResourceBundle bundle    = ResourceBundle.getBundle("application");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(bundle.getString("URL"),bundle.getString("USER"),bundle.getString("PASS"));
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}catch (SQLException e ) {
			e.printStackTrace();
		}
	}
}
