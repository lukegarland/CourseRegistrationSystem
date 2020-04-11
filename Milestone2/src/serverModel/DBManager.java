package serverModel;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	


	public void loadFromTextFile(String studentFile, String courseFile)
	{
		String input;
		String[] splitInput;
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = new ArrayList<Course>();
		this.studentList = new ArrayList<Student>();

		
		try {
			BufferedReader f = new BufferedReader(new FileReader(studentFile));
			while(true)
			{
				input = f.readLine();
				if(input == null)
					break;
				
				splitInput = input.split("\\s+");
				this.studentList.add(new Student(splitInput[0], Integer.parseInt(splitInput[1])));
			}
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = 0;
		
		
		try {
			BufferedReader f1 = new BufferedReader(new FileReader(courseFile));
			while(true)
			{
				input = f1.readLine();
				if(input == null)
					break;
				
				splitInput = input.split("\\s+");
				courseList.add(new Course(splitInput[0], Integer.parseInt(splitInput[1])));
				courseList.get(i).addOffering(new CourseOffering(Integer.parseInt(splitInput[2]), Integer.parseInt(splitInput[3])));
				courseList.get(i).addOffering(new CourseOffering(Integer.parseInt(splitInput[4]), Integer.parseInt(splitInput[5])));

				i++;
				
			}
			
			f1.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.courseList = new CourseCatalogue();
		this.courseList.setCourseList(courseList);
		
	}
	
	
	//===============Below this is text file setup and testing===============

	private void loadDatabaseSim()
	{
		ArrayList<Course> courseList = new ArrayList<Course>();;		
		courseList.add(new Course ("ENGG", 233));
		courseList.get(0).addOffering(new CourseOffering(0, 25));
		courseList.get(0).addOffering(new CourseOffering(0, 20));

		courseList.add(new Course ("ENSF", 409));
		courseList.get(1).addOffering(new CourseOffering(0, 40));
		courseList.get(1).addOffering(new CourseOffering(0, 45));

		courseList.add(new Course ("PHYS", 259));
		courseList.get(2).addOffering(new CourseOffering(0, 35));
		courseList.get(2).addOffering(new CourseOffering(0, 50));

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
		studentList.add(new Student ("Billy", 4));
		studentList.add(new Student ("Megan", 5));
		studentList.add(new Student ("Cameron", 6));
		studentList.add(new Student ("Guillaume", 7));
		studentList.add(new Student ("Luke", 8));
		studentList.add(new Student ("Taylor", 17));
		studentList.add(new Student ("Michael", 42));
		this.courseList = new CourseCatalogue();
		this.courseList.setCourseList(courseList);
		
		
	}
	
	private void createFileFromSim()
	{
		try {
			FileWriter f = new FileWriter(new File("StudentData.txt"));
			FileWriter f1 = new FileWriter(new File("CourseData.txt"));

			
			for(Student s : studentList)
			{
				f.write(s.getStudentName() + " " +s.getStudentId() + "\n");
			}
			for(Course c: courseList.getCourseList())
			{
				f1.write(c.getCourseName() + " " +c.getCourseNum() + " "+ c.getCourseOfferingAt(0).getSecNum()+ " " +" " + c.getCourseOfferingAt(0).getSecCap() + " "+ c.getCourseOfferingAt(1).getSecNum()+ " " +" " + c.getCourseOfferingAt(1).getSecCap()+ "\n");
			}
			f.flush();
			f1.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) 
	{
		DBManager db = new DBManager();
		db.loadDatabaseSim();
		db.createFileFromSim();
		
		db.loadFromTextFile("StudentData.txt", "CourseData.txt");
		return;
	}
}
