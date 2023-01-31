package com.dmdev.http.socket;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DatagramRunner {
    public static void main(String[] args) throws IOException {
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getByName("localhost");
        try(DatagramSocket datagramSocket = new DatagramSocket()){
            byte[] bytes = "Hello from UDP client".getBytes();
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length,inet4Address,7777);
            datagramSocket.send(packet);
        }
    }
}
