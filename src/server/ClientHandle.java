package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandle implements Runnable{
    private Socket socket;

    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client connected.");
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            int fileContentLength = dataInputStream.readInt();
            byte[] fileContent = new byte[fileContentLength];
            dataInputStream.readFully(fileContent);
            String data = new String(fileContent);
            String[] test = data.split("\n");
            String gradedTest = Checker.check(test);
            dataOutputStream.writeUTF(gradedTest);

            System.out.println("Closing connection");
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
