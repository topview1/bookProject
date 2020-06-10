package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.PageBean;
import web.service.ProductService;



/**
 * Servlet implementation class ShowProductByPageServlet
 */
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义当前页码数,默认是1
		int currentPage = 1;
		//可以通过url参数来改变
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.定义每页显示条数,默认为4
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		//3.查找的分类，默认是全部商品
		String category = "全部商品";
		String _category = request.getParameter("category");
		if (_category != null) {
			category = _category;
		}
		//4.调用sevice,完成分页查询
		ProductService service = new ProductService();
		PageBean bean=service.findProductByPage(currentPage, currentCount, category);
		//5 将数据存到request的域中，发送给前端 product_list.jsp
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/jsp/product_list.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
