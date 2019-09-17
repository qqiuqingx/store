package cn.tedu.store.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.controller.ex.FileContentTypeException;
import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIOException;
import cn.tedu.store.controller.ex.FileIllegalStateException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdataException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.ResponseResult;
/**
 * 控制器的基类
 * @author tarena
 *
 */
 public abstract class BaseController {

	/**
	 * 响应结果的状态:成功
	 */
	public static final Integer SUCCESS=200;
	/**
	 * 从session获取当前用户的uid
	 * @param session
	 * @return 当前用户的id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
			return Integer.valueOf(session.getAttribute("uid").toString());
	}
	/**
	 * 统一处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public  ResponseResult<Void> handlerException(Throwable e){
		ResponseResult<Void> rr= new ResponseResult<>();
		rr.setMessage(e.getMessage());
		if (e instanceof UsernameDuplicateException) {
			//400用户名冲突
			rr.setState(400);
		}else if(e instanceof UserNotFoundException) {
			System.err.println("用户不存在");
			//用户名不存在401
			rr.setState(401);
		}else if(e instanceof PasswordNotMatchException) {
			System.err.println("密码错误");
			//密码错误402
			rr.setState(402);
		}else if( e instanceof InsertException) {
			//500插入数据异常
			rr.setState(500);
		}else if( e instanceof UpdataException) {
			//501更新数据异常
			rr.setState(501);
		}else if( e instanceof DeleteException) {
			//502删除数据异常
			rr.setState(502);
		}else if( e instanceof FileContentTypeException) {
			//600文件格式异常
			rr.setState(600);
		}else if( e instanceof FileEmptyException) {
			//601文件为空
			rr.setState(601);
		}else if( e instanceof FileSizeException) {
			//602文件大小
			rr.setState(602);
		}else if( e instanceof FileIOException) {
			//603文件流异常
			rr.setState(603);
		}else if( e instanceof FileIllegalStateException) {
			//604上传文件非法异常
			rr.setState(604);
		}else if( e instanceof AccessDeniedException) {
			//605拒绝访问,可能因为权限不足或数据归属有误
			rr.setState(605);
		}else if( e instanceof AddressNotFoundException) {
			//606收货地址数据不存在
			rr.setState(606);
		}else if( e instanceof CartNotFoundException) {
			//700购物车商品未找到异常
			rr.setState(700);
		}
		return rr;
		
	}
}
