package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
	
	@Autowired
	private IGoodsService goods;
	@Test
	public  void getHotGoods() {
		List<Goods> goodss=goods.getHotGoods();
		System.err.println("开始");
		for (Goods goods : goodss) {
			System.err.println(goods);
		}
		System.err.println("结束");
	}
}
