package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.GuestBookVo;

public class GuestBookDao extends BaseDao {
	private String dbuser;
	private String dbpass;

	public GuestBookDao(String dbuser, String dbpass) {
		super(dbuser, dbpass);
	}


	public List<GuestBookVo> getlist()  {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<GuestBookVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM guestbook ORDER BY reg_date DESC";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");
				list.add(new GuestBookVo(no, name,  content, regDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void insert(String name, String password, String content)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO guestbook (no, name, password, content, reg_date) "
				+ "VALUES (seq_guestbook_no.nextval, ?, ?, ?, sysdate)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, content);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean delete(int no, String password)  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "SELECT password FROM guestbook WHERE no = ?";
		String sql2 = "DELETE FROM guestbook WHERE no = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);

			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String storedPassword = rs.getString("password");
				if (password.equals(storedPassword)) {
					pstmt2.setInt(1, no);
					int rowsAffected = pstmt2.executeUpdate();
					return rowsAffected > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
