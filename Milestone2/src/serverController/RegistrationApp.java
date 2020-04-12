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
				
				String responseType, response;
				try 
				{
					response = actOnMessage(input.getType() + " " + input.getContent());
					responseType = MessageTypes.validResponse;
					
				}
				catch (RegistrationSystemException e) // Something went wrong.
				{
					response = e.getMessage();
					responseType = MessageTypes.errorResponse;
				}

				messageOutputStream.reset();
				messageOutputStream.writeObject(new Message(responseType, response)); //TODO: check for error response?
			} 
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}


	}

	private String actOnMessage(String input) throws RegistrationSystemException {
		// TODO
		
		String [] inputTokens = input.split("\\s+");
		String type = inputTokens[0];
		String[] content = Arrays.copyOfRange(inputTokens, 1, inputTokens.length);
		
		
		String rv;
		try
		{
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
					rv = viewCatalogue();
					break;
				case MessageTypes.searchStudentCourses:
					rv = viewStudentCourse(content);
					break;

				default: 
					throw new RegistrationSystemException("Message Communication Error");
			}
		}
		catch(NumberFormatException e)
		{
			throw new RegistrationSystemException(invalidInputError());
		}
		
		return rv;
	}

	private String viewStudentCourse(String[] content) throws RegistrationSystemException {

		if(content.length == 0)
			throw new RegistrationSystemException(invalidInputError());
		
		Student st = searchStudent(String.join(" ", content)); //Rejoin content in case query is name (e.g. Michael Smith)
		
		if(st == null)
		{
			throw new RegistrationSystemException(studentNotFoundError());
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
	
	
	private String viewCatalogue() 
	{

		return db.getCatalogue().toString();
	}

	private String removeStudentFromCourse(String[] content) throws RegistrationSystemException {

		if(content.length != 4)
			throw new RegistrationSystemException(invalidInputError());
		
		String id = content[0];
		String courseName = content[1];

		int courseNumber = Integer.parseInt(content[2]);

		
		Student st = searchStudent(id);
	
		if(st == null)
		{
			throw new RegistrationSystemException(studentNotFoundError());
		}
		
		for(Registration r: st.getStudentRegList())
		{
			Course c = r.getTheOffering().getTheCourse();
			if(c.getCourseName().equalsIgnoreCase(courseName) && c.getCourseNum() == courseNumber)
			{
				st.getStudentRegList().remove(r);
				r.getTheOffering().getOfferingRegList().remove(r);
				return "Removed student from course!";
			}
		}
		
		
		throw new RegistrationSystemException(courseNotFoundError());
	}

	private String addStudentToCourse(String[] content) throws RegistrationSystemException
	{
		if(content.length != 4)
			throw new RegistrationSystemException(invalidInputError());
		
		String id = content[0];
		String courseName = content[1];
		int courseNumber = Integer.parseInt(content[2]);
		int sectionNumber = Integer.parseInt(content[3]);
		
		Student st = searchStudent(id);
		
		if(st == null)
		{
	
			throw new RegistrationSystemException(studentNotFoundError());
		}
		
		
		Course c = db.getCatalogue().searchCat(courseName, courseNumber);
				
		if (c == null)
		{
			throw new RegistrationSystemException(courseNotFoundError());
		}else if (sectionNumber >= c.getNumberOfOfferings())
			throw new RegistrationSystemException(offeringDoesNotExistError());
		
		
		Registration r = new Registration();
		r.completeRegistration(st, c.getCourseOfferingAt(sectionNumber));
		
		return "Student registered in course";
	}

	private String searchCourseCatalogue(String[] content) throws RegistrationSystemException {
		
		if(content.length != 2)
			throw new RegistrationSystemException(invalidInputError());
		
		
		String courseName = content[0];
		int courseNumber = Integer.parseInt(content[1]);

			Course c = db.getCatalogue().searchCat(courseName, courseNumber);
		if(c == null)
			throw new RegistrationSystemException(courseNotFoundError());
		
		return c.toString();

	}
	
	private String invalidInputError()
	{
		return "Error: Invalid input";

	}

	private String offeringDoesNotExistError() 
	{
		return "Error: Offering does not exist";
	}

	
	private String studentNotFoundError()
	{
		return "Error: Student not found";
	}
	
	private String courseNotFoundError()
	{
		return "Course not found";
	}

}
