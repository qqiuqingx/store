package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.ResponseResult;
import cn.tedu.store.vo.CartVo;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {
	
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("/add")
	public ResponseResult<Void> addToCart(Cart cart ,HttpSession session){
		cart.setUid(getUidFromSession(session));	
		String username=session.getAttribute("username").toString();
		System.err.println(username);
		System.err.println(cart);
		cartService.addToCart(username, cart);
		System.err.println("控制器层执行完毕");
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("/{id}/add_num")
	public ResponseResult<Void> addNum(
			@PathVariable("id")Integer cid,HttpSession session
			){
		String username=session.getAttribute("username").toString();
		Integer uid=getUidFromSession(session);
		cartService.addNum(username, uid, cid);
		
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/")
	public ResponseResult<List<CartVo>> getByuid(HttpSession session){
		List<CartVo> cartvos=cartService.getByuid(getUidFromSession(session));
		return new ResponseResult<List<CartVo>>(SUCCESS, cartvos);
	}
	
	
	
	@GetMapping("/checked_list")
	public ResponseResult<List<CartVo>> getByCids(Integer [] cids){
		List<CartVo> cartvos=cartService.getByCids(cids);
		return new ResponseResult<List<CartVo>>(SUCCESS, cartvos);
	}
	
	
}
