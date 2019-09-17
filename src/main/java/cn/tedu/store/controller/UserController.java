package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileContentTypeException;
import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIOException;
import cn.tedu.store.controller.ex.FileIllegalStateException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/users")
public class UserController  extends BaseController{
	

	@Autowired
	@Qualifier("a")
	private IUserService userService;
	/**
	 * 确定上传文件的名称
	 */
	private static final String UPLOAD_DIR="upload";
	/**
	 * 确定上传文件的的最大值
	 */
	private static final long FILE_SIZE=1*1024*1024;
	/**
	 * 确定允许上传的类型的列表
	 */
	private static final List<String> UPLOAD_CONTENT_TYPE=new ArrayList<String>();
	static {
		UPLOAD_CONTENT_TYPE.add("image/jpeg");
		UPLOAD_CONTENT_TYPE.add("image/png");
		UPLOAD_CONTENT_TYPE.add("image/gif");
		UPLOAD_CONTENT_TYPE.add("image/bmp");
	}
	@PostMapping("/reg")
	public ResponseResult<Void> reg(User user) {
			userService.reg(user);
			System.err.println("OK");
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("/login")
	public ResponseResult<User>login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpSession session){//参数前面有RequestParam 则客户端必须给数据 否则404
		User user =userService.login(username, password);
		System.out.println("OK");
		//封装用户信息
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<>(SUCCESS,user);
	}
	@PostMapping("/change_password")
	public  ResponseResult<Void> changePassworld(
			@RequestParam("old_password")String oldpassword,
			@RequestParam("new_password")String newpassword,
			HttpSession session
			){
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		userService.changePassword(uid, username, newpassword, oldpassword);
		System.err.println("修改完毕");
		return new ResponseResult<>(SUCCESS);
	}
	@RequestMapping("/change_info")
	public ResponseResult<Void> changInfo(User user,HttpSession session){
		user.setUid(getUidFromSession(session));
		user.setUsername(session.getAttribute("username").toString());
		userService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);
	}
	@GetMapping("/info")
	public ResponseResult<User> getById(HttpSession session){
		Integer uid=getUidFromSession(session);
		User user=userService.getByUid(uid);
		return new ResponseResult<User>(SUCCESS, user);
		
	}
	
	
	
	@PostMapping("/change_Avatar")
	public ResponseResult<String> changeAvatar(HttpServletRequest request,
			@RequestParam("file") MultipartFile file){
		if (file.isEmpty()) {
			throw new FileEmptyException("请上传头像");
		}
		if (file.getSize()>FILE_SIZE) {
			throw new FileSizeException("请上传大小小于:"+FILE_SIZE/1024+"KB的头像");
		}
		if (!UPLOAD_CONTENT_TYPE.contains(file.getContentType())) {
			throw new FileContentTypeException("请上传正确格式的头像");
		}
	    //文件夹路径  
		String filePath=request.getServletContext().getRealPath(UPLOAD_DIR);
		System.err.println("filePath:"+filePath);
		//创建文件夹  File f=new File(filePath);
		File parent=new File(filePath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		
		//文件后缀
		String s=file.getOriginalFilename();
		String p="";
		if (s.lastIndexOf(".")>0) {
			p=s.substring(s.lastIndexOf("."));
		}
		//创建文件名 :filename UUID.randomUUID().toString()/System.nanoTime()+""
		String filename=System.nanoTime()+p;
		//创建文件 File dest =new File(文件夹,文件名.后缀);
		File dest=new File(parent,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileIllegalStateException("上传文件非法状态");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileIOException("IO异常");
		}
		
		//获取uid 
		Integer uid=getUidFromSession(request.getSession());
		//获取文件路径/路径/文件名.后缀
		String avatar="/"+UPLOAD_DIR+"/"+filename;
		System.err.println("filePath:"+filePath);
		System.err.println("filename:"+filename);
		//执行更新:userService.changeAvatar(uid, avatar);
		userService.changeAvatar(uid, avatar);
		return 	 new ResponseResult<String>(SUCCESS, avatar);
	}
	
	
}
