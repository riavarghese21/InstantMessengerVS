import java.net.*;
import java.io.*;

public class TCPServer {
    private ServerSocket server;
    private Socket socket1;
    private Socket socket2;
    private BufferedReader reader1;
    private BufferedReader reader2;
    private PrintWriter writer1;
    private PrintWriter writer2;

    public TCPServer(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
        System.out.println("Server is running on port " + portNumber);

        System.out.println("Waiting for client 1...");
        socket1 = server.accept();
        System.out.println("Client 1 connected from " + socket1.getInetAddress());

        System.out.println("Waiting for client 2...");
        socket2 = server.accept();
        System.out.println("Client 2 connected from " + socket2.getInetAddress());

        reader1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        writer1 = new PrintWriter(socket1.getOutputStream(), true);

        reader2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        writer2 = new PrintWriter(socket2.getOutputStream(), true);

        new Thread(new MessageTransfer(reader1, writer2, reader2, writer1)).start();
        new Thread(new MessageTransfer(reader2, writer1, reader1, writer2)).start();
    }

    private class MessageTransfer implements Runnable {
        private BufferedReader readerFrom;
        private PrintWriter writerTo;
        private BufferedReader readerOther;
        private PrintWriter writerOther;
    

        public MessageTransfer(BufferedReader readerFrom, PrintWriter writerTo, BufferedReader readerOther, PrintWriter writerOther) {
            this.readerFrom = readerFrom;
            this.writerTo = writerTo;
            this.readerOther = readerOther;
            this.writerOther = writerOther;
        }	
        
        public void run() {
            try {
                String message;
                while ((message = readerFrom.readLine()) != null) {
                    System.out.println("Message from client: " + message);
                    writerOther.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

    public static void main(String[] args) throws IOException {
        try{
            new TCPServer(3066);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
