package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.vo.CartVo;
/**
 * 订单和订单商品数据业务层实现类
 * @author tarena
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	@Transactional
	public Order createOrder(Integer uid, String username, Integer aid, Integer[] cids)throws InsertException {
		Date now =new Date();
		if (cids[0].equals("")) {
			throw new InsertException("cid 为空");
		}
		// 调用cartService方法根据cids获取数据：List<CartVO> getByCids(Integer[] cids);
		List<CartVo> cartvos=cartService.getByCids(cids);
		System.err.println(cartvos.size());
		Long price=0L;
		// 遍历计算商品总价
		for (CartVo cartVo : cartvos) {
			price +=cartVo.getPrice()*cartVo.getNum();
		}
		// 创建当前时间对象now

		// 调用cartService方法根据cids获取数据：List<CartVO> getByCids(Integer[] cids);
		// 遍历计算商品总价
		Order order=new Order();
		// 创建订单数据对象：new Order()
		order.setUid(uid);
		// 封装订单数据的属性：uid
		Address address=addressService.getByAid(aid);
		if (address==null) {
			throw new AddressNotFoundException("创建订单失败,收货地址不存在");
		}
		if (!address.getUid().equals(uid)) {
			throw new AccessDeniedException("创建订单失败,数据归属异常");
		}
		// 调用addressService获取收货地址数据：getByAid(Integer aid)
		// 封装订单数据的属性：name, phone, address
		order.setAddress(address.getDistrict()+"-"+address.getAddress());
		order.setName(address.getName());
		order.setPhone(address.getPhone());
		// 封装订单数据的属性：status:0
		order.setStatus(0);
		// 封装订单数据的属性：price:null
		order.setPrice(price);
		// 封装订单数据的属性：orderTime:now
		order.setOrderTime(now);
		// 封装订单数据的属性：payTime:null
		order.setPayTime(null);
		// 封装4项日志属性
		order.setCreatedUser(username);
		order.setCreaterTime(now);
		order.setModifiedTime(now);
		order.setModifiedUser(username);
		// 插入订单数据：insertOrder(Order order)
		insertOrder(order);

		// 遍历以上查询结果
		OrderItem orderItem =new OrderItem();
		for (CartVo cartVo : cartvos) {
			// -- 封装订单商品数据的属性：gid,title,image,price,num
			orderItem.setGid(cartVo.getGid());
			System.err.println(cartVo.getGid());
			orderItem.setImage(cartVo.getImage());
			orderItem.setPrice(cartVo.getPrice());
			orderItem.setNum(cartVo.getNum());
			orderItem.setTitle(cartVo.getTitle());
			// -- 封装订单商品数据的属性：oid
			orderItem.setOid(order.getOid());
			// -- 封装4项日志属性
			orderItem.setCreatedUser(username);
			orderItem.setCreaterTime(now);
			orderItem.setModifiedTime(now);
			orderItem.setModifiedUser(username);
			insertOrderItem( orderItem);
		}
		// -- 创建订单商品数据对象：new OrderItem()
		
		
		// -- 插入订单商品数据：insertOrderItem(OrderItem orderItem)
		return order;
	}
	
	
	
	
	/**
	 * 	向订单表中增加数据
	 * @param order 对应的订单数据
	 * @return
	 */
	private void insertOrder(Order order) {
		Integer rows=orderMapper.insertOrder(order);
		if (rows!=1) {
			throw new InsertException("创建订单失败!插入数据异常");
		}
	}
	
	/**
	 * 	向订单商品表中增加数据
	 * @param orderItem 对应的订单商品数据
	 * @return
	 */
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows =orderMapper.insertOrderItem(orderItem);
		if (rows!=1) {
			throw new InsertException("创建订单失败!插入数据异常");
		}
	}
}
