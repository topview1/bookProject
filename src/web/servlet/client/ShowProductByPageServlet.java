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
		//���嵱ǰҳ����,Ĭ����1
		int currentPage = 1;
		//����ͨ��url�������ı�
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.����ÿҳ��ʾ����,Ĭ��Ϊ4
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		//3.���ҵķ��࣬Ĭ����ȫ����Ʒ
		String category = "ȫ����Ʒ";
		String _category = request.getParameter("category");
		if (_category != null) {
			category = _category;
		}
		//4.����sevice,��ɷ�ҳ��ѯ
		ProductService service = new ProductService();
		PageBean bean=service.findProductByPage(currentPage, currentCount, category);
		//5 �����ݴ浽request�����У����͸�ǰ�� product_list.jsp
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
