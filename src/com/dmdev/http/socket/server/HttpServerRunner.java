package com.dmdev.http.socket.server;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9000);
        httpServer.run();
    }
}
