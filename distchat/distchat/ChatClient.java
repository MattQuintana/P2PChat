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
public class ChatClient{
    // Information needed to be saved for the client
    String username; 
    //String ip_addr;
    //Socket clientSocket;
    int PORT = 12345;
    
    // Holding all information of every connected client
    Map<String, Integer> ip_table = new HashMap<String, Integer>();
    List<Socket> other_clients = new ArrayList<Socket>();
    
    public ChatClient(String userName)
    {
        // Something something
        this.username = userName;
        
    }
    
    void requestJoin(String ip, String username) throws Exception
    {
      // Luis 
      System.out.print("User tries to join ");
      Socket join_socket = new Socket(ip, PORT);
    }
    
    
    void closeConnection() throws Exception
    {
      // For whoever sent the close message, close their input socket 
      // Luis, basically close the socket and removing from IP list 

      // Sending message to close connection
      String message = String.format("%s has disconnected.\n", username);
      //broadcastMessage(message);

      //clientSocket.close();// close socket
      Iterator<Socket> iteratorSpot = other_clients.iterator();
      while (iteratorSpot.hasNext()) {
        Socket current = iteratorSpot.next();
        current.getInetAddress();
        //if(username == current){
          //iteratorSpot.remove();
        //}
      }
    }
    
    void addClientSocket(String ip_addr, int port)
    {
        ip_table.put(ip_addr, port);
    }

    void acceptConnection(String ip_addr, int port)
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
}
