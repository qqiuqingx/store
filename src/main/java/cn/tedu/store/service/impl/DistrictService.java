package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
/**
 * 处理省/市/区  业务层实现类
 * @author tarena
 *
 */
@Service
public class DistrictService implements IDistrictService {

	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public List<District> getByParent(String parent) {
		return findByParent(parent);
	}
	
	@Override
	public District getByCode(String code) {
		District ids=findByCode(code);
		return ids;
	}
	
	/**
	 * 根据parent查找省/市/区的列表
	 * @param parent 各省/市/区的代号
	 * @return 省/市/区列表
	 */
   private List<District> findByParent(String parent){
	   return districtMapper.findByParent(parent);
   }
   
	/**
	 * 根据代号获取省/市/区的信息
	 * @param code 代号
	 * @return 匹配的省市区的信息,若没有返回null
	 */
	private District findByCode(String code) {
		return districtMapper.findByCode(code);
	};
   
   
	
   
}
