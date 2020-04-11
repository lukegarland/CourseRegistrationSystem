/**
 * 
 */
package clientController;
import clientView.*;
import common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * @author lukeg
 *
 */
public class Client implements MessageTypes
{

	
	private MainFrame GUI;
	private Listeners GUIController;
	private Socket socket;
;
	
	private ObjectInputStream messageInputStream;
	private ObjectOutputStream messageOutputStream;

	
	public Client(String serverName, int portNumber) {

		try {
			socket = new Socket(serverName, portNumber);

			messageOutputStream = new ObjectOutputStream(socket.getOutputStream());
			messageInputStream = new ObjectInputStream((socket.getInputStream()));
			
			
			GUI = new MainFrame();
			GUIController = new Listeners(this, GUI); // Create Listeners/GUI Controller
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Client client = new Client("localhost", 8099);		
	}
	
	public String communicate(String messageType, String content)
	{
		
		Message toSend = new Message(messageType, content);
		
		try {
			messageOutputStream.reset(); // clear stream cache
			
			
			messageOutputStream.writeObject(toSend);
			
			Message response = (Message) messageInputStream.readObject();
			if(response.getType().equals(MessageTypes.validResponse))
				return response.getContent();	//Successful message back
	 
				
			
		} catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		
		return "Error Response"; // TODO: Check for error responses. (Possibly just show JOptionPane)
		
	}
	

}
