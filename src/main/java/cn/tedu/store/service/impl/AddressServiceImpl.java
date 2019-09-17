package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdataException;
/**
 *	 处理收货地址数据的业务层实现类
 * @author tarena
 *
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
	@Autowired
	private DistrictService district;
    @Override
	public void addnew(Address address, String username) throws InsertException{
		Integer rows=countByUid(address.getUid());
		//判断数量是否为0
		//是 将当前增加的第一条数据设为默认
		//否 将当前增加的数据设为0（非默认）
		address.setIsDefault(rows==0 ? 1:0);
		//  解决district
		String district=getDistrict(address.getProvince(), address.getCity(), address.getArea());
		address.setDistrict(district);
		Date now=new Date();
		address.setCreatedUser(username);
		address.setCreaterTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		insert(address);
	}
	
    @Override
    @Transactional
	public void setDefault(Integer uid, Integer aid)
			throws AddressNotFoundException, AccessDeniedException, UpdataException {
   	// 根据aid查询数据
		Address address=findByAid(aid);
		// 判断数据是否为null
		// 是：AddressNotFoundException
		if (address==null) {
			throw new AddressNotFoundException("数据不存在");
		}
		// 判断参数uid与查询结果中的uid是否不一致
		// 是：AccessDeniedException
		if (!address.getUid().equals(uid)) {
			throw new AccessDeniedException("设置默认收货地址失败,数据归属错误");
		}
		// 全部设置为非默认
		updataNoDefault(uid);
		// 把指定的设置为默认
		updataDefalut(aid);
    }
    
	@Override
	@Transactional
	public void delByAid(Integer uid, Integer aid)
			throws AddressNotFoundException, AccessDeniedException, UpdataException ,DeleteException{
			Address address=findByAid(aid);
			if (address==null) {
				throw new AddressNotFoundException("数据不存在");
			}
			//检查数据归属是否不正确
			if (!uid.equals(address.getUid())) {
				throw new AccessDeniedException("删除收货地址失败,数据归属错误");
			}
			//执行删除
			deleteByAid(aid);
			//检查此前查询的结果中isdefault是否为0
			if (address.getIsDefault().equals(0)) {
				return;
			}
			//获取当前用户的收货地址的数量
			Integer rows=countByUid(uid);
			//判断是否为0
			if (rows.equals(0)) {
				return;
			}
			//获取当前用户最后修改的收货地址数据
			Address add=findByTime(uid);
			//updataNoDefault(uid);
			updataDefalut(add.getAid());
			
			
	}

    
    @Override
    public List<Address> getByUidAll(Integer uid) {
    	List<Address> addresss=findByUid(uid);
    	return addresss;
    }
    
	@Override
	public Address getByAid(Integer aid) {
		return findByAid(aid);
	}

    
	
	/**
	 * 	添加收货地址
	 * @param address 收货地址数据
	 */
	private void insert(Address address) {
		Integer rows=addressMapper.insert(address);
		if (rows!=1) {
			throw new InsertException("增加收货地址时数据出现位置错误");
		}
	}
	
	/**
	 * 将用户的所有收货地址都设为非默认
	 * @param uid 用户的uid
	 */
	private void updataNoDefault(Integer uid) {
		Integer rows=addressMapper.updataNoDefault(uid);
		if (rows<1) {
			throw new UpdataException("设为默认收货地址时未知错误");
		}
	};

	/**
	 * 将用户的收货地址设为默认
	 * @param aid  收货地址的aid
	 */
	private void updataDefalut(Integer aid)  {
		Integer rows=addressMapper.updataDefalut(aid);
		if (rows!=1) {
			throw new UpdataException("更新数据出现错误"); 
		}
	};
	
	/**
	 * 	删除指定aid的收货数据
	 * @param aid 要删除收货地址的aid
	 */
	private void deleteByAid(Integer aid) {
		Integer rows=addressMapper.deleteByAid(aid);
		if (rows!=1) {
			throw new DeleteException("删除数据出现未知错误");
		}
	}
	
	/**
	 *  查找用户有几条收货地址
	 * @param uid 用户的id
	 * @return 实际条数
	 */
	private Integer countByUid(Integer uid) {
		return addressMapper.countByUid(uid);
	}
	
	/**
	 * 根据uid查找用户的收货地址
	 * @param uid 用户的UID
	 * @return 用户的所有收货地址
	 */
	private List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	/**
	 * 根据aid查找到用户信息
	 * @param aid 用户的收货地址的aid
	 * @return  用户信息
	 */
	private Address  findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	};
	
	/**
	 * 	查询用户修改时间最后的一条收货数据
	 * @param uid 用户的uid
	 * @return 修改时间最后的一条收货数据
	 */
	private Address findByTime(Integer uid) {
		return addressMapper.findByTime(uid);
	}
	
	
	/**
	 * 根据省市区的代号获取名称
	 * @param province 省的代号
	 * @param city 市的代号
	 * @param area 区的代号
	 * @return 省市区的代号对应的名称
	 */
	private String  getDistrict(String province,String city,String area) {
		District p=district.getByCode(province);
		District c=district.getByCode(city);
		District a=district.getByCode(area);
		String proName=p==null?"NULL":p.getName();
		String cityName=c==null?"NULL":c.getName();
		String areaName=a==null?"NULL":a.getName();
		StringBuilder bud=new StringBuilder();
		bud.append(proName);
		bud.append("-");
		bud.append(cityName);
		bud.append("-");
		bud.append(areaName);
		return bud.toString();
	}



	


	
}
