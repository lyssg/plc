<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	$("#addForm").submit(function(){
		if(!checkEmpty("name","用户名"))
			return false;
		if(!checkEmpty("password","密码"))
			return false;
		return true;
	});
});
<c:if test="${!empty res}">
alert("用户已存在");
</c:if>
</script>

<title>设备管理</title>


<div class="workingArea">
	<h1 class="label label-info" >分类管理</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>用户类型</th>
					<th>邮箱</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${theus}" var="u">
				
				<tr>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td>${u.password}</td>
					<td>${u.type}</td>
					<td>${u.email}</td>
<%--					<td><img height="40px" src="img/category/${d.id}.jpg"></td>
					<td>${d.name}</td>--%>
<%-- 					<td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>					 --%>
<%-- 					<td><a href="admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>	--%>				 
					<td><a href="admin_user_edit?id=${u.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_user_delete?id=${u.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	  
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增用户</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_user_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>用户名</td>
	    				<td><input  id="name" name="name" value="" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>密码</td>
	    				<td><input  id="password" name="password" value="" type="text" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>邮箱</td>
	    				<td><input  id="email" name="email" value="" type="text" class="form-control"></td>
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