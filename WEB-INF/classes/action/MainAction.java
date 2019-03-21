package action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardDTO;
import board.CommandAction;
import product.ProductDAO;
import product.ProductDTO;

public class MainAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");

		//������ animate �̺�Ʈ
		String anchor = request.getParameter("menuHidden");
		System.out.println("anchor: " + anchor);
		if(anchor != null) {
			if(anchor.equals("��ǰ��")) {
				request.setAttribute("anchor", "itemAnchor");
			} else if(anchor.equals("���Ҹ�")) {
				request.setAttribute("anchor", "customerAnchor");
			} else if(anchor.equals("Ŀ�´�Ƽ")) {
				request.setAttribute("anchor", "communityAnchor");
			} else if(anchor.equals("about ������")) {
				request.setAttribute("anchor", "aboutSharepotAnchor");
			}
		}
		
		return "main.jsp";
	}
}
