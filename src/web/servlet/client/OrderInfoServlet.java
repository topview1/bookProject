package web.servlet.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Order;
import web.entity.OrderItem;
import web.entity.Product;
import web.service.OrderService;

/**
 * Servlet implementation class OrderInfoServlet
 */
public class OrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡurl�еĶ���id��
		String id = request.getParameter("id");
		request.getSession().setAttribute("order_id", id); //Ϊ��ʹ֧����������
		//2.����service
		OrderService service = new OrderService();
		Map<Product, Integer> items= service.findOrderInfoById(id);
		//3.���õ�request��
		request.setAttribute("items", items);
		//4.��д���ݵ�jspҳ��
		request.getRequestDispatcher("/client/jsp/search_orderInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
