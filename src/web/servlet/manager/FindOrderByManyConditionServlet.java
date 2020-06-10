package web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Order;
import web.service.OrderService;

/**
 *订单的模糊查询
 */
public class FindOrderByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindOrderByManyConditionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.准备参数
		String id =request.getParameter("id");
		String receiverName = request.getParameter("receiverName");
		//2，调用Order
		OrderService service = new OrderService();
		List<Order> orders = service.findOrderByManyCondition(id,receiverName);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}

}
