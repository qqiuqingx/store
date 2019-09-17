package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdataException;
import cn.tedu.store.vo.CartVo;
/**
 * 处理购物车业务成接口
 */
public interface ICartService {
	
	/**
	 * 	添加购物车
	 * @param username 用户的用户名
	 * @param cart  需要的参数如uid  aid
	 * @throws InsertException  插入数据异常
	 * @throws UpdataException  修改数据异常
	 */
	void addToCart(String username,Cart cart)throws InsertException,UpdataException;
	
	/**
	 * 获取用户在购物车中的所有商品
	 * @param uid 用户的uid
	 * @return 有则返回无则返回null
	 */
	List<CartVo> getByuid(Integer uid);
	
	/**
	 *  将用户选中的商品数量+1
	 * @param username 用户名
	 * @param uid 用户的id
	 * @param cid 对应商品的cid
	 */
	void addNum(String username,Integer uid,Integer cid)throws CartNotFoundException,UpdataException,AccessDeniedException;
	
	/**
	 * 查找用户所给的cid所对应的商品信息
	 * @param cid  商品在购物车中的id
	 * @return 
	 */
	List<CartVo> getByCids(Integer[] cid);
	
}
