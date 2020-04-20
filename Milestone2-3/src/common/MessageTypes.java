package common;

/**
 * Interface containing the different message type constants.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public interface MessageTypes 
{
	//Client message types
	/**
	 * String constant to define the message type getCatalogue
	 */
	public static final String getCatalogue = "GET_CAT";
	/**
	 * String constant to define the message type searchCatalogue
	 */
	public static final String searchCatalogue = "SEARCH_CAT";
	/**
	 * String constant to define the message type searchStudentCourses
	 */
	public static final String searchStudentCourses = "GET_STU_COURSES";
	/**
	 * String constant to define the message type addCourse
	 */
	public static final String addCourse = "ADD_COURSE";
	/**
	 * String constant to define the message type removeCourse
	 */
	public static final String removeCourse = "REM_COURSE";
	/**
	 * String constant to define the message type loginStudent
	 */
	public static final String loginStudent = "LOGIN_STU";
	/**
	 * String constant to define the message type loginAdmin
	 */
	public static final String loginAdmin = "LOGIN_ADMIN";
	/**
	 * String constant to define the message type addOffering
	 */
	public static final String addOffering = "ADD_OFFERING";
	//Server response types
	/**
	 * String constant to define the message type validResponse
	 */
	public static final String validResponse = "RESPONSE";
	/**
	 * String constant to define the message type errorResponse
	 */
	public static final String errorResponse = "ERROR";
	
	
	
	
}
