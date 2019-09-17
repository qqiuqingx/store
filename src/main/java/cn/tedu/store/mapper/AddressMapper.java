package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址持久层接口
 * @author tarena
 *
 */
public interface AddressMapper {
	/**
	 * 	添加收货地址
	 * @param address 收货地址数据
	 * @return 受影响行数
	 */
	Integer insert(Address address);
	
	/**
	 * 	删除指定aid的收货数据
	 * @param aid 要删除收货地址的aid
	 * @return受影响的行数
	 */
	Integer deleteByAid(Integer aid);
	
	
	/**
	 * 将用户的所有收货地址都设为非默认
	 * @param uid 用户的uid
	 * @return 受影响行数
	 */
	Integer updataNoDefault(Integer uid);

	/**
	 * 将用户的收货地址设为默认
	 * @param aid  收货地址的aid
	 * @return 受影响行数
	 */
	Integer updataDefalut(Integer aid);
	
	

	/**
	 *  查找用户有几条收货地址
	 * @param uid 用户的id
	 * @return 实际条数
	 */
	Integer countByUid(Integer uid);
	
	/**
	 * 根据uid查找用户的收货地址
	 * @param uid 用户的UID
	 * @return 用户的所有收货地址
	 */
	List<Address> findByUid(Integer uid);
    
	/**
	 * 根据aid查找到用户信息
	 * @param aid 用户的收货地址的aid
	 * @return  用户信息
	 */
	Address  findByAid(Integer aid);
	
	/**
	 * 	查询用户修改时间最后的一条收货数据
	 * @param uid 用户的uid
	 * @return 修改时间最后的一条收货数据
	 */
	Address findByTime(Integer uid);

}












