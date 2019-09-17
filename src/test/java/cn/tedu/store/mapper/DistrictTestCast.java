package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictTestCast {
	@Autowired
	private DistrictMapper district;
	@Test
	public void findbByP() {
		List<District>districts=district.findByParent("542300");
		System.err.println("begin");
		for (District district : districts) {
			System.err.println(district);
		}
		System.err.println("end");
	}
	@Test
	public void findbByCode() {
		District districts=district.findByCode("320102");
		System.err.println(districts);
	}
}
