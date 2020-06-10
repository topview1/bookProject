package web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.entity.User;

/**
 * 进行登录拦截
 */
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
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取当前请求路径
        String requestURL = request.getRequestURI().toString();
        //获取最后路径
        String url = requestURL.substring(requestURL.lastIndexOf("/"));
        //不放行的url
        String not_allowUrls = "/cart.jsp,/order.jsp,/orderSuccess.jsp";
        //如果当前路径是在不放行路径里面，则要进行登录验证
        if(not_allowUrls.indexOf(url) != -1) {
        	//获取session
            HttpSession session = request.getSession();
            //判断session是否有值
          User user = (User) session.getAttribute("user");
            if(user == null){
                //没有session，即未登录，重定向到主页面
            	 response.getWriter().println("您还未登陆，三秒钟后跳转至登录页面");
            	 response.setHeader("refresh","3;"+request.getContextPath()+"/client/jsp/login.jsp");
                return;//便不会执行后面的语句
            }
        }
        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("已经拦截");
	}

}
