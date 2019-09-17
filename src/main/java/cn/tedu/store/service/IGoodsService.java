package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

/**
 * 	处理商品业务层接口
 */
public interface IGoodsService {
	/**
	 * 	获取热销商品
	 * @return 热销商品列表
	 */
	List<Goods> getHotGoods();
	
	
	/**
	 * 	根据商品id查询商品信息
	 * @param id 商品的id
	 * @return 匹配的商品信息
	 */
	Goods getById(Long id);
	
}
