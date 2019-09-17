package cn.tedu.store.util;

import java.io.Serializable;
/**
 * 用于向客户端响应数据的类型,
 * @author tarena
 *
 * @param <E>//响应数据的类型
 */
public class ResponseResult<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5368505763231357265L;
	private Integer state;
	private String message;
	private E data;
	
	public ResponseResult(Integer state, E data) {
		this.state = state;
		this.data = data;
	}
	public ResponseResult(Integer state) {
		this.state = state;
	}
	
	public ResponseResult() {
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
}
