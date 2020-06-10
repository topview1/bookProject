package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.User;
import web.service.UserService;

/**
 * 用户登录功能
 * 当用户为超级用户，跳转到后台管理页面
 * 当用户为普通用户，跳转到首页
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password  = request.getParameter("password");
		//调用service来判断用户名和密码是否正确
		UserService service = new UserService();
		User user = service.login(userName,password);
		//登录失败
		if(user == null) {
			request.setAttribute("loginEorror_message", "用户名或密码错误");
			request.getRequestDispatcher("/client/jsp/login.jsp").forward(request, response);
			return;
		}
	   //登录成功
		if(user.getState() == 1) {  //当用户为激活状态
			request.getSession().setAttribute("user", user);
			//当用户为超级用户时，跳到后台管理页面
			String role = user.getRole();
			if(role.equals("超级用户")) {
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/client/jsp/index.jsp");
				return;
			}
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
