package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportCommentDAO {

	Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
	// String url =
	// "jdbc:mysql://ktdsdb.ctiouv3q7dtp.us-east-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// // ����Ϸ���
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// �����ͺ��̽����� ������
	// URL ���
	String id = "ktds"; // ����� ��
	String pw = "ktds1234"; // ����� ������ �н�����

	Connection dbconn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ReportCommentDAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

	}


	public List<ReportCommentDTO> getCommentReport(String report_no) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		String sql = "select * from report_comment where report_no=" + report_no; // no�� ���� ������ ����
		List<ReportCommentDTO> reportCommentlist = new ArrayList<ReportCommentDTO>();
		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(sql);
			// ����
			while (rs.next()) {
				ReportCommentDTO ReportCommentDTO = new ReportCommentDTO(); // ���ο� ��ü ����
				ReportCommentDTO.setComment_no(rs.getInt(1));
	
				ReportCommentDTO.setManager_id(rs.getInt(2));
				ReportCommentDTO.setReport_no(rs.getInt(3));
				ReportCommentDTO.setContent(rs.getString(4));
				ReportCommentDTO.setDate(rs.getTimestamp(5));
				ReportCommentDTO.setRef(rs.getInt(6));
				reportCommentlist.add(ReportCommentDTO);
				
				System.out.println("99999999999999999999");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail report");
		}

		return reportCommentlist;

	}


	public ReportCommentDTO insertCommentWrite(int comment_no,int manager_id, int report_no, String content, int ref) { // �������� �۾��� (������ ����)
		ReportCommentDTO reportwrite = new ReportCommentDTO();
		String sql = " INSERT INTO report_comment(comment_no,manager_id,report_no,content,date,ref) VALUES(?,?,?,?,?,?)";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		System.out.println("dfsfsdf");
		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			PreparedStatement pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, comment_no);
			pstmt.setInt(2, manager_id);
			pstmt.setInt(3, report_no);
			pstmt.setString(4, content);
			pstmt.setTimestamp(5, ts);
			pstmt.setInt(6, ref);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception ����");
			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					if (stmt != null)
						stmt.close();
				if (dbconn != null)
					dbconn.close();
			} catch (Exception e) {
			}

		}

		return reportwrite;
	}


}
