package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.CommunicationException;

import serverModel.DBManager;

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
		db.loadFromTextFile("StudentData.txt", "CourseData.txt");
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
	
	public void communicateWithClient()
	{
		
		try 
		{
			while(true)
			{	
				RegistrationApp r = new RegistrationApp(serverSocket.accept(), db); // Will block until ServerSocket accepts a new connection
				pool.execute(r);
			}

			
		}catch(IOException e)
		{
			e.printStackTrace();	
			pool.shutdown();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(8099);
		server.communicateWithClient();
		
	}

}
