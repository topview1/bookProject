package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Product;
import web.exception.FindProductByIdException;
import web.service.ProductService;

/**
 * Servlet implementation class findProductByIdServlet
 */
public class findProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findProductByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取product的id号
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别普通用户和超级用户
				String type = request.getParameter("type");
		//2.调用service方法进行查找
		ProductService service = new ProductService();
		Product p;
		try {
			p = service.findProductById(id);
			request.setAttribute("p", p);
			//3.进行页面跳转
			if(type == null) {
				request.getRequestDispatcher("/client/jsp/product_info.jsp").forward(request, response);
				return;
			}
			//如果是超级用户，进入编辑页面
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
