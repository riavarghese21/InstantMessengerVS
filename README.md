# Java Socket Programming Example

Simple client-server socket programming in Java. Server is able to authenticate client’s identity after client enters user name and password.
Server is able to display client’s status.

Server will be listening on a port (say port# 3066) and Client will connect to Server.

Client accepts command line parameter to set the number of concurrent file transfers. 
For example, java Client 5 will transfer five files concurrently at any given time as long as there are enough files.  
If concurrency number is not entered, it should transfer one file at a time (aka concurrency=1), by default.

# Files
1. Client.java: Client Application to make socket connection with the Server.
2. Server.java: Server Application to accept socket connection from client. This can accept multiple connection.

# Test Cases

1. Create a dataset with 100 files each 10MB size and transfer with concurrency 1, 2,4 and 8 and measure throughput
2. Create a dataset with 10 files each 1GB size and transfer with concurrency 1, 2, 4 and 8 and measure throughput
3. Combine above two datasets in a single dataset and transfer with concurrency 1,2,4 and 8 and measure throughput

# Generating Test Data 
1. Install Java -VSCode
2. (*not sure how to run both applications, only shows that it is functional -> as in being connected only*)
 
 # Running the program
 1. Go to terminal, type `java Server`. This will run Server, which will accept connections from the client.
 2. Open another terminal, type `java Client`, enter concurrency when promted, this will run client and start transfering files to the server from the client directory.
