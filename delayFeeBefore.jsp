<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="dao.mypageDAO" %>
<%@ page import="dto.Rent" %>
<%@ page import="action.MypageAction" %>
<%
//��ϵ�
String thisPage = request.getParameter("thisPage");
int member_id =  Integer.parseInt((String) session.getAttribute("idKey"));
if(thisPage==null){
	thisPage="1";
}
mypageDAO dao = new mypageDAO();
int perPage =10;
Rent[] rent_bf = dao.getRents(Integer.parseInt(thisPage), perPage, MypageAction.DELAYALL,member_id);
int totalNum_bf = dao.getTotalNum(MypageAction.DELAYALL,member_id);
long[] delayDay_bf = new long[rent_bf.length];

//����¡
int totalPageNumBf = totalNum_bf/perPage;
if(totalNum_bf % perPage !=0){
	totalPageNumBf++;
}

//
int idex =0;
for(Rent temp: rent_bf){
	delayDay_bf[idex] = (temp.getReturn_date().getTime()-temp.getRent_date().getTime())/(24*60*60*1000) - 1;
	idex++;
}

request.setAttribute("rent_bf", rent_bf);
request.setAttribute("totalNum_bf", totalNum_bf);
request.setAttribute("delayDay_bf", delayDay_bf);
request.setAttribute("totalPageNumBf", totalPageNumBf);

%>
<script>
$(function() {
	$('.delayAllPaging').click(function(){
		$.ajax({
			url: "delayFeeBefore.jsp",
			data:{thisPage:$(this).text()},
			success: function(src){
	  	  		$(".FeeTable").html(src);
	  	    },
	  	    error : function(error) {
	  	    	alert(error)
	  	    }
		})
	})
})
</script>

<br>
<br>
<font style="font-size:18px; color:#414141; font-weight:bold; color: #d55454">  * ��ü�� ���� �̷�</font><br>
<div style="padding-top:10px;padding-left: 15px"><font style="font-size:14px; color:#acacac">������� ��ü�Ḧ ������ �̷��Դϴ�.<br></font></div>


<br>
<br>
<font style="font-size: 16px">�� <b>${requestScope.totalNum_bf }</b>��</font>

<table class="content-table" width="740" border="1" frame="void"
	rules="rows" cellpadding="0" cellspacing="0"
	style="margin-bottom: 40px">
	<tr height="40" style="background-color: #f6f6f6">
		<td id="table-td" width="60">�뿩��ȣ</td>
		<td id="table-td" class="table-line" width="270" colspan="2">��ǰ��</td>
		<td id="table-td" class="table-line" width="80">��ġ</td>
		<td id="table-td" class="table-line" width="100">�ݳ���</td>
		<td id="table-td" class="table-line" width="70">��ü�ϼ�</td>
	</tr>
	<c:choose>
		<c:when test="${requestScope.totalNum_bf ==0 || empty requestScope.totalNum_bf}">
			<tr  height="100">
				<td colspan="4">��ü ���� �̷��� �����ϴ�.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.rent_bf}" var="rent_bf" varStatus="status">
				<tr>
				<td align="center" style="padding-top: 10px; padding-bottom: 10px">${rent_bf.rent_id}</td>
				<td align="center" width="80" style="padding-left: 80px;"><img alt="" src="${rent_bf.img }" width="80"></td>
				<td align="left" style="padding-left:25px">${rent_bf.item_name }</td>
				<td align="center">${rent_bf.location }</td>
				<td align="center">
					<fmt:formatDate value="${rent_bf.return_date }" pattern="yyyy-MM-dd" />
				</td>
				<td align="center">
					${delayDay_bf[status.index] }�� 
				</td>
			</tr>
			
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</table>
	
	
	<c:set var="thisPage" value="${requestScope.thisPage }"/>
	<c:set var="totalPageNum" value="${requestScope.totalPageNumBf }"/>
	
	
	<c:if test="${totalPageNum > 0 }">
	<table cellpadding="0" cellspacing="0" border="1" bordercolor="#e5e5e5" style="border-collapse:collapse" align="center">
		<tr height="40">
			<c:forEach var="idx" begin="1" end="${totalPageNum }">
				<c:choose>
					<c:when test="${idx==thisPage }">
						<td class="delayAllPaging" style="background-color:#f2f2f2; font-size:11px; vertical-align:middle" width="31" align="center">${idx }</td>
					</c:when>
					<c:otherwise>
						<td class="delayAllPaging" style="font-size:11px; vertical-align:middle; cursor:pointer" width="31" align="center">${idx }</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>
	
	</c:if>


