<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑用户</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("name","用户名"))
			return false;
		if(!checkEmpty("password","密码"))
			return false;
		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_device_list">所有用户</a></li>
	  <li class="active">编辑用户</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑用户</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_device_update"  enctype="multipart/form-data">
	    		<table class="editTable">
	    			<tr>
	    				<td>用户名</td>
	    				<td><input  id="name" name="name" value="${u.name}" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>密码</td>
	    				<td><input  id="password" name="password" value="${u.htype}" type="text" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>类型</td>
	    				<td><input  id="type" name="type" value="${u.stype}" type="number" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>邮箱</td>
	    				<td><input  id="email" name="email" value="${u.mid}" type="text" class="form-control"></td>
	    			</tr>	 			
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<input type="hidden" name="id" value="${u.id}">
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