package cn.tedu.store.controller.ex;
/**
 * 上传文件时文件类型异常
 * @author tarena
 *
 */
public class FileContentTypeException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6935375018112733030L;

	public FileContentTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
