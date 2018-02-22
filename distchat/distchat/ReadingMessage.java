/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distchat;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Matt Q
 */
public class ReadingMessage implements Runnable {
    ChatClient client;
    ServerSocket server_socket;
    
    public ReadingMessage(ChatClient client)
    {
        this.client = client;
        server_socket = client.chat_socket;
    }
    @Override
    public void run()
    {
        //System.out.println("In reading thread.");
        while(true)
        {
            try
            {
                Socket input_client = server_socket.accept();   
                System.out.println("In try clause");
            }
            catch (Exception e)
            {
                
            }
        }
    }
}
