<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	$("#addForm").submit(function(){
		if(!checkEmpty("name","设备名称"))
			return false;
		return true;
	});
});
<c:if test="${!empty res}">
alert("设备已存在");
</c:if>
</script>

<title>设备管理</title>


<div class="workingArea">
	<h1 class="label label-info" >设备列表</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>名称</th>
					<th>硬件类型</th>
					<th>软件类型</th>
					<th>管理员</th>
					<th>操作工人</th>
					<th>所在地</th>
					<th>状态</th>
					<th>生产日期</th>
<!-- 					<th>属性管理</th> -->
<!-- 					<th>产品管理</th> -->
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${theds}" var="d">
				
				<tr>
					<td>${d.id}</td>
					<td>${d.name}</td>
					<td>${d.htype}</td>
					<td>${d.stype}</td>
					<td>${d.mid}</td>
					<td>${d.oid}</td>
					<td>${d.location}</td>
					<td>${d.status}</td>
					<td>${d.bdate}</td>
<%--					<td><img height="40px" src="img/category/${d.id}.jpg"></td>
					<td>${d.name}</td>--%>
<%-- 					<td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>					 --%>
<%-- 					<td><a href="admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>	--%>				 
					<td><a href="admin_device_edit?id=${d.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_device_delete?id=${d.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	  
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增设备</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_device_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>设备名称</td>
	    				<td><input  id="name" name="name" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>硬件型号</td>
	    				<td><input  id="htype" name="htype" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>软件型号</td>
	    				<td><input  id="stype" name="stype" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>管理员</td>
	    				<td><input  id="mid" name="mid" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>操作工人</td>
	    				<td><input  id="oid" name="oid" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>所在地</td>
	    				<td><input  id="location" name="location" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>设备状态</td>
	    				<td><input  id="status" name="status" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>生产日期</td>
	    				<td><input  id="bdate" name="bdate" type="number" class="form-control"></td>
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