import java.net.*;
import java.io.*;

public class Servidor {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    public Servidor(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a cliente...");

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            String line = "";

            while(!line.equals("Encerrar")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            out.writeUTF("Receba essa mensagem cliente!");
            System.out.println("Closing connection");
            socket.close();
            in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Servidor servidor = new Servidor(5000);
    }
}