package serverModel;


import java.util.ArrayList;

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

	public void addRegistration(Registration registration) 
	{
		if(studentRegList.size() <= 5)
			studentRegList.add(registration);
		else
			System.err.println("Error: Cannot register, as student cannot be enrolled in 6 or more courses.");
	}

	public void printCourses()
	{
		System.out.println("The registered courses for:\n");
		System.out.println(this);
		int i = 0;
		for(Registration r : studentRegList)
		{
			System.out.println("Course #"+(i+1) +". "+ r);
			i++;
		
		}
	}
	
}