import java.net.*;
import java.io.*;

public class TCPServer 
{
	
	private Socket socket = null;
	private ServerSocket server;
	private DataInputStream userInput;

	public Socket getSocket()
    {
        return socket;
    } 
    
    public void setSocket(Socket socket) 
    {
        this.socket = socket;
    }

	public ServerSocket getServer() 
	{
		return server;
	}

	public void setServer(ServerSocket server) 
	{
		this.server = server;
	}

	public DataInputStream getuserInput() 
    {
        return userInput;
    }

    public void setuserInput(DataInputStream userInput) 
    {
        this.userInput = userInput;
    }

    public TCPServer(int portNumber) 
	{
		try (ServerSocket server = new ServerSocket(portNumber)) {
			System.out.println("Server connected! ");
			System.out.println("Client waiting . . .");
		
			try (Socket socket = server.accept())
			{
				System.out.println("client accepting server connection. ");
		
				try (DataInputStream userInput = new DataInputStream(
						new BufferedInputStream(socket.getInputStream()))) {
				String message;
				while (true)
				{
					try 
					{
						message = userInput.readUTF();
						if (message.equals("terminate")) 
						{
							break;
						}
						System.out.println(message);
						} catch (IOException e) 
						{
							System.out.println("input error : " + e.getMessage());
									break;
						}
					}
				}
			} catch (IOException e) {
				System.out.println("could not configure client server. : " + e.getMessage());
			}
		} catch (IOException e) 
		{
			System.out.println("error occurred when starting server! : " + e.getMessage());
		}
		try {
			if (socket != null) 
			{
				socket.close();
			}
			if (userInput != null) 
			{
				userInput.close();
			}
		} catch(IOException e) {
			System.out.println("closing connection error " + e.getMessage());
		}
	}
	//local host: 3066//
	public static void main(String args[]) 
	{
			new TCPServer(3066);
		}
}

