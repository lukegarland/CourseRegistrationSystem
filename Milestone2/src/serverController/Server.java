package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverModel.DBManager;

/**
 * Main server class. 
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 *
 */
public class Server {

	
	private DBManager db;
	
	private ServerSocket serverSocket;

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
	 * Starts the server
	 * @param args not used.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(8099);
		server.communicateWithClient();
		
	}

}
