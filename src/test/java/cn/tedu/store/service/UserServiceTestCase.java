package cn.tedu.store.service;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	private IUserService service;
	@Test
	public  void reg() {
		try {
		
			User user=new User();
		  user.setUsername("DS");
		   user.setPassword("12345");
		   user.setPhone("181001010031");
		   user.setEmail("aess@tedu.com");
		   user.setAvatar("http://http://localhost:8088/tedu/as.png");
		   user.setGender(1);
		service.reg(user);
		System.err.println("OK");
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	@Test
	public void messageDigest() {
		String password="123456";
		String result=DigestUtils.md5DigestAsHex(password.getBytes());
		System.err.println(result);
		System.err.println(UUID.randomUUID());
	}
	@Test
	public void login() {
		//童话 123456
		User user=service.login("童话", "qiu");
		System.err.println(user);
	}
	@Test
	public void ChangePassword() {
		String username="童话";
		String newpassword="qiu";
		String oldpassword="123456";
		//童话 qiu
		service.changePassword(14, username, newpassword, oldpassword);
		System.err.println("OK");
	}
	@Test
	public void changeInfo() {
		User oldUser=new User();
		oldUser.setUid(14);
		oldUser.setUsername("超管");
		oldUser.setPhone("181110193");
		oldUser.setEmail("738984966@qq.com");
		oldUser.setGender(1);
		service.changeInfo(oldUser);
		System.err.println("OK");
	}
	@Test
	public void getByUid() {
		//童话 123456
		User user=service.getByUid(14);
		System.err.println(user);
	}
	@Test
	public void changeavatar() {
		
		service.changeAvatar(13, "http://新");
		System.err.println("OK");
	}
}
