/**
 * 
 */
package common;

/**
 * Basic user-defined exception to signify an error when controlling the ServerModel
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 *
 */
public class RegistrationSystemException extends Exception {


	private static final long serialVersionUID = 1L;


	public RegistrationSystemException() {
		// TODO Auto-generated constructor stub
	}


	public RegistrationSystemException(String message) {
		super(message);
	}


	public RegistrationSystemException(Throwable cause) {
		super(cause);
	}

	public RegistrationSystemException(String message, Throwable cause) {
		super(message, cause);
	}


	public RegistrationSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
