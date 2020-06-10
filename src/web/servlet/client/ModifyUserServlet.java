package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.User;
import web.service.UserService;

/**
 *�޸��û��ĸ�����Ϣ�����޸ĵ����룬�ֻ�����
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
		//1.��ȡ�޸���Ϣ
		 String password = request.getParameter("password");
		 String telephone = request.getParameter("telephone");
		 //2.��ȡҪ�޸ĵ��û�����
		 User user = (User) request.getSession().getAttribute("user");
		UserService  service = new UserService();
		//3.����service�����û�����Ϣ�޸�
		service.modifyUserInfo(user,password,telephone);
		//4.��ת���޸ĳɹ�ҳ��
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
