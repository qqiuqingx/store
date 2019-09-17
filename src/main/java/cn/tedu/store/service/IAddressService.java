package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdataException;

/**
 *  处理用户收货地址的业务层接口
 * @author tarena
 *
 */
public interface IAddressService {
    /**
     * 	增加用户收货地址
     * @param address 收货地址数据
     */
	void addnew(Address address,String username)throws InsertException;
    
	/**
	 * 获得用户的所有收货地址
	 * @param uid 用户的uid
	 * @return 返回用户的所有收货地址
	 */
	List<Address> getByUidAll(Integer uid);
	
	/**
	 *  	根据aid查到用户的收货地址
	 * @param aid
	 * @return 收货地址
	 */
	Address getByAid(Integer aid);
		
	
	/**
	 * 用户修改默认收货地址
	 * @param uid 用户id
	 * @param aid 要设为默认收货地址的aid
	 * @throws AddressNotFoundException 数据不存在异常
	 * @throws AccessDeniedException  拒绝访问,数据更新归属有误
	 * @throws UpdataException  数据更新异常
	 */
	void setDefault(Integer uid, Integer aid) throws
	AddressNotFoundException, AccessDeniedException, UpdataException,DeleteException;
	
	/**
	 * 删除用户指定收货地址
	 * @param uid 用户uid
	 * @param aid 要删除的收货地址的aid
	 * @throws AddressNotFoundException 数据不存在
	 * @throws AccessDeniedException 拒绝访问,数据归属异常
	 * @throws UpdataException  数据更新异常
	 */
	void delByAid(Integer uid,Integer aid) throws AddressNotFoundException,AccessDeniedException,UpdataException;
	
}
