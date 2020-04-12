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

	ModelController controller;
	private ObjectInputStream messageInputStream;
	private ObjectOutputStream messageOutputStream;

	public RegistrationApp(Socket s, DBManager db)
	{
		
		controller = new ModelController(db);
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
					rv = controller.searchCourseCatalogue(content);
					break;
				case MessageTypes.addCourse:
					rv = controller.addStudentToCourse(content);
					break;
				case MessageTypes.removeCourse:
					rv = controller.removeStudentFromCourse(content);
					break;
				case MessageTypes.getCatalogue:
					rv = controller.viewCatalogue();
					break;
				case MessageTypes.searchStudentCourses:
					rv = controller.viewStudentCourse(content);
					break;

				default: 
					throw new RegistrationSystemException("Message Communication Error");
			}
		}
		catch(NumberFormatException e)
		{
			throw new RegistrationSystemException(ModelController.invalidInputError());
		}
		
		return rv;
	}

}
