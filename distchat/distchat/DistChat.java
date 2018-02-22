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
        Scanner connInfo = new Scanner(System.in);
        // Looking for user input to get desired username
        System.out.print("Enter a username: \n");
        String clientName = connInfo.nextLine();
        ChatClient new_client = new ChatClient(clientName);
        
        //Infinite loop to handle accepting clients
        while(true)	
        {    
            System.out.println("Type ./start to start a chat or ./join to join an existing one.");
            String choice = connInfo.nextLine();

            if (choice.equals("./join"))
            {
                while(true)
                {
                     // Looking for user input to get desired IP
                     connInfo = new Scanner(System.in);
                     System.out.print("Enter an IP to connect to: \n");
                     String hostIP = connInfo.nextLine();

                     try{
                         (new Thread(new WritingMessage(new_client))).start();
                         (new Thread(new ReadingMessage(new_client))).start();
                         //Spawn a new thread with the client socket
                         break;
                     }
                     catch(Exception e){
                         System.out.println("Could not connect");
                     }
                }
                break;
            }
            else if(choice.equals("./start"))
            {
                (new Thread(new WritingMessage(new_client))).start();
                (new Thread(new ReadingMessage(new_client))).start();
                break;
            }
            else
            {
                System.out.println(choice + " is an invalid choice. ");
            }
        } 
    }
}
