package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdataException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
/**
 * 处理用户数据的业务层接口
 * @author tarena
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @throws UsernameDuplicateException 用户名被占用时异常
	 * @throws InsertException 插入数据失败时异常
	 */
	void reg(User user )throws UsernameDuplicateException,InsertException;
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登陆的用户信息
	 * @throws UserNotFoundException 用户名不存在/isdelete標记为删除
	 * @throws PasswordNotMatchException 加密后的密码错误
	 */
	User login(String username,String password)throws UserNotFoundException,PasswordNotMatchException;
	/**
	 *    更新密码
	 * @param uid 当前登录的用户id
	 * @param username 当前用户登录的用户名
	 * @param newpassword 新密码
	 * @param oldpassword 旧密码
	 * @throws UserNotFoundException 若uid不存在 或isdelete为1 
	 * @throws PasswordNotMatchException 旧密码和数据库中密码不对
	 * @throws UpdataException 更新数据异常
	 */
	void changePassword(Integer uid,String username,String newpassword,String oldpassword)throws UserNotFoundException,PasswordNotMatchException,UpdataException;
	
	/**
	 *  更新用户的资料
	 * @param user 用户提交的新资料
	 * @throws UserNotFoundException 用户不存在
	 * @throws UpdataException 更新失败
	 */
	void changeInfo(User user)throws UserNotFoundException,UpdataException;
	
	/**
	 *  更新用户的头像
	 * @param uid 用户的id
	 *  @param avatar 用户上传的路径地址
	 * @throws UserNotFoundException 用户不存在
	 * @throws UpdataException 更新失败
	 */
	void changeAvatar(Integer uid,String avatar)throws UserNotFoundException,UpdataException;
	
	/**
	 *  	根据用户id查找用户相关信息
	 * @param uid
	 * @return 匹配的用户数据,没有则返回null
	 */
	User getByUid(Integer uid)throws UserNotFoundException;
	
}
