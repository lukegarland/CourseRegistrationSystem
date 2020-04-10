package serverModel;


import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {
	
	private volatile CourseCatalogue courseList;
	private volatile ArrayList <Student> studentList;
	
	
	public synchronized CourseCatalogue getCatalogue() {
		return courseList;
	}



	public synchronized ArrayList<Student> getStudentList() {
		return studentList;
	}
	


	public void loadDatabaseSim()
	{
		ArrayList<Course> courseList = new ArrayList<Course>();;		
		courseList = new ArrayList<Course>();
		courseList.add(new Course ("ENGG", 233));
		courseList.get(0).addOffering(new CourseOffering(0, 150));
		courseList.add(new Course ("ENSF", 409));
		courseList.get(1).addOffering(new CourseOffering(0, 150));
		courseList.add(new Course ("PHYS", 259));
		courseList.get(2).addOffering(new CourseOffering(0, 150));

		courseList.add(new Course ("ENGG", 200));
		courseList.get(3).addOffering(new CourseOffering(0, 100));
		courseList.get(3).addOffering(new CourseOffering(1, 50));

		courseList.add(new Course ("ENGG", 201));
		courseList.get(4).addOffering(new CourseOffering(0, 70));
		courseList.get(4).addOffering(new CourseOffering(1, 70));
	
		courseList.add(new Course ("ENGG", 202));
		courseList.get(5).addOffering(new CourseOffering(0, 80));
		courseList.get(5).addOffering(new CourseOffering(1, 80));

		courseList.add(new Course ("ENGG", 225));
		courseList.get(6).addOffering(new CourseOffering(0, 50));
		courseList.get(6).addOffering(new CourseOffering(1, 50));

		courseList.add(new Course ("ENCM", 511));
		courseList.get(7).addOffering(new CourseOffering(0, 0x42));
		courseList.get(7).addOffering(new CourseOffering(1, 0x2B - 1));
		
		studentList = new ArrayList<Student>();
		
		studentList.add(new Student ("Sara", 1));
		studentList.add(new Student ("Bob", 2));
		studentList.add(new Student ("Joe", 3));
		studentList.add(new Student ("Billy", 5));
		studentList.add(new Student ("Megan", 10));
		studentList.add(new Student ("Michael", 42));
		studentList.add(new Student ("Taylor", 17));
		this.courseList = new CourseCatalogue();
		this.courseList.setCourseList(courseList);
	}
}
