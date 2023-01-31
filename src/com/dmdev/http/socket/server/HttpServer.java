package com.dmdev.http.socket.server;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void processSocket(Socket socket){
        try(socket;
           InputStream inputStream = new DataInputStream(socket.getInputStream());
           OutputStream outputStream = new DataOutputStream(socket.getOutputStream())){
            //step 1 обработка запроса
            System.out.println("Request : " + new String(inputStream.readNBytes(400)));

            // step 2 обработка ответа
           byte [] body = Files.readAllBytes(Path.of("resources","example.html"));
           byte [] headers = """
                   HTTP/1.1 200 OK
                   content-type: text/html
                   content-length: %s
                   """.formatted(body.length).getBytes();
           outputStream.write(headers);
           outputStream.write(System.lineSeparator().getBytes());
           outputStream.write(body);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
