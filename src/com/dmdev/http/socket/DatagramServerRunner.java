package com.dmdev.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServerRunner {
    public static void main(String[] args) throws IOException {
        try(DatagramSocket datagramSocket = new DatagramSocket(7777)){
            byte[] buffer = new byte[512];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);
            System.out.println(new String(buffer));
        }
    }
}
