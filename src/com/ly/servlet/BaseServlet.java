package com.ly.servlet;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ly.DAO.ApkFileDAO;
import com.ly.DAO.DeviceDAO;
import com.ly.DAO.EhtFileDAO;
import com.ly.DAO.UserDAO;
import com.ly.util.*;


public abstract class BaseServlet extends HttpServlet{
	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) ;
	
	
	protected DeviceDAO deviceDAO = new DeviceDAO();
	protected UserDAO userDAO = new UserDAO();
	protected EhtFileDAO ehtfileDAO = new EhtFileDAO();
	protected ApkFileDAO apkfileDAO = new ApkFileDAO();

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("BaseBackServlet service");
			/*��ȡ��ҳ��Ϣ*/
			int start= 0;
			int count = 12;
			try {
				start = Integer.parseInt(request.getParameter("page.start"));
			} catch (Exception e) {
				
			}
			try {
				count = Integer.parseInt(request.getParameter("page.count"));
			} catch (Exception e) {
			}
			Page page = new Page(start,count);
			
			/*�������䣬���ö�Ӧ�ķ���*/
			String method = (String) request.getAttribute("method");
			
			if(method==null) {
				System.out.println("method is none");
				method="list";
			}else {
				System.out.println("method is:"+method);
			}
			if(method.contains("fail")) {
				request.setAttribute("res", "exist");
				method="list";//method.replace("fail", "");//substring(0,method.indexOf("fail"));
				System.out.println("method is:"+method);
			}
			
			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
					javax.servlet.http.HttpServletResponse.class,Page.class);
			System.out.println("xixi");
			String redirect = m.invoke(this,request, response,page).toString();
			
			/*���ݷ����ķ���ֵ��������Ӧ�Ŀͻ�����ת���������ת�����߽���������ַ��� redirect is:admin/listDevice.jsp*/
			System.out.println("redirect is:"+redirect);
			if(redirect.startsWith("@"))
				response.sendRedirect(redirect.substring(1));
			else if(redirect.startsWith("%"))
				response.getWriter().print(redirect.substring(1));
			else
				request.getRequestDispatcher(redirect).forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
			InputStream is =null;
			try {
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            // �����ϴ��ļ��Ĵ�С����Ϊ10M
	            factory.setSizeThreshold(1024 * 10240);
	             
	            List items = upload.parseRequest(request);
	            Iterator iter = items.iterator();
	            while (iter.hasNext()) {
	                FileItem item = (FileItem) iter.next();
	                if (!item.isFormField()) {
	                    // item.getInputStream() ��ȡ�ϴ��ļ���������
	                    is = item.getInputStream();
	                } else {
	                	String paramName = item.getFieldName();
	                	String paramValue = item.getString();
	                	paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
	                	params.put(paramName, paramValue);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return is;
		}
}
