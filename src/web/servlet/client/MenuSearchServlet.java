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
				//3.��ȡ�������ֵ
				String search_key = request.getParameter("search_key");
				if("����������".equals(search_key)) {
					//����û������������ȫ����ѯ
					request.getRequestDispatcher("/ShowProductByPageServlet").forward(request, response);
					return;
				}
				//��������,���������з�ҳ����
				ProductService  service = new  ProductService();
				PageBean bean = service.findProductByName(currentPage,currentCount,search_key);
				// �����ݴ洢��request��Χ����ת��product_search_list.jspҳ��չʾ
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
