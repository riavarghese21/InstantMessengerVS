package Files;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

    private final int port;

    public Server(int port) 
    {
        this.port = port;
    }

    public void listen() {
        try(ServerSocket serverSocket = new ServerSocket(this.port)) 
        {
            System.out.println("Server ready to accept connections");
            System.out.println("Waiting for clients");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(socket.getInputStream())));
            String line;

            while((line = reader.readLine()) != null)
             {
                // Only iterate if the received message wasn't empty
                if(line.length() == 0) continue;

                // Listen if client sent "exit", if it did break the loop to close all readers and exit the program
                if(line.startsWith("exit")) break;

                System.out.println(line);
            }

            // Close all connections and readers when the loop is broken
            socket.close();
            serverSocket.close();
            reader.close();

        } catch(IOException e) {
            System.out.println("----------ERROR MESSAGE----------");
            System.out.println(e.getMessage());
            System.out.println("----------ERROR MESSAGE----------");
            e.printStackTrace();
        }
    }

    // Not needed but sure I'll add it :)
    public int getPort() {
        return port;
    }

    public static void main(String[] args) {
        Server server = new Server(3066);

        server.listen();
    }
}
