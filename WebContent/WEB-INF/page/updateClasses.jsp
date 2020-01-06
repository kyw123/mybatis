<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级修改页面</title>
</head>
<body>
<div align="center">
	<form action="<%=request.getContextPath()%>/updateClass" method="post">
		<c:forEach var="classes" items="${deparList}">
			<input type="hidden" name="id" value="${classes.getId()}"/>
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<h3>班 级 修 改 页 面</h3>
				</tr>
				<tr>
					<th>新班级名称:</th>
					<th>
						<input type="text" name="name" placeholder="${classes.getName()}"/>
					</th>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="reset" value="重置">
						<input type="submit" value="修改"/>
						<input type="button" value="返回" onclick="back()">
					</td>
				</tr>
			</table>
		</c:forEach>
	</form>
	<input type="hidden" value="${error}" id="errId"/>
	${error}
</div>
</body>
<script type="text/javascript">
	var value = document.getElementById("errId").value;
	if(2 == value) {
		alert("不能为空！");
		window.history.back();
	}
	function back(){
		window.history.back();
	}
</script>
</html>