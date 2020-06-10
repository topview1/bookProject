package web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Order;
import web.entity.User;
import web.service.OrderService;

/**
 * ���ұ��û������ж���
 */
public class FindOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindOrderByUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ�û�
		User user = (User) request.getSession().getAttribute("session");
		//2.����OrderService
		OrderService service = new OrderService();
		List<Order> orders = (List<Order>) service.findOrderByUser(user);
		//3.�����ݻ��Ե�search_order.jsp��ҳ����
		request.setAttribute("orders",orders);
		request.getRequestDispatcher("/client/jsp/search_order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
