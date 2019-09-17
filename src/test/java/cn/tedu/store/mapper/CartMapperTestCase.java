package cn.tedu.store.mapper;

import java.util.Date;
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
public class CartMapperTestCase {
	@Autowired
	private CartMapper cart;
	
	@Test
	public void  insert() {
		Cart cart=new Cart();
		cart.setGid(12L);
		cart.setUid(14);
		cart.setNum(15);
		cart.setCreatedUser("超管");
		cart.setModifiedUser("超管");
		Date now =new Date();
		cart.setCreaterTime(now);
		cart.setModifiedTime(now);
		Integer rows=this.cart.insert(cart);
		System.err.println(rows);
	}
	
	@Test
	public void  UpdateNum() {
		Integer rows=this.cart.UpdateNum(1, 17,"超人",new Date());
		System.err.println("rows:"+rows);
	}
	
	@Test
	public void  findByUidAndGid() {
		Cart cart=this.cart.findByUidAndGid(14, 12L);
		System.err.println(cart);
	}
	@Test
	public void  findByuid() {
		List<CartVo> lists=this.cart.findByuid(14);
		System.err.println("begin");
		for (CartVo cartVo : lists) {
			System.err.println(cartVo);
		}
		System.err.println("end");
	}
	@Test
	public void  findByCid() {
		Cart ss=cart.findByCid(4);
		System.err.println(ss);
	}
	@Test
	public void  findByCidS() {
		Integer [] arr= {2,3};
		List<CartVo>A=cart.findByCids(arr);
		System.err.println("begin");
		for (CartVo cartVo : A) {
			System.err.println(cartVo);
		}
		System.err.println("end");
	}
}
