package client;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private FileInputStream fileInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private File fileToSend;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }

    }
    private void chooseFile()  {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showOpenDialog(null);
        jFileChooser.setDialogTitle("Choose a file to send.");
        if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fileToSend = jFileChooser.getSelectedFile();
            System.out.println("File to send: " + fileToSend.getName());
        }

    }

    private void sendFile() {
        try {
            fileInputStream = new FileInputStream(fileToSend.getAbsolutePath());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            byte[] fileBytes = new byte[(int)fileToSend.length()];
            fileInputStream.read(fileBytes);

            dataOutputStream.writeInt(fileBytes.length);
            dataOutputStream.write(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveAnswer() {
        try {
            System.out.println(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Client client = new Client("localhost", 5000);
        client.chooseFile();
        client.sendFile();
        client.receiveAnswer();
    }

}
