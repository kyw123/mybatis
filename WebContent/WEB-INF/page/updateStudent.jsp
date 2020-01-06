<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="<%=request.getContextPath()%>/jquery/jquery-3.2.1.js"></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>修改数据学生</title>
</head>
<body>
	<div align="center">
		<form action="<%=request.getContextPath()%>/updateStudents" method="post">
			<c:forEach var="student" items="${deparList}">
				<input type="hidden" name="id" value="${student.getId()}"/>
				<!--
					用来回显数据的值用的-->
				<input type="hidden" name="fuck" id="fuck" value="${student.getClasses().getName()}"/>
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<h3> 学 生 的 修 改 页 面</h3>
					</tr>
					<tr>
						<th>新名称:</th>
						<th>
							<input type="text" name="name" placeholder="${student.getName()}"/>
						</th>
					</tr>
					<tr>
						<th>新年龄:</th>
						<th>
							<input type="text" name="age" placeholder="${student.getAge()}"/>
						</th>
					</tr>
					<tr>
						<th>请输入学生的性别:</th>
						<th>
							<c:if test="${student.getSex()==1}">
							
								<input type="radio" name="sex" class = "sex" value="1" id="nan" checked="checked"/>男
								<input type="radio" name="sex" class = "sex" value="2" id="nv"/>女<br>
							</c:if>
							<c:if test="${student.getSex()==2}">
								<input type="radio" name="sex" class = "sex" value="1" id="nan" />男
								<input type="radio" name="sex" class = "sex" value="2" id="nv" checked="checked"/>女<br>
							</c:if>
						</th>
					</tr>
					<tr>
						<th>
							请输入学生的所属班级:
						</th>
						<th>
							<select id="classValue" name="className">
							</select>
						</th>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="reset" value="重置">
							<input type="submit" value="修改" />
							<input type="button" value="返回" onclick="back()">
						</td>
					</tr>
				</table>
			</c:forEach>
		</form>
	<input type="hidden" value="${err}" id="errId"/>
</div>
</body>
<script type="text/javascript">
	$(function(){   // 页面加载完成的时候执行以下函数  
		getClassesData();
		$("#submitId").click(function() {// 鼠标单击事件,点击提交的时候调setData函数
			setStudent();
		})
	});
	
	/**
	   班级数据回显*/
	function getClassesData() {
		$.ajax({
			type:"post",
			url :'<%=request.getContextPath()%>/addClassValue',
			success : function(list) {
				var cont = '';
				cont =cont+ "<option value='无'>请选择班级</option>";
				$.each(list,function(i,obj){
					cont = cont+ "<option value='"+obj.id+"' name='"+obj.name+"'>"+obj.name+"</option>";
				});
				$("#classValue").html(cont);
// 				alert($("#classValue").html());
				//回显数据班级属性 
				$("#classValue option[name='"+$("#fuck").val()+"']").prop("selected","selected");
			},
		});
	}

	/**
	返回上一级*/
	function back() {
		window.history.back();
	}
	var value = document.getElementById("errId").value;
	if(2 == value) {
		alert("不能为空");
		window.history.back();
	}else if(3 == value){
		alert("部门不存在");
		window.history.back();
	}
	
	/**
	 是否为空！*/
	function isNum(){
		var num = $("#numId").val();//取新修改的部门id的值
		var reg = '^[0-9]*$';//只能输入数字的正则表达式
		var re = new RegExp(reg);
		if (!re.test(num)) { // 如果新修改的部门id不符合正则表达式
			alert("部门id不合法");//提示不合法
			location.reload(location);//刷新页面
		}
	}
</script>
</html>