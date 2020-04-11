package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import common.Messages;
import serverModel.*;

public class RegistrationApp implements Runnable, Messages
{

	DBManager db;
	
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public RegistrationApp(Socket s, DBManager db)
	{
		
		this.db = db;
		try {
			socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			socketOut = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	@Override
	public void run() 
	{
		String input = "";
		String response = "";
		
		try 
		{
			while(true)
			{
				input = socketIn.readLine();//Get input from socket

				response = actOnMessage(input);
				socketOut.print(response);
				socketOut.println("\0");//End of message
				socketOut.flush();
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private String actOnMessage(String input) {
		// TODO
		
		String [] inputTokens = input.split("//s+");
		String type = inputTokens[0].trim();
		String[] content = Arrays.copyOfRange(inputTokens, 1, inputTokens.length);
		
		
		String rv;
		
		switch(type)
		{
		
			case Messages.searchCatalogue:
				rv = searchCourseCatalogue(content);
				break;
			case Messages.addCourse:
				rv = addStudentToCourse(content);
				break;
			case Messages.removeCourse:
				rv = removeStudentFromCourse(content);
				break;
			case Messages.getCatalogue:
				rv = viewCatalogue(content);
				break;
			case Messages.searchStudentCourses:
				rv = viewStudentCourse(content);
				break;

			default: 
				rv = "Error";
		}

		return rv;
	}

	private String viewStudentCourse(String[] content) {

		StringBuilder sb = new StringBuilder();
		
		

		Student st = searchStudent(content[0]);
		if(st == null)
		{
			return studentNotFoundError();
		}
		
		sb.append("\n==========View a student's courses==========\n");
		sb.append(st.printCourses());
		return sb.toString();
	}

	private Student searchStudent(String query)
	{
		
		if(query.matches("[0-9]+"))// One or more digits...Searching by id number.
		{
			int id = Integer.parseInt(query);
			
			
			for(Student s : db.getStudentList())
			{
				if(id == s.getStudentId())
					return s;
			}
		}
		else //If not int (i.e, searching by name)
		{
			String name = query;
			for(Student s : db.getStudentList())
			{
				if(name.equals(s.getStudentName()))
					return s;
			}
			
		}
		
		return null;
	}
	
	
	private String viewCatalogue(String[] content) {
		
		StringBuilder sb = new StringBuilder();

		sb.append("\n==========View course catalogue==========\n");

		sb.append(db.getCatalogue().toString());
		return sb.toString();
	}

	private String removeStudentFromCourse(String[] content) {

		String id = content[0];
		String courseName = content[1];

		int courseNumber = Integer.parseInt(content[2]);

		
		Student st = searchStudent(id);
	
		if(st == null)
		{
			return studentNotFoundError();
		}
		
		for(Registration r: st.getStudentRegList())
		{
			Course c = r.getTheOffering().getTheCourse();
			if(c.getCourseName().equalsIgnoreCase(courseName) && c.getCourseNum() == courseNumber)
			{
				st.getStudentRegList().remove(r);
				return "Removed student from course!";
			}
		}
		
		
		return "Course not found";
	}

	private String addStudentToCourse(String[] content)
	{
		String id = content[0];
		String courseName = content[1];
		int courseNumber = Integer.parseInt(content[2]);
		int sectionNumber = Integer.parseInt(content[3]);
		
		Student st = searchStudent(id);
		
		if(st == null)
		{
	
			return studentNotFoundError();
		}
		
		
		Course c = db.getCatalogue().searchCat(courseName, courseNumber);
				
		if (c == null)
		{
			return courseNotFoundError();
		}
		
		Registration r = new Registration();
		r.completeRegistration(st, c.getCourseOfferingAt(sectionNumber));
		
		return "Student registered in course";
	}

	private String searchCourseCatalogue(String[] content) {

		String courseName = content[0];
		int courseNumber = Integer.parseInt(content[1]);
		
		return db.getCatalogue().searchCat(courseName, courseNumber).toString();
	}
	
	private String invalidInputError()
	{
		return("Error: Invalid input");
	}
	
	private String studentNotFoundError()
	{
		return("Error: Student not found");
	}
	
	private String courseNotFoundError()
	{
		return("Course not found");
	}
	
	private String studentNotEnrolledError() 
	{
		return ("Error: Student not enrolled in any courses.");		
	}

}
