package com.ly.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ly.DAO.ApkFileDAO;

public class DownloadNbServlet extends HttpServlet{
	public ApkFileDAO apkdao=new ApkFileDAO();
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int apkid=0;
		apkid=apkdao.getMaxVersionId(2);
		File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+"/nb"+apkid+".apk");         //获得输出流     
		/*
		 * 两个头一个流
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. 流：下载文件的数据
		 */
		String filename =request.getSession().getServletContext().getRealPath("file/apk")+"/nb"+apkid+".apk";//niweismebubianyi
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
		response.setHeader("Content-Disposition", "attachment; filename=NBSEWAPP.apk");//contentDisposition);
		 
		// 获取绑定了响应端的流
		ServletOutputStream output = response.getOutputStream();
		//commons-io-1.4.jar中的IOUtils
		IOUtils.copy(input, output);//把输入流中的数据写入到输出流中。---输出流的节点就是客户端
		 
		input.close();
		
	}
}
