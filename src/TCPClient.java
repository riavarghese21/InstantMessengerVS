import java.net.*;
import java.io.*;

public class TCPClient 
{

	private final Socket socket;
	private final BufferedReader input;
	private final DataOutputStream output;
	this.port = port;

	public TCPClient() 
	{
   		socket = null;
   		input = null;
   		output = null;
	}

	public TCPClient(String IP_address, int port) throws IOException 
	{
		socket = new Socket(IP_address, port);
		System.out.println("connected ^^ ");
		try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) 
			 {
		} catch (UnknownHostException u) 
		{
			throw new ConnectException("can't connect to local host: " + u.getMessage());
		} catch (IOException i) 
		{
			throw new ConnectException("IO error : " + i.getMessage());
		}

		String inputMessage = "";
		try {
			while (!inputMessage.equals("terminate")) 
			{
				inputMessage= input.readLine();
				output.writeUTF(inputMessage);
			}
		} catch (IOException i)
		{
			throw new RuntimeException("IO error: ", i); //undetermined data error//
		} finally 
		{
			try 
			{
				if (input != null) 
				{
					input.close(); //it was easier do try/catches but ofc this can be changed. basically just closing userInput, socket, and serverSocket//
				}
				if (output != null)
				 {
					output.close();
				}
				if (socket != null) 
				{
					socket.close();
				}
			} catch (IOException e) 
			{
				System.out.println("closing error " + e.getMessage());
			}
		}
	}
	public static void main(String args[])
	{
		try {
			TCPClient client = new TCPClient("192.168.50.167", 3306);

		} catch (ConnectException e) {
			System.err.println("There is an error, ending TCPClient Program! " + e.getMessage());
		} 
		}
	}
