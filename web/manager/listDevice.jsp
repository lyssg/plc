<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/manager/managerNavigator.jsp"%>

<script>
$(function(){
	$("#addForm").submit(function(){
		if(!checkEmpty("name","设备名称"))
			return false;
		return true;
	});
});

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
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	  
	<!-- 
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增设备</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_device_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>设备名称</td>
	    				<td><input  id="name" name="name" value="${d.name}" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>硬件型号</td>
	    				<td><input  id="htype" name="htype" value="${d.htype}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>软件型号</td>
	    				<td><input  id="stype" name="stype" value="${d.stype}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>管理员</td>
	    				<td><input  id="mid" name="mid" value="${d.mid}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>操作工人</td>
	    				<td><input  id="oid" name="oid" value="${d.oid}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>所在地</td>
	    				<td><input  id="location" name="location" value="${d.location}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>设备状态</td>
	    				<td><input  id="status" name="status" value="${d.status}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>生产日期</td>
	    				<td><input  id="bdate" name="bdate" value="${d.bdate}" type="number" class="form-control"></td>
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
	 -->
	
</div>

<%@include file="../include/admin/adminFooter.jsp"%>