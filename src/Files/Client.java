package Files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final String ipAddress;
    private final int port;

    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void connect() {
        try(Socket socket = new Socket(this.ipAddress, this.port)) {
            System.out.println("Connected!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            String line;

            // Create an infinite while loop, while also reading the System.in stream
            while((line = reader.readLine()) != null) {
                // Checking if the line typed in is empty. If so skip to the next loop itteration
                if(line.toLowerCase().length() == 0) continue;

                // Instead of .equals use .startsWith to ignore everything after the exit
                if(line.startsWith("exit")) break;

                if(socket.isClosed()) break;

                // Write the typed in message to the sockets OutputStream
                writer.write(line + "\n");
                writer.flush();
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("----------ERROR MESSAGE----------");
            System.out.println(e.getMessage());
            System.out.println("----------ERROR MESSAGE----------");
            e.printStackTrace();
        }
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
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 3066);
        client.connect();
    }
}

    
    
