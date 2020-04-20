package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverModel.DBManager;

/**
 * Server class responsible for communicating with the clients.
 * 
 * Server is responsible for connecting to the client and initializing 
 * a new communication controller to communicate with the client.
 * The server is opened to port 8099.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class Server {

	/**
	 * Database of the course registration system.
	 */
	private DBManager db;
	/**
	 * ServerSocket used to connect to the port
	 */
	private ServerSocket serverSocket;
	/**
	 * Used for multi-client support.
	 */
	private ExecutorService pool;
	/**
	 * Constructor for server.
	 * @param port Port number of client
	 */
	public Server(int port)
	{
		db = new DBManager();
		//db.loadFromTextFile("StudentData.txt", "CourseData.txt");
		db.loadCourseList();
		db.loadStudentList();
			try
			{
				serverSocket = new ServerSocket(port);
				pool = Executors.newCachedThreadPool();
				 
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Server is now running");
			
	}
	
	/**
	 * Will wait for a client to connect to the server, then creates a communication controller to communicate with the client.
	 */
	private void communicateWithClient()
	{
		
		try 
		{
			while(true)
			{	
				CommunicationController r = new CommunicationController(serverSocket.accept(), db); // Will block until ServerSocket accepts a new connection
				System.out.println("New connection started!");
				pool.execute(r);
			}

			
		}catch(IOException e)
		{
			e.printStackTrace();	
			pool.shutdown();
		}
	}
	
	/**
	 * Starts the server opened to port 8099.
	 * @param args not used.
	 */
	public static void main(String[] args) {
		Server server = new Server(8099);
		server.communicateWithClient();
	}

}
