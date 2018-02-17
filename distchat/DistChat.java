/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distchat;

import java.net.ServerSocket;
import java.util.Scanner;

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
    public static void main(String[] args) 
    {
        while(true){    //Infinite loop to handle accepting clients
            
            Scanner connInfo = new Scanner(System.in);
            System.out.print("Enter an IP to connect to: ");

            String hostIP = connInfo.nextLine();

            connInfo = new Scanner(System.in);
            System.out.print("Enter a username: ");

            String clientName = connInfo.nextLine();
            ChatClient client = new ChatClient(clientName);
            client.username = clientName;
            //ChatClient client = new ChatClient(clientName);

            //client.RequestJoin(hostIP, clientName);
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
