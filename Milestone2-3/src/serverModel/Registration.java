package serverModel;

import common.RegistrationSystemException;
/**
 * A class to join a student with a course offering, along with a grade.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since February 13 2020
 * @version 1.1
 */
public class Registration {
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	
	/**
	 * Registers a student {@code st} with the course offering {@code of}. 
	 * @param st Student
	 * @param of CourseOffering
	 * @throws RegistrationSystemException if the student is registered in too many courses, or if student is already registered in the course. 
	 */
	public void completeRegistration (Student st, CourseOffering of) throws RegistrationSystemException 
	{
		theStudent = st;
		theOffering = of;
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	
	
	/**
	 * @return The student
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	/**
	 * @param theStudent to set
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	/**
	 * @return The offering of this registration.
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * @param theOffering to set
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/**
	 * @return the student's grade in the course. Will be '0' if grade has not been set.
	 */
	public char getGrade() {
		return grade;
	}
	
	/**
	 * @param grade to set once student has completed the course.
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * returns a string of the offering of this registration and the students grade. Note: this does not print the student's name or ID.
	 */
	@Override
	public String toString () 
	{
		String st = "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}
	

}
