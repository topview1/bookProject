package web.servlet.client;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.entity.Product;
import web.exception.FindProductByIdException;
import web.service.ProductService;

/**
 * Servlet implementation class ChangeProductNumServlet
 */
public class ChangeProductNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeProductNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1.�õ���Ʒ��id
		String id = request.getParameter("id");
		//2.�õ�Ҫ�޸ĵ�����
		int count =Integer.parseInt(request.getParameter("count"));
		//3.��session�л�ȡ���ﳵ
		HttpSession session = request.getSession();
		Map<Product,Integer> cart =(Map<Product, Integer>) session.getAttribute("cart");
		
		ProductService service = new ProductService();
		//�ҵ��޸ĵ��Ǹ���Ʒ
		for(Product p:cart.keySet()) {
			if(p.getId().equals(id)) {
				if(count != 0) {
					cart.put(p, count);
				}else {
					cart.remove(p);
				}	
			}
		}
		
		//4.��ת��cart.jspҳ��
		response.sendRedirect(request.getContextPath()+"/client/jsp/cart.jsp");
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
