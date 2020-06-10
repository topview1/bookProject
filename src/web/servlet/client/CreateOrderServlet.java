package web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import web.entity.Order;
import web.entity.OrderItem;
import web.entity.Product;
import web.entity.User;
import web.service.OrderService;
import web.utils.IdUtils;

/**
 * Servlet implementation class CreateOrderServlet
 */
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.封装数据到javabean
		Order order =  new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//2.补全数据
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<Product,Integer> cart  = (Map<Product, Integer>) session.getAttribute("cart");
		order.setUser(user);
		order.setId(IdUtils.getUUID());
			//把对应的商品封装到订单条目里
		for(Product p:cart.keySet()) {
			OrderItem  item = new  OrderItem();
			item.setOrder(order);
			item.setP(p);
			item.setBuynum(cart.get(p));
			order.getOrderItems().add(item);
		}
		//3.调用service来生成订单
		OrderService service = new OrderService();
	   boolean flag = service.addOrder(order);
			//4.跳转到下单成功页面
	   		if(flag) {
	   			response.sendRedirect(request.getContextPath()+"/client/jsp/orderSuccess.jsp");
	   		}else {
	   			response.getWriter().print("生成订单失败");
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
