package com.ly.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.bean.User;
import com.ly.util.Page;

public class UserServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		String name= params.get("name");
		if(userDAO.checkNameExist(name)) {
			return "@admin_user_addfail";
		} 
		String password= params.get("password");
		String email= params.get("email");
		User u = new User();
		System.out.println(name);
		u.setName(name);
		u.setPassword(password);
		u.setType(2);
		u.setEmail(email);
		userDAO.add(u);
		
//		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
//		File file = new File(imageFolder,c.getId()+".jpg");
//		
//		try {
//			if(null!=is && 0!=is.available()){
//			    try(FileOutputStream fos = new FileOutputStream(file)){
//			        byte b[] = new byte[1024 * 1024];
//			        int length = 0;
//			        while (-1 != (length = is.read(b))) {
//			            fos.write(b, 0, length);
//			        }
//			        fos.flush();
//			        //通过如下代码，把文件保存为jpg格式
//			        BufferedImage img = ImageUtil.change2jpg(file);
//			        ImageIO.write(img, "jpg", file);		
//			    }
//			    catch(Exception e){
//			    	e.printStackTrace();
//			    }
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		return "@admin_user_list";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(id);
		return "@admin_user_list";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		User d = userDAO.getById(id);
		request.setAttribute("d", d);
		return "admin/editUser.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("UserServlet service update");
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		System.out.println(params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));
		String password = params.get("password");
		String email = params.get("email");
//		int type = Integer.parseInt(params.get("type"));

		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setPassword(password);
		u.setType(2);
		u.setEmail(email);
		userDAO.update(u);
		
//		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
//		File file = new File(imageFolder,c.getId()+".jpg");
//		file.getParentFile().mkdirs();
//		
//		try {
//			if(null!=is && 0!=is.available()){
//			    try(FileOutputStream fos = new FileOutputStream(file)){
//			        byte b[] = new byte[1024 * 1024];
//			        int length = 0;
//			        while (-1 != (length = is.read(b))) {
//			            fos.write(b, 0, length);
//			        }
//			        fos.flush();
//			        //通过如下代码，把文件保存为jpg格式
//			        BufferedImage img = ImageUtil.change2jpg(file);
//			        ImageIO.write(img, "jpg", file);		
//			    }
//			    catch(Exception e){
//			    	e.printStackTrace();
//			    }
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "@admin_user_list";

	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("UserServlet service list");
		List<User> us = userDAO.list(page.getStart(),page.getCount());
		int total = userDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("theus", us);
		request.setAttribute("page", page);
		
		return "admin/listUser.jsp";
	}
}

