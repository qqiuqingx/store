package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
/**
 *	订单相关持久层接口 
 *
 */
public interface OrderMapper {
	/**
	 * 	向订单表中增加数据
	 * @param order 对应的订单数据
	 * @return
	 */
	Integer insertOrder(Order order);
	
	/**
	 * 	向订单商品表中增加数据
	 * @param orderItem 对应的订单商品数据
	 * @return
	 */
	Integer insertOrderItem(OrderItem orderItem);
}
