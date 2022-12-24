package ru.dmdev.servlets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777);
             Socket socket = serverSocket.accept();
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            Scanner sc = new Scanner(System.in);
            String request = inputStream.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                String response = sc.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
        }
    }
}
