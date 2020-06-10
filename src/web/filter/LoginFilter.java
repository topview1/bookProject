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
 * ���е�¼����
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
        //��ȡ��ǰ����·��
        String requestURL = request.getRequestURI().toString();
        //��ȡ���·��
        String url = requestURL.substring(requestURL.lastIndexOf("/"));
        //�����е�url
        String not_allowUrls = "/cart.jsp,/order.jsp,/orderSuccess.jsp";
        //�����ǰ·�����ڲ�����·�����棬��Ҫ���е�¼��֤
        if(not_allowUrls.indexOf(url) != -1) {
        	//��ȡsession
            HttpSession session = request.getSession();
            //�ж�session�Ƿ���ֵ
          User user = (User) session.getAttribute("user");
            if(user == null){
                //û��session����δ��¼���ض�����ҳ��
            	 response.getWriter().println("����δ��½�������Ӻ���ת����¼ҳ��");
            	 response.setHeader("refresh","3;"+request.getContextPath()+"/client/jsp/login.jsp");
                return;//�㲻��ִ�к�������
            }
        }
        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("�Ѿ�����");
	}

}
