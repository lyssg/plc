<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑分类</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("name","设备名称"))
			return false;

		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_device_list">所有设备</a></li>
	  <li class="active">编辑设备</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑设备</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_device_update"  enctype="multipart/form-data">
	    		<table class="editTable">
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
	    					<input type="hidden" name="id" value="${d.id}">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
	</div>	
</div>
<!-- 
					<tr>
	    				<td>设备型号</td>
	    				<td>
	    					<input id="categoryPic" accept="image/*" type="file" name="filepath" />
	    				</td>
	    			</tr>
	    			 -->