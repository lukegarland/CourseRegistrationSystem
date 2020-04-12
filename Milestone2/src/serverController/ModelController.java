package serverController;

import common.RegistrationSystemException;
import serverModel.Course;
import serverModel.DBManager;
import serverModel.Registration;
import serverModel.Student;

public class ModelController {

	
	DBManager db;
	
	public ModelController(DBManager db) {
		this.db = db;
		// TODO Auto-generated constructor stub
	}

	

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
	
	
	protected String viewCatalogue() 
	{

		return db.getCatalogue().toString();
	}

	protected String removeStudentFromCourse(String[] content) throws RegistrationSystemException {

		if(content.length != 4)
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

	
}
