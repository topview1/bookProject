package web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

import web.entity.User;
import web.exception.RegisterException;
import web.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//校验验证码
		String verCode = request.getParameter("verCode");
		String result = (String) request.getSession().getAttribute("result");
		if(!verCode.equals(result)) {
			request.setAttribute("codeError", "验证码错误");
			request.getRequestDispatcher("/client/jsp/register.jsp").forward(request, response);
			return;
		}
		//如果验证码正确封装User
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//调用sevice层，进行user的添加操作
		UserService  service = new UserService();
		try {
			service.register(user);
			//注册成功
			response.sendRedirect(request.getContextPath()+"/client/jsp/registerSuccsess.jsp");
		} catch (RegisterException e) {
			String error = e.getMessage();
			response.getWriter().write(error);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
