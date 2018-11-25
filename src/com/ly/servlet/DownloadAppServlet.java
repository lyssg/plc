package com.ly.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.ly.DAO.ApkFileDAO;
import com.ly.util.Page;

public class DownloadAppServlet extends HttpServlet{
	public ApkFileDAO apkdao=new ApkFileDAO();
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int apkid=0;
		apkid=apkdao.getMaxVersionId(1);
		File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+"/lj"+apkid+".apk");         //获得输出流     
		
		/*
		 * 两个头一个流
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. 流：下载文件的数据
		 */
		String filename =request.getSession().getServletContext().getRealPath("file/apk")+"/lj"+apkid+".apk";//niweismebubianyi
		System.out.println("file:"+filename);
		// 为了使下载框中显示中文文件名称不出乱码！
		// String framename = new String("流光%飞舞.mp3".getBytes("GBK"), "ISO-8859-1");
//		String framename = filenameEncoding("Singre.mp3", request);
		 
		String contentType = this.getServletContext().getMimeType(filename);//通过文件名称获取MIME类型
//		String contentDisposition = "attachment;filename=" + framename;
		// 一个流
		FileInputStream input = new FileInputStream(filename);
		 
		//设置头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", "attachment; filename=LJSEWAPP.apk");//contentDisposition);
		 
		// 获取绑定了响应端的流
		ServletOutputStream output = response.getOutputStream();
		//commons-io-1.4.jar中的IOUtils
		IOUtils.copy(input, output);//把输入流中的数据写入到输出流中。---输出流的节点就是客户端
		 
		input.close();
		
//		System.out.println("file:"+file.getAbsolutePath());
//		if(file.exists()){  
//            FileInputStream  fis = new FileInputStream(file);  
//            String filename=URLEncoder.encode(file.getName(),"utf-8"); //解决中文文件名下载后乱码的问题  
//            byte[] b = new byte[fis.available()];  
//            fis.read(b);  
//            response.setCharacterEncoding("utf-8");  
//            response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
//            //获取响应报文输出流对象  
//            ServletOutputStream  out =response.getOutputStream();  
//            //输出  
//            out.write(b);  
//            out.flush();  
//            out.close();  
//        }     

//		try {
//			//获取系要下载的文件        
//			File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+apkid+".apk");         //获得输出流        
//			OutputStream os = response.getOutputStream();         
//			//获得文件扩展名        
//			String extfilename = FilenameUtils.getExtension("D:\\下载\\BaiduNetdisk_6.2.0.exe");         
//			
//			//指定文件下载名 并指定编码格式        
//			response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode("百度云盘."+extfilename,"utf-8"));
//			//将文件写到输出流        
//			IOUtils.copy(new FileInputStream(file),os);        //刷新流        
//			os.flush();        //关闭流        
//			os.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	}
	
//	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
//		String agent = request.getHeader("User-Agent"); //获取浏览器
//		if (agent.contains("Firefox")) {
//		BASE64Encoder base64Encoder = new BASE64Encoder();
//		filename = "=?utf-8?B?"
//		+ base64Encoder.encode(filename.getBytes("utf-8"))
//		+ "?=";
//		} else if(agent.contains("MSIE")) {
//		filename = URLEncoder.encode(filename, "utf-8");
//		} else {
//		filename = URLEncoder.encode(filename, "utf-8");
//		}
//		return filename;
//		}
}
