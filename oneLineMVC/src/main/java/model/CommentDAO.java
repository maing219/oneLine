package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class CommentDAO {
	private static CommentDAO instance;

	public CommentDAO() {

	}

	public static CommentDAO getInstance() {
		if (instance == null)
			instance = new CommentDAO();

		return instance;
	}

	public int getSeq() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		try {
			conn = DBConnection.getConnection();
			String sql = "select boardcomment_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		
		return result;
	}

	public ArrayList<CommentDTO> getCommentList(int boardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		sql = "select * from board_comment where comment_board=? order by comment_num desc";

		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_id(rs.getString("comment_id"));
				comment.setComment_date(rs.getString("comment_date"));
				comment.setComment_content(rs.getString("comment_content"));
				list.add(comment);
			}
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}

		return list;
	}

	public void insertComment(CommentDTO comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into board_comment values(?, ?, ?, ?, ?)";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getComment_num());
			pstmt.setInt(2, comment.getComment_board());
			pstmt.setString(3, comment.getComment_id());
			pstmt.setString(4, comment.getComment_date());
			pstmt.setString(5, comment.getComment_content());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
	}
	
	public void deleteComment(int comment_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from board_comment where comment_num=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt);
		}
	}

}
