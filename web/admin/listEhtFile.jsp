<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	$("#addForm").submit(function(){
		if(!checkEmpty("type","电控程序类型"))
			return false;
		if(!checkEmpty("version","电控程序版本"))
			return false;
		if(!checkEmpty("filepath","文件"))
			return false;
		return true;
	});
});
<c:if test="${!empty res}">
alert("版本已存在");
</c:if>
</script>

<title>电控程序管理</title>


<div class="workingArea">
	<h1 class="label label-info" >电控程序列表</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>类型</th>
					<th>版本</th>
					<th>说明</th>
					<th>上传日期</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${thees}" var="e">
				
				<tr>
					<td>${e.id}</td>
					<td>${e.type}</td>
					<td>${e.version}</td>
					<td>${e.note}</td>
					<td>${e.bdate}</td>	 
					<td><a href="admin_ehtfile_edit?id=${e.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_ehtfile_delete?id=${e.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	  
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增电控程序</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_ehtfile_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>类型</td>
	    				<td><input  id="type" name="type" type="number" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>选择文件</td>
	    				<td>
	    					<input id="filepath" accept="file/eht/*" type="file" name="filepath"/>
	    				</td>
	    			</tr>
	    			<tr>
	    				<td>版本</td>
	    				<td><input  id="version" name="version" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>说明</td>
	    				<td><input  id="note" name="note" type="text" class="form-control"></td>
	    			</tr>	
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
	</div>
	
	
</div>

<%@include file="../include/admin/adminFooter.jsp"%>