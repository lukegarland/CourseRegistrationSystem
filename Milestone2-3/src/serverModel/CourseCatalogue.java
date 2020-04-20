package serverModel;


import java.util.ArrayList;

import common.RegistrationSystemException;

/**
 * Class to store a list of courses and relevant functions.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since February 13 2020
 * @version 1.1
 */
public class CourseCatalogue {
	
	private ArrayList <Course> courseList;
	
	/**
	 * Default constructor
	 */
	public CourseCatalogue () 
	{
		
	}
	
	
	/**
	 * Creates a offering for course c.
	 * @param c The course
	 * @param secNum Offering number ({@code secNum = c.getNumberOfOfferings} is recommended)
	 * @param secCap Section's capacity
	 */
	public void createCourseOffering (Course c, int secNum, int secCap)
	{
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	
	/**
	 * Search for a course object.
	 * @param courseName Course's name
	 * @param courseNum Course's number
	 * @return The corresponding course, or null if not found.
	 */
	public Course searchCat (String courseName, int courseNum) 
	{
		for (Course c : courseList) {
			if (courseName.equalsIgnoreCase(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		return null;
	}
	
	
	
	/**
	 * @return The list of courses in the catalogue
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	/**
	 * Sets the catalogue to be courseList
	 * @param courseList
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Prints all courses in the catalogue according to Course's {@code toString()}
	 */
	@Override
	public String toString () 
	{
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}

}
