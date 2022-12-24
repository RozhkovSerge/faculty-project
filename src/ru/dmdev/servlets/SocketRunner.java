package ru.dmdev.servlets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {

    public static void main(String[] args) throws IOException {
        InetAddress address = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(address, 7777);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            Scanner sc = new Scanner(System.in);
            while(sc.hasNext()) {
                String request = sc.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Response from server: " + inputStream.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
