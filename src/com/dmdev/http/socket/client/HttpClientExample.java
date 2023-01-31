package com.dmdev.http.socket.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build();
       // HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.google.com")).POST(HttpRequest.BodyPublishers.ofFile()).build();

        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.headers());
        System.out.println(send.body());
    }
}
