package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestCase {
	@Autowired
	private IDistrictService districtService;
	@Test
	public void getbByP() {
		List<District>districts=districtService.getByParent("542300");
		System.err.println("begin");
		for (District district : districts) {
			System.err.println(district);
		}
		System.err.println("end");
	}
	@Test
	public void getbByCode() {
		//542300
		District districts=districtService.getByCode("542300");
		System.err.println(districts);
	}
}
