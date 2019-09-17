package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVo;

/**
 * 	购物车持久层接口
 * @author tarena
 *
 */
public interface CartMapper {
	
	/**
	 * 	购物车添加商品
	 * @param cart 添加的商品信息
	 * @return
	 */
	Integer insert(Cart cart);
	
	/**
	 *   修改购物车中商品的数量
	 * @param cid  购物车里的商品id
	 * @param num 购物车商品的数量
	 * @return
	 */
	Integer UpdateNum(@Param("cid")Integer cid,@Param("num")Integer num,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime
			);
	
	/**
	 *  	根据用户id和商品id查找购物车是否存在
	 * @param uid 当前用户的id
	 * @param gid 当前商品的id 
	 * @return 若存在则返回商品信息,不存在则返回null
	 */
	Cart  findByUidAndGid(@Param("uid")Integer uid,@Param("gid")Long gid);

	/**
	 * 	根据购物车商品的cid 查到对应的数据:uid,num
	 * @param cid
	 * @return 
	 */
	Cart findByCid(Integer cid);
	
	/**
	 * 获取用户在购物车中的所有商品
	 * @param uid 用户的uid
	 * @return 有则返回无则返回null
	 */
	List<CartVo> findByuid(Integer uid);
	
	/**
	 * 查找用户所给的cid所对应的商品信息
	 * @param cid  商品在购物车中的id
	 * @return 
	 */
	List<CartVo> findByCids(Integer[] cid);
	
	
}
