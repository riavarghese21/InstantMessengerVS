import java.net.*;
import java.io.*;

public class TCPClient 
{
	private Socket socket = null;
	private BufferedReader userInput;
	private DataOutputStream userOutput;

	public Socket getSocket()
	{
        return socket;
    	} 
    
    	public void setSocket(Socket socket) 
    	{
        this.socket = socket;
    	}
        
	public BufferedReader getuserInput()
	{
	return userInput;
	}

    public void setuserInput(BufferedReader userInput) 
    {
        this.userInput = userInput;
    }
	
    public DataOutputStream getuserOutput()
    {
		return userOutput;
    }
 
    public void setuserOutput(DataOutputStream userOutput) 
    {
        this.userOutput= userOutput;
    }
    
    //LOGIN----> Username: client3306 Password: computernetworks345

	public boolean login() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter username: ");
		String username = reader.readLine();
		System.out.print("Enter password: ");
		String password = reader.readLine();

		if (username.equals("client3306") && password.equals("computernetworks345")) {
			System.out.println("Login successful!");
			return true;
		} else {
			System.out.println("Invalid username or password.");
			return false;
		}
	}
	
    public TCPClient(String IP_Address, int portNumber) throws UnknownHostException, IOException 
    {
		if(!login()){
			System.exit(0);
		}

		socket = new Socket(IP_Address, portNumber);
		InetAddress localHost = InetAddress.getLocalHost();
        	System.out.println("IP Address: " + localHost.getHostAddress());
		System.out.println("TCP Client / Server are connected ");
		userInput = new BufferedReader(new InputStreamReader(System.in));
		userOutput = new DataOutputStream(socket.getOutputStream()); 
		String message;
		while (true)
		{
			try 
				{
					message = userInput.readLine();
					if (message.equals("terminate")) 
					{
						break;
					}
					System.out.println(message);
				 }
				catch (IOException e) 
				{
					System.out.println("input error : " + e.getMessage());
					break;
				}
				try 
				{
					if (userInput != null) 
					{
						userInput.close();
					}
					if (userOutput != null) 
					{
						userOutput.close();
					}
					if (socket != null) 
					{
						socket.close();
					}
				} 
				catch(IOException e) 
				{
					System.out.println("closing connection error " + e.getMessage());
				}
		}
	}
	//use your own ip address, and local host to make it work :) // 
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		new TCPClient("192.168.86.136", 3066);
	}
}
