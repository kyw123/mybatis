<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<%=request.getContextPath() %>/jquery/jquery-3.2.1.js"></script>
<title>部门添加页面</title>
</head>
<body>
	<div align="center">
		<form id="formId">
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<h3> 班 级 添 加 页 面</h3>
				</tr>
				<tr>
					<th>
						请输入班级名称:
						<input type="text" name="name" id="nameId"placeholder="请输入班级名称"/>
					</th>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="reset" value="重置">
						<input type="button" value="提交" id="subId" /> 
						<input type="button" value="返回" onclick="back()"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(function() {//页面加载完成就执行以下函数
		$("#subId").click(function() {//鼠标点击事件,点击提交就执行setData函数
			setData();
		})
	});
	function setData() {
		var name = $("#nameId").val();//得到输入的部门名称
		if ("" == name) {//判断是否为null
			alert("数据不能为空");
			window.close();
		} else {
			$.ajax({
				type : "post",
				url : '<%=request.getContextPath()%>/addClass',
				data : $("#formId").serialize(),
				success : function(mark) {
					if (mark == "success") {
						alert("添加成功");
						window.location.href = "classes";
					} else {
						alert("添加失败");
					}
				},
			});
		}
	}
	function back(){
		window.history.back(); // 返回历史记录
	}
</script>
</html>
</html>