package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.CommandAction;

import java.sql.Timestamp;
import manager.QnADTO;
import manager.QnADAO;
import manager.QnACommentDAO;
import manager.QnACommentDTO;

public class MngQnAAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String no = request.getParameter("qna_no"); // qna_no�� ������� ������?
		QnADAO qnaDAO = new QnADAO();
		QnADTO qnaDTO = new QnADTO();
		List<QnADTO> qnalist = new ArrayList<QnADTO>();
		List<QnACommentDTO> qnaCommentlist = new ArrayList<QnACommentDTO>();
		qnalist = qnaDAO.selectQnA();
		QnACommentDAO qnaCommentDAO = new QnACommentDAO();

		request.setAttribute("qnalist", qnalist);
		qnaDTO = qnaDAO.getQnA(no);

		qnaCommentlist = qnaCommentDAO.getCommentQnA(no);
		
		int answerCheck = 0;

		if (qnaCommentlist.size() !=0) { // �亯���� �ִ��� üũ answerCheck = 1;
			answerCheck = 1;
		} else {
			answerCheck = 0;
		}
		
		if (no != null) {
			request.setAttribute("answerCheck", answerCheck);
			request.setAttribute("qnaDTO", qnaDTO);
			request.setAttribute("qnaCommentlist", qnaCommentlist);
			return "managementQnADetail.jsp";
		} else {

			return "/managerPage/managementQnA.jsp";

		}

	}
}
