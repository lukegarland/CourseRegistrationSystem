package serverModel;


import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {
	
	ArrayList <Course> courseList;

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	public ArrayList<Course> readFromDataBase() 
	{

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
		
		
		return courseList;
	}

}
