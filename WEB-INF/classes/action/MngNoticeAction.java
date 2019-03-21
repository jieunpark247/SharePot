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
		request.setCharacterEncoding("utf-8");
		NoticeDAO noticeDAO = new NoticeDAO();
		List<NoticeDTO> mdtos = new ArrayList<NoticeDTO>();
		mdtos = noticeDAO.selectMember();

		request.setAttribute("mdtos", mdtos);
		//request.setAttribute("key", "value");
	

//		
		//int no = Integer.parseInt(request.getParameter("notice_no")); // ���޾Ƽ� no ����
		String no = request.getParameter("notice_no");
		if(no != null) { 
			NoticeDTO ndto = new NoticeDTO();
			noticeDAO.insertHitsWrite( Integer.parseInt(no));
			ndto = noticeDAO.getNotice(no); //no �ΰ��� ��� ��� ��ü �޾ƿ���	
			request.setAttribute("ndto", ndto);
			
			return "/managerPage/managementNoticeDetail.jsp";
		} else {
			return "/managerPage/managementNotice.jsp";
		}
		
	
	}
	

 
}
