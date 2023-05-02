# Java Socket Programming Example

Simple client-server socket programming in Java. Server is able to authenticate client’s identity after client enters user name and password.
Server is able to display client’s status.

Server will be listening on a port (say port# 3066) and Client will connect to Server.


# Files 
1. Client.java: Client Application to make socket connection with the Server.
2. Server.java: Server Application to accept socket connection from client. This can accept multiple connection.

# Generating Test Data 
1. Install Java
 
 # Running the program
 1. Go to terminal, type `java Server`. This will run Server, which will accept connections from the client.
 2. Open another terminal, type `java Client`, enter concurrency when promted, this will run client and start transfering files to the server from the client directory.
