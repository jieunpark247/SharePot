package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.CommandAction;
import manager.NoticeDAO;
import manager.NoticeDTO;

public class MngNoticeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		NoticeDAO noticeDAO = new NoticeDAO();
		List<NoticeDTO> mdtos = new ArrayList<NoticeDTO>();
		mdtos = noticeDAO.selectMember();

		request.setAttribute("mdtos", mdtos);
		//request.setAttribute("key", "value");
		
		
		
		//int no = Integer.parseInt(request.getParameter("notice_no")); // ���޾Ƽ� no ����
		String no = request.getParameter("notice_no");
		if(no != null) { 
			NoticeDTO ndto = new NoticeDTO();
			ndto = noticeDAO.getNotice(no); //no �ΰ��� ��� ��� ��ü �޾ƿ���	
			request.setAttribute("ndto", ndto);
			//////////////////////////////////////////////////////////////////////////////////-------hitsó���ϱ�
//			int hits =  Integer.parseInt(request.getParameter("hits"));
//			hits = hits+1;  
//			//update ����� �Ѵ�. hits 
			//////////////////////////////////////////////////////////////////////////////////-------hitsó���ϱ�
			
			return "managementNoticeDetail.jsp";
		} else {
			return "managementNotice.jsp";
		}
		
	
	}
	

 
}
