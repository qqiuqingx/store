package cn.tedu.store.controller.ex;
/**
 * 上传文件时读写异常
 * @author tarena
 *
 */
public class FileIOException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2515513803832013958L;

	public FileIOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileIOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileIOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileIOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
