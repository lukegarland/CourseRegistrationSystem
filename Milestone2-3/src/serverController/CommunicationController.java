package serverController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

import common.*;
import serverModel.*;

/**
 * Controller that directly communicates with the client. 
 * 
 * The controller will create and parse messages to and from 
 * the socket and invoke ModelController's methods to manipulate the database
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19, 2020
 * @version 1.1
 *
 */
public class CommunicationController implements Runnable, MessageTypes
{
	/**
	 * Controller for the server.
	 */
	private ModelController controller;
	/**
	 * Object reader for the socket input stream.
	 */
	private ObjectInputStream messageInputStream;
	/**
	 * Object reader for the socket output stream.
	 */
	private ObjectOutputStream messageOutputStream;

	/**
	 * Constructs a CommunicationController with values given by the parameters s and db.
	 * @param s Socket
	 * @param db Reference to the database
	 */
	public CommunicationController(Socket s, DBManager db)
	{
		
		controller = new ModelController(db);
		try {

			messageInputStream = new ObjectInputStream((s.getInputStream()));
			messageOutputStream = new ObjectOutputStream(s.getOutputStream());
			
			
			
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * Main runnable class to receive and send messages to the client.
	 */
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
				messageOutputStream.writeObject(new Message(responseType, response)); 
			} 
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}


	}

	/**
	 * Takes the Client message given by input and calls the required controller methods.
	 * 
	 * @param input Content of a message from the Client
	 * @return Message content to send back to the client
	 * @throws RegistrationSystemException if ModelController throws an exception
	 */
	private String actOnMessage(String input) throws RegistrationSystemException {
		
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
				case MessageTypes.loginAdmin:
					rv = controller.loginAdmin(content);
					break;
				case MessageTypes.loginStudent:
					rv = controller.loginStudent(content);
					break;
				case MessageTypes.addOffering:
					rv = controller.addOffering(content);
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
