<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script src="<%=request.getContextPath() %>/jquery/jquery-3.2.1.js"></script>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="pragma" CONTENT="no-cache">  -->
<!-- <meta http-equiv="Cache-Control" CONTENT="no-cache, must-revalidate">  -->
<!-- <meta http-equiv="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> -->
<title>学生页面</title>
</head>
<body>
	<div align="center">
		<table class="imagetable" style="font-size: 15px;">
			<tr>
				<h3>学 生 信 息 表</h3>
			</tr>
			<tr>
				<td colspan="8">
					<input type='button' value='添加' onclick="javascript:location.href='<%=request.getContextPath()%>/studentjspadd'"/>					
				</td>
			</tr>
			<tr height=10>
				<th>学生编号</th>
				<th>学名名称</th>
				<th>学生年龄</th>
				<th>学生性别</th>
				<th>所属班级</th>
				<th>操作</th>
			</tr>
			<tbody id="empId"></tbody >
		</table>
	</div>
</body>
<script type="text/javascript">
$(function(){
	getEmp();
})
function getEmp() {
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/queryStudent',
		success : function(studentList) {
			var html = "";
			$.each(studentList,function(i){
				html += "<tr  height='40px'><td align='center'>"+studentList[i].id+"</td>"+
				"<td  align='center'>"+studentList[i].name+"</td>"+
				"<td  align='center'>"+studentList[i].age+"</td>"+
				"<td  align='center'>"+studentList[i].sex+"</td>"+
				"<td  align='center'>"+studentList[i].classes.name+"</td>"+
				"<td  align='center'>"+
				"<input type='button' value='修改' onclick=\"javascript:location.href='<%=request.getContextPath()%>/"+studentList[i].id+"/getStudentId'\">"+
				"<input type='button' value=\"删除\"onclick=delStudent('"+studentList[i].id+"')>"+ 
				"</td></tr>";
			});
			$('#empId').html(html);
		},
	})
}
function delStudent(id){
	if (confirm("是否删除id'"+id+"'的学生信息?")){
		$.ajax({  
			url:'<%=request.getContextPath()%>/deleteStudent/'+id,  
            type:'POST',
            success:function(data){ 
				if (data == "success") {
					alert("删除成功");
					window.location.href="<%=request.getContextPath()%>/student";
				}else{
					alert("删除失败");
				}
            }  
        });  
	}
}
</script>
</html>