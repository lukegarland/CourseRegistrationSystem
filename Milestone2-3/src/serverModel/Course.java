package serverModel;

import java.util.ArrayList;

import common.RegistrationSystemException;

/**
 * General Class for a educational course. The course is identified by a 4-letter course 'name' and a number. 
 * A course must have at least one CourseOffering, and may have pre-requisite courses.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since February 13 2020
 * @version 1.1
 */
public class Course {

	/**
	 * 4 letter code of the name of the Course. (e.g. "ENGG", "ENSF", "BIOL")  
	 */
	private String courseName;
	
	/**
	 * Course number of the Course. 
	 */
	private int courseNum;
	
	/**
	 * Course Prerequisites.
	 */
	@SuppressWarnings("unused")
	private ArrayList<Course> preReq;
	
	/**
	 * List of current offerings of the course. 
	 */
	private ArrayList<CourseOffering> offeringList;

	/**
	 * Constructor
	 * @param courseName the Name
	 * @param courseNum the Number
	 */
	public Course(String courseName, int courseNum) 
	{
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * Adds an offering to the course
	 * @param offering CourseOffering to add
	 */
	public void addOffering(CourseOffering offering) 
	{
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			
			offeringList.add(offering);
		}
	}


	/**
	 * @return Course's 4 letter name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName 4-Letter code to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return Course's number
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * @param courseNum Number to set
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * @return The number of offerings for this course
	 */
	public int getNumberOfOfferings()
	{
		return offeringList.size();
	}
	
	/**
	 * Prints all the courses offerings and all the offering's relevant data.
	 */
	@Override
	public String toString () 
	{
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}
	
	/**
	 * Checks if courseName and courseNumber match those of {@code c}.
	 * @param c Course to compare for equality
	 * @return true if both name and number match, false otherwise
	 */
	public boolean equals(Course c)
	{
		if(c.courseName.equals(this.courseName) && c.courseNum == this.courseNum)
			return true;
		return false;
		
	}

	/**
	 * Returns offering in the i-th index in {@code offeringList}
	 * @param i index
	 * @return The course offering at i
	 */
	public CourseOffering getCourseOfferingAt(int i) 
	{
		// TODO Auto-generated method stub
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}
	

}
