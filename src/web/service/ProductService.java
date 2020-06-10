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
	
	// 获取当前页数据
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
			//获取总的页数
			int totalPage = (int)Math.ceil((totalCount*1.0)/currentCount);
			bean.setTotalPage(totalPage);
			//获取当页的数据
			ps = dao.findByPage(currentPage, currentCount, category);
			bean.setPs(ps);
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页显示商品失败！");
		}
		return bean;
	}

	//通过id查找product
	public Product findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindProductByIdException("根据ID查找商品失败");
		}
	
	}

	/**
	 * 根据书名查找产品 并分页显示
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
			//获取总的页数
			int totalPage = (int)Math.ceil((totalCount*1.0)/currentCount);
			bean.setTotalPage(totalPage);
			//获取当页的数据
			ps = dao.findBookNameByPage(currentPage, currentCount, search_key);
			bean.setPs(ps);
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("搜索框根据关键词搜索失败！");
		}
		return bean;
	}
/**
 * 查找全部商品信息
 * @return
 */
	public List<Product> findAllProduct() {
		try {
			List<Product> products= dao.findAllProduct();
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查找全部商品信息失败");
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
		throw new RuntimeException("多条件查询失败");
	}
}

/**
 * 添加商品功能
 * @param p
 * @throws AddProductException 
 */
public void addProduct(Product p) throws AddProductException {
	try {
		dao.addProduct(p);
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AddProductException("添加失败");
	}
	
}

/**
 * 编辑商品功能
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
 * 删除商品功能
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
 * 后台查询销售榜单数据
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
