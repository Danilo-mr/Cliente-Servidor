package server;

import java.net.*;
import java.io.*;

public class Server {

    public Server(int port)  {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client...");
            while(true) {
                Socket socket = serverSocket.accept();
                ClientHandle clientHandle = new ClientHandle(socket);
                Thread thead = new Thread(clientHandle);
                thead.start();
            }
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    public static void main(String args[]) {
        new Server(5000);
    }
}