package web.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.entity.Product;
import web.exception.FindProductByIdException;
import web.service.ProductService;

/**
 * 将商品添加到购物车
 */
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取商品id
			String id = request.getParameter("id");
			ProductService service = new ProductService();
			HttpSession session = request.getSession();
			//2.调用service查找product对象
			Product product = service.findProductById(id);
			//3.获取前端页面的购物车,进行商品的加入
			Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
				//如果购物车为空,表示第一次购买
			if(cart == null) {
				cart = new ConcurrentHashMap<Product, Integer>(); //实现线程安全
			}
					//如果此商品没有在购物车，返回的count值为空,/如果不为空，会返回旧值，并且会将product，1存入购物车,
			Integer count = cart.put(product, 1);
				//如果购物车已经有了该商品，put进行覆盖
			if(count != null) {
				cart.put(product, count+1);
			}
			//4.将新的购物车对象返给cart.jsp进行数据重新读取
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/client/jsp/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			//打印报错信息
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
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
