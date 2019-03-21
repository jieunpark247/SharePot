<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dao.mypageDAO" %>
<%@ page import="dto.Rent" %>
<%@ page import="action.MypageAction" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
	int member_id =  Integer.parseInt((String) session.getAttribute("idKey"));
	String thisPage = request.getParameter("thisPage");  
	if(thisPage==null){
		thisPage="1";
	}
	int perPage= 10;
	mypageDAO dao = new mypageDAO();
	Rent[] rents = dao.getRents(Integer.parseInt(thisPage), perPage,MypageAction.DELAYRENT,member_id);

	int totalNum = dao.getTotalNum(MypageAction.DELAYRENT,member_id);

	int totalPageNum = totalNum/perPage;
	if(totalNum % perPage !=0){
		totalPageNum++;
	}
	long[] delayDay = new long[rents.length];
	int idx = 0;
	
	for(Rent unit : rents){
		if(unit.getState()==0){
			//�뿩 ���̶��
			delayDay[idx] = (System.currentTimeMillis() - unit.getRent_date().getTime())/(24*60*60*1000);
		}else{
			delayDay[idx] = (unit.getReturn_date().getTime() - unit.getRent_date().getTime())/(24*60*60*1000) -1;
		}
		idx++;
	}
	request.setAttribute("totalNum", totalNum);
	request.setAttribute("rents", rents);
	request.setAttribute("thisPage", thisPage);
	request.setAttribute("totalPageNum", totalPageNum);
	request.setAttribute("delayDay", delayDay);
%>

<script>
$(function() {
	$('.delayPaging').click(function(){
		$.ajax({
			url: "delayNoFee.jsp",
			data:{thisPage:$(this).text()},
			success: function(src){
	  	  		$(".noFeeTable").html(src);
	  	    },
	  	    error : function(error) {
	  	    	alert(error)
	  	    }
		})
	})
})

</script>
<font style="font-size:18px; color:#414141; font-weight:bold; color: #d55454">  * �̳� ��ü ��Ȳ</font><br>
<div style="padding-top:10px;padding-left: 15px">
<font style="font-size:14px; color:#acacac">��ǰ�� �ݳ�������, ���� ��ü ����� ���� ���� ��Ȳ�Դϴ�. �̹ݳ� ��ǰ�� ��ü��Ȳ�� �뿩��Ȳ���� Ȯ���ϼ���.<br></font>
</div>

<br>
<br>
<font style="font-size: 16px">��<b> ${requestScope.totalNum }</b>��</font>
	
	<table class="content-table" width="740" border="1" frame="void"
		rules="rows" cellpadding="0" cellspacing="0"
		style="margin-bottom: 14px">
		<tr height="40" style="background-color: #f6f6f6">
			<td id="table-td" width="60">�뿩��ȣ</td>
			<td id="table-td" class="table-line" width="270" colspan="2">��ǰ��</td>
			<td id="table-td" class="table-line" width="100">��ġ</td>
			<td id="table-td" class="table-line" width="100">��ü �ϼ�</td>
			<td id="table-td" class="table-line" width="100">��ü �ݾ�</td>
		</tr>
		<c:choose>
			<c:when test="${requestScope.totalNum == 0 || empty requestScope.totalNum}">
				<tr style="height: 100.67px">
				<td colspan="6"> ��ü ��Ȳ�� �����ϴ�.</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:forEach  items="${rents}" var="rent" varStatus="status">
				<tr>
					<td align="center" style="padding-top: 10px; padding-bottom: 10px">${rent.rent_id}</td>
					<td align="center" width="80" style="padding-left: 70px;"><img alt="" src="${rent.img }" width="80"></td>
					<td align="left">${rent.item_name }</td>
					<td align="center">${rent.location }</td>
					<td align="center">${delayDay[status.index] }��</td>
					<td align="center">
						${delayDay[status.index] * rent.item_cost } ��
					</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</table>
		<div align="right" style="width:740px"><font style="font-size:12px; color:#acacac">* ��ü�ݾ� = ��ü�ϼ� * 500�� <br></font></div>
		
		<c:set var="thisPage" value="${requestScope.thisPage }"/>
		<c:set var="totalPageNum" value="${requestScope.totalPageNum }"/>

		
		<c:if test="${totalPageNum > 0 }">
		<table cellpadding="0" cellspacing="0" border="1" bordercolor="#e5e5e5" style="border-collapse:collapse" align="center">
			<tr height="40">
				<c:forEach var="idx" begin="1" end="${totalPageNum }">
					<c:choose>
						<c:when test="${idx==thisPage }">
							<td class="delayPaging" style="background-color:#f2f2f2; font-size:11px; vertical-align:middle" width="31" align="center">${idx }</td>
						</c:when>
						<c:otherwise>
							<td class="delayPaging"  style="font-size:11px; vertical-align:middle; cursor:pointer" width="31" align="center">${idx }</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>
		
		</c:if>
		
		
	