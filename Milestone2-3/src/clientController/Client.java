/**
 * .
 */
package clientController;
import clientView.*;
import common.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;


/**
 * Client class responsible for communicating with the course registration server.
 * Client is responsible for connecting to the server, initializing the GUI model 
 * and controller and sending Messsage objects to the server. The client is opened 
 * to port 8099.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class Client implements MessageTypes
{

	/**
	 * The primary JFrame which contains all buttons to access sub menus.
	 *
	 */
	private MainFrame GUI;
	/**
	 * Controller responsible for creating and managing all listeners for the GUI.
	 */
	@SuppressWarnings("unused")
	private Listeners GUIController;
	/**
	 * Socket to connect to the port.
	 */
	private Socket socket;
	/**
	 * Object reader for the socket input stream.
	 */
	private ObjectInputStream messageInputStream;
	/**
	 * Object reader for the socket output stream.
	 */
	private ObjectOutputStream messageOutputStream;

	/**
	 * Default constructor for client. 
	 * Creates a new client and initializes all buffer variables
	 * @param serverName name of server to connect to
	 * @param portNumber number of port to connect to
	 */
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
	 * Creates the client and opens it to port 8099.
	 * @param args Default - not used
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Client client = new Client("10.0.0.47", 8099);		
	}
	/**
	 * Communicates with the server. A Message object given by messageType and content
	 * is sent to the server and the server's response is returned if valid.
	 * @param messageType type of message being sent
	 * @param content contains any necessary data to be sent with the message
	 * @return response from the server. If the response is invalid, an error message is returned instead.
	 */
	public String communicate(String messageType, String content)
	{
		
		Message toSend = new Message(messageType, content);
		
		try {
			messageOutputStream.reset(); // clear stream cache
			messageOutputStream.writeObject(toSend);
			
			Message response = (Message) messageInputStream.readObject();
			
			if(response.getType().equals(MessageTypes.validResponse))
				return response.getContent();	//Successful message back
			else // error
			{
				JOptionPane.showMessageDialog(null, response.getContent(), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		return null; 
	}
	

}
