package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoobsMapperTestCase {
	@Autowired
	private GoodsMapper goob;
	@Test
	public  void findHotGoobs() {
		List<Goods> goobss=goob.findHotGoods();
		System.err.println("begin:");
		for (Goods goobs : goobss) {
			System.err.println(goobs);
		}
		System.err.println("END");
	}
	@Test
	public void findById() {
		Goods d=goob.findById(10000001L);
		System.err.println(d);
	}
}
