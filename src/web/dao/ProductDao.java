package web.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import web.entity.Order;
import web.entity.OrderItem;
import web.entity.Product;
import web.utils.DataSourceUtils;

public class ProductDao {

	// ������Ʒ�����ȡ��Ʒ����������
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(!"ȫ����Ʒ".equals(category)) {
			sql += " where category=?";
			Long count = (Long)runner.query(sql, new ScalarHandler(),category);
			return count.intValue();
		}else {
			Long count = (Long)runner.query(sql, new ScalarHandler());
			return count.intValue();
		}
	}
/**
 * ���ݲ�ѯ��������ȡ����������
 * @param search_key
 * @return
 * @throws SQLException 
 */
	public int findAllCountByName(String search_key) throws SQLException {
		String sql = "select count(*) from products where name like'%"+search_key+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	
	//��ȡ��ǰҳ�����ݼ���
	public List<Product> findByPage(int currentPage, int currentCount, String category) throws SQLException {
		String sql = null;
		Object[] obj= null;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(!"ȫ����Ʒ".equals(category)) {
			sql = "select * from products  where category=? limit ?,?";
			obj = new Object[]{category,(currentPage-1)*currentCount,currentCount};
		}else {
			sql = "select * from products   limit ?,?";
			obj = new Object[]{(currentPage-1)*currentCount,currentCount};
		}
		return runner.query(sql,  new BeanListHandler<Product>(Product.class), obj);
	}

	//ͨ��id������
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from products  where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	/**
	 * �޸Ŀ������
	 * @param order
	 * @throws SQLException 
	 */
	public void changeProductNum(Order order) throws SQLException {
		// 1.׼��sql���
		String sql = "update products  set pnum=pnum-? where id = ?";
		//2.����������Ŀ
		List<OrderItem> items = order.getOrderItems();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		for(OrderItem item:items) {
			int buynum = item.getBuynum();
			String product_id = item.getP().getId();
			runner.update(sql, buynum,product_id);	
		}	
	}
	
	/**
	 * ��ȡģ����ѯ��ǰҳ�����ݼ���
	 * @param currentPage
	 * @param currentCount
	 * @param search_key
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findBookNameByPage(int currentPage, int currentCount, String search_key) throws SQLException {
		String sql =  "select * from products where name like'%"+search_key+"%' limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return 	runner.query(sql, new BeanListHandler<Product>(Product.class),(currentPage-1)*currentCount,currentCount);
	}
	
	/**
	 * ��ѯ������Ʒ
	 * @return 
	 * @throws SQLException 
	 * 
	 */
	public List<Product> findAllProduct() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
		
	}
	
	/**
	 * ���ж�������ѯ��Ʒ
	 * @param id
	 * @param name
	 * @param category
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findProductByManyCondition(String id, String name, String category, String minprice,
			String maxprice) throws SQLException {
		//1.׼��sql,����д��Ŀ����Ϊ�˼������ж�
  		String sql = "select * from products where 1 = 1";
		//2.׼��������ݵ�ArrayList
		ArrayList  parameters = new ArrayList();
		if(id != null && !id.trim().equals("")) {
			sql += " and id = ?";
			parameters.add(id);
		}
		if(name != null &&!name.trim().equals("")) {
			sql += " and name = ?";
			parameters.add(name);
		}
		
		if(category != null && !category.trim().equals("")) {
			sql += " and category = ?";
			parameters.add(category);
		}
		if( (minprice != null &&  !minprice.trim().equals("") )&& (maxprice != null &&!maxprice.trim().equals("") )) {
			sql += " and price  between ? and ?";
			parameters.add(minprice);
			parameters.add(maxprice);
		}
		
		Object[] obj = parameters.toArray();
		//3.��������
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class), obj);
	}
	
	/**
	 * �����Ʒ
	 * @param p
	 * @throws SQLException 
	 */
	public void addProduct(Product p) throws SQLException {
		//1.sql���
		String sql = "insert into products  values(?,?,?,?,?,?,?)";
		//id,name,price,category,pnum,imgurl,description
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getId(),p.getName(),p.getPrice(),p.getCategory(),p.getPnum(),p.getImgurl(),p.getDescription());	
	}
	
	/**
	 * �༭��Ʒ
	 * @param p
	 * @throws SQLException 
	 */
	public void editProduct(Product p) throws SQLException {
		String sql = "update  products set name =?,price=?,category=?,pnum=?,description=?";
		ArrayList  parameters = new ArrayList();
		parameters.add(p.getName());
		parameters.add(p.getPrice());
		parameters.add(p.getCategory());
		parameters.add(p.getPnum());
		parameters.add(p.getDescription());
		if(p.getImgurl() != null && !p.getImgurl().trim().equals("")) {
			sql += ",imgurl=?";
			parameters.add(p.getImgurl());
		}
		sql += " where id =?";
		parameters.add(p.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, parameters.toArray());
	}
	
	/**
	 * ɾ����Ʒ
	 * @param id
	 * @throws SQLException 
	 */
	public void deleteProduct(String id) throws SQLException {
		String sql = "delete from products where id =?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,id);
		
	}
	
	/**
	 * ��̨��ѯ�Ѿ�֧������Ʒ��Ϣ
	 * @param year
	 * @param month
	 * @return 
	 * @throws SQLException 
	 */
	public List<Object[]> download(String year, String month) throws SQLException {
		String sql = "select products.name,sum(orderitem.buynum) as totalsalenum "+
								"from orders,orderitem,products "+
								"where orderitem.order_id = orders.id "+
								" and orderitem.product_id = products.id "+
								"and  orders.paystate=1 "+
								"and year(ordertime) = ? and month(ordertime) =? "+
								"group by products.name order by totalsalenum desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object []> list = runner.query(sql, new ArrayListHandler(),year,month);
		return list;
	}

}
