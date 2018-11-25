<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	$("#addForm").submit(function(){
		if(!checkEmpty("type","APP类型"))
			return false;
		if(!checkEmpty("version","APP版本"))
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

<title>APP管理</title>


<div class="workingArea">
	<h1 class="label label-info" >APP列表</h1>
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
				<c:forEach items="${theas}" var="a">
				
				<tr>
					<td>${a.id}</td>
					<td>${a.type}</td>
					<td>${a.version}</td>
					<td>${a.note}</td>
					<td><fmt:formatDate value="${a.bddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	 
					<td><a href="admin_apkfile_edit?id=${a.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_apkfile_delete?id=${a.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
	 
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	  
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增APP</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_apkfile_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>类型</td>
	    				<td><select id="type" name="type" class="form-control">
							   <option>乐江智能缝纫机</option>
							   <option>南邦智能缝纫机</option>
							</select>
						</td>
	    			</tr>
	    			<tr>
	    				<td>选择文件</td>
	    				<td>
	    					<input id="filepath" accept="file/apk/*" type="file" name="filepath"/>
	    				</td>
	    			</tr>
	    			<tr>
	    				<td>版本</td>
	    				<td><input  id="version" name="version" type="number" class="form-control">
	    				
						</td>
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