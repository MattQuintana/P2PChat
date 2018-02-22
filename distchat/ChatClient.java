// Making some test changes and some more stuff. 
package distchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;

/**
 *
 * @author Matt Q, Luis M, Erik S
 */
public class ChatClient implements Runnable{
	// Information needed to be saved for the client
    String username; 
    String ip_addr;
    Socket clientSocket;
    
    // Holding all information of every connected client
    Map<String, Integer> ip_table = new HashMap<String, Integer>();
    List<Socket> other_clients = new ArrayList<Socket>();
    
    public ChatClient(String userName)
    {
        // Something something
        this.username = userName;
    }
    
    void requestJoin(String ip, String usrname)
    {
      // Luis 
      System.out.print("User tries to join ")
    }
    
    void typeMessage()
    {
    	// Looking for user input to make their message
    	Scanner message = new Scanner(System.in);
    	System.out.print("Message: ");
    	
    	String userInput = message.nextLine();
    	broadcastMessage(userInput);
    }
    
    void broadcastMessage(String message)
    {
        // for every address in the table do an output stream to other client
        for (Socket client: other_clients)
        {
            try(
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            )
            {
            	String finalMessage = String.format("%s: %s", username, message);
                toClient.println(finalMessage);
            }
            catch(Exception e){
                System.out.println("** UNABLE TO SEND MESSAGE **");
            }       
        }  
    }
    
    void closeConnection()
    {
        // For whoever sent the close message, close their input socket 
        // Luis, basically close the socket and removing from IP list 

      // Sending message to close connection
      String message = String.format("%s has disconnected.\n", username);
      broadcastMessage(message);

      clientSocket.close() ;// close socket
      Iterator<Socket> iteratorSpot = other_clients.iterator();
      while (iteratorSpot.hasNext()) {
        Socket current = iteratorSpot.next();
        if(username == current) {
          iteratorSpot.remove();
        }
      }
    }
<<<<<<< HEAD
    
    void addClientSocket(String ip_addr, int port)
=======

    void acceptConnection(String ip_addr, int port)
>>>>>>> 948fbb2a65a59f46ca5e599e81dfcf93c127957a
    {
        // If the IP isn't already in the table
        if (!ip_table.containsKey(ip_addr))
        {
            // Add it in along with the port
            ip_table.put(ip_addr, port);
        }
        
        // Create a new socket for the new client to connect to
        try(
            Socket new_connection = new Socket(ip_addr, port);
        )
        {
            // Add it to our list of socket connections
            other_clients.add(new_connection);
        }
        catch (Exception e)
        {
            System.out.println("Could not create socket to: %s".format(ip_addr));
        }
    }
    
    void buildIPTable(Map<String, Integer> table)
    {
        // 
        ip_table = table;
        
        for (Map.Entry<String, Integer> entry : ip_table.entrySet())
        {
            addClientSocket(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public void run()
    {
        try(
            //Set up a writer for output
            PrintWriter toClient = 
                    new PrintWriter(clientSocket.getOutputStream(), true);
                
            //Set up a reader for input
            BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        )
        {
            //Getting input from client
            toClient.println(String.format("Successfully connected to"
                    + " server on port %d. \n", clientSocket.getPort()));
            
            
            
            //Finish and clean up; closing sockets
            System.err.println("Closing connection with client");
            toClient.close();
            fromClient.close();
            clientSocket.close();
        
        }
        catch(Exception e)
        {
            System.out.println("Could not connect");
        }
    }
    
}
