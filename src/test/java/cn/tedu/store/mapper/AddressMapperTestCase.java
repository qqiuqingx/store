package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {
	@Autowired
	private AddressMapper mapper;
	@Test
	public void insert() {
		Address add=new Address();
		add.setUid(2);
		add.setName("王二娃");
		Integer rows=mapper.insert(add);
		System.err.println(rows);
	}
	
	@Test 
	public  void updataNoDefault() {
		Integer rows=mapper.updataNoDefault(14);
		System.err.println(rows);
	}
	@Test 
	public  void updataDefault() {
		Integer rows=mapper.updataDefalut(12);
		System.err.println(rows);
	}
	@Test 
	public  void countByUid() {
		Integer rows=mapper.countByUid(2);
		System.err.println(rows);
	}
	@Test 
	public  void findByUid() {
		List<Address> addres=mapper.findByUid(14);
		System.err.println("BEGIN");
		for (Address address : addres) {
			System.err.println(address);
		}
		System.err.println("END");
	}
	@Test 
	public  void findByAid() {
		Address address=mapper.findByAid(14);
		System.err.println(address);
	}
	@Test 
	public  void deleteByAid() {
		Integer rows=mapper.deleteByAid(4);
		System.err.println(rows);
	}
	@Test 
	public  void findByTime() {
		Address rows=mapper.findByTime(14);
		System.err.println(rows.getAid());
	}
}
