<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<style>
<!--

-->
span.namespan {
	font-size:25px;
	margin-top:5px;
	margin-right:20px;
}
span.namespan a{
	margin-right:20px;
	color:#c2c2c2;
}

span.namespan a:hover{
	color: #FFF;
    text-decoration: none;
}

</style>
<div class="navitagorDiv">
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<img style="margin-left:10px;margin-right:0px" class="pull-left" src="img/site/ljcloudlogo.png" height="45px">
		<a class="navbar-brand" href="#nowhere">缝纫机云端后台</a>
		<a class="navbar-brand" href="admin_device_list">设备管理</a>
		<a class="navbar-brand" href="admin_ehtfile_list">电控程序管理</a>
		<a class="navbar-brand" href="admin_apkfile_list">APP升级管理</a>
		<a class="navbar-brand" href="admin_user_list">用户管理</a>
		<span class="pull-right namespan"><a href="">${name}</a><a href="login.html">注销登陆</a></span>
	</nav>
</div>
