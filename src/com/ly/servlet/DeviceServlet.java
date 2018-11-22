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

import com.ly.bean.Device;
import com.ly.util.Page;

public class DeviceServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		String name= params.get("name");
		if(deviceDAO.checkNameExist(name)) {
			return "@admin_device_addfail";
		} 
		int htype=Integer.parseInt(params.get("htype"));
		int stype=Integer.parseInt(params.get("stype"));
		int mid=Integer.parseInt(params.get("mid"));
		int oid=Integer.parseInt(params.get("oid"));
		int location=Integer.parseInt(params.get("location"));
		int status=Integer.parseInt(params.get("status"));
		int bdate=Integer.parseInt(params.get("bdate"));
		Device d = new Device();
		d.setName(name);
		d.setHtype(htype);
		d.setStype(stype);
		d.setMid(mid);
		d.setOid(oid);
		d.setLocation(location);
		d.setStatus(status);
		d.setBdate(bdate);
		deviceDAO.add(d);
		
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
		return "@admin_device_list";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		deviceDAO.delete(id);
		return "@admin_device_list";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Device d = deviceDAO.getById(id);
		request.setAttribute("d", d);
		return "admin/editDevice.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("DeviceServlet service update");
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		System.out.println(params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));
		int htype = Integer.parseInt(params.get("htype"));
		int stype = Integer.parseInt(params.get("stype"));
		int mid = Integer.parseInt(params.get("mid"));
		int oid = Integer.parseInt(params.get("oid"));
		int location = Integer.parseInt(params.get("location"));
		int status = Integer.parseInt(params.get("status"));
		long bdate = Integer.parseInt(params.get("bdate"));

		Device d = new Device();
		d.setId(id);
		d.setName(name);
		d.setHtype(htype);
		d.setStype(stype);
		d.setMid(mid);
		d.setOid(oid);
		d.setLocation(location);
		d.setStatus(status);
		d.setBdate(bdate);
		deviceDAO.update(d);
		
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
		return "@admin_device_list";

	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int type=(int)request.getSession().getAttribute("type");
		int id=(int)request.getSession().getAttribute("id");
		
		if(type==1) {
			System.out.println("DeviceServlet service list");
			List<Device> ds = deviceDAO.list(page.getStart(),page.getCount());
			int total = deviceDAO.getTotal();
			page.setTotal(total);
			
			request.setAttribute("theds", ds);
			request.setAttribute("page", page);
			
			return "admin/listDevice.jsp";
		}else if(type==2){
			System.out.println("DeviceServlet service list");
			List<Device> ds = deviceDAO.listByMid(page.getStart(),page.getCount(),id);
			int total = deviceDAO.getTotalByMid(id);
			page.setTotal(total);
			
			request.setAttribute("theds", ds);
			request.setAttribute("page", page);
			
			return "manager/listDevice.jsp";
		}
		return "login.html";
	}
}
