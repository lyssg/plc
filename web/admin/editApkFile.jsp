<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑APP信息</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("type","APP类型"))
			return false;
		if(!checkEmpty("version","APP版本"))
			return false;
		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_apkfile_list">所有APP</a></li>
	  <li class="active">编辑APP信息</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑APP信息</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_apkfile_update"  enctype="multipart/form-data">
	    		<table class="editTable">
	    			<tr>
	    				<td>类型</td>
	    				<td><input  id="name" name="name" value="${a.type}" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>版本</td>
	    				<td><input  id="password" name="password" value="${a.version}" type="text" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>说明</td>
	    				<td><input  id="type" name="type" value="${a.note}" type="number" class="form-control"></td>
	    			</tr>		 			
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<input type="hidden" name="id" value="${a.id}">
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