package com.ly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.DAO.UserDAO;
import com.ly.bean.User;

public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
//		System.out.println(b);
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		System.out.println("name:"+name+"   password:"+password);
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("type");
		request.getSession().removeAttribute("id");
		
		UserDAO userDAO = new UserDAO();
		User user=new User();
		user=userDAO.checkPassword(name, password);
		int type=user.getType();
		if(type==0) {
			response.sendRedirect("login.html");
		}else if(type==1){
			int id=user.getId();
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("type", type);
			request.getSession().setAttribute("id", id);
			response.sendRedirect("/admin_device_list");//.forward(request, response);
		}else if(type==2){
			int id=user.getId();
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("type", type);
			request.getSession().setAttribute("id", id);
			response.sendRedirect("/manager_device_list");//.forward(request, response);
		}
	}
}
