package web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *处理乱码的过滤器
 */
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
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
		//处理请求乱码 ，
		HttpServletRequest httpServletRequest  = (HttpServletRequest) request;
		//为了应对tomcat8以前的版本
		//HttpServletRequest myRequest =  new MyRequest(httpServletRequest);
		
		//tomcat8以后的版本
		request.setCharacterEncoding("utf-8");
		
		//处理响应乱码，为了应对 PrintWriter的情况
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		private boolean hasEncode;
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		@Override
		public String getParameter(String name) {
			Map<String,String[]> parameterMap = getParameterMap();
			String[] values = parameterMap.get(name);
			if(values == null) {
				return null;
			}
			return values[0];//取回参数的第一个值
		}
		
		
		
		
		@Override
		public Map<String, String[]> getParameterMap() {
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equalsIgnoreCase("get")) {
			//get请求,使用于tomcat8以下版本 
			Map<String,String[]>  parameterMap = request.getParameterMap();
			if(!hasEncode) {  //确保get手动编码只允许一次
				for(String parameterName:parameterMap.keySet()) {
					String[] values = parameterMap.get(parameterName);
					if(values != null) {
						for(int i = 0;i<values[i].length();i++) {
							try {
								values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode = true;
			}
			return parameterMap;
		}
			return super.getParameterMap();
		}
		
		
		@Override
		public String[] getParameterValues(String name) {
			Map<String,String[]> parameterMap = getParameterMap();
			String[] values = parameterMap.get(name);
			return values;
		}
		
	}

}
