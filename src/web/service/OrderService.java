package web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.dao.OrderDao;
import web.dao.OrderItemDao;
import web.dao.ProductDao;
import web.entity.Order;
import web.entity.OrderItem;
import web.entity.Product;
import web.entity.User;
import web.utils.DataSourceUtils;

public class OrderService {
	private OrderDao odao = new OrderDao();  
	private OrderItemDao oidao = new OrderItemDao();
	private ProductDao  pdao = new ProductDao();
	
	/**
	 * //添加订单
	 * @param order
	 * @return
	 */

	public boolean addOrder(Order order)   {
		try {
			//开启事务
			DataSourceUtils.startTransation();
			// 添加订单和订单条目
			odao.addOrder(order);
			//有个bug，在购物车添加2相同的书，只会插入一本
			oidao.addOrderItem(order.getOrderItems());
			//修改商品的库存
			pdao.changeProductNum(order);
			//正常情况下结束事务
			DataSourceUtils.releaseAndCloseConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常则回滚事务
				try {
					DataSourceUtils.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		return false;
	}

	//找到用户的所有订单
	public List<Order> findOrderByUser(User user) {
		try {
			List<Order> orders =odao.findOrderByUser(user);
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询所有订单失败");
		}
	}

	//找到用户的订单号是id的所有商品信息,前端查询
	public Map<Product, Integer> findOrderInfoById(String id) {
		//1.调用OrderItem的dao层进行多表查询,
		try {
			Map<Product,Integer> product_items = new HashMap<Product,Integer>();
			List<Map<String, Object>> items =oidao.findOrderItemsInfo(id);
			for(Map map : items) {
				String name = (String) map.get("name");
				double price = (double) map.get("price");
				Integer buynum = (int) map.get("buynum");
				Product p = new Product();
				p.setName(name);
				p.setPrice(price);
				product_items.put(p,buynum);
			}
			return product_items;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("通过id查找订单信息失败");
		}
	}

	//支付订单
	public void payOrder(String order_id) {
		int  payState = 1;
		try {
			odao.updatePayState(payState,order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("支付失败");
		}	
	}

	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Order> findAllOrder() {
		try {
			List<Order> orders = odao.findAllOrder();
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 多条件查询
	 * @param id
	 * @param receiverName
	 */
	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		// TODO Auto-generated method stub
		try {
			List<Order> orders  = odao.findOrderByManyCondition(id, receiverName);
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过id查找用户-订单信息
	 */
	public Order findOrderById(String id) {
		try {
			Order order = odao.findOrderByID(id);
			List<OrderItem> items = oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除订单以及订单条目
	 * @param id
	 */
	public void deleteOrder(String id) {
		//1.开启事务
		try {
			DataSourceUtils.startTransation();
			odao.deleteOrder(id);
			oidao.deleteOrderItem(id);
			DataSourceUtils.releaseAndCloseConnection();
		} catch (SQLException e) {
			//异常则回滚事务
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}
