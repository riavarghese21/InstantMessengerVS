import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;

public class Client1 {
    private Socket socket = null;
    private BufferedReader userInput;
    private DataOutputStream userOutput;

    public Socket getSocket() {
        return socket;
    } 

    public void setSocket(Socket socket)
     {
        this.socket = socket;
    }

    public BufferedReader getUserInput() 
    {
        return userInput;
    }

    public void setUserInput(BufferedReader userInput) 
    {
        this.userInput = userInput;
    }

    public DataOutputStream getUserOutput() 
    {
        return userOutput;
    }

    public void setUserOutput(DataOutputStream userOutput) 
    {
        this.userOutput = userOutput;
    }

    // LOGIN
    public boolean login() throws IOException 
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username: ");
        String username = reader.readLine();
        System.out.print("Enter password: ");
        String password = reader.readLine();

        if (username.equals("client1") && password.equals("password1")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public Client1(String ipAddress, int portNumber) throws UnknownHostException, IOException 
    {
        if (!login()) 
        {
            System.exit(0);
        }
        socket = new Socket(ipAddress, portNumber);
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("IP Address: " + localHost.getHostAddress());
        System.out.println("TCP Client 1 / Server are connected ");
        userInput = new BufferedReader(new InputStreamReader(System.in));
        userOutput = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException 
    {
        userOutput.writeUTF(message);
        userOutput.flush();
    }

    public void sendFile(String filePath) throws IOException
    {
        File file;  int bytesRead;
        file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[4096];
        Reader inputStream;
        while ((bytesRead = fileInputStream.read()) != -1) 
        {
                userOutput.write(buffer, 0, bytesRead);
        }
            userOutput.flush();
    }

    
    public static void main(String args[]) throws UnknownHostException, IOException 
    {
        Client1 client1 = new Client1("localhost", 3066);
        while (true) {
            System.out.print("Enter message (\"quit\" to exit): ");
            String message = client1.getUserInput().readLine();
            if (message.equals("quit")) {
                break;
            }
            client1.sendMessage(message);
            System.out.print("Enter file path to send (\"quit\" to exit): ");
            String filePath = client1.getUserInput().readLine();
            if (filePath.equals("quit")) {
                break;
            }
            client1.sendFile(filePath);
        }
        try {
            if (client1.getSocket() != null) {
                client1.getSocket().close();
            }
            if (client1.getUserInput() != null) {
                client1.getUserInput().close();
            }
            if (client1.getUserOutput() != null) {
                client1.getUserOutput().close();
            }
        } catch (IOException e) {
            System.out.println("closing connection error " + e.getMessage());
        }
    }
}
