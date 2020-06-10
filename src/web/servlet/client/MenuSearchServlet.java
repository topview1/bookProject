package web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.PageBean;
import web.service.ProductService;

/**
 * Servlet implementation class MenuSearchServlet
 */
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuSearchServlet() {
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
				//3.获取搜索框的值
				String search_key = request.getParameter("search_key");
				if("请输入书名".equals(search_key)) {
					//等于没有书名，进行全部查询
					request.getRequestDispatcher("/ShowProductByPageServlet").forward(request, response);
					return;
				}
				//输入书名,对书名进行分页查找
				ProductService  service = new  ProductService();
				PageBean bean = service.findProductByName(currentPage,currentCount,search_key);
				// 将数据存储到request范围，跳转到product_search_list.jsp页面展示
				request.setAttribute("bean", bean);
				request.getRequestDispatcher("/client/jsp/product_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
