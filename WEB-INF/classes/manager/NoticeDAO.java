package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import com.mysql.fabric.Response;

public class NoticeDAO {

	// String url =
	// "jdbc:mysql://ktdsdb.ctiouv3q7dtp.us-east-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// // ����Ϸ���
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds?useSSL=false";

	// ������ //
	// URL ���
	String id = "ktds"; // ����� ����
	String pw = "ktds1234"; // ����� ������ �н�����

	public NoticeDAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

	}

	Connection dbconn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public NoticeDTO getNotice(String no) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		NoticeDTO ndto = new NoticeDTO();
		// List<NoticeDTO> mdtos1 = new ArrayList<NoticeDTO>();
		int no1 = Integer.parseInt(no);
		String sql = "select * from notice where notice_no=" + no; // no�� ���� ������ ����

		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			// stmt.setInt(1, no1);
			rs = stmt.executeQuery(sql);
			// ����
			while (rs.next()) {
				ndto.setNotice_no(rs.getInt(1));
				ndto.setManager_id(rs.getString(2));
				ndto.setTitle(rs.getString(3));
				ndto.setContent(rs.getString(4));
				ndto.setDate(rs.getTimestamp(5));
				ndto.setHits(rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ndto;

	}

	public ArrayList<NoticeDTO> selectMember() { // �� ��Ͽ� �Ѹ���
		ArrayList<NoticeDTO> mdtos = new ArrayList<NoticeDTO>();

		String sql = "select* from notice order by notice_no asc";

		try {

			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				NoticeDTO ndto = new NoticeDTO();
				ndto.setNotice_no(rs.getInt(1));
				ndto.setManager_id(rs.getString(2));
				ndto.setTitle(rs.getString(3));
				ndto.setContent(rs.getString(4));
				ndto.setDate(rs.getTimestamp(5));
				ndto.setHits(rs.getInt(6));
			
				mdtos.add(ndto);

			}

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

		return mdtos;

	}
	
	public NoticeDTO insertHitsWrite(int notice_no) { // �������� �۾��� (������ ����)
		NoticeDTO noticewrite = new NoticeDTO();
		String sql = " UPDATE notice SET hits=hits+1 WHERE notice_no=?";

		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			PreparedStatement pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, notice_no);
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

		return noticewrite;
	}

	public NoticeDTO insertNoticeWrite(int manager_id, String title, String content, int hits) { // �������� �۾��� (������ ����)
		NoticeDTO noticewrite = new NoticeDTO();
		String sql = " INSERT INTO notice(manager_id,title,content,date,hits) VALUES(?,?,?,?,?)";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);

		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			PreparedStatement pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, manager_id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setTimestamp(4, ts);
			pstmt.setInt(5, hits);
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

		return noticewrite;
	}

	// �� �����ϱ�
	public int deleteNotice(String no) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		int rowCount = 0;
		NoticeDTO ndtodelete = new NoticeDTO();
		// List<NoticeDTO> mdtos1 = new ArrayList<NoticeDTO>();

		int no1 = Integer.parseInt(no);// notice_no���� �����ͼ� �װ��� �ش��ϴ� �� ����
		String sql = "delete from notice where notice_no=?"; // no�� ���� ������ ����

		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			PreparedStatement pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, no1);

			pstmt.executeUpdate();

			// stmt.setInt(1, no1);
			// ����
			System.out.println("--====" + rowCount);
			rowCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCount;

	}

	// �� ������Ʈ

	public int updateNotice(String title, String content, int notice_no) {

		int rowCount = 0;
		NoticeDTO noticewrite = new NoticeDTO();
		String sql = "UPDATE notice SET title=?, content=? WHERE notice_no=?";
		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			PreparedStatement pstmt = dbconn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, notice_no);
			pstmt.executeUpdate();
			
			rowCount = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
			
		return rowCount;
	}

}
