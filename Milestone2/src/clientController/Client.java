/**
 * 
 */
package clientController;
import clientView.*;
import common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.sun.java.accessibility.util.GUIInitializedListener;

/**
 * @author lukeg
 *
 */
public class Client implements Messages
{

	
	private MainFrame GUI;
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	
	
	public Client(String serverName, int portNumber) {

		try {
			socket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			
			GUI = new MainFrame();
			
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
		StringBuilder sb = new StringBuilder();
		sb.append(messageType.trim());
		sb.append(" "); // Mark 
		sb.append(content);
		sb.append("\0"); //Mark end of content
		
		socketOut.println(sb.toString());
		
		sb = new StringBuilder();
		
		String line = "";
		while(true)
		{		
			try 
			{
				line = socketIn.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			
			if(line.contains(String.valueOf("\0")))
			{
				line.replace("\0", " ");
				sb.append(line);
				break;
			}else
			{
				sb.append(line);
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	

}
