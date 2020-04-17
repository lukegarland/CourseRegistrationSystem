package common;

/**
 * Different message type constants
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 *
 */
public interface MessageTypes 
{

	public static final String getCatalogue = "GET_CAT";
	public static final String searchCatalogue = "SEARCH_CAT";
	public static final String searchStudentCourses = "GET_STU_COURSES";
	public static final String addCourse = "ADD_COURSE";
	public static final String removeCourse = "REM_COURSE";
	public static final String loginStudent = "LOGIN_STU";
	public static final String loginAdmin = "LOGIN_ADMIN";

	public static final String validResponse = "RESPONSE";
	public static final String errorResponse = "ERROR";
	
	
	
	
}
