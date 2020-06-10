package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.User;
import web.service.UserService;

/**
 * �û���¼����
 * ���û�Ϊ�����û�����ת����̨����ҳ��
 * ���û�Ϊ��ͨ�û�����ת����ҳ
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
		//����service���ж��û����������Ƿ���ȷ
		UserService service = new UserService();
		User user = service.login(userName,password);
		//��¼ʧ��
		if(user == null) {
			request.setAttribute("loginEorror_message", "�û������������");
			request.getRequestDispatcher("/client/jsp/login.jsp").forward(request, response);
			return;
		}
	   //��¼�ɹ�
		if(user.getState() == 1) {  //���û�Ϊ����״̬
			request.getSession().setAttribute("user", user);
			//���û�Ϊ�����û�ʱ��������̨����ҳ��
			String role = user.getRole();
			if(role.equals("�����û�")) {
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
