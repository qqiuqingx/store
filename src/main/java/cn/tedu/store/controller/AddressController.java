package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{
     @Autowired
	private IAddressService addressService;
     @RequestMapping("/addnew")
    public ResponseResult<Void> addnew(Address address,HttpSession session){
    	System.err.println("进入控制器");
    	 address.setUid(getUidFromSession(session));
    	String username=session.getAttribute("username").toString();
    	addressService.addnew(address, username);
    	System.err.println("执行到了");
    	return new ResponseResult<>(SUCCESS);
    }
     
     @GetMapping("/")
     public ResponseResult<List<Address>> getByUidAll(HttpSession session){
    	 List<Address> adds=addressService.getByUidAll(getUidFromSession(session));
    	 return  new ResponseResult<List<Address>>(SUCCESS, adds);
     }
     
     @RequestMapping("/{aid}/set_default")
     public ResponseResult<Void> setDefault(
    		 @PathVariable("aid")Integer aid,
    		 HttpSession session
    		 ){
    	 Integer uid=getUidFromSession(session);
    	 addressService.setDefault(uid, aid);
    	 return new ResponseResult<>(SUCCESS);
     }
     
     @RequestMapping("/{aid}/delete")
     public ResponseResult<Void>delByAid(
    		 @PathVariable("aid")Integer aid,
    		 HttpSession session
    		 ){
    	 addressService.delByAid(getUidFromSession(session), aid);
    	 return new ResponseResult<>(SUCCESS);
     }

}
