package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import common.*;
import serverModel.*;

public class RegistrationApp implements Runnable, MessageTypes
{

	DBManager db;

	private ObjectInputStream messageInputStream;
	private ObjectOutputStream messageOutputStream;

	public RegistrationApp(Socket s, DBManager db)
	{
		
		this.db = db;
		try {

			messageInputStream = new ObjectInputStream((s.getInputStream()));
			messageOutputStream = new ObjectOutputStream(s.getOutputStream());
			
			
			
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	@Override
	public void run() 
	{
		
			
		
		try {
			while(true)
			{
				Message input;

				input = (Message) messageInputStream.readObject();

				String response = actOnMessage(input.getType() + " " + input.getContent());

				messageOutputStream.reset();
				messageOutputStream.writeObject(new Message(MessageTypes.validResponse, response)); //TODO: check for error response?
			} 
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}


	}

	private String actOnMessage(String input) {
		// TODO
		
		String [] inputTokens = input.split("\\s+");
		String type = inputTokens[0];
		String[] content = Arrays.copyOfRange(inputTokens, 1, inputTokens.length);
		
		
		String rv;
		
		switch(type)
		{
		
			case MessageTypes.searchCatalogue:
				rv = searchCourseCatalogue(content);
				break;
			case MessageTypes.addCourse:
				rv = addStudentToCourse(content);
				break;
			case MessageTypes.removeCourse:
				rv = removeStudentFromCourse(content);
				break;
			case MessageTypes.getCatalogue:
				rv = viewCatalogue(content);
				break;
			case MessageTypes.searchStudentCourses:
				rv = viewStudentCourse(content);
				break;

			default: 
				rv = "Errorz";
		}

		return rv;
	}

	private String viewStudentCourse(String[] content) {

		Student st = searchStudent(content[0].trim());
		if(st == null)
		{
			return studentNotFoundError();
		}
		
		
		return st.printCourses();
	}

	private Student searchStudent(String query)
	{
		
		if(query.matches("\\d+"))// One or more digits...Searching by id number.
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
	
	
	private String viewCatalogue(String[] content) 
	{

		return db.getCatalogue().toString();
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
