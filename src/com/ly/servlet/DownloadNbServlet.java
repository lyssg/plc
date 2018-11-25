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
		File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+"/nb"+apkid+".apk");         //��������     
		/*
		 * ����ͷһ����
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. ���������ļ�������
		 */
		String filename =request.getSession().getServletContext().getRealPath("file/apk")+"/nb"+apkid+".apk";//niweismebubianyi
		System.out.println("file:"+filename);
		// Ϊ��ʹ���ؿ�����ʾ�����ļ����Ʋ������룡
		// String framename = new String("����%����.mp3".getBytes("GBK"), "ISO-8859-1");
//		String framename = filenameEncoding("Singre.mp3", request);
		 
		String contentType = this.getServletContext().getMimeType(filename);//ͨ���ļ����ƻ�ȡMIME����
//		String contentDisposition = "attachment;filename=" + framename;
		// һ����
		FileInputStream input = new FileInputStream(filename);
		 
		//����ͷ
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", "attachment; filename=NBSEWAPP.apk");//contentDisposition);
		 
		// ��ȡ������Ӧ�˵���
		ServletOutputStream output = response.getOutputStream();
		//commons-io-1.4.jar�е�IOUtils
		IOUtils.copy(input, output);//���������е�����д�뵽������С�---������Ľڵ���ǿͻ���
		 
		input.close();
		
	}
}
