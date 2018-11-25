package com.ly.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.bean.ApkFile;
import com.ly.util.Page;

public class ApkFileServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		int type=0;
		if(params.get("type").equals("乐江智能缝纫机")) {
			type=1;
		}else if(params.get("type").equals("南邦智能缝纫机")) {
			type=2;
		}
		int version= Integer.parseInt(params.get("version"));
		if(apkfileDAO.checkVersionExist(type,version)) {
			return "@admin_apkfile_addfail";
		} 
		String note= params.get("note");
		ApkFile apkfile = new ApkFile();
		System.out.println("apkfile type :"+type);
		apkfile.setType(type);
		apkfile.setVersion(version);
		apkfile.setNote(note);
		Date bd=new Date();
		apkfile.setBdate(bd.getTime());
		apkfileDAO.add(apkfile);
		
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("file/apk"));
		File file;
		if(type==1) {
			file = new File(imageFolder,"lj"+apkfile.getId()+".apk");
		}else {
			file = new File(imageFolder,"nb"+apkfile.getId()+".apk");
		}
		
		try {
			if(null!=is && 0!=is.available()){
			    try(FileOutputStream fos = new FileOutputStream(file)){
			        byte b[] = new byte[1024 * 1024];
			        int length = 0;
			        while (-1 != (length = is.read(b))) {
			            fos.write(b, 0, length);
			        }
			        fos.flush();
			        fos.close();
			        //通过如下代码，把文件保存为jpg格式
//			        BufferedImage img = ImageUtil.change2jpg(file);
//			        ImageIO.write(img, "jpg", file);		
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "@admin_apkfile_list";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int type=apkfileDAO.getTypeById(id);
		apkfileDAO.delete(id);
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("file/apk"));
		File file;
		if(type==1) {
			file = new File(imageFolder,"lj"+id+".apk");
		}else {
			file = new File(imageFolder,"nb"+id+".apk");
		}
		file.delete();
		return "@admin_apkfile_list";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApkFile e = apkfileDAO.getById(id);
		request.setAttribute("e", e);
		return "admin/editApkFile.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("ApkFileServlet service update");
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		System.out.println(params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));
		int type=Integer.parseInt( params.get("type"));
		int version= Integer.parseInt(params.get("version"));
		String note= params.get("note");
		ApkFile apkfile = new ApkFile();
		System.out.println("apkfile type :"+type);
		apkfile.setId(id);
		apkfile.setType(type);
		apkfile.setVersion(version);
		apkfile.setNote(note);
		Date bd=new Date();
		
		apkfile.setBdate(bd.getTime());
		apkfileDAO.add(apkfile);
		
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
		return "@admin_apkfile_list";

	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("ApkFileServlet service list");
		List<ApkFile> as = apkfileDAO.list(page.getStart(),page.getCount());
		int total = apkfileDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("theas", as);
		request.setAttribute("page", page);
		
		return "admin/listApkFile.jsp";
	}
}
