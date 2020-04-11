package serverModel;



import java.util.ArrayList;

public class CourseOffering {
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	//private ArrayList<Student> studentList;
	private ArrayList <Registration> offeringRegList;



	private boolean validCourse; // True if 8 or more students are registered 
	
	
	public CourseOffering (int secNum, int secCap) 
	{
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
		validCourse = false;
	}
	
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public int getSecCap() {
		return secCap;
	}
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	public Course getTheCourse() {
		return theCourse;
	}
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	@Override
	public String toString () 
	{
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() + ", seats left: " + (getSecCap() - offeringRegList.size()) + "\nenough students: "+ validCourse+ "\n";
		//We also want to print the names of all students in the section
		return st;
	}
	
	
	public void addRegistration(Registration registration) 
	{
		if(offeringRegList.size()>= 8)
			validCourse = true;
		
		if(offeringRegList.size()<= secCap)
			offeringRegList.add(registration);
		else
			System.err.println("Student cannot be registered; class is full");
	}
	public ArrayList<Registration> getOfferingRegList() {
		return offeringRegList;
	}
	
}
