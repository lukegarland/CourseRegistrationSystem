package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.Messages;
import serverModel.DBManager;

public class RegistrationApp implements Runnable, Messages
{

	DBManager db;
	
	private Socket s;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public RegistrationApp(Socket s, DBManager db)
	{
		
		this.db = db;
		this.s = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			socketOut = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	@Override
	public void run() 
	{
		String input = "";
		String response = "";
		
		try 
		{
			while(true)
			{
				input = socketIn.readLine();//Get input from socket

				response = actOnMessage(input);
				//Once \0 has been read, get input from user (console)
				socketOut.print(response);
				socketOut.flush();
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private String actOnMessage(String input) {
		// TODO
		return null;
	}

}
