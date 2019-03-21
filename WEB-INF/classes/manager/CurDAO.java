package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import manager.CurVO;


/**
 * Servlet implementation class jkgjhkg
 */


public class CurDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://ktds.couso1h6oido.ap-northeast-2.rds.amazonaws.com:3306/ktds";
	String username = "ktds";
	String password = "ktds1234";

			
	
			
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurDAO() {
        super();
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.sendRedirect("managerPage/managementItem.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// �۾���
	
	/*
	public int boardWrite(BoardDTO boardDTO) {
		String sql = "insert into board values "
				+ "(seq_board.nextval, ?, ?, ?, ?, 0, sysdate)";
		int su = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getName());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getContent());
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return su;
	}
	// ��Ϻ���
	public List<BoardDTO> boardList(int startNum, int endNum) {
		List<BoardDTO> list = new ArrayList<>();
		String sql = "select * from " 
				+ "(select rownum rn, tt.* from " 
				+ "(select * from board order by seq desc) tt) " 
				+ "where rn>=? and rn<=?";
		BoardDTO boardDTO = null;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
				list.add(boardDTO);
			}
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}*/
	// �� �󼼺���
	

	public List<CurVO> listView() {

		String sql = "select a.item_name as item_name, a.su as su, ifnull (b.cursu,0) as cursu from (select item_name,count(*) as su from items group by item_name) as a left outer join (select item_name,count(*) as cursu from items where state=1 group by item_name) b on (a.item_name=b.item_name) order by su desc limit 10";
		List<CurVO> list = new ArrayList<CurVO>();
		
		conn = getConnection();
		
		CurVO boardDTO =null;
		try {
			pstmt = conn.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
			while(rs.next()) {
		
				
				boardDTO = new CurVO();
				
				
			//	boardDTO.setItem_no(rs.getInt("item_no"));
				boardDTO.setItem_name(rs.getString("item_name"));
				boardDTO.setSu(rs.getInt("su"));
				boardDTO.setCursu(rs.getInt("cursu"));
//				boardDTO.setImg(rs.getString("img"));
//				boardDTO.setState(rs.getInt("state"));
//				boardDTO.setLocation(rs.getString("location"));
//				boardDTO.setDate(rs.getString("date"));
//				boardDTO.setCost(rs.getInt("cost"));
				list.add(boardDTO);				
			}
		} catch (SQLException e) {
			list=null;
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	/*
	// �Ѹ�ϼ� ���ϱ�
	public int getTotalA() {
		String sql = "select count(*) from board";
		int totalA = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalA = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return totalA;
	} 
	 �ۻ��� 
	public int boardDelete(int seq) {
		String sql = "delete board where seq=?";
		int su = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return su;
	}
	
	*/

}
