package serverController;

import common.RegistrationSystemException;
import serverModel.Course;
import serverModel.DBManager;
import serverModel.Registration;
import serverModel.Student;

/**
 * Controller for the model. Communication controller will call this object's  methods in order to interface with the model/database.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 *
 */
public class ModelController {

	
	private DBManager db;
	
	public ModelController(DBManager db) {
		this.db = db;
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * View the student's courses.
	 * @param content Either student ID or student's name. e.g. {"Michael", "Smith"}
	 * @return A formatted string displaying the student's courses
	 * @throws RegistrationSystemException If student does not exist or invalid input
	 */
	protected String viewStudentCourse(String[] content) throws RegistrationSystemException {

		if(content.length == 0)
			throw new RegistrationSystemException(invalidInputError());
		
		Student st = searchStudent(String.join(" ", content)); //Rejoin content in case query is name (e.g. Michael Smith)
		
		if(st == null)
		{
			throw new RegistrationSystemException(studentNotFoundError());
		}
		
		
		return st.printCourses();
	}

	/**
	 * 
	 * @param query Search key (Student id or name)
	 * @return the appropriate Student object. Null if not found
	 */
	private Student searchStudent(String query)
	{
		
		if(query.matches("\\d+"))// One or more digits...Searching by id number.
		{
			int id = Integer.parseInt(query);
			
			
			for(Student s : db.getStudentList())
			{
				if(id == s.getStudentId())
					return s;
			}
		}
		else //If not int (i.e, searching by name)
		{
			String name = query;
			for(Student s : db.getStudentList())
			{
				if(name.equals(s.getStudentName()))
					return s;
			}
			
		}
		
		return null;
	}
	
	
	/**
	 * @return the course catalog in a formatted string
	 */
	protected String viewCatalogue() 
	{

		return db.getCatalogue().toString();
	}

	/**
	 * Remove a student from a course
	 * @param content Format {"id or name", "Course name", "Course Number"}
	 * @return "Removed student from course!" if successful
	 * @throws RegistrationSystemException if student or course is not found
	 */
	protected String removeStudentFromCourse(String[] content) throws RegistrationSystemException {

		if(content.length != 3)
			throw new RegistrationSystemException(invalidInputError());
		
		String id = content[0];
		String courseName = content[1];

		int courseNumber = Integer.parseInt(content[2]);

		
		Student st = searchStudent(id);
	
		if(st == null)
		{
			throw new RegistrationSystemException(studentNotFoundError());
		}
		
		for(Registration r: st.getStudentRegList())
		{
			Course c = r.getTheOffering().getTheCourse();
			if(c.getCourseName().equalsIgnoreCase(courseName) && c.getCourseNum() == courseNumber)
			{
				st.getStudentRegList().remove(r);
				r.getTheOffering().getOfferingRegList().remove(r);
				return "Removed student from course!";
			}
		}
		
		
		throw new RegistrationSystemException(courseNotFoundError());
	}

	/**
	 * Add a student from a course
	 * @param content Format {"id or name", "Course name", "Course Number", "Section Number"}
	 * @return "Added student to course!" if successful
	 * @throws RegistrationSystemException if student or course is not found
	 */
	protected String addStudentToCourse(String[] content) throws RegistrationSystemException
	{
		if(content.length != 4)
			throw new RegistrationSystemException(invalidInputError());
		
		String id = content[0];
		String courseName = content[1];
		int courseNumber = Integer.parseInt(content[2]);
		int sectionNumber = Integer.parseInt(content[3]);
		
		Student st = searchStudent(id);
		
		if(st == null)
		{
	
			throw new RegistrationSystemException(studentNotFoundError());
		}
		
		
		Course c = db.getCatalogue().searchCat(courseName, courseNumber);
				
		if (c == null)
		{
			throw new RegistrationSystemException(courseNotFoundError());
		}else if (sectionNumber >= c.getNumberOfOfferings())
			throw new RegistrationSystemException(offeringDoesNotExistError());
		
		
		Registration r = new Registration();
		r.completeRegistration(st, c.getCourseOfferingAt(sectionNumber));
		
		return "Student registered in course";
	}

	/**
	 * @param Format: {"Course name", "Course Number"}
	 * @return Formatted string of the offering details of the course
	 * @throws RegistrationSystemException if course is not found
	 */
	protected String searchCourseCatalogue(String[] content) throws RegistrationSystemException {
		
		if(content.length != 2)
			throw new RegistrationSystemException(invalidInputError());
		
		
		String courseName = content[0];
		int courseNumber = Integer.parseInt(content[1]);

			Course c = db.getCatalogue().searchCat(courseName, courseNumber);
		if(c == null)
			throw new RegistrationSystemException(courseNotFoundError());
		
		return c.toString();

	}
	
	protected static String invalidInputError()
	{
		return "Error: Invalid input";

	}

	protected static String offeringDoesNotExistError() 
	{
		return "Error: Offering does not exist";
	}

	
	protected static String studentNotFoundError()
	{
		return "Error: Student not found";
	}
	
	protected static String courseNotFoundError()
	{
		return "Course not found";
	}

	public String loginAdmin(String[] content) {
		db.loadAdminLoginList();
		for(String[] s : db.getAdminLoginList())
		{
			if(s[0].equals(content[0]) && s[1].equals(content[1]))
				return "admin";
		}
		return null;
	}

	public String loginStudent(String[] content) {
		db.loadStudentLoginList();
		for(String[] s : db.getStudentLoginList())
		{
			if(s[0].equals(content[0]) && s[1].equals(content[1]))
				return "student" + " " + s[2];
		}
		return null;
	}



	public String addOffering(String[] content) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
