/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;

/**
 *
 * @author Matt Q
 */
public class ChatClient implements Runnable{
    String username; 
    String ip_addr;
    Socket clientSocket;
    
    Map<String, Integer> ip_table = new HashMap<String, Integer>();
    List<Socket> other_clients = new ArrayList<Socket>();
    
    public ChatClient(Socket newClient)
    {
        
    }
    
    void requestJoin(String ip, String usrname)
    {
        // Luis 
    }
    
    void broadcastMessage()
    {
        // for every address in the table do an output stream to other client
        for (Map.Entry<String, Integer> entry : ip_table.entrySet())
        {
            
        }
        
        
    }
    
    void sendCloseMessage()
    {
        // send a message to every other client that they are leaving chat
        //  
    }
    
    void closeConnection()
    {
        // For whoever sent the close message, close their input socket 
        // Luis, basically close the socket and removing from IP list 
    }
    
    void acceptConnection(String ip_addr, int port)
    {
        
    }
    
    @Override
    public void run()
    {
        try(
            //Set up a variable to send output
            PrintWriter toClient = 
                    new PrintWriter(clientSocket.getOutputStream(), true);
                
            //Set up a variable to read input
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
    
    
    void listen(int port)
    {
        while(true){    //Infinite loop to handle accepting clients
            try(ServerSocket serverSocket = new ServerSocket(port);){
                (new Thread(new ChatClient(serverSocket.accept()))).start();
                //Spawn a new thread with the client socket
            }
            catch(Exception e){
                System.out.println("Could not connect");
            }
        }
    }
    
}
