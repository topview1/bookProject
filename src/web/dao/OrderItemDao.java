package web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import web.entity.Order;
import web.entity.OrderItem;
import web.entity.Product;
import web.utils.DataSourceUtils;

public class OrderItemDao {

	public void addOrderItem(List<OrderItem> orderItems) throws SQLException {
		// 1.准备sql语句
		String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		//2.插入订单条目有多条
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		for(OrderItem item:orderItems) {
			String order_id = item.getOrder().getId();
			String product_id = item.getP().getId();
			int 	buynum  = item.getBuynum();
			runner.update(sql, order_id,product_id,buynum);
	       }
		}

	
	public List<Map<String, Object>> findOrderItemsInfo(String id) throws SQLException {
		//1.准备sql语句
		String sql = "select name,price,buynum from orderitem,products where product_id=id and order_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//将结果集中的每一行数据都封装到一个Map里，然后再存放到List
		return runner.query(sql, new MapListHandler(), id);
	}


	//查找购买数量,商品信息
	public List<OrderItem> findOrderItemByOrder(Order order) throws SQLException {
		String sql = "select orders.*,orderitem.*,products.* from  products,orderitem,orders "+
							"where orders.id = orderitem.order_id and orderitem.product_id = products.id "+
							" and orders.id = ? ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<OrderItem> items = runner.query(sql, new ResultSetHandler<List<OrderItem>>() {

			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				List<OrderItem> items = new ArrayList<OrderItem>();
				while(rs.next()) {
					Product p = new Product();
					OrderItem item = new OrderItem();
					p.setId(rs.getString("products.id"));
					p.setCategory(rs.getString("products.category"));
					p.setDescription(rs.getString("products.description"));
					p.setName(rs.getString("products.name"));
					p.setPnum(rs.getInt("products.pnum"));
					p.setPrice(rs.getDouble("products.price"));
					p.setImgurl(rs.getString("products.imgurl"));
					item.setBuynum(rs.getInt("orderitem.buynum"));
					item.setP(p);
					item.setOrder(order);
					
					items.add(item);
				}
				return items;
			}
			
			
		}, order.getId());
		return items;
	}

	
/**
 * 删除订单条目
 * @param id
 * @throws SQLException 
 */
	public void deleteOrderItem(String id) throws SQLException {
		String sql = "delete from orderitem  where order_id = ?";
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
		
	}
		
	}


