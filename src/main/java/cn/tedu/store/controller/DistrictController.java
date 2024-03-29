package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.District;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.util.ResponseResult;

/**
 * 处理省/市/区控制器
 * @author tarena
 *
 */
@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController {
   @Autowired
   private IDistrictService districtService;
   @GetMapping("/")
   public ResponseResult<List<District>> getByParent(@RequestParam("parent")String parent){
	List<District> lists= districtService.getByParent(parent);
	
	   return new ResponseResult<List<District>>(SUCCESS, lists);
   }
	
}
