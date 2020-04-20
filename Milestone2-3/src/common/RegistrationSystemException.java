/**
 * 
 */
package common;

/**
 * Basic user-defined exception to signify an error when controlling the ServerModel.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class RegistrationSystemException extends Exception {

	/**
	 * Default not used.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an empty RegistrationSystemException.
	 */
	public RegistrationSystemException() {
	}

	/**
	 * Constructs an exception with a message given by message.
	 * @param message exception message to be sent
	 */
	public RegistrationSystemException(String message) {
		super(message);
	}

	/**
	 * Constructs an exception with a cause given by cause.
	 * @param cause Throwable object for the exception
	 */
	public RegistrationSystemException(Throwable cause) {
		super(cause);
	}
	/**
	 * Constructs an exception with values given by the parameters message and cause.
	 * @param message exception message to be sent
	 * @param cause Throwable object for the exception
	 */
	public RegistrationSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs an exception with values given by the parameters message, cause, enableSuppression and writableStackTrace.
	 * @param message exception message to be sent
	 * @param cause Throwable object for the exception
	 * @param enableSuppression enables suppression of the exception
	 * @param writableStackTrace enables a writable stack trace
	 */
	public RegistrationSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
