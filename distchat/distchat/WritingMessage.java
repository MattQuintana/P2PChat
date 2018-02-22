/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distchat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author Matt Q
 */
public class WritingMessage implements Runnable{
    
    ChatClient client;
    List<Socket> other_clients; 
    String username;
    public WritingMessage(ChatClient client)
    {
        this.client = client;
        this.other_clients = client.other_clients;
        this.username = client.username;
    }
    
    void broadcastMessage(String message)
    {
        // for every address in the table do an output stream to other client
        for (Socket client: other_clients)
        {
            try(
                PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);
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
    void typeMessage()
    {
    	// Looking for user input to make their message
    	Scanner message = new Scanner(System.in);
    	System.out.print("Message: ");
    	
    	String userInput = message.nextLine();
    	broadcastMessage(userInput);
    }
    
    
    @Override
    public void run(){
        
        while(true)
        {
            typeMessage();
        }
    
    }
    
}
