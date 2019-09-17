package cn.tedu.store.controller.ex;
/**
 * 上传文件时非法状态
 * @author tarena
 *
 */
public class FileIllegalStateException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2633371394657028484L;

	public FileIllegalStateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileIllegalStateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileIllegalStateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileIllegalStateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileIllegalStateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
