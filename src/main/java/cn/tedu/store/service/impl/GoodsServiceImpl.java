package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;
/**
 *	 商品信息业务成实现类
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    
	@Autowired
	private GoodsMapper goodsMapper;
	
	public List<Goods> getHotGoods() {
		return findHotGoods();
	}
	
	@Override
	public Goods getById(Long id) {
		return findById(id);
	};
	
	/**
	 * 	获取热销商品
	 * @return
	 */
	private List<Goods> findHotGoods(){
		return goodsMapper.findHotGoods();
	};
	
	/**
	 * 根据商品id查询商品信息
	 * @param id 商品的id
	 * @return 匹配的商品信息
	 */
	private Goods findById(Long id) {
		return goodsMapper.findById(id);
	}

	

}
