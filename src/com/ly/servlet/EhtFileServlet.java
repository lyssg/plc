package com.ly.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.bean.EhtFile;
import com.ly.util.Page;

public class EhtFileServlet extends BaseServlet{
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		int type=Integer.parseInt( params.get("type"));
		int version= Integer.parseInt(params.get("version"));
		if(ehtfileDAO.checkVersionExist(type,version)) {
			request.setAttribute("res", "exist");
			return "@admin_ehtfile_addfail";
		}
		String note= params.get("note");
		EhtFile ehtfile = new EhtFile();
		System.out.println("ehtfile type :"+type);
		ehtfile.setType(type);
		ehtfile.setVersion(version);
		ehtfile.setNote(note);
		ehtfile.setBdate(1);
		ehtfileDAO.add(ehtfile);
		
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("file/eht"));
		File file = new File(imageFolder,ehtfile.getId()+".eht");
		
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
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "@admin_ehtfile_list";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ehtfileDAO.delete(id);
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("file/eht"));
		File file = new File(imageFolder,id+".eht");
		file.delete();
		return "@admin_ehtfile_list";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		EhtFile e = ehtfileDAO.getById(id);
		request.setAttribute("e", e);
		return "admin/editEhtFile.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("EhtFileServlet service update");
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		System.out.println(params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));
		int type=Integer.parseInt( params.get("type"));
		int version= Integer.parseInt(params.get("version"));
		String note= params.get("note");
		EhtFile ehtfile = new EhtFile();
		System.out.println("ehtfile type :"+type);
		ehtfile.setId(id);
		ehtfile.setType(type);
		ehtfile.setVersion(version);
		ehtfile.setNote(note);
		ehtfile.setBdate(1);
		ehtfileDAO.add(ehtfile);
		
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
		return "@admin_ehtfile_list";

	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		System.out.println("EhtFileServlet service list");
		List<EhtFile> es = ehtfileDAO.list(page.getStart(),page.getCount());
		int total = ehtfileDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("thees", es);
		request.setAttribute("page", page);
		
		return "admin/listEhtFile.jsp";
	}
}
