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
    String ip_addr;
    int port;
    //int PORT = 12345;
    ServerSocket chat_socket;
    
    // Holding all information of every connected client
    Map<String, Integer> ip_table = new HashMap<String, Integer>();
    List<Socket> other_clients = new ArrayList<Socket>();
    
    public ChatClient(String userName) throws Exception
    {
        // Something something
        this.username = userName;     
        chat_socket = new ServerSocket(0);
        String full_ip_string = chat_socket.getLocalSocketAddress().toString().replace("/", ":");
        ip_addr = full_ip_string.split(":")[0];
        port = Integer.parseInt(full_ip_string.split(":")[2]);
        System.out.println("Your IP: " + ip_addr);
        System.out.println("Your Port: " + port);
    }
    
    /*
    void requestJoin(String ip, String username) throws Exception
    {
      // Luis 
      System.out.print("User tries to join ");
      Socket join_socket = new Socket(ip, PORT);
    }*/
    
    
    void removeClosedConnections() throws Exception
    {
      // For whoever sent the close message, close their input socket 
      // Luis, basically close the socket and removing from IP list 

      // Sending message to close connection
      String message = String.format("%s has disconnected.\n", username);
      //broadcastMessage(message);
      
      for (Socket o_client : this.other_clients)
      {
          if (o_client.isClosed())
          {
              other_clients.remove(o_client);
          }
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
