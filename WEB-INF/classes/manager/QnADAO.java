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

public class QnADAO {
//https://injava.tistory.com/20
	// request.setCharacterEncoding("euc-kr");

	Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	String id = "ktds"; // ����� ����
	String pw = "ktds1234"; // ����� ������ �н�����

	Connection dbconn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public QnADAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

	}
	// ������ ����
	public int insertQnAComment(QnACommentDTO QnAComment) {
		int rowCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName("com.mysql.jdbc.Driver"); // �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			conn = DriverManager.getConnection(url, id, pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
	//		conn = this.getConnection();

			String sql = "INSERT INTO qna_comment(board_pw, board_title, board_content, board_user, board_date) values(?,?,now(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql); // prepareStatement���� �ش� sql�� �̸� �������Ѵ�.
			// rs = pstmt.executeQuery(); // ������ �����ϰ� ����� ResultSet ��ü�� ��´�.
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, QnAComment.getComment_no());
			pstmt.setInt(2, QnAComment.getQna_no());
			pstmt.setTimestamp(3, QnAComment.getDate());
			pstmt.setString(4, QnAComment.getContent());
			pstmt.setInt(5, QnAComment.getMember_id());
			pstmt.setInt(6, QnAComment.getRef());
//			pstmt.setInt(7, QnAComment.getRe_step());
//			pstmt.setInt(8, QnAComment.getRe_level());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}

		return rowCount;
	}

	// ������ ����
	public int deleteQnA(QnACommentDTO QnAComment) {
		int rowCount = 0;
		// Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "DELETE FROM qna_comment WHERE comment_no=?";
		try {
			//conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, QnAComment.getComment_no());
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return rowCount;
	}

	// qna ��� ���̺� ������ ����
	public ArrayList<QnADTO> selectQnA() {
		ArrayList<QnADTO> qnalist = new ArrayList<QnADTO>();
		// QnADTO qnaDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "select * from qna order by qna_no asc ";
	
		try {
			 Class.forName("com.mysql.jdbc.Driver");

			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			
			rs = stmt.executeQuery(sql);
			System.out.println("QnA db�� ����Ǿ����ϴ�."); // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.

			while (rs.next()) {
		
				QnADTO qnaDTO = new QnADTO(); // ���ο� ��ü ����
				qnaDTO.setQna_no(rs.getInt("qna_no"));
				qnaDTO.setMember_id(rs.getInt("member_id"));
				qnaDTO.setTitle(rs.getString("title"));
				qnaDTO.setContent(rs.getString("content"));
				qnaDTO.setDate(rs.getTimestamp("date"));
				qnaDTO.setHits(rs.getInt("hits"));
				// System.out.println(rs.getInt("qna_no"));
				qnalist.add(qnaDTO);

			}

		} catch (Exception e) {
			System.out.println("fail");
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return qnalist;
	}
	
	public QnADTO getQnA(String qna_no) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		String sql = "select * from qna where qna_no=" + qna_no; // no�� ���� ������ ����
		QnADTO qnaDTO = new QnADTO(); // ���ο� ��ü ����
		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(sql);
			// ����
			while (rs.next()) {
				
				qnaDTO.setQna_no(rs.getInt("qna_no"));
				qnaDTO.setMember_id(rs.getInt("member_id"));
				qnaDTO.setTitle(rs.getString("title"));
				qnaDTO.setContent(rs.getString("content"));
				qnaDTO.setDate(rs.getTimestamp("date"));
				qnaDTO.setHits(rs.getInt("hits"));
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qnaDTO;

	}
	
	


	public int selectTotalQnACount() {
		int rowCount = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		System.out.println("~~~~~~");
		String sql = "SELECT COUNT(*) FROM qna";
		try {
		//	connection = this.getConnection();
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

//	public int selectTotalQnACommentCount() {
//        int rowCount = 0;
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultset = null;
//        String sql = "SELECT COUNT(*) FROM qn_comment";
//        try {
//            connection = this.getConnection();
//            statement = connection.prepareStatement(sql);
//            resultset = statement.executeQuery();
//            if(resultset.next()) {
//                rowCount = resultset.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            this.close(connection, statement, resultset);
//        }
//        return rowCount;
//    }

//	private Connection getConnection() {
//		Connection connection = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(this.url, this.id, this.pw);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}

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
