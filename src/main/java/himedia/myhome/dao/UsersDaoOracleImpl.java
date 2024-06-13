package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class UsersDaoOracleImpl extends BaseDao implements UsersDao {
	private String dbuser;
	private String dbpass;

	public UsersDaoOracleImpl(String dbuser, String dbpass) {
		super(dbuser, dbpass);
	}


	@Override
	public List<UserVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<UserVo> list = new ArrayList<>();

		try {
			// connection
			conn = getConnection();
			// statement 생성
			stmt = conn.createStatement();
			// 쿼리 전송
			String sql = "SELECT * FROM users ORDER BY created_at DESC";
			// 결과 셋
			rs = stmt.executeQuery(sql);
			// 결과 셋 -> 자바 객체로 전환
			while (rs.next()) { // 뒤쪽 레코드를 받아옴
				// Java 객체로 전환
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				Date createdAt = rs.getDate("created_at");

				UserVo vo = new UserVo(no, name, password, gender, email, createdAt);

				list.add(vo);
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean insert(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			// 커넥션 획득
			conn = getConnection();
			// 실행 계획
			String sql = "INSERT INTO users (no, name, password, gender, email, created_at) "
					+ "values (seq_users_pk.nextval, ?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			// 데이터 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getGender());
			pstmt.setString(4, userVo.getEmail());
			// 확정된 쿼리 수행
			insertedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean update(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;

		try {
			conn = getConnection();
			String sql = "Update users SET name=?, password=?, gender=?, email=? WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getGender());
			pstmt.setString(4, userVo.getEmail());
			pstmt.setLong(5, userVo.getNo());

			updatedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == updatedCount;
	}

	@Override
	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM users WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			deletedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == deletedCount;
	}

	@Override
	public UserVo getUserByIdAndPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo userVo = null;

		try {
			conn = getConnection();
			
			String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
	
			if (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date createdAt = rs.getDate("created_at");

				userVo = new UserVo(no, name, password, gender, email, createdAt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}

}
