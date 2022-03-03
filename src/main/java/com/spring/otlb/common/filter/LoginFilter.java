package com.otlb.semi.common.filter;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/message/*", "/board/*"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//로그인여부 검사
		HttpSession session = httpRequest.getSession();
		Emp loginEmp = (Emp) session.getAttribute("loginEmp");
		
		//System.out.println("++++++++++++++++++++통과전");
		
		if(loginEmp == null) {
			//System.out.println("++++++++통화후");
			session.setAttribute("msg", "로그인이 필요한 페이지 입니다.");
			httpResponse.sendRedirect(httpRequest.getContextPath());
			return;
		} else {
			//System.out.println("---------------------ddd-------");
			String empNo = String.valueOf(loginEmp.getEmpNo());
			String filepath = LoginFilter.class.getResource("/../../img/profile").getPath();
			File ownProfileImage = new File(filepath + empNo + ".png");
			if(ownProfileImage.exists()) session.setAttribute("ownProfileImageExists", true);
			else session.setAttribute("ownProfileImageExists", false);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
