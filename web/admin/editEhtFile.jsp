<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑电控程序信息</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("type","电控程序类型"))
			return false;
		if(!checkEmpty("version","电控程序版本"))
			return false;
		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_ehtfile_list">所有电控程序</a></li>
	  <li class="active">编辑电控程序</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑电控程序</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_ehtfile_update"  enctype="multipart/form-data">
	    		<table class="editTable">
	    			<tr>
	    				<td>类型</td>
	    				<td><input  id="name" name="name" value="${e.type}" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>版本</td>
	    				<td><input  id="password" name="password" value="${e.version}" type="text" class="form-control"></td>
	    			</tr>	
	    			<tr>
	    				<td>说明</td>
	    				<td><input  id="type" name="type" value="${e.note}" type="number" class="form-control"></td>
	    			</tr>		 			
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<input type="hidden" name="id" value="${e.id}">
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