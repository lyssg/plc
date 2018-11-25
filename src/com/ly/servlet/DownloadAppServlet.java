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
		File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+"/lj"+apkid+".apk");         //��������     
		
		/*
		 * ����ͷһ����
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. ���������ļ�������
		 */
		String filename =request.getSession().getServletContext().getRealPath("file/apk")+"/lj"+apkid+".apk";//niweismebubianyi
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
		response.setHeader("Content-Disposition", "attachment; filename=LJSEWAPP.apk");//contentDisposition);
		 
		// ��ȡ������Ӧ�˵���
		ServletOutputStream output = response.getOutputStream();
		//commons-io-1.4.jar�е�IOUtils
		IOUtils.copy(input, output);//���������е�����д�뵽������С�---������Ľڵ���ǿͻ���
		 
		input.close();
		
//		System.out.println("file:"+file.getAbsolutePath());
//		if(file.exists()){  
//            FileInputStream  fis = new FileInputStream(file);  
//            String filename=URLEncoder.encode(file.getName(),"utf-8"); //��������ļ������غ����������  
//            byte[] b = new byte[fis.available()];  
//            fis.read(b);  
//            response.setCharacterEncoding("utf-8");  
//            response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
//            //��ȡ��Ӧ�������������  
//            ServletOutputStream  out =response.getOutputStream();  
//            //���  
//            out.write(b);  
//            out.flush();  
//            out.close();  
//        }     

//		try {
//			//��ȡϵҪ���ص��ļ�        
//			File file = new File(request.getSession().getServletContext().getRealPath("file/apk")+apkid+".apk");         //��������        
//			OutputStream os = response.getOutputStream();         
//			//����ļ���չ��        
//			String extfilename = FilenameUtils.getExtension("D:\\����\\BaiduNetdisk_6.2.0.exe");         
//			
//			//ָ���ļ������� ��ָ�������ʽ        
//			response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode("�ٶ�����."+extfilename,"utf-8"));
//			//���ļ�д�������        
//			IOUtils.copy(new FileInputStream(file),os);        //ˢ����        
//			os.flush();        //�ر���        
//			os.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	}
	
//	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
//		String agent = request.getHeader("User-Agent"); //��ȡ�����
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
