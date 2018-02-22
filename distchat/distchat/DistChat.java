/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distchat;

import java.util.Scanner;
import java.net.*;

/**
 *
 * @author Matt Q
 */ 

// Hello world
public class DistChat 
{
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        while(true)	//Infinite loop to handle accepting clients
        {    
            // Looking for user input to get desired IP
            Scanner connInfo = new Scanner(System.in);
            System.out.print("Enter an IP to connect to: \n");

            String hostIP = connInfo.nextLine();

            // Looking for user input to get desired username
            connInfo = new Scanner(System.in);
            System.out.print("Enter a username: \n");
            String clientName = connInfo.nextLine();
            
            //ChatClient client = new ChatClient(clientName);
            //client.username = clientName;

            //client.requestJoin(hostIP, clientName);
            
            try{
                ChatClient new_client = new ChatClient(clientName);
                new_client.addClientSocket(hostIP, new_client.PORT);
                (new Thread(new WritingMessage(new_client))).start();
                (new Thread(new ReadingMessage(new_client))).start();
                //Spawn a new thread with the client socket
            }
            catch(Exception e){
                System.out.println("Could not connect");
            }
        } 
    }
}
