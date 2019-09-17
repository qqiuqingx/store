package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdataException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
/**
 * 处理用户数据的业务层实现类
 * @author tarena
 *
 */
@Service("a")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	
	

	
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		String username=user.getUsername();
		User us=findByUsername(username);
		if (us==null) {
			// 设置is_delete 
			user.setIsDelete(0);
			//  设置4项日志
			Date now=new Date();
			user.setCreatedUser(username);
			user.setCreaterTime(now);
			user.setModifiedTime(now);
			user.setModifiedUser(username);
			//生成随机盐
			String salt=UUID.randomUUID().toString();
			//执行密码加密,得到加密后的密码
			String md5Password=getMd5Password(user.getPassword(), salt);
			System.err.println("自己输入的盐值:"+user.getSalt());
			//将盐和加密后的密码封装到user中
			user.setPassword(md5Password);
			user.setSalt(salt);
			System.err.println("修改后的盐值:"+salt);
			//执行注册
	        insert(user);
		}else {
			//已占用，抛出UsernameDuplicateException异常
			throw new UsernameDuplicateException("注册失败!用户名("+user.getUsername()+")重复");
		}
	}
	
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user=findByUsername(username);
		if (user==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (user.getIsDelete().equals(1)) {
			throw new UserNotFoundException("用户不存在");
		}
		String salt=user.getSalt();
		String pwd=getMd5Password(password, salt);
		if (!pwd.equals(user.getPassword())) {
			throw new PasswordNotMatchException("密码错误");
		}
		//准备返回结果，去除部分不需要对外使用的数据
		user.setPassword(null);
		user.setSalt(null);
		user.setIsDelete(null);
		return user;
	}
	
	public void changePassword(Integer uid, String username, String newpassword, String oldpassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdataException {
		//查询数据
		User user=selectByUid(uid);
		if (user==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(user.getIsDelete().equals(1)) {
			System.err.println("isdelete为1");
			throw new UserNotFoundException("用户不存在");
		}
		String salt=user.getSalt();
		String oldMd5password=getMd5Password(oldpassword, salt);
		if (!oldMd5password.equals(user.getPassword())) {
			System.err.println("密码不对》》》》》》");
			throw new PasswordNotMatchException("密码错误");
		}
		String newMd5passworld=getMd5Password(newpassword, salt);
		Date now =new Date();
		System.err.println("newMd5password:"+newMd5passworld);
		updateById(uid, newMd5passworld, username, now);
		System.err.println("修改完毕");
		
		
	}
	
	public void changeInfo(User user) throws UserNotFoundException, UpdataException {
		User oldUser=selectByUid(user.getUid());
		if (oldUser==null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (oldUser.getIsDelete().equals(1)) {
			throw new UserNotFoundException("用户不存在");
		}
				
		String modiUser=user.getUsername();
		Date now =new Date();
		user.setModifiedUser(modiUser);
		user.setModifiedTime(now);
		updateInfo(user);
	}
	
	@Override
	public void changeAvatar(Integer uid,String avatar) throws UserNotFoundException, UpdataException {
		User user=selectByUid(uid);
		if (user==null) {
			throw new UserNotFoundException("修改头像失败,用户信息不存在");
		}
		if (user.getIsDelete().equals(1)) {
			throw new UserNotFoundException("修改头像失败,用户信息不存在");
		}
		
		updateAvatar(uid, avatar, user.getUsername(), new Date());
		
	}
	
	@Override
	public User getByUid(Integer uid)throws UserNotFoundException {
		User user=selectByUid(uid);
		if (user==null) {
			throw new UserNotFoundException("获取用户信息失败");
		}
		if (user.getIsDelete().equals(1)) {
			throw new UserNotFoundException("获取用户信息失败");
		}
		//返回之前隐藏不向外提供的数据
			user.setPassword(null);
			user.setSalt(null);
			user.setIsDelete(null);
		return user;
	}
	
	

	
	/**
	 * 执行MD5消息摘要算法加密
	 * @param password  用户密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password,String salt) {
		//加密规则:使用‘盐+密码+盐’作为原始数据,执行10次
		String result=salt+password+salt;
		for(int i=0;i<10;i++) {
			result=DigestUtils.md5DigestAsHex(result.getBytes());
		}
		return result;
	}
	
	/**
	 *	 根据用户姓名,查询用户信息
	 * @param username 用户名
	 * @return 匹配的用户的数据,没有则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	/**
	 *  	根据用户id查找用户相关信息
	 * @param uid
	 * @return 匹配的用户数据,没有则返回null
	 */
	private User selectByUid(Integer uid) {
		
		return userMapper.selectByUid(uid);
	}
	
	/**
	 * 	添加用户
	 * @param user用户数据
	 * @return 受影响的行数
	 */
	private void insert(User user) {
		Integer rows=userMapper.insert(user);
		if (rows!=1) {
			throw new InsertException("插入用户数据未知错误！！！！！");
		}
	}

	/**
	 *   修改用户个人资料
	 * @param user 用户信息
 	 */
	private void updateInfo(User user) {
		Integer rows=userMapper.updateInfo(user);
		if (rows!=1) {
			throw new UpdataException("修改数据未知错误");
		}
	}
	
	/**
	 * 修改用后头像
	 * @param uid 用户id
	 * @param avatar 头像路径
	 * @param modifiedUser 修改人 
	 * @param modifiedTime 修改时间
	 * @return 受影响行数
	 */
	private void updateAvatar(Integer uid,
			 String avatar,
			String modifiedUser,
			Date modifiedTime) {
		Integer rows=userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if (rows!=1) {
			throw new UpdataException("修改数据未知错误");
		}
	}
	
	/**
	 *   	根据uid修改密码及相关信息
	 * @param uid 用户id
	 * @param password 新密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	private void updateById(Integer uid,String password,
			String modifiedUser,Date modifiedTime) {
		Integer rows=userMapper.updateById(uid, password, modifiedUser, modifiedTime);
		if (rows!=1) {
			throw new UpdataException("更新数据出错");
		}
	}
	
	
}
