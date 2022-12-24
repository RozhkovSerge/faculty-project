package ru.dmdev.servlets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] arr = "Hello from UDP client".getBytes();
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(arr, arr.length, address, 7777);
        datagramSocket.send(packet);
    }
}
