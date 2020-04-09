package serverModel;

import java.util.ArrayList;
import java.util.Scanner;

import ex1.Tool;

public class RegistrationApp {
	
	CourseCatalogue courseCatalogue;
	ArrayList<Student> students;
	public Scanner s;
	
	public RegistrationApp(CourseCatalogue courseCatalogue, ArrayList<Student> students) 
	{
		this.courseCatalogue = courseCatalogue;
		this.students = students;	
		s = new Scanner(System.in);

	}
	
	public Course searchCourseCatalogue()
	{
		System.out.println("\n==========Search for course==========");

			System.out.print("\nEnter Course name and ID Number (e.g. ENGG 233):");
			String[] userInput = s.nextLine().split(" ");
			
			if(userInput.length == 2 && userInput[1].matches("^[0-9]*$"))
			// if user input is two tokens, and second contains only numbers
			{
				Course c = courseCatalogue.searchCat(userInput[0], Integer.parseInt(userInput[1]));
				if (c == null)
				{	
					courseNotFoundError();
					return null;
				}
				else 
				{
					System.out.println(c);
					return c;
				}	
			}
			else
			{
				invalidInputError();
				return null;
			}

	}
	
	
	public void addStudentToCourse()
	{
		
		System.out.println("\n==========Add a student to a course==========");

		Student st = searchStudent();
		
		if(st == null)
		{
			studentNotFoundError();
			return;
		}
		System.out.println("\nSearch Catalogue to add the student to a course\n");
		
		Course c = searchCourseCatalogue();
		System.out.println("Which section will the student register in?:\n");
		int sec = s.nextInt();
		s.nextLine();
		try 
		{
			CourseOffering section = c.getCourseOfferingAt(sec);
			Registration r = new Registration();
			r.completeRegistration(st, section);
		}
		catch(Exception e)
		{
			invalidInputError();
		}	
	}
	
	
	
	public void removeStudentFromCourse() 
	{
		System.out.println("\n=========Remove a student to a course=========\n");

		Student st = searchStudent();
	
		if(st == null)
		{
			studentNotFoundError();
			return;
		}
		
		if(st.getStudentRegList().size()==0)
		{
			studentNotEnrolledError();
		}
		
		st.printCourses();
		
		System.out.println("Remove course #:");
		
		int courseIndex = s.nextInt() - 1;
		
		s.nextInt();

		if(courseIndex < 0 || courseIndex >= st.getStudentRegList().size())
			invalidInputError();
		else
			st.getStudentRegList().remove(courseIndex);
	}


	public void viewCatalogue()
	{
		System.out.println("\n==========View course catalogue==========\n");

		System.out.println(courseCatalogue);
	}
	

	public void viewStudentCourse()
	{
		System.out.println("\n==========View a student's courses==========\n");

		Student st = searchStudent();
		if(st == null)
		{
			studentNotFoundError();
			return;
		}
		st.printCourses();
	}
	
	
	private Student searchStudent()
	{
		System.out.println("\n==========Search student details==========\n");

		System.out.print("Enter Student name or ID number:");
		
		if(s.hasNextInt())
		{
			int id = s.nextInt();
			s.nextLine();
			for(Student s : students)
			{
				if(id == s.getStudentId())
					return s;
			}
		}
		else //If not int (i.e, searching by name)
		{
			String name = s.nextLine();
			for(Student s : students)
			{
				if(name.equals(s.getStudentName()))
					return s;
				
			}
			
		}
		
		return null;
	}

	
	private void invalidInputError()
	{
		System.err.println("Error: Invalid input");
	}
	
	private void studentNotFoundError()
	{
		System.err.println("Error: Student not found");
	}
	
	private void courseNotFoundError()
	{
		System.err.println("Course not found");
	}
	
	private void studentNotEnrolledError() 
	{
		System.err.println("Error: Student not enrolled in any courses.");		
	}
}