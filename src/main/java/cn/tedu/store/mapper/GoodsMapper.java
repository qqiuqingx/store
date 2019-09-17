package cn.tedu.store.mapper;

import java.util.List;


import cn.tedu.store.entity.Goods;

/**
 * 	处理商品类持久层接口
 * @author tarena
 *
 */
public interface GoodsMapper {
	
	/**
	 * 	获取热销商品
	 * @return
	 */
	List<Goods> findHotGoods();
	
	/**
	 * 根据商品id查询商品信息
	 * @param id 商品的id
	 * @return 匹配的商品信息
	 */
	Goods findById(Long id);
}
