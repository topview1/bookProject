package web.service;

import java.sql.SQLException;
import java.util.List;

import web.dao.ProductDao;
import web.entity.PageBean;
import web.entity.Product;
import web.exception.AddProductException;
import web.exception.FindProductByIdException;

public class ProductService {
	private ProductDao  dao = new ProductDao();
	
	// ��ȡ��ǰҳ����
	public PageBean findProductByPage(int currentPage, int currentCount, String category) {
		// TODO Auto-generated method stub
		PageBean bean = new PageBean();
		bean.setCurrentPage(currentPage);
		bean.setCurrentCount(currentCount);
		bean.setCategory(category);
		List<Product> ps;
		
		int totalCount;
		try {
			totalCount = dao.findAllCount(category);
			bean.setTotalCount(totalCount);
			//��ȡ�ܵ�ҳ��
			int totalPage = (int)Math.ceil((totalCount*1.0)/currentCount);
			bean.setTotalPage(totalPage);
			//��ȡ��ҳ������
			ps = dao.findByPage(currentPage, currentCount, category);
			bean.setPs(ps);
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ҳ��ʾ��Ʒʧ�ܣ�");
		}
		return bean;
	}

	//ͨ��id����product
	public Product findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindProductByIdException("����ID������Ʒʧ��");
		}
	
	}

	/**
	 * �����������Ҳ�Ʒ ����ҳ��ʾ
	 * @param currentPage
	 * @param currentCount
	 * @param search_key
	 * @return
	 */
	public PageBean findProductByName(int currentPage, int currentCount, String search_key) {
		PageBean bean = new PageBean();
		bean.setCurrentPage(currentPage);
		bean.setCurrentCount(currentCount);
		bean.setSearchfield(search_key);
		List<Product> ps;
		
		int totalCount;
		try {
			totalCount = dao.findAllCountByName(search_key);
			bean.setTotalCount(totalCount);
			//��ȡ�ܵ�ҳ��
			int totalPage = (int)Math.ceil((totalCount*1.0)/currentCount);
			bean.setTotalPage(totalPage);
			//��ȡ��ҳ������
			ps = dao.findBookNameByPage(currentPage, currentCount, search_key);
			bean.setPs(ps);
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��������ݹؼ�������ʧ�ܣ�");
		}
		return bean;
	}
/**
 * ����ȫ����Ʒ��Ϣ
 * @return
 */
	public List<Product> findAllProduct() {
		try {
			List<Product> products= dao.findAllProduct();
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("����ȫ����Ʒ��Ϣʧ��");
		}
	}

public List<Product> findProductByManyCondition(String id, String name, String category, String minprice,
		String maxprice) {
	List<Product> products;
	try {
		products = dao.findProductByManyCondition(id,name,category,minprice,maxprice);
		return products;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException("��������ѯʧ��");
	}
}

/**
 * �����Ʒ����
 * @param p
 * @throws AddProductException 
 */
public void addProduct(Product p) throws AddProductException {
	try {
		dao.addProduct(p);
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AddProductException("���ʧ��");
	}
	
}

/**
 * �༭��Ʒ����
 * @param p
 */
public void editProduct(Product p) {

	try {
		dao.editProduct(p);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

/**
 * ɾ����Ʒ����
 * @param id
 */
public void deleteProduct(String id) {
	try {
		dao.deleteProduct(id);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
/**
 * ��̨��ѯ���۰�����
 * @param year
 * @param month
 * @return
 */
public List<Object[]> download(String year, String month) {
	try {
		List<Object []> ps=dao.download(year,month);
		return ps;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}


}
