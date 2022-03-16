import java.net.*;
import java.io.*;

public class Cliente {
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private BufferedReader reader = null;

    public Cliente (String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            reader = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }

        String line = "";
        while(!line.equals("Encerrar")) {
            try {
                line = reader.readLine();

                out.writeUTF(line);
            } catch(IOException i) {
                System.out.println(i);
            }
        }
        try {
            System.out.println(in.readUTF());
            reader.close();
            out.close();
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }


    public static void main(String args[]) {
        Cliente cliente = new Cliente("localhost", 5000);
    }

}