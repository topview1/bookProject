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
 * ����Ʒ��ӵ����ﳵ
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
			//1.��ȡ��Ʒid
			String id = request.getParameter("id");
			ProductService service = new ProductService();
			HttpSession session = request.getSession();
			//2.����service����product����
			Product product = service.findProductById(id);
			//3.��ȡǰ��ҳ��Ĺ��ﳵ,������Ʒ�ļ���
			Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
				//������ﳵΪ��,��ʾ��һ�ι���
			if(cart == null) {
				cart = new ConcurrentHashMap<Product, Integer>(); //ʵ���̰߳�ȫ
			}
					//�������Ʒû���ڹ��ﳵ�����ص�countֵΪ��,/�����Ϊ�գ��᷵�ؾ�ֵ�����һὫproduct��1���빺�ﳵ,
			Integer count = cart.put(product, 1);
				//������ﳵ�Ѿ����˸���Ʒ��put���и���
			if(count != null) {
				cart.put(product, count+1);
			}
			//4.���µĹ��ﳵ���󷵸�cart.jsp�����������¶�ȡ
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/client/jsp/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			//��ӡ������Ϣ
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
