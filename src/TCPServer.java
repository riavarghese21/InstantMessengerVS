import java.net.*;
import java.io.*;

public class TCPServer
{
	private final Socket socket;
	private final ServerSocket server;
	private final DataInputStream userInput;
	this.port = port;


	public TCPServer() throws IOException 
	{
    	socket = null;
    	server = new ServerSocket();
    	userInput = null;
	}

	public TCPServer(int port) throws IOException 
	{
		ServerSocket serverSocket = new ServerSocket(port);
    	System.out.println("Server connecting. . . " + port);
		System.out.println("Client waiting . . . ");

		Socket clientSocket = serverSocket.accept();
		System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
	
		DataInputStream userInput = new DataInputStream(clientSocket.getInputStream());
		String inputMessage;

		while ((inputMessage = userInput.readUTF()) != null)
		 {
   			 if (inputMessage.equals("terminate")) 
			 {
       		break;
    	}
    System.out.println("message shown: " + inputMessage);
}
//it was easier do try/catches but ofc this can be changed. basically just closing userInput, socket, and serverSocket//
	System.out.println("connection ending. . .");
	try 
		{
			if (userInput != null) 
			{
				userInput.close();
			}
		} catch (IOException e) 
		{
			System.out.println("There is an error, ending the DataInputStream! : " + e.getMessage());
		}
		
		try {
			if (socket != null)
			 {
				socket.close();
			}
		} catch (IOException e) 
		{
			System.out.println("There is an error, ending Socket! " + e.getMessage());
		}
		
		try {
			if (serverSocket != null) 
			{
				serverSocket.close();
			}
		} catch (IOException e) {
			System.out.println("There is an error, ending Server! " + e.getMessage());
		}
	}
//local host: 3306//
	public static void main(String[] args)
	{
		try {
			TCPServer server = new TCPServer(3306);
		} catch (IOException e) 
		{
			System.out.println("There is an error, ending TCPServer Program! " + e.getMessage());
		}
	}
}
