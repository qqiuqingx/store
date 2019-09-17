package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {

	@Autowired
	private ICartService cartService;
	@Test
	public  void addToCart() {
		String username="超管";
		Cart cart=new Cart();	
		cart.setGid(12L);
		cart.setUid(14);
		cart.setNum(2);
		cartService.addToCart(username, cart);
		System.err.println("OK");
	}
	
	@Test
	public  void addNum() {
		cartService.addNum("大鲨鱼", 14, 2);
		System.err.println("OK");
	}
	
	@Test
	public  void getByCids() {
		Integer[] cid= {2,3};
		List<CartVo>A=cartService.getByCids(cid);
		System.err.println("begin");
		for (CartVo cartVo : A) {
			System.err.println(cartVo);
		}
		System.err.println("end");
	}
	
	
}
