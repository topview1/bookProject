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
	 * //��Ӷ���
	 * @param order
	 * @return
	 */

	public boolean addOrder(Order order)   {
		try {
			//��������
			DataSourceUtils.startTransation();
			// ��Ӷ����Ͷ�����Ŀ
			odao.addOrder(order);
			//�и�bug���ڹ��ﳵ���2��ͬ���飬ֻ�����һ��
			oidao.addOrderItem(order.getOrderItems());
			//�޸���Ʒ�Ŀ��
			pdao.changeProductNum(order);
			//��������½�������
			DataSourceUtils.releaseAndCloseConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�쳣��ع�����
				try {
					DataSourceUtils.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		return false;
	}

	//�ҵ��û������ж���
	public List<Order> findOrderByUser(User user) {
		try {
			List<Order> orders =odao.findOrderByUser(user);
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("��ѯ���ж���ʧ��");
		}
	}

	//�ҵ��û��Ķ�������id��������Ʒ��Ϣ,ǰ�˲�ѯ
	public Map<Product, Integer> findOrderInfoById(String id) {
		//1.����OrderItem��dao����ж���ѯ,
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
			throw new RuntimeException("ͨ��id���Ҷ�����Ϣʧ��");
		}
	}

	//֧������
	public void payOrder(String order_id) {
		int  payState = 1;
		try {
			odao.updatePayState(payState,order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("֧��ʧ��");
		}	
	}

	/**
	 * ��ѯ���ж���
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
	 * ��������ѯ
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
	 * ͨ��id�����û�-������Ϣ
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
	 * ɾ�������Լ�������Ŀ
	 * @param id
	 */
	public void deleteOrder(String id) {
		//1.��������
		try {
			DataSourceUtils.startTransation();
			odao.deleteOrder(id);
			oidao.deleteOrderItem(id);
			DataSourceUtils.releaseAndCloseConnection();
		} catch (SQLException e) {
			//�쳣��ع�����
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}
