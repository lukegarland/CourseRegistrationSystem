package serverModel;


import java.util.ArrayList;

import common.RegistrationSystemException;

public class Student {
	
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public Student (String studentName, int studentId) 
	{
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString () 
	{
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

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
	
}
