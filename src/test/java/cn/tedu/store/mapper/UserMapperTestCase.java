package cn.tedu.store.mapper;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	@Autowired
	public UserMapper mapper;
	
   @Test
   public void insert() {
	   User user=new User();
	   user.setUsername("root");
	   user.setPassword("123456");
	   user.setSalt("123456");
	   user.setPhone("18181881");
	  Integer rows= mapper.insert(user);
	  System.err.println(rows);
   }
   
	
	
	@Test
	public  void updateById() {
		Date date=new Date();
		Integer id=mapper.updateById(9, "6666", "DS2", date);
		System.err.println(id);
	}
	
	@Test
	public  void updateInfo() {
		User user =new User();
		user.setUid(14);
		user.setPhone("18131493709");
		user.setEmail("7380@163.com");
		user.setGender(1);
		user.setModifiedUser("童话");
		Date now=new Date();
		user.setModifiedTime(now);
		Integer rows=mapper.updateInfo(user);
		System.err.println(rows);
	}
	
	@Test
	public  void selectByUid() {
		User user=mapper.selectByUid(14);
		System.err.println(user);
	}
	
	@Test
	public  void updateAvatar() {
		Date date=new Date();
		Integer id=mapper.updateAvatar(7, "http://1024.com", "超管", date);
		System.err.println(id);
	}
	
	@Test
	public  void findByUsername() {
		User user=mapper.findByUsername("萨尔");
		System.err.println(user);
	}
	
}
