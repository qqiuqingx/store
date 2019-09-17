package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdataException;
import cn.tedu.store.vo.CartVo;

/**
 * 处理购物车业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartMapper cartMapper;
	@Override
	public void addToCart(String username, Cart cart) throws InsertException, UpdataException {
		//根据uid和gid去查询数据
		Cart result=findByUidAndGid(cart.getUid(), cart.getGid());
		System.err.println("业务层:"+result);
		Date now=new Date();
		cart.setCreatedUser(username);
		cart.setCreaterTime(now);
		cart.setModifiedUser(username);
		cart.setModifiedTime(now);
		//判断查询结果是否为null
		if (result!=null) {
			//不为null 说明购物车有此商品 直接添加用户选择的数量
			Integer num=cart.getNum()+result.getNum();
			UpdateNum(result.getCid(), num,username,now);
			return;
		}
		//为null 购物车没有此商品 直接添加
		insert(cart);
	}
	


	
	@Override
	public List<CartVo> getByuid(Integer uid) {
		List<CartVo> cartvos=findByuid(uid);
		return cartvos;
	};

	@Override
	public void addNum(String username, Integer uid, Integer cid)
			throws CartNotFoundException, UpdataException, AccessDeniedException {
		Cart result=findByCid(cid);
		if (result==null) {
			throw new CartNotFoundException("数据不存在");
		}
		if (!result.getUid().equals(uid)) {
			throw new AccessDeniedException("数据归属错误");
		}
		Integer num=result.getNum()+1;
		// TODO 判断库存
		UpdateNum(cid, num, username, new Date());
		
	}
	

	@Override
	public List<CartVo> getByCids(Integer[] cid) {
		return findByCids(cid);
	}
	

	/**
	 * 	购物车添加商品
	 * @param cart 添加的商品信息
	 */
	private void insert(Cart cart) {
		Integer rows=cartMapper.insert(cart);
		if (rows!=1) {
			throw new InsertException("添加购物车出现不可知错误");
		}
	};
	
	/**
	 *   修改购物车中商品的数量
	 * @param cid  购物车里的商品id
	 * @param num 购物车商品的数量
	 * @return
	 */
	private  void UpdateNum(Integer cid,Integer num,
			String modifiedUser,
			Date modifiedTime
			) {
		Integer rows=cartMapper.UpdateNum(cid, num, modifiedUser, modifiedTime);
		if (rows!=1) {
			throw new UpdataException("添加购物车出现不可知错误");
		}
	};
	
	/**
	 *  	根据用户id和商品id查找购物车是否存在
	 * @param uid 当前用户的id
	 * @param gid 当前商品的id 
	 * @return 若存在则返回商品信息,不存在则返回null
	 */
	private Cart  findByUidAndGid(Integer uid,Long gid) {
		return cartMapper.findByUidAndGid(uid, gid);
	};
	
	/**
	 * 	根据购物车商品的cid 查到对应的数据:uid,num
	 * @param cid
	 * @return 
	 */
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
	};
	
	/**
	 * 获取用户在购物车中的所有商品
	 * @param uid 用户的uid
	 * @return 有则返回无则返回null
	 */
	private List<CartVo> findByuid(Integer uid){
		return cartMapper.findByuid(uid);
	}
	
	/**
	 * 查找用户所给的cid所对应的商品信息
	 * @param cid  商品在购物车中的id
	 * @return 
	 */
	private List<CartVo> findByCids(Integer[] cid){
		return cartMapper.findByCids(cid);
	}





	



	
}
