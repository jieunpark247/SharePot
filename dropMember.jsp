<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <% String user_id = request.getParameter("user_id");
    request.setAttribute("user_id", user_id);
    %>
<script>
	$(function(){
		$('#dropMember').click(function(){
			var checkPw = $('#dropPw').val();
			if(checkPw==""){
				document.getElementById('msgAboutDrop').style.color="red";
				document.getElementById('msgAboutDrop').innerHTML="��й�ȣ�� �Է����ּ���";
			}else{
				var formData = $('form[name=dropForm]').serialize();
				$.ajax({
					type:"POST",
					dataType:"json",
					data:formData,
					url:"memberDrop.do",
					success:function(data){
						if(data.check=="no"){
							document.getElementById('msgAboutDrop').style.color="red";
							document.getElementById('msgAboutDrop').innerHTML="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
						}else{
							$('#dropForm').submit();
						}
					},
					error:function(){
						alert("error!")
					}
					
				})
				
			}
		});
	})
</script>
<br>
<br>
<br>
<form id="dropForm" name="dropForm" action="memberDropComplete.do" method="post">

   <font style="font-size:22px; color:#414141; font-weight:bold; color: #d55454">* Ż�� �ȳ�*</font><br><div style="margin-bottom:2px"></div>
   <font style="font-size:14px; color:#acacac">ȸ�� Ż�� ��û�ϱ� ���� �ȳ� ������ �� Ȯ�����ּ���.
   <br>
   <br> * Ż�� �� ȸ������ �� �뿩 ���� �̿� ����� ��� ���� �˴ϴ�.
   <br> * Ż�� �Ŀ��� �Խ����� ���񽺿� ����� �Խù��� �״�� ���� �ֽ��ϴ�.
   
   <br>
   <br> <b>�ȳ� ������ ��� Ȯ���Ͽ�����, �̿� �����Ѵٸ�, <br>
   	������ ����� ���� ��й�ȣ�� �ٽ� �� �� �Է����ּ���.
   </b>
   </font>
   <table class="table_basic" width="500" cellpadding="0" cellspacing="0" style="margin-top:15px; border:1px #acacac solid">
      <!-- ���̵� -->
      <tr height="60">
         <td align="center">
            <input type="text" class="regiPrimeInput" name="user_id" id="user_id" value="${user_id }"
             id="dropId" style="outline:0; margin-top:5px; margin-bottom:3px; width:480px;
              height:26px; font-family:'�������'; font-size:18px; border:0px; text-align:center"
              readonly="readonly">
         </td>
      </tr>
      <tr height="1">
				<td align="center"><img src="images/registerCheck/dot.jpg" width="440" height="1"></td>
	</tr>
      <!-- ��й�ȣ -->
      <tr height="60">
         <td align="center">
            <input type="password" class="regiPrimeInput" placeholder="��й�ȣ" name="user_pw" id="user_pw" style="outline:0; margin-top:5px; margin-bottom:3px; width:480px; height:26px; font-family:'�������'; font-size:18px; border:0px; text-align:center">
            <div id="msgAboutDrop">
            </div>
         </td>
      </tr>
   </table>
   <table class="table_basic" cellpadding="0" cellspacing="0" style="margin-top:60px; margin-bottom:80px">
      <tr>
         <td align="center"><input id="dropMember" name="dropMember" type="button" value="Ȯ��" style="width:180px; height:70px; background-color:#d55454; border:0; boder-radius:0; font-size:24px; color:#ffffff; font-family:'�������'; cursor:pointer"></td>
         <td width="20"></td>
      </tr>
   </table>
   
</form>