package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.User;
import web.service.UserService;

/**
 *修改用户的个人信息，可修改的密码，手机号码
 */
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取修改信息
		 String password = request.getParameter("password");
		 String telephone = request.getParameter("telephone");
		 //2.获取要修改的用户对象
		 User user = (User) request.getSession().getAttribute("user");
		UserService  service = new UserService();
		//3.调用service进行用户的信息修改
		service.modifyUserInfo(user,password,telephone);
		//4.跳转到修改成功页面
		response.sendRedirect(request.getContextPath()+"/client/jsp/modifySuccsess.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
