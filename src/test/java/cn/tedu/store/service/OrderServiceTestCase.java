package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestCase {
	@Autowired
	private OrderServiceImpl addressImpl;
	
	@Test
	public void createOrder() {
		Integer[] cids= {2,3};
		Order order=addressImpl.createOrder(14, "童话", 14, cids);
		System.err.println(order);
	}
	
}
