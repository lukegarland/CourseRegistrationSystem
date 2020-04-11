package serverModel;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *			
 * 			FrontEnd.java
 * 			@author lukeg
 * 			@since	Feb. 10, 2020
 * 
 */

public class FrontEnd {


	public static void main (String [] args) {
		CourseCatalogue cat = new CourseCatalogue ();
		ArrayList<Student> students = new ArrayList<Student>();
		
		students.add(new Student ("Sara", 1));
		students.add(new Student ("Bob", 2));
		students.add(new Student ("Joe", 3));
		students.add(new Student ("Billy", 5));
		students.add(new Student ("Megan", 10));
		students.add(new Student ("Michael", 42));
		students.add(new Student ("Taylor", 17));

		RegistrationApp app = new RegistrationApp(cat, students);		
		Scanner s = app.s;
		String userInput = "";
		
		while(true)
		{

			System.out.println("Enter a one letter command.\n"
					+ "s - Search course catalogue\n"
					+ "a - Add student to course\n"
					+ "r - Remove student from course\n"
					+ "c - View course catalogue\n"
					+ "e - View all courses enrolled by student\n"
					+ "q - Terminate program\n");

			
			userInput = s.nextLine();
			
			switch(userInput)
			{
			
				case "s":
					app.searchCourseCatalogue();
					break;
				case "a":
					app.addStudentToCourse();
					break;
				case "r":
					app.removeStudentFromCourse();
					break;
				case "c":
					app.viewCatalogue();
					break;
				case "e":
					app.viewStudentCourse();
					break;
				case "q":
					System.exit(0);
			}

		
		}
		
	}
	
}
