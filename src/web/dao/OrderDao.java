package web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import web.entity.Order;
import web.entity.User;
import web.utils.DataSourceUtils;

public class OrderDao {

	/**
	 * 生成订单
	 * @param order
	 * @throws SQLException
	 */
	public void addOrder(Order order) throws SQLException {
		// 1.准备插入语句
		String sql = "insert into orders(id,money,receiverAddress,receiverName,receiverPhone,use_id)"+
		"values(?,?,?,?,?,?)";
		Object[] parames = {order.getId(),order.getMoney(),order.getReceiverAddress(),	order.getReceiverName(),order.getReceiverPhone(),order.getUser().getId()};
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, parames);
		if(row == 0) {
			throw new RuntimeException();
		}
		
		
	}

	//查询该用户的所有订单
	public List<Order> findOrderByUser(User user) throws SQLException {
		//1.准备sql语句
		String sql = "select * from orders";
		//2.建立连接
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class));
	}

	//通过id查找订单详情
	public Order findOrderByID(String id) throws SQLException {
		String sql = "select orders.*,user.* from orders,user where orders.use_id = user.id  and orders.id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Order _order = runner.query(sql, new ResultSetHandler<Order>() {

			public Order handle(ResultSet rs) throws SQLException {
			//遍历结果集rs
				Order order =  new Order();
				while(rs.next()) {
					//创建订单对象
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.money"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					//创建user对象
					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setUsername(rs.getString("user.username"));
					user.setEmail(rs.getString("user.email"));
					user.setTelephone(rs.getString("user.telephone"));
					user.setGender(rs.getString("user.gender"));
					user.setRole(rs.getString("user.role"));
					order.setUser(user);					
				}
				return order;
			}
			
		},id);
		return _order;
	}

	//更新订单支付状态
	public void updatePayState(int payState, String order_id) throws SQLException {
		String sql = "update orders set paystate= ? where id = ?";
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, payState,order_id);
	}

	
	//查询所有订单
	public List<Order> findAllOrder() throws SQLException {
	//1.准备sql语句
		String sql = "select orders.*,user.* from orders,user where orders.use_id = user.id";
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		//2. 因为有两个javaBean ，采用匿名内部类方式封装数据
		List<Order> orders = runner.query(sql, new ResultSetHandler<List<Order>>() {

			public List<Order> handle(ResultSet rs) throws SQLException {
			//遍历结果集rs
				List<Order> orders = new ArrayList<Order>();
				while(rs.next()) {
					//创建订单对象
					Order order =  new Order();
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.money"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					//创建user对象
					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setUsername(rs.getString("user.username"));
					user.setEmail(rs.getString("user.email"));
					user.setTelephone(rs.getString("user.telephone"));
					user.setGender(rs.getString("user.gender"));
					user.setRole(rs.getString("user.role"));
					order.setUser(user);
					
					orders.add(order);
					
				}
				return orders;
			}
			
		});
		return orders;
	}
	
	
/**
 * 多条件查询
 * @param id
 * @param receiverName
 * @return
 * @throws SQLException 
 */
	public List<Order> findOrderByManyCondition(String id, String receiverName) throws SQLException {
		//1.准备sql语句
		String  sql = "select orders.*,user.* from orders,user where orders.use_id = user.id ";
		ArrayList parameters = new ArrayList ();
		if(id != null && !id.trim().equals("")) {
			sql += "and orders.id = ? ";
			parameters.add(id);
		}
		if(receiverName != null && !receiverName.trim().equals("")) {
			sql += " and orders.receiverName = ?";
			parameters.add(receiverName);
		}
		//2.建立连接
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		//3.获取数据
		List<Order> orders = runner.query(sql, new ResultSetHandler<List<Order>>() {

			public List<Order> handle(ResultSet rs) throws SQLException {
			//遍历结果集rs
				List<Order> orders = new ArrayList<Order>();
				while(rs.next()) {
					//创建订单对象
					Order order =  new Order();
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.money"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					//创建user对象
					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setUsername(rs.getString("user.username"));
					user.setEmail(rs.getString("user.email"));
					user.setTelephone(rs.getString("user.telephone"));
					user.setGender(rs.getString("user.gender"));
					user.setRole(rs.getString("user.role"));
					order.setUser(user);
					
					orders.add(order);
					
				}
				return orders;
			}
			
		},parameters.toArray());
		return orders;
	}

	/**
	 * 删除订单
	 * @param id
	 * @throws SQLException 
	 */
	public void deleteOrder(String id) throws SQLException {
		String sql = "delete from orders  where id = ?";
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
		
	}

}
