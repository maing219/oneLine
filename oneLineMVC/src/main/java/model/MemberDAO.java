package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class MemberDAO {
	private static MemberDAO instance;

	public MemberDAO() {

	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();

		return instance;
	}

	public void addMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(?, ?, ?)";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
	}

	public MemberDTO loginMember(String id, String password) {
		Connection conn = null;
		MemberDTO loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			sql = "select * from member where id=? and password=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginMember = new MemberDTO();
				loginMember.setId(rs.getString("id"));
				loginMember.setPassword(rs.getString("password"));
				loginMember.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}

		return loginMember;
	}

	public void updateMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update member set password=?, name=? where id=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
	}
	
	public MemberDTO updateFormMember(String sessionId) {
		MemberDTO member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			sql = "select * from member where id=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
		
		return member;
	}
	
	public void deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from member where id=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
	}
}
