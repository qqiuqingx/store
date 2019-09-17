package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.InsertException;

/**
 * 	订单和订单商品数据业务层接口
 * @author tarena
 *
 */
public interface IOrderService {
	
	/**
	 * 	 生成订单
	 * @param uid 用户id
	 * @param username  用户的昵称
	 * @param aid 用户的收货地址
	 * @param cids 选择即将购买的购物车的商品的id
	 * @return 成功创建的订单对象
	 */
	Order createOrder(Integer uid,String username,Integer aid,Integer[]cids)throws InsertException;
}
