package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getByName("localhost");
        try(Socket socket = new Socket(inet4Address,80)){
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                String response = dataInputStream.readUTF();
                System.out.println("Response from server: " + response);
            }

        }
    }
}
