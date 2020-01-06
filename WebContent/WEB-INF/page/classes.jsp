<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script src="<%=request.getContextPath()%>/jquery/jquery-3.2.1.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>班级类welcome.jsp</title>
</head>
<script type="text/javascript">
$(function() {
	getDepar();
	
}); 
function getDepar() {
	$.ajax({
		type:"post",
		url :'<%=request.getContextPath()%>/queryClasses',
		success : function(classList){
			var html = "";
			$.each(classList,function(i){
				html += "<tr  height='40px'><td align='center'>"+classList[i].id+"</td>"+
				"<td  align='center'>"+classList[i].name+"</td>"+
				"<td  align='center'>"+
				"<input type='button' value='修改' onclick=\"javascript:location.href='<%=request.getContextPath()%>/"+classList[i].id+"/getClassId'\">"+
				"<input type='button' value=\"删除\"onclick=delDepar('"+classList[i].id+"')>"+ 
				"</td></tr>";
			});
			$('#deparId').html(html);
		},
	})
}
</script>
<body>
	<div align="center">
		<table class="imagetable" style="font-size: 15px;">
			<tr>
				<h3>班 级 信 息 表</h3>
			</tr>
			<tr>
				<td colspan="4">
					<input type='button' value='添加' onclick="javascript:location.href='<%=request.getContextPath()%>/classesSkip'">	
				</td>
			</tr>
			<tr height=10>
				<th>班级编号</th>
				<th>班级名称</th>
				<th>操作</th>
			</tr>
			<tbody id="deparId"></tbody >
		</table>
	</div>
	<br/>
	<br/>
	<br/>
	<jsp:include page="student.jsp" flush="true"/>
</body>
<script type="text/javascript">
function delDepar(id) {
	alert("获取id的值为:      " + id);
	if (confirm(" 是否删除 id为      '" + id + "'   的班级信息！")) {
		$.ajax({  
			url:'<%=request.getContextPath()%>/deleteClasses/'+id,  
            type:'post',
            success:function(data){  
				if (data == "success") {
					alert("删除成功！");
					window.location.href = "classes";
				}else {
					alert("删除失败!");
				}
            }  
        }); 
	}else{
		alert("已取消!");  
	}
}
</script>
</html>