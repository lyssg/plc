package com.ly.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class BackServletFilter implements Filter {

	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String contextPath=request.getServletContext().getContextPath();
		System.out.println("contextPath is:"+contextPath);
		String uri = request.getRequestURI();
		System.out.println("uri is:"+uri);
		
		if(uri.endsWith("login.html")||uri.endsWith("loginServlet")||uri.endsWith(".css")||uri.endsWith(".js")) {
			chain.doFilter(request, response);
			return;
		}
		
		String username=(String)request.getSession().getAttribute("name");
		int type=0;
		if(username==null) {
			response.sendRedirect("login.html");
			return;
		}else {
			type=(int)request.getSession().getAttribute("type");
		}
		
		if(type==1) {
			uri =StringUtils.remove(uri, contextPath);
			if(uri.startsWith("/admin_")){		
				String servletPath = StringUtils.substringBetween(uri,"_", "_") + "Servlet";
				String method = StringUtils.substringAfterLast(uri,"_" );
				request.setAttribute("method", method);
				req.getRequestDispatcher("/" + servletPath).forward(request, response);
				return;
			}
		}else {
			uri =StringUtils.remove(uri, contextPath);
			if(uri.startsWith("/manager_")){		
				String servletPath = StringUtils.substringBetween(uri,"_", "_") + "Servlet";
				String method = StringUtils.substringAfterLast(uri,"_" );
				request.setAttribute("method", method);
				req.getRequestDispatcher("/" + servletPath).forward(request, response);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig arg0) throws ServletException {
	
	}
}
