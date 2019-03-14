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

public class UserDAO {


	// "jdbc:mysql://ktdsdb.ctiouv3q7dtp.us-east-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	// // ����Ϸ��� �����ͺ��̽����� ������ URL ���
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds?useSSL=false";
	String id = "ktds"; // ����� ����
	String pw = "ktds1234"; // ����� ������ �н�����

	Connection dbconn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public UserDAO() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}
	}

	public ArrayList<UserDTO> selectUser() { // �� ��Ͽ� �Ѹ���
		ArrayList<UserDTO> userlist = new ArrayList<UserDTO>();
		String sql = "select * from member order by member_id asc ";
		try {

			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { 
				UserDTO user = new UserDTO();
				user.setMember_id(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setTel(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setBirth_date(rs.getString(6));
				user.setDate(rs.getTimestamp(7));
				user.setEmail_check(rs.getInt(8));
	
				userlist.add(user);

			}

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.

			e.printStackTrace();

			System.out.println("�����߽��ϴ�.");

		} finally { // ������ ���� �Ǵ� ���п� ������� ����� �ڿ��� ���� �Ѵ�. (�����߿�)

			if (rs != null)
				try {
				
					rs.close();
				} catch (SQLException sqle) {
				} // Resultset ��ü ����

			if (stmt != null)
				try {
				
					stmt.close();
				} catch (SQLException sqle) {
				} // PreparedStatement ��ü ����

			if (dbconn != null)
				try {
		
					dbconn.close();
				} catch (SQLException sqle) {
				} // Connection ����

		}
		return userlist;
		
	}
	
	public UserDTO getUser(String member_id) { // notice_no �����ͼ� Detail�κп� �� �Ѹ���
		UserDTO user = new UserDTO();
		String sql = "select * from member where member_id=" + member_id; // no�� ���� ������ ����

		try {
			dbconn = DriverManager.getConnection(url, id, pw);
			stmt = dbconn.createStatement();
			// stmt.setInt(1, no1);
			rs = stmt.executeQuery(sql);
			// ����
			while (rs.next()) {
				user.setMember_id(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setTel(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setBirth_date(rs.getString(6));
				user.setDate(rs.getTimestamp(7));
				user.setEmail_check(rs.getInt(8));
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}
}
