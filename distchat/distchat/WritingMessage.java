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
        for (Socket o_client: other_clients)
        {
            try(
                PrintWriter toClient = new PrintWriter(o_client.getOutputStream(), true);
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
    boolean typeMessage()
    {
    	// Looking for user input to make their message
    	Scanner message = new Scanner(System.in);
    	System.out.print("Message: ");
    	
    	String userInput = message.nextLine();
        if (userInput.equals("./quit"))
        {
            // Do the quitting procedure here
            String leave_message = String.format("%s has disconnected.\n", username);
            broadcastMessage(leave_message);
            return false;
        }
        else
        {
            broadcastMessage(userInput);
            return true;
        }
    	
    }
    
    
    @Override
    public void run(){
        System.out.println("Type ./quit to leave the chat. \n");
        while(typeMessage());
    }
    
}
