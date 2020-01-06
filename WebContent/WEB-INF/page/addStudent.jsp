<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="jquery/jquery.serializejson.js"></script><!-- jquery.serializejson.js -->
	<title>学生添加页面</title>
</head>
<body>
	<div align ="center">
		<form id="formId">
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<h3>学 生 添 加 页 面</h3>
				</tr>
				<tr>
					<th>
					请输入学生名称:
					<input type="text" name="name" id="nameId" placeholder="请输入学生名称" />
					</th>
				</tr>
				<tr>
					<th>
					请输入学生年龄:
						<input type="text" name="age" id="ageId"placeholder="请输入学生年龄" />
					</th>
				</tr>
				<tr>
					<th>
					请输入学生的性别:
						<input type="radio" name="sex" class = "sex" value="1" id="nan"/>男
						<input type="radio" name="sex" class = "sex" value="2" id="nv"/>女<br>
					</th>
				</tr>
				<tr>
					<th>
					请输入学生的所属班级:
					<select id="classValue" name="className"></select>
					</th>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="reset" value="重置">
						<input type="button" value="提交" id="submitId" /> 
						<input type="button" value="返回" onclick="back()"/>
					</td>
				</tr>
			</table>
		</form>
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
	  添加学生函数*/
	function setStudent(){
		var serialize1= $("#formId").serializeJSON();
		var sex = $('input[name="sex"]:checked').val(); 	
		serialize1.sex =sex;
		$.ajax({
			type:"post",
			url:'<%=request.getContextPath()%>/addStudents',
			data:serialize1,
			success:function(mark){ // 由服务器返回的数据
				if(mark=="success"){
					window.location.href="student"; ////windows.location.href="/url" 当前页面打开URL页面，前面三个用法相同。
				}else{
					alert("学生添加失败!");
				}
			},
			error:function(xhr,status,error){
				alert(error);
			}
		});
	}
	
	/**
	   班级数据回显
	 */
	function getClassesData() {
		$.ajax({
			type:"post",
			url :'<%=request.getContextPath()%>/addClassValue',
			success : function(list) {
				var cont = '';
				cont =cont+ "<option value='无'>请选择班级</option>";
				$.each(list,function(i,obj){
					cont = cont+ "<option value='"+obj.id+"'>"+obj.name+"</option>";
				});
				$("#classValue").html(cont)
			},
		});
	}
	
	/*
	返回上一级*/
	function back() {
	 	window.history.back();
	}
</script>
</html>