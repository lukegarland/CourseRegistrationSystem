package serverModel;


import java.util.ArrayList;

import common.RegistrationSystemException;

/**
 * Class to record data about a student. Data such as their name, ID number, and course registrations are accessed from this class. 
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since February 13 2020
 * @version 1.1
 */
public class Student {
	
	/**
	 * Student's name. In this version, only the student's first name is recorded.
	 */
	private String studentName;
	
	/**
	 * Student's ID number.
	 */
	private int studentId;
	
	
	/**
	 * The course registrations that this student has registered in. 
	 */
	private ArrayList<Registration> studentRegList;
	
	/**
	 * @return ArrayList of registrations that this student currently has.
	 */
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	/**
	 * Constructor for new student.
	 * @param studentName Student's name (only the student's first name is recorded)
	 * @param studentId Identification number.
	 */
	public Student (String studentName, int studentId) 
	{
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	/**
	 * @return The students name
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return Student's ID number
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	/**
	 * Adds a registration to this student's {@code studentRegList} 
	 * @param registration to add 
	 * @throws RegistrationSystemException if student is already enrolled in this course.
	 */
	public void addRegistration(Registration registration) throws RegistrationSystemException 
	{
		Course courseToAdd = registration.getTheOffering().getTheCourse();
		
		for(Registration r : studentRegList)
		{
			if(r.getTheOffering().getTheCourse().equals(courseToAdd)) // Compare all courses the student is registered in against the course to add
			{
				throw new RegistrationSystemException("Error: Student is already enrolled in this course.");
			}
		}
		if(studentRegList.size() <= 5)
			studentRegList.add(registration);
		else
			throw new RegistrationSystemException("Error: Cannot register, as student cannot be enrolled in 6 or more courses.");
	}

	/**
	 * @return A string of the courses that the student is registered in. 
	 */
	public String printCourses()
	{
		if(this.studentRegList.isEmpty())
			return "No courses enrolled for:\n" + this.toString();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("The registered courses for:\n");
		sb.append(this.toString());
		int i = 0;
		for(Registration r : studentRegList)
		{
			sb.append("Course #"+(i+1) +". "+ r.toString());
			i++;
		
		}
		return sb.toString();
	}
	
	
	/**
	 * Returns a string of the student's name and ID number.
	 */
	@Override
	public String toString () 
	{
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}
	
}
