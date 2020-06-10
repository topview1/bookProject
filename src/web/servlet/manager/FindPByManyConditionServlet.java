package web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.entity.Product;
import web.service.ProductService;

/**
 * ��������ѯ��Ʒ
 */
public class FindPByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPByManyConditionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ�����ѯ����
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		//2.����service
		ProductService service = new ProductService();
		//3.���÷���
		List<Product> ps  = service.findProductByManyCondition(id,name,category,minprice,maxprice);
		//4.����request��
		request.setAttribute("ps", ps);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
