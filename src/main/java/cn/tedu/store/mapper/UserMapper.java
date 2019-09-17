package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
/**
 *	 处理用户数据的持久层接口
 * @author tarena
 *
 */
public interface UserMapper {
	/**
	 * 	添加用户
	 * @param user用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	/**
	 *   	根据uid修改密码及相关信息
	 * @param uid 用户id
	 * @param password 新密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	Integer updateById(@Param("uid")Integer uid,@Param("password")String password,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	/**
	 *   修改用户个人资料
	 * @param user 用户信息
	 * @return 受影响行数
 	 */
	Integer updateInfo(User user) ;
	
	/**
	 * 修改用后头像
	 * @param uid 用户id
	 * @param avatar 头像路径
	 * @param modifiedUser 修改人 
	 * @param modifiedTime 修改时间
	 * @return 受影响行数
	 */
	Integer updateAvatar(@Param("uid") Integer uid,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 *	 根据用户姓名,查询用户信息
	 * @param username 用户名
	 * @return 匹配的用户的数据,没有则返回null
	 */
	User findByUsername(String username);

	/**
	 *  	根据用户id查找用户相关信息
	 * @param uid
	 * @return 匹配的用户数据,没有则返回null
	 */
	User selectByUid(Integer uid);
}
