package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/create")
	public ResponseResult<Order> createOrder(Integer aid,Integer[]cids, HttpSession session){
		if (cids.length==0) {
			throw new CartNotFoundException("请选择正确的物品");
		}
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		Order data=orderService.createOrder(uid, username, aid, cids);
		return new ResponseResult<Order>(SUCCESS, data);
	}
}
