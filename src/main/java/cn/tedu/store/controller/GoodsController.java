package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.util.ResponseResult;

/**
 *	处理商品数据请求的控制器类 
 */

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@GetMapping("/hot")
	public ResponseResult<List<Goods>> getHotGoods(){
		//获取数据
		List<Goods> hotGoods=goodsService.getHotGoods();
		//返回
		return new ResponseResult<List<Goods>>(SUCCESS, hotGoods);
	}
	
	@GetMapping("/{id}/details")
	public ResponseResult<Goods> getById(@PathVariable("id") Long id){
		Goods goods=goodsService.getById(id);
		System.err.println("执行了商品信息");
		return new ResponseResult<Goods>(SUCCESS, goods);
	}
	
}
