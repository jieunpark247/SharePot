package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QnACommentDAO {

	Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
	// String url =
	// "jdbc:mysql://ktdsdb.ctiouv3q7dtp.us-east-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// // ����Ϸ���
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// �����ͺ��̽����� ������
	// URL ���
	String id = "ktds"; // ����� ����
	String pw = "ktds1234"; // ����� ������ �н�����

	Connection dbconn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public QnACommentDAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

	}

	public List<QnACommentDTO> getCommentQnA(String qna_no) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		String sql = "select * from qna_comment where qna_no=" + qna_no; // no�� ���� ������ ����
		List<QnACommentDTO> qnaCommentlist = new ArrayList<QnACommentDTO>();
		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("qna comment db����");
			// ����
			while (rs.next()) {
				QnACommentDTO qnaCommentDTO = new QnACommentDTO(); // ���ο� ��ü ����
				qnaCommentDTO.setComment_no(rs.getInt(1));
				qnaCommentDTO.setQna_no(rs.getInt(2));
				qnaCommentDTO.setDate(rs.getTimestamp(3));
				qnaCommentDTO.setContent(rs.getString(4));
				qnaCommentDTO.setMember_id(rs.getInt(5));
				qnaCommentDTO.setRef(rs.getInt(6));
//				qnaCommentDTO.setRe_step(rs.getInt(7));
//				qnaCommentDTO.setRe_level(rs.getInt(8));
				System.out.println("-----------------"+rs.getString(4));
				qnaCommentlist.add(qnaCommentDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qnaCommentlist;

	}

	// ��� select
//	public List<QnACommentDTO> selectQnAComment(int beginRow, int pagePerRow) { 
	public List<QnACommentDTO> selectQnAComment() {

		List<QnACommentDTO> list = new ArrayList<QnACommentDTO>();
		// QnADTO qnaDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();
			Class.forName("com.mysql.jdbc.Driver"); // �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			conn = DriverManager.getConnection(url, id, pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
	
			String sql = "SELECT * from qna_comment ";
			pstmt = conn.prepareStatement(sql); // prepareStatement���� �ش� sql�� �̸� �������Ѵ�.
			rs = pstmt.executeQuery(); // ������ �����ϰ� ����� ResultSet ��ü�� ��´�.

			// .setInt(1, beginRow);
			// pstmt.setInt(2, pagePerRow);

			while (rs.next()) { // ����� �� �྿ ���ư��鼭 �����´�.
				QnACommentDTO qnaCommentDTO = new QnACommentDTO(); // ���ο� ��ü ����
				qnaCommentDTO.setComment_no(rs.getInt("comment_no"));
				qnaCommentDTO.setQna_no(rs.getInt("qna_no"));
				qnaCommentDTO.setDate(rs.getTimestamp("date"));
				qnaCommentDTO.setContent(rs.getString("content"));
				qnaCommentDTO.setMember_id(rs.getInt("member_id"));
//				qnaCommentDTO.setRef(rs.getInt("ref"));
//				qnaCommentDTO.setRe_step(rs.getInt("re_step"));
//				qnaCommentDTO.setRe_level(rs.getInt("re_level"));
				list.add(qnaCommentDTO);

			}

		} catch (Exception e) {
			System.out.println("qna comment ����");
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return list;
	}

	// ��� �ڸ�Ʈ ���� count
	public int selectTotalQnACommentCount() {
		int rowCount = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		String sql = "SELECT COUNT(*) FROM qn_comment";
		try {
			connection = this.getConnection();
			statement = connection.prepareStatement(sql);
			resultset = statement.executeQuery();
			if (resultset.next()) {
				rowCount = resultset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(connection, statement, resultset);
		}
		return rowCount;
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(this.url, this.id, this.pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void close(Connection connection, Statement statement, ResultSet resultset) {
		if (resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
