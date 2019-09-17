package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.impl.AddressServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	private AddressServiceImpl addressImpl;
	@Test
	public  void insert() {
		try {
		Address address=new Address();
		address.setUid(2);
		address.setName("9527");
		addressImpl.addnew(address, "大鲨鱼");
		System.err.println("OJBK");
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void  findByUidAll() {
		List<Address> adds=addressImpl.getByUidAll(14);
		System.err.println("BEGIN");
		for (Address address : adds) {
			System.err.println(address);
		}
	}
	@Test
	public void  setDefault() {
		try {
			addressImpl.setDefault(14, 20);
			System.err.println("OK");
			
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void  delByAid() {
		try {
			addressImpl.delByAid(2, 14);
			System.err.println("OK");
			
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void  getByAid() {
		Address address=addressImpl.getByAid(14);
		System.err.println(address);
	}
	
}
