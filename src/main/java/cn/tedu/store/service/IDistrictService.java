package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * 处理省/市/区业务层接口
 * @author tarena
 *
 */
public interface IDistrictService {
	/**
	 * 根据parent查找省/市/区的列表
	 * @param parent 各省/市/区的代号
	 * @return 省/市/区列表
	 */
	List<District> getByParent(String parent);
	
	/**
	 * 根据代号获取省/市/区的信息
	 * @param code 代号
	 * @return 匹配的省市区的信息,若没有返回null
	 */
	District getByCode(String code);
	
}

