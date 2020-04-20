package serverModel;



import java.util.ArrayList;

/**
 * Class to represent an offering/section of a {@code Course}. An offering has an identifying number, a total capacity, and a list of registrations (students).
 * A course must have 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since February 13 2020
 * @version 1.1
 */
public class CourseOffering {
	
	/**
	 * Offering number
	 */
	private int secNum;
	
	/**
	 * Offering capacity
	 */
	private int secCap;
	
	/**
	 * The course corresponding to this offering.
	 */
	private Course theCourse;
	
	
	/**
	 * List of student registrations.
	 */
	private ArrayList <Registration> offeringRegList;


	private final int MIN_NUMBER_OF_STUDENTS = 8;
	
	/**
	 * True if MIN_NUMBER_OF_STUDENTS or more students are registered 
	 */
	private boolean validCourse; 
	
	
	/**
	 * Creates a course offering object. Note, theCourse must be set after the offering is constructed.
	 * @param secNum Offering's number
	 * @param secCap Offering's capacity
	 */
	public CourseOffering (int secNum, int secCap) 
	{
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
		validCourse = false;
	}
	
	/**
	 * @return Section/Offering's number
	 */
	public int getSecNum() {
		return secNum;
	}
	/**
	 * Sets the number of the offering.
	 * @param secNum Section/Offering's number to set
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	/**
	 * @return Section/Offering's capacity
	 */
	public int getSecCap() {
		return secCap;
	}
	/**
	 * @param secCap Section/Offering's capacity to set
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/**
	 * @return The course of this offering.
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * Sets the course of the offering. 
	 * @param theCourse course to set.
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * Returns a string with the number, capacity (and seats left) and the status of the course (valid or not valid).
	 */
	@Override
	public String toString () 
	{
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() + ", seats left: " + (getSecCap() - offeringRegList.size()) + "\nenough students: "+ validCourse+ "\n";
		return st;
	}
	
	
	/**
	 * Adds a student registration to this offering.
	 * @param registration Registration to add.
	 */
	public void addRegistration(Registration registration) 
	{
		if(offeringRegList.size()>= MIN_NUMBER_OF_STUDENTS)
			validCourse = true;
		
		if(offeringRegList.size()<= secCap)
			offeringRegList.add(registration);
		else
			System.err.println("Student cannot be registered; class is full");
	}
	
	/**
	 * @return an ArrayList of all registrations currently listed in the course.
	 */
	public ArrayList<Registration> getOfferingRegList() {
		return offeringRegList;
	}
	
}
